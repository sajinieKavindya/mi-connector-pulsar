/*
 *  Copyright (c) 2025, WSO2 LLC. (https://www.wso2.com).
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.integration.connector.utils;

/**
 * Contains error codes and details
 * related to Apache Pulsar connector
 */
public enum Error {

    CONNECTION_ERROR("700200", "PULSAR:CONNECTION_ERROR"),
    INVALID_CONFIGURATION("700201", "PULSAR:INVALID_CONFIGURATION"),
    OPERATION_ERROR("700202", "PULSAR:OPERATION_ERROR");

    private final String code;
    private final String message;

    /**
     * Create an error code.
     *
     * @param code    error code represented by number
     * @param message error message
     */
    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getErrorCode() {
        return this.code;
    }

    public String getErrorMessage() {
        return this.message;
    }
}
