package org.wso2.integration.connector;

import java.util.Map;
import java.util.Objects;

public class ProducerKey {

    String topicName;
    String sendTimeoutMs;
    String blockIfQueueFull;
    String maxPendingMessages;
    String maxPendingMessagesAcrossPartitions;
    String batchingEnabled;
    String batchingMaxMessages;
    String batchingMaxPublishDelayMicros;
    String compressionType;
    String hashingScheme;
    String messageRoutingMode;
    String chunkingEnabled;
    String cryptoFailureAction;
    String initialSubscriptionName;

    private final Map<String, String> config;

    public ProducerKey(String topic, Map<String, String> config) {
        this.topicName = topic;
        this.config = config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProducerKey)) return false;
        ProducerKey that = (ProducerKey) o;
        return Objects.equals(topicName, that.topicName) &&
                Objects.equals(config, that.config);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicName, config);
    }

}
