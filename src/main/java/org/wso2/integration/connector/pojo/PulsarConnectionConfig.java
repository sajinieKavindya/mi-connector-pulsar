package org.wso2.integration.connector.pojo;

import org.apache.commons.lang3.StringUtils;
import org.wso2.integration.connector.utils.PulsarConstants;

import java.util.HashMap;
import java.util.Map;

public class PulsarConnectionConfig {

    private String serviceUrl;
    private Integer operationTimeoutSeconds;
    private Integer statsIntervalSeconds;
    private Integer numIoThreads;
    private Integer numListenerThreads;
    private Boolean useTcpNoDelay;
    private Integer requestTimeoutMs;
    private Integer maxLookupRequests;
    private Integer maxConcurrentLookupRequests;
    private Integer maxRejectedRequestsPerConnection;
    private Integer keepAliveIntervalSeconds;
    private Long maxBackoffIntervalNanos;
    private Integer concurrentLookupRequest;
    private Integer connectionMaxIdleSeconds;
    private Integer connectionTimeoutMs;
    private Integer connectionsPerBroker;
    private String description;
    private String dnsLookupBindAddress;
    private Integer dnsLookupBindPort;
    private String dnsServerAddresses;
    private Boolean enableBusyWait;
    private Boolean enableTransaction;
    private Long initialBackoffIntervalNanos;
    private String listenerName;
    private Integer lookupTimeoutMs;
    private Integer maxLookupRedirects;
    private Integer maxLookupRequest;
    private Integer maxNumberOfRejectedRequestPerConnection;
    private Long memoryLimitBytes;

    public Map<String, Object> constructConnectionConfigMap() {
        Map<String, Object> connectionConfig = new HashMap<>();
        loadConfig(PulsarConstants.SERVICE_URL, serviceUrl, connectionConfig);
        loadConfig(PulsarConstants.OPERATION_TIMEOUT_SECONDS, operationTimeoutSeconds, connectionConfig);
        loadConfig(PulsarConstants.STATS_INTERVAL_SECONDS, statsIntervalSeconds, connectionConfig);
        loadConfig(PulsarConstants.NUM_IO_THREADS, numIoThreads, connectionConfig);
        loadConfig(PulsarConstants.NUM_LISTENER_THREADS, numListenerThreads, connectionConfig);
        loadConfig(PulsarConstants.USE_TCP_NO_DELAY, useTcpNoDelay, connectionConfig);
        loadConfig(PulsarConstants.REQUEST_TIMEOUT_MS, requestTimeoutMs, connectionConfig);
        loadConfig(PulsarConstants.MAX_LOOKUP_REQUESTS, maxLookupRequests, connectionConfig);
        loadConfig(PulsarConstants.MAX_CONCURRENT_LOOKUP_REQUESTS, maxConcurrentLookupRequests, connectionConfig);
        loadConfig(PulsarConstants.MAX_REJECTED_REQUESTS_PER_CONNECTION, maxRejectedRequestsPerConnection, connectionConfig);
        loadConfig(PulsarConstants.KEEP_ALIVE_INTERVAL_SECONDS, keepAliveIntervalSeconds, connectionConfig);
        loadConfig(PulsarConstants.MAX_BACKOFF_INTERVAL_NANOS, maxBackoffIntervalNanos, connectionConfig);
        loadConfig(PulsarConstants.CONCURRENT_LOOKUP_REQUEST, concurrentLookupRequest, connectionConfig);
        loadConfig(PulsarConstants.CONNECTION_MAX_IDLE_SECONDS, connectionMaxIdleSeconds, connectionConfig);
        loadConfig(PulsarConstants.CONNECTION_TIMEOUT_MS, connectionTimeoutMs, connectionConfig);
        loadConfig(PulsarConstants.CONNECTIONS_PER_BROKER, connectionsPerBroker, connectionConfig);
        loadConfig(PulsarConstants.DESCRIPTION, description, connectionConfig);
        loadConfig(PulsarConstants.DNS_LOOKUP_BIND_ADDRESS, dnsLookupBindAddress, connectionConfig);
        loadConfig(PulsarConstants.DNS_LOOKUP_BIND_PORT, dnsLookupBindPort, connectionConfig);
        loadConfig(PulsarConstants.DNS_SERVER_ADDRESSES, dnsServerAddresses, connectionConfig);
        loadConfig(PulsarConstants.ENABLE_BUSY_WAIT, enableBusyWait, connectionConfig);
        loadConfig(PulsarConstants.ENABLE_TRANSACTION, enableTransaction, connectionConfig);
        loadConfig(PulsarConstants.INITIAL_BACKOFF_INTERVAL_NANOS, initialBackoffIntervalNanos, connectionConfig);
        loadConfig(PulsarConstants.LISTENER_NAME, listenerName, connectionConfig);
        loadConfig(PulsarConstants.LOOKUP_TIMEOUT_MS, lookupTimeoutMs, connectionConfig);
        loadConfig(PulsarConstants.MAX_LOOKUP_REDIRECTS, maxLookupRedirects, connectionConfig);
        loadConfig(PulsarConstants.MAX_LOOKUP_REQUEST, maxLookupRequest, connectionConfig);
        loadConfig(PulsarConstants.MAX_NUMBER_OF_REJECTED_REQUEST_PER_CONNECTION, maxNumberOfRejectedRequestPerConnection, connectionConfig);
        loadConfig(PulsarConstants.MEMORY_LIMIT_BYTES, memoryLimitBytes, connectionConfig);
        return connectionConfig;
    }

    public void loadConfig(String configName, Object configValue, Map<String, Object> connectionConfig) {
        if (configValue != null) {
            connectionConfig.put(configName, configValue);
        }
    }

    public String getServiceUrl() {

        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {

        if (StringUtils.isNotEmpty(serviceUrl)) {
            this.serviceUrl = serviceUrl;
        }
    }

    public Integer getOperationTimeoutSeconds() {

        return operationTimeoutSeconds;
    }

    public void setOperationTimeoutSeconds(String operationTimeoutSeconds) {

        if (StringUtils.isNotEmpty(operationTimeoutSeconds)) {
            this.operationTimeoutSeconds = Integer.parseInt(operationTimeoutSeconds);
        }
    }

    public Integer getStatsIntervalSeconds() {

        return statsIntervalSeconds;
    }

    public void setStatsIntervalSeconds(String statsIntervalSeconds) {

        if (StringUtils.isNotEmpty(statsIntervalSeconds)) {
            this.statsIntervalSeconds = Integer.parseInt(statsIntervalSeconds);
        }
    }

    public Integer getNumIoThreads() {

        return numIoThreads;
    }

    public void setNumIoThreads(String numIoThreads) {

        if (StringUtils.isNotEmpty(numIoThreads)) {
            this.numIoThreads = Integer.parseInt(numIoThreads);
        }
    }

    public Integer getNumListenerThreads() {

        return numListenerThreads;
    }

    public void setNumListenerThreads(String numListenerThreads) {

        if (StringUtils.isNotEmpty(numListenerThreads)) {
            this.numListenerThreads = Integer.parseInt(numListenerThreads);
        }
    }

    public Boolean getUseTcpNoDelay() {

        return useTcpNoDelay;
    }

    public void setUseTcpNoDelay(String useTcpNoDelay) {

        if (StringUtils.isNotEmpty(useTcpNoDelay)) {
            this.useTcpNoDelay = Boolean.parseBoolean(useTcpNoDelay);
        }
    }

    public Integer getRequestTimeoutMs() {

        return requestTimeoutMs;
    }

    public void setRequestTimeoutMs(String requestTimeoutMs) {

        if (StringUtils.isNotEmpty(requestTimeoutMs)) {
            this.requestTimeoutMs = Integer.parseInt(requestTimeoutMs);
        }
    }

    public Integer getMaxLookupRequests() {

        return maxLookupRequests;
    }

    public void setMaxLookupRequests(String maxLookupRequests) {

        if (StringUtils.isNotEmpty(maxLookupRequests)) {
            this.maxLookupRequests = Integer.parseInt(maxLookupRequests);
        }
    }

    public Integer getMaxConcurrentLookupRequests() {

        return maxConcurrentLookupRequests;
    }

    public void setMaxConcurrentLookupRequests(String maxConcurrentLookupRequests) {

        if (StringUtils.isNotEmpty(maxConcurrentLookupRequests)) {
            this.maxConcurrentLookupRequests = Integer.parseInt(maxConcurrentLookupRequests);
        }
    }

    public Integer getMaxRejectedRequestsPerConnection() {

        return maxRejectedRequestsPerConnection;
    }

    public void setMaxRejectedRequestsPerConnection(String maxRejectedRequestsPerConnection) {

        if (StringUtils.isNotEmpty(maxRejectedRequestsPerConnection)) {
            this.maxRejectedRequestsPerConnection = Integer.parseInt(maxRejectedRequestsPerConnection);
        }
    }

    public Integer getKeepAliveIntervalSeconds() {

        return keepAliveIntervalSeconds;
    }

    public void setKeepAliveIntervalSeconds(String keepAliveIntervalSeconds) {

        if (StringUtils.isNotEmpty(keepAliveIntervalSeconds)) {
            this.keepAliveIntervalSeconds = Integer.parseInt(keepAliveIntervalSeconds);
        }
    }

    public Long getMaxBackoffIntervalNanos() {

        return maxBackoffIntervalNanos;
    }

    public void setMaxBackoffIntervalNanos(String maxBackoffIntervalNanos) {

        if (StringUtils.isNotEmpty(maxBackoffIntervalNanos)) {
            this.maxBackoffIntervalNanos = Long.parseLong(maxBackoffIntervalNanos);
        }
    }

    public Integer getConcurrentLookupRequest() {

        return concurrentLookupRequest;
    }

    public void setConcurrentLookupRequest(String concurrentLookupRequest) {

        if (StringUtils.isNotEmpty(concurrentLookupRequest)) {
            this.concurrentLookupRequest = Integer.parseInt(concurrentLookupRequest);
        }
    }

    public Integer getConnectionMaxIdleSeconds() {

        return connectionMaxIdleSeconds;
    }

    public void setConnectionMaxIdleSeconds(String connectionMaxIdleSeconds) {

        if (StringUtils.isNotEmpty(connectionMaxIdleSeconds)) {
            this.connectionMaxIdleSeconds = Integer.parseInt(connectionMaxIdleSeconds);
        }
    }

    public Integer getConnectionTimeoutMs() {

        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(String connectionTimeoutMs) {

        if (StringUtils.isNotEmpty(connectionTimeoutMs)) {
            this.connectionTimeoutMs = Integer.parseInt(connectionTimeoutMs);
        }
    }

    public Integer getConnectionsPerBroker() {

        return connectionsPerBroker;
    }

    public void setConnectionsPerBroker(String connectionsPerBroker) {

        if (StringUtils.isNotEmpty(connectionsPerBroker)) {
            this.connectionsPerBroker = Integer.parseInt(connectionsPerBroker);
        }
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        if (StringUtils.isNotEmpty(description)) {
            this.description = description;
        }
    }

    public String getDnsLookupBindAddress() {

        return dnsLookupBindAddress;
    }

    public void setDnsLookupBindAddress(String dnsLookupBindAddress) {

        if (StringUtils.isNotEmpty(dnsLookupBindAddress)) {
            this.dnsLookupBindAddress = dnsLookupBindAddress;
        }
    }

    public Integer getDnsLookupBindPort() {

        return dnsLookupBindPort;
    }

    public void setDnsLookupBindPort(String dnsLookupBindPort) {

        if (StringUtils.isNotEmpty(dnsLookupBindPort)) {
            this.dnsLookupBindPort = Integer.parseInt(dnsLookupBindPort);
        }
    }

    public String getDnsServerAddresses() {

        return dnsServerAddresses;
    }

    public void setDnsServerAddresses(String dnsServerAddresses) {

        if (StringUtils.isNotEmpty(dnsServerAddresses)) {
            this.dnsServerAddresses = dnsServerAddresses;
        }
    }

    public Boolean getEnableBusyWait() {

        return enableBusyWait;
    }

    public void setEnableBusyWait(String enableBusyWait) {

        if (StringUtils.isNotEmpty(enableBusyWait)) {
            this.enableBusyWait = Boolean.parseBoolean(enableBusyWait);
        }
    }

    public Boolean getEnableTransaction() {

        return enableTransaction;
    }

    public void setEnableTransaction(String enableTransaction) {

        if (StringUtils.isNotEmpty(enableTransaction)) {
            this.enableTransaction = Boolean.parseBoolean(enableTransaction);
        }
    }

    public Long getInitialBackoffIntervalNanos() {

        return initialBackoffIntervalNanos;
    }

    public void setInitialBackoffIntervalNanos(String initialBackoffIntervalNanos) {

        if (StringUtils.isNotEmpty(initialBackoffIntervalNanos)) {
            this.initialBackoffIntervalNanos = Long.parseLong(initialBackoffIntervalNanos);
        }
    }

    public String getListenerName() {

        return listenerName;

    }

    public void setListenerName(String listenerName) {

        if (StringUtils.isNotEmpty(listenerName)) {
            this.listenerName = listenerName;
        }
    }

    public Integer getLookupTimeoutMs() {

        return lookupTimeoutMs;
    }

    public void setLookupTimeoutMs(String lookupTimeoutMs) {

        if (StringUtils.isNotEmpty(lookupTimeoutMs)) {
            this.lookupTimeoutMs = Integer.parseInt(lookupTimeoutMs);
        }
    }

    public Integer getMaxLookupRedirects() {

        return maxLookupRedirects;
    }

    public void setMaxLookupRedirects(String maxLookupRedirects) {

        if (StringUtils.isNotEmpty(maxLookupRedirects)) {
            this.maxLookupRedirects = Integer.parseInt(maxLookupRedirects);
        }
    }

    public Integer getMaxLookupRequest() {

        return maxLookupRequest;
    }

    public void setMaxLookupRequest(String maxLookupRequest) {

        if (StringUtils.isNotEmpty(maxLookupRequest)) {
            this.maxLookupRequest = Integer.parseInt(maxLookupRequest);
        }
    }

    public Integer getMaxNumberOfRejectedRequestPerConnection() {

        return maxNumberOfRejectedRequestPerConnection;
    }

    public void setMaxNumberOfRejectedRequestPerConnection(String maxNumberOfRejectedRequestPerConnection) {

        if (StringUtils.isNotEmpty(maxNumberOfRejectedRequestPerConnection)) {
            this.maxNumberOfRejectedRequestPerConnection = Integer.parseInt(maxNumberOfRejectedRequestPerConnection);
        }
    }

    public Long getMemoryLimitBytes() {

        return memoryLimitBytes;
    }

    public void setMemoryLimitBytes(String memoryLimitBytes) {

        if (StringUtils.isNotEmpty(memoryLimitBytes)) {
            this.memoryLimitBytes = Long.parseLong(memoryLimitBytes);
        }
    }
}
