package org.wso2.integration.connector.pojo;

import org.apache.pulsar.client.api.Authentication;

public abstract class PulsarAuthConfig {

    private String authentication;
    private String authParams;
    protected String authPluginClassName;

    public abstract String getAuthMethod();

    public abstract Authentication getAuthentication();

}
