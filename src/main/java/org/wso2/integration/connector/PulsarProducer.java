package org.wso2.integration.connector;

import org.apache.pulsar.client.api.Producer;
import org.apache.synapse.MessageContext;
import org.wso2.integration.connector.core.AbstractConnector;
import org.wso2.integration.connector.core.connection.ConnectionHandler;
import org.wso2.integration.connector.core.ConnectException;

public class PulsarProducer extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {
        ConnectionHandler handler = ConnectionHandler.getConnectionHandler();

//        Producer<String> producer = pulsarClient.newProducer(Schema.STRING)
//                .topic("my-topic")
//                .create();
//
//        producer.newMessage()
//                .key("my-key") // Set the message key
//                .eventTime(System.currentTimeMillis()) // Set the event time
//                .sequenceId(1203) // Set the sequenceId for the deduplication purposes
//                .deliverAfter(1, TimeUnit.HOURS) // Delay message delivery for 1 hour
//                .property("my-key", "my-value") // Set the customized metadata
//                .property("my-other-key", "my-other-value")
//                .replicationClusters(
//                        Lists.newArrayList("r1", "r2")) // Set the geo-replication clusters for this message.
//                .value("content")
//                .send();
//
//        producer.newMessage().

    }
}
