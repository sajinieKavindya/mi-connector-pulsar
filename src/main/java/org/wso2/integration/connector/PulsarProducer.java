package org.wso2.integration.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.apache.axiom.om.OMOutputFormat;
import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.MessageFormatter;
import org.apache.axis2.transport.base.BaseUtils;
import org.apache.axis2.util.MessageProcessorSelector;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.ProducerBuilder;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.TypedMessageBuilder;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.wso2.integration.connector.connection.PulsarConnection;
import org.wso2.integration.connector.core.AbstractConnector;
import org.wso2.integration.connector.core.AbstractConnectorOperation;
import org.wso2.integration.connector.core.connection.ConnectionHandler;
import org.wso2.integration.connector.core.ConnectException;
import org.wso2.integration.connector.core.util.ConnectorUtils;
import org.wso2.integration.connector.exception.PulsarConnectorException;
import org.wso2.integration.connector.utils.PulsarConstants;
import org.wso2.integration.connector.utils.PulsarUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class PulsarProducer extends AbstractConnectorOperation {

    Map<ProducerKey, Producer<byte[]>> producerCache = new ConcurrentHashMap<>();


    @Override
    public void execute(MessageContext messageContext, String responseVariable, Boolean overwriteBody)
            throws ConnectException {
        ConnectionHandler handler = ConnectionHandler.getConnectionHandler();
        PulsarConnection pulsarConnection = (PulsarConnection) handler.getConnection(PulsarConstants.CONNECTOR_NAME, getConnectionName(messageContext));
        PulsarClient pulsarClient = pulsarConnection.getClient();

        String topicName = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.PRODUCER_TOPIC_NAME);
        String sendTimeoutMs = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.SEND_TIMEOUT_MS);
        String blockIfQueueFull = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.BLOCK_IF_QUEUE_FULL);
        String maxPendingMessages = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.MAX_PENDING_MESSAGES);
        String maxPendingMessagesAcrossPartitions = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.MAX_PENDING_MESSAGES_ACROSS_PARTITIONS);
        String batchingEnabled = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.BATCHING_ENABLED);
        String batchingMaxMessages = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.BATCHING_MAX_MESSAGES);
        String batchingMaxPublishDelayMicros = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.BATCHING_MAX_PUBLISH_DELAY_MICROS);
        String compressionType = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.COMPRESSION_TYPE);
        String hashingScheme = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.HASHING_SCHEME);
        String messageRoutingMode = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.MESSAGE_ROUTING_MODE);
        String chunkingEnabled = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.CHUNKING_ENABLED);
        String cryptoFailureAction = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.CRYPTO_FAILURE_ACTION);

        Map<String, String > producerConfig = new ConcurrentHashMap<>();
        producerConfig.put(PulsarConstants.SEND_TIMEOUT_MS, sendTimeoutMs);
        producerConfig.put(PulsarConstants.BLOCK_IF_QUEUE_FULL, blockIfQueueFull);
        producerConfig.put(PulsarConstants.MAX_PENDING_MESSAGES, maxPendingMessages);
        producerConfig.put(PulsarConstants.MAX_PENDING_MESSAGES_ACROSS_PARTITIONS, maxPendingMessagesAcrossPartitions);
        producerConfig.put(PulsarConstants.BATCHING_ENABLED, batchingEnabled);
        producerConfig.put(PulsarConstants.BATCHING_MAX_MESSAGES, batchingMaxMessages);
        producerConfig.put(PulsarConstants.BATCHING_MAX_PUBLISH_DELAY_MICROS, batchingMaxPublishDelayMicros);
        producerConfig.put(PulsarConstants.COMPRESSION_TYPE, compressionType);
        producerConfig.put(PulsarConstants.HASHING_SCHEME, hashingScheme);
        producerConfig.put(PulsarConstants.MESSAGE_ROUTING_MODE, messageRoutingMode);
        producerConfig.put(PulsarConstants.CHUNKING_ENABLED, chunkingEnabled);
        producerConfig.put(PulsarConstants.CRYPTO_FAILURE_ACTION, cryptoFailureAction);

        Producer<byte[]> producer = getProducer(topicName, producerConfig, pulsarClient);

        TypedMessageBuilder<byte[]> messageBuilder = producer.newMessage();
        getMessagePropertiesFromMessageContextAndConstructMessageBuilder(messageBuilder, messageContext);

        // Send the message
        String sendMode = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.SEND_MODE);
        if (sendMode != null && sendMode.equalsIgnoreCase(PulsarConstants.ASYNC)) {
            messageBuilder.sendAsync()
                    .thenAccept(messageId -> {
                        log.info("Message sent successfully with ID: " + messageId);
                    })
                    .exceptionally(e -> {
                        log.error("Failed to send message", e);
                        return null;
                    });
        } else {
            JsonObject resultJSON;
            try {
                MessageId messageId = messageBuilder.send();
                resultJSON = PulsarUtils.buildSuccessResponse(messageId);
            } catch (PulsarClientException e) {
                resultJSON = PulsarUtils.buildErrorResponse(messageContext, e);
            }
            handleConnectorResponse(messageContext, responseVariable, overwriteBody, resultJSON, null, null);
        }
    }

    private void getMessagePropertiesFromMessageContextAndConstructMessageBuilder(TypedMessageBuilder<byte[]> messageBuilder, MessageContext messageContext) throws PulsarConnectorException {
        String key = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.KEY);
        if (key != null) {
            messageBuilder.key(key);
        }

        String eventTime = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.EVENT_TIME);
        if (eventTime != null) {
            messageBuilder.eventTime(Long.parseLong(eventTime));
        } else {
            messageBuilder.eventTime(System.currentTimeMillis());
        }

        String sequenceId = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.SEQUENCE_ID);
        if (sequenceId != null) {
            messageBuilder.sequenceId(Long.parseLong(sequenceId));
        }

        String deliverAfter = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.DELIVER_AFTER);
        if (deliverAfter != null) {
            messageBuilder.deliverAfter(Long.parseLong(deliverAfter), TimeUnit.MILLISECONDS);
        }

        String properties = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.PROPERTIES);
        if (properties != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode jsonArray = mapper.readTree(properties);
                if (jsonArray != null && jsonArray.isArray()) {
                    for (JsonNode node : jsonArray) {
                        if (node.isObject()) {
                            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
                            while (fields.hasNext()) {
                                Map.Entry<String, JsonNode> entry = fields.next();
                                String propertyKey = entry.getKey();
                                JsonNode propertyValue = entry.getValue();
                                if (propertyKey != null && propertyValue.isTextual()) {
                                    messageBuilder.property(key, propertyValue.asText());
                                } else {
                                    log.warn("Skipping non-textual value or null key in custom header entry: " + entry);
                                }
                            }
                        } else {
                            log.warn("Skipping non-object item in customHeaders array: " + node);
                        }
                    }
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        String value = (String) ConnectorUtils.lookupTemplateParamater(messageContext, PulsarConstants.VALUE);
        if (value != null) {
            messageBuilder.value(value.getBytes());
        } else {
            try {
                value = getMessage(messageContext);
                messageBuilder.value(value.getBytes());
            } catch (AxisFault e) {
                throw new PulsarConnectorException("Cannot obtain the message from the message context", e);
            }
        }

    }

    /**
     * Get the messages from the message context and format the messages.
     */
    private String getMessage(MessageContext messageContext) throws AxisFault {

        Axis2MessageContext axisMsgContext = (Axis2MessageContext) messageContext;
        org.apache.axis2.context.MessageContext msgContext = axisMsgContext.getAxis2MessageContext();
        return formatMessage(msgContext);
    }

    /**
     * Format the messages when the messages are sent to the kafka broker
     *
     * @param messageContext Message Context
     * @return formatted message
     * @throws AxisFault if failed to format
     */
    private static String formatMessage(org.apache.axis2.context.MessageContext messageContext)
            throws AxisFault {

        OMOutputFormat format = BaseUtils.getOMOutputFormat(messageContext);
        MessageFormatter messageFormatter = MessageProcessorSelector.getMessageFormatter(messageContext);
        StringWriter stringWriter = new StringWriter();
        OutputStream out = new WriterOutputStream(stringWriter, format.getCharSetEncoding());
        try {
            messageFormatter.writeTo(messageContext, format, out, true);
        } catch (IOException e) {
            throw new AxisFault("The Error occurs while formatting the message", e);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                throw new AxisFault("The Error occurs while closing the output stream", e);
            }
        }
        return stringWriter.toString();
    }

    Producer<byte[]> getProducer(String topic, Map<String, String> config, PulsarClient client) {
        ProducerKey key = new ProducerKey(topic, config);

        return producerCache.computeIfAbsent(key, k -> {
            try {
                ProducerBuilder<byte[]> builder = client.newProducer()
                        .topic(topic);

                applyConfig(builder, config); // Apply batching, compression, etc.

                return builder.create();
            } catch (PulsarClientException e) {
                throw new RuntimeException("Failed to create producer", e);
            }
        });
    }

    void applyConfig(ProducerBuilder<byte[]> builder, Map<String, String> config) {
        if (config.get(PulsarConstants.SEND_TIMEOUT_MS) != null) {
            builder.sendTimeout(Integer.parseInt(config.get(PulsarConstants.SEND_TIMEOUT_MS)), TimeUnit.MILLISECONDS);
        }
        if (config.get(PulsarConstants.BLOCK_IF_QUEUE_FULL) != null) {
            builder.blockIfQueueFull(Boolean.parseBoolean(config.get(PulsarConstants.BLOCK_IF_QUEUE_FULL)));
        }
        if (config.get(PulsarConstants.MAX_PENDING_MESSAGES) != null) {
            builder.maxPendingMessages(Integer.parseInt(config.get(PulsarConstants.MAX_PENDING_MESSAGES)));
        }
        if (config.get(PulsarConstants.MAX_PENDING_MESSAGES_ACROSS_PARTITIONS) != null) {
            builder.maxPendingMessagesAcrossPartitions(Integer.parseInt(config.get(PulsarConstants.MAX_PENDING_MESSAGES_ACROSS_PARTITIONS)));
        }
        if (config.get(PulsarConstants.BATCHING_ENABLED) != null) {
            builder.enableBatching(Boolean.parseBoolean(config.get(PulsarConstants.BATCHING_ENABLED)));
        }
        if (config.get(PulsarConstants.BATCHING_MAX_MESSAGES) != null) {
            builder.batchingMaxMessages(Integer.parseInt(config.get(PulsarConstants.BATCHING_MAX_MESSAGES)));
        }
        if (config.get(PulsarConstants.BATCHING_MAX_PUBLISH_DELAY_MICROS) != null) {
            builder.batchingMaxPublishDelay(Long.parseLong(config.get(PulsarConstants.BATCHING_MAX_PUBLISH_DELAY_MICROS)), TimeUnit.MICROSECONDS);
        }
        if (config.get(PulsarConstants.COMPRESSION_TYPE) != null) {
            builder.compressionType(org.apache.pulsar.client.api.CompressionType.valueOf(config.get(PulsarConstants.COMPRESSION_TYPE)));
        }
        if (config.get(PulsarConstants.HASHING_SCHEME) != null) {
            builder.hashingScheme(org.apache.pulsar.client.api.HashingScheme.valueOf(config.get(PulsarConstants.HASHING_SCHEME)));
        }
        if (config.get(PulsarConstants.MESSAGE_ROUTING_MODE) != null) {
            builder.messageRoutingMode(org.apache.pulsar.client.api.MessageRoutingMode.valueOf(config.get(PulsarConstants.MESSAGE_ROUTING_MODE)));
        }
        if (config.get(PulsarConstants.CHUNKING_ENABLED) != null) {
            builder.enableChunking(Boolean.parseBoolean(config.get(PulsarConstants.CHUNKING_ENABLED)));
        }
        if (config.get(PulsarConstants.CRYPTO_FAILURE_ACTION) != null) {
            builder.cryptoFailureAction(org.apache.pulsar.client.api.ProducerCryptoFailureAction.valueOf(config.get(PulsarConstants.CRYPTO_FAILURE_ACTION)));
        }
    }


    private String getConnectionName(MessageContext messageContext) throws PulsarConnectorException {

        String connectionName = (String) messageContext.getProperty(PulsarConstants.CONNECTION_NAME);
        if (connectionName == null) {
            throw new PulsarConnectorException("Connection name is not set.");
        }
        return connectionName;
    }

}
