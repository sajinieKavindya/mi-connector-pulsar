package org.wso2.integration.connector.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.wso2.integration.connector.exception.PulsarConnectorException;

public class PulsarUtils {

    private static final Log log = LogFactory.getLog(PulsarUtils.class);

    private PulsarUtils() {
        // Utility class, prevent instantiation
    }

    public static void setErrorResponse(MessageContext messageContext, Throwable e, int errorCode) {

        messageContext.setProperty(SynapseConstants.ERROR_CODE, errorCode);
        messageContext.setProperty(SynapseConstants.ERROR_MESSAGE, e.getMessage());
        messageContext.setProperty(SynapseConstants.ERROR_DETAIL, e.getMessage());
        messageContext.setProperty(SynapseConstants.ERROR_EXCEPTION, e);
    }

    public static void handleError(MessageContext messageContext, Throwable e, int errorCode, String message)
            throws PulsarConnectorException {

        setErrorResponse(messageContext, e, errorCode);
        handleException(message, e);
    }


    public static void handleException(String message, Throwable throwable) throws PulsarConnectorException {

        log.error(message, throwable);
        throw new PulsarConnectorException(message, throwable);
    }
}
