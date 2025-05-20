package org.wso2.integration.connector.exception;

import org.wso2.integration.connector.core.ConnectException;

public class PulsarConnectorException extends ConnectException {

    public PulsarConnectorException(Throwable e) {

        super(e);
    }

    public PulsarConnectorException(String msg) {

        super(msg);
    }

    public PulsarConnectorException(String msg, Throwable e) {

        super(e, msg);
    }
}
