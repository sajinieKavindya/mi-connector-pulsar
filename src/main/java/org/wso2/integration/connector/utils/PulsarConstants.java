package org.wso2.integration.connector.utils;

public class PulsarConstants {

    public static final String CONNECTOR_NAME = "pulsar";
    public static final String CONNECTION_NAME = "name";

    // Pulsar connection parameter constants
    public static final String SERVICE_URL = "serviceUrl";
    public static final String AUTHENTICATION = "authentication";
    public static final String OPERATION_TIMEOUT_SECONDS = "operationTimeoutSeconds";
    public static final String STATS_INTERVAL_SECONDS = "statsIntervalSeconds";
    public static final String NUM_IO_THREADS = "numIoThreads";
    public static final String NUM_LISTENER_THREADS = "numListenerThreads";
    public static final String USE_TCP_NO_DELAY = "useTcpNoDelay";
    public static final String REQUEST_TIMEOUT_MS = "requestTimeoutMs";
    public static final String MAX_LOOKUP_REQUESTS = "maxLookupRequests";
    public static final String MAX_CONCURRENT_LOOKUP_REQUESTS = "maxConcurrentLookupRequests";
    public static final String MAX_REJECTED_REQUESTS_PER_CONNECTION = "maxRejectedRequestsPerConnection";
    public static final String KEEP_ALIVE_INTERVAL_SECONDS = "keepAliveIntervalSeconds";
    public static final String MAX_BACKOFF_INTERVAL_NANOS = "maxBackoffIntervalNanos";
    public static final String CONCURRENT_LOOKUP_REQUEST = "concurrentLookupRequest";
    public static final String CONNECTION_MAX_IDLE_SECONDS = "connectionMaxIdleSeconds";
    public static final String CONNECTION_TIMEOUT_MS = "connectionTimeoutMs";
    public static final String CONNECTIONS_PER_BROKER = "connectionsPerBroker";
    public static final String DESCRIPTION = "description";
    public static final String DNS_LOOKUP_BIND_ADDRESS = "dnsLookupBindAddress";
    public static final String DNS_LOOKUP_BIND_PORT = "dnsLookupBindPort";
    public static final String DNS_SERVER_ADDRESSES = "dnsServerAddresses";
    public static final String ENABLE_BUSY_WAIT = "enableBusyWait";
    public static final String ENABLE_TRANSACTION = "enableTransaction";
    public static final String INITIAL_BACKOFF_INTERVAL_NANOS = "initialBackoffIntervalNanos";
    public static final String LISTENER_NAME = "listenerName";
    public static final String LOOKUP_TIMEOUT_MS = "lookupTimeoutMs";
    public static final String MAX_LOOKUP_REDIRECTS = "maxLookupRedirects";
    public static final String MAX_LOOKUP_REQUEST = "maxLookupRequest";
    public static final String MAX_NUMBER_OF_REJECTED_REQUEST_PER_CONNECTION = "maxNumberOfRejectedRequestPerConnection";
    public static final String MEMORY_LIMIT_BYTES = "memoryLimitBytes";

    public static final String TLS_CERTIFICATE_FILE_PATH = "tlsCertificateFilePath";
    public static final String TLS_KEY_FILE_PATH = "tlsKeyFilePath";
    public static final String TLS_TRUST_CERTS_FILE_PATH = "tlsTrustCertsFilePath";
    public static final String TLS_ALLOW_INSECURE_CONNECTION = "tlsAllowInsecureConnection";
    public static final String TLS_HOSTNAME_VERIFICATION_ENABLE = "tlsHostnameVerificationEnable";
    public static final String USE_KEY_STORE_TLS = "useKeyStoreTls";
    public static final String TLS_TRUST_STORE_TYPE = "tlsTrustStoreType";
    public static final String TLS_TRUST_STORE_PATH = "tlsTrustStorePath";
    public static final String TLS_TRUST_STORE_PASSWORD = "tlsTrustStorePassword";
    public static final String TLS_KEY_STORE_TYPE = "tlsKeyStoreType";
    public static final String TLS_KEY_STORE_PATH = "tlsKeyStorePath";
    public static final String TLS_KEY_STORE_PASSWORD = "tlsKeyStorePassword";
    public static final String TLS_CIPHERS = "tlsCiphers";
    public static final String TLS_PROTOCOLS = "tlsProtocols";
    public static final String USE_TLS = "useTLS";

    public static final String AUTH_TYPE = "authorizationType";
    public static final String AUTH_JWT = "JWT";
    public static final String AUTH_TLS = "TLS";
    public static final String AUTH_OAUTH2 = "OAUTH2";
    public static final String AUTH_NONE = "NONE";
    public static final String AUTH_PARAM_MAP = "authParamMap";
    public static final String AUTH_PARAMS = "authParams";
    public static final String AUTH_PLUGIN_CLASS_NAME = "authPluginClassName";
    public static final String AUTO_CERT_REFRESH_SECONDS = "autoCertRefreshSeconds";

    public static final String TOKEN = "token";

    // Pulsar producer configuration parameter constants
    public static final String PRODUCER_TOPIC_NAME = "topicName";
    public static final String PRODUCER_NAME = "producerName";
    public static final String SEND_TIMEOUT_MS = "sendTimeoutMs";
    public static final String BLOCK_IF_QUEUE_FULL = "blockIfQueueFull";
    public static final String MAX_PENDING_MESSAGES = "maxPendingMessages";
    public static final String MAX_PENDING_MESSAGES_ACROSS_PARTITIONS = "maxPendingMessagesAcrossPartitions";
    public static final String BATCHING_ENABLED = "batchingEnabled";
    public static final String BATCHING_MAX_MESSAGES = "batchingMaxMessages";
    public static final String BATCHING_MAX_PUBLISH_DELAY_MICROS = "batchingMaxPublishDelayMicros";
    public static final String COMPRESSION_TYPE = "compressionType";
    public static final String HASHING_SCHEME = "hashingScheme";
    public static final String MESSAGE_ROUTING_MODE = "messageRoutingMode";
    public static final String CHUNKING_ENABLED = "chunkingEnabled";
    public static final String CRYPTO_FAILURE_ACTION = "cryptoFailureAction";

    public static final String KEY = "key";
}
