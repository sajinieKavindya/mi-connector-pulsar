package org.wso2.integration.connector.connection;

import org.apache.pulsar.client.api.ClientBuilder;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.synapse.MessageContext;
import org.wso2.integration.connector.core.ConnectException;
import org.wso2.integration.connector.core.connection.Connection;
import org.wso2.integration.connector.core.connection.ConnectionConfig;
import org.wso2.integration.connector.exception.PulsarConnectorException;
import org.wso2.integration.connector.pojo.ConnectionConfiguration;
import org.wso2.integration.connector.utils.PulsarUtils;

public class PulsarConnection implements Connection {

    private static final String ERROR_MESSAGE = "Error occurred while connecting to the Pulsar server.";

    private PulsarClient client;

    public PulsarConnection(MessageContext messageContext, ConnectionConfiguration configuration) throws Exception {

        try {
            ClientBuilder clientBuilder = PulsarClient.builder();
            PulsarConnectionSetup connectionSetup = new PulsarConnectionSetup();
            connectionSetup.constructClientBuilder(configuration, clientBuilder);
            this.client = clientBuilder.build();

//            Producer<String> producer = client.newProducer(Schema.STRING)
//                    .topic("my-topic")
//                    .hashingScheme()
//                    .cryptoFailureAction()
//                    .messageRoutingMode()
//                    .create();

        } catch (IllegalArgumentException e) {
            PulsarUtils.handleError(messageContext, e, 700000, ERROR_MESSAGE);
        } catch (PulsarClientException e) {
            PulsarUtils.handleError(messageContext, e, 908989, ERROR_MESSAGE);
        } catch (Exception e) {
            PulsarUtils.handleError(messageContext, e, 900000, ERROR_MESSAGE);
        }
    }

    public PulsarClient getClient() {

        return client;
    }

    @Override
    public void connect(ConnectionConfig connectionConfig) throws ConnectException {
        //Nothing to do here
    }

    @Override
    public void close() throws ConnectException {
        if (client != null) {
            try {
                client.close();
            } catch (PulsarClientException e) {
                throw new PulsarConnectorException("Error closing Pulsar client", e);
            }
        }
    }
}
