package com.cdzyx.pushnotice

import com.google.gson.annotations.SerializedName

class PgyTokenBean {
    private int code
    private String message
    private Data data

    PgyTokenBean(int code, String message, Data data) {
        this.code = code
        this.message = message
        this.data = data
    }

    int getCode() {
        return code
    }

    String getMessage() {
        return message
    }

    Data getData() {
        return data
    }

    static class Data {
        private String key
        private String endpoint
        private Params params

        String getKey() {
            return key
        }

        String getEndpoint() {
            return endpoint
        }

        Params getParams() {
            return params
        }

        Data(String key, String endpoint, Params params) {
            this.key = key
            this.endpoint = endpoint
            this.params = params
        }

        static class Params {
            private String signature
            @SerializedName("x-cos-security-token")
            private String token
            private key

            Params(String signature, String token, key) {
                this.signature = signature
                this.token = token
                this.key = key
            }

            String getSignature() {
                return signature
            }

            String getToken() {
                return token
            }

            def getKey() {
                return key
            }
        }
    }
}