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
        String buildIcon
        String buildKey

        String getBuildShortcutUrl() {
            return buildShortcutUrl
        }

        String getBuildQRCodeURL() {
            return buildQRCodeURL
        }

        String getBuildIcon() {
            return buildIcon
        }

        String getBuildKey() {
            return buildKey
        }

        Data(String buildShortcutUrl, String buildQRCodeURL, String buildIcon, String buildKey) {
            this.buildShortcutUrl = buildShortcutUrl
            this.buildQRCodeURL = buildQRCodeURL
            this.buildIcon = buildIcon
            this.buildKey = buildKey
        }
    }
}