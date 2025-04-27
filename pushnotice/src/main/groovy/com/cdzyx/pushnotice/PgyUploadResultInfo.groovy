package com.cdzyx.pushnotice


class PgyUploadResultInfo {
    int code
    String message
    Data data

    int getCode() {
        return code
    }

    String getMessage() {
        return message
    }

    PgyUploadResultInfo(int code, String message, Data data) {
        this.code = code
        this.message = message
        this.data = data
    }

    Data getData() {
        return data
    }

    static class Data {
        String buildShortcutUrl
        String buildQRCodeURL

        String getBuildShortcutUrl() {
            return buildShortcutUrl
        }

        String getBuildQRCodeURL() {
            return buildQRCodeURL
        }

        Data(String buildShortcutUrl, String buildQRCodeURL) {
            this.buildShortcutUrl = buildShortcutUrl
            this.buildQRCodeURL = buildQRCodeURL
        }
    }
}