package org.wso2.integration.connector.utils;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pulsar.client.api.MessageId;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.wso2.integration.connector.exception.PulsarConnectorException;

public class PulsarUtils {

    private static final Log log = LogFactory.getLog(PulsarUtils.class);

    private PulsarUtils() {
        // Utility class, prevent instantiation
    }

    public static void setErrorPropertiesToMessageContext(MessageContext messageContext, Throwable e, int errorCode) {

        messageContext.setProperty(SynapseConstants.ERROR_CODE, errorCode);
        messageContext.setProperty(SynapseConstants.ERROR_MESSAGE, e.getMessage());
        messageContext.setProperty(SynapseConstants.ERROR_DETAIL, e.getMessage());
        messageContext.setProperty(SynapseConstants.ERROR_EXCEPTION, e);
    }

    public static void handleError(MessageContext messageContext, Throwable e, int errorCode, String message)
            throws PulsarConnectorException {

        setErrorPropertiesToMessageContext(messageContext, e, errorCode);
        handleException(message, e);
    }


    public static void handleException(String message, Throwable throwable) throws PulsarConnectorException {

        log.error(message, throwable);
        throw new PulsarConnectorException(message, throwable);
    }

    public static JsonObject buildSuccessResponse(MessageId messageId) {

        // Create a new JSON payload
        JsonObject resultJson = new JsonObject();

        // Add the basic success information
        resultJson.addProperty("success", "true");
        resultJson.addProperty("messageId", messageId.toString());

        return resultJson;
    }

    public static JsonObject buildErrorResponse(MessageContext messageContext, Throwable e) {

        // Create a new JSON payload
        JsonObject resultJson = new JsonObject();

        // Add the basic success information
        resultJson.addProperty("success", "false");

        JsonObject errorJson = new JsonObject();

        setErrorPropertiesToMessageContext(messageContext, e, 500);

        errorJson.addProperty("detail", e.getMessage());
        errorJson.addProperty("exceptionType", e.getClass().getSimpleName());

        resultJson.add("error", errorJson);

        return resultJson;
    }

}
