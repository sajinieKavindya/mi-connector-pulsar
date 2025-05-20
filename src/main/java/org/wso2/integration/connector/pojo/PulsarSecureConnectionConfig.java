package org.wso2.integration.connector.pojo;

import org.apache.commons.lang3.StringUtils;
import org.wso2.integration.connector.utils.PulsarConstants;

import java.util.Map;

public class PulsarSecureConnectionConfig extends PulsarConnectionConfig {

    private Boolean useTls;
    private Boolean allowTlsInsecureConnection;
    private Boolean enableTlsHostnameVerification;
    private String tlsTrustCertsFilePath;
    private String tlsProtocols;
    private String tlsCiphers;
    private Boolean useKeyStoreTls;
    private String tlsTrustStorePath;
    private String tlsTrustStorePassword;
    private String tlsTrustStoreType;
    private Integer autoCertRefreshSeconds;

    public Map<String, Object> constructConnectionConfigMap() {
        Map<String, Object> connectionConfig = super.constructConnectionConfigMap();
        loadConfig(PulsarConstants.USE_TLS, useTls, connectionConfig);
        loadConfig(PulsarConstants.TLS_ALLOW_INSECURE_CONNECTION, allowTlsInsecureConnection, connectionConfig);
        loadConfig(PulsarConstants.TLS_HOSTNAME_VERIFICATION_ENABLE, enableTlsHostnameVerification, connectionConfig);
        loadConfig(PulsarConstants.TLS_TRUST_CERTS_FILE_PATH, tlsTrustCertsFilePath, connectionConfig);
        loadConfig(PulsarConstants.TLS_PROTOCOLS, tlsProtocols, connectionConfig);
        loadConfig(PulsarConstants.TLS_CIPHERS, tlsCiphers, connectionConfig);
        loadConfig(PulsarConstants.USE_KEY_STORE_TLS, useKeyStoreTls, connectionConfig);
        loadConfig(PulsarConstants.TLS_TRUST_STORE_PATH, tlsTrustStorePath, connectionConfig);
        loadConfig(PulsarConstants.TLS_TRUST_STORE_PASSWORD, tlsTrustStorePassword, connectionConfig);
        loadConfig(PulsarConstants.TLS_TRUST_STORE_TYPE, tlsTrustStoreType, connectionConfig);
        loadConfig(PulsarConstants.AUTO_CERT_REFRESH_SECONDS, autoCertRefreshSeconds, connectionConfig);
        return connectionConfig;
    }

    public Boolean useTls() {
        return useTls;
    }

    public void setUseTls(String useTls) {
        if (StringUtils.isNotEmpty(useTls)) {
            this.useTls = Boolean.parseBoolean(useTls);
        }
    }

    public Boolean getAllowTlsInsecureConnection() {
        return allowTlsInsecureConnection;
    }

    public void setAllowTlsInsecureConnection(String allowTlsInsecureConnection) {
        if (StringUtils.isNotEmpty(allowTlsInsecureConnection)) {
            this.allowTlsInsecureConnection = Boolean.parseBoolean(allowTlsInsecureConnection);
        }
    }

    public Boolean getEnableTlsHostnameVerification() {
        return enableTlsHostnameVerification;
    }

    public void setEnableTlsHostnameVerification(String enableTlsHostnameVerification) {
        if (StringUtils.isNotEmpty(enableTlsHostnameVerification)) {
            this.enableTlsHostnameVerification = Boolean.parseBoolean(enableTlsHostnameVerification);
        }
    }

    public String getTlsTrustCertsFilePath() {
        return tlsTrustCertsFilePath;
    }

    public void setTlsTrustCertsFilePath(String tlsTrustCertsFilePath) {
        if (StringUtils.isNotEmpty(tlsTrustCertsFilePath)) {
            this.tlsTrustCertsFilePath = tlsTrustCertsFilePath;
        }
    }

    public String getTlsProtocols() {
        return tlsProtocols;
    }

    public void setTlsProtocols(String tlsProtocols) {
        if (StringUtils.isNotEmpty(tlsProtocols)) {
            this.tlsProtocols = tlsProtocols;
        }
    }

    public String getTlsCiphers() {
        return tlsCiphers;
    }

    public void setTlsCiphers(String tlsCiphers) {
        if (StringUtils.isNotEmpty(tlsCiphers)) {
            this.tlsCiphers = tlsCiphers;
        }
    }

    public Boolean getUseKeyStoreTls() {
        return useKeyStoreTls;
    }

    public void setUseKeyStoreTls(String useKeyStoreTls) {
        if (StringUtils.isNotEmpty(useKeyStoreTls)) {
            this.useKeyStoreTls = Boolean.parseBoolean(useKeyStoreTls);
        }
    }

    public String getTlsTrustStorePath() {
        return tlsTrustStorePath;
    }

    public void setTlsTrustStorePath(String tlsTrustStorePath) {
        if (StringUtils.isNotEmpty(tlsTrustStorePath)) {
            this.tlsTrustStorePath = tlsTrustStorePath;
        }
    }

    public String getTlsTrustStorePassword() {
        return tlsTrustStorePassword;
    }

    public void setTlsTrustStorePassword(String tlsTrustStorePassword) {
        if (StringUtils.isNotEmpty(tlsTrustStorePassword)) {
            this.tlsTrustStorePassword = tlsTrustStorePassword;
        }
    }

    public String getTlsTrustStoreType() {
        return tlsTrustStoreType;
    }

    public void setTlsTrustStoreType(String tlsTrustStoreType) {
        if (StringUtils.isNotEmpty(tlsTrustStoreType)) {
            this.tlsTrustStoreType = tlsTrustStoreType;
        }
    }

    public Integer getAutoCertRefreshSeconds() {
        return autoCertRefreshSeconds;
    }

    public void setAutoCertRefreshSeconds(String autoCertRefreshSeconds) {
        if (StringUtils.isNotEmpty(autoCertRefreshSeconds)) {
            this.autoCertRefreshSeconds = Integer.parseInt(autoCertRefreshSeconds);
        }
    }

}
