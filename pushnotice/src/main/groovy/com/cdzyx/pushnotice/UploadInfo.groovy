package com.cdzyx.pushnotice


class UploadInfo {
    boolean needUpload
    String changeLog
    String appTestVersionCodeText
    String needAtPeopleMobiles
    String apiToken
    String packageName
    String robotToken
    String appName
    String appIconPath
    String firAppName
    String platform
    String pgyApiKey


    @Override
    String toString() {
        return "UploadInfo{" +
                "needUpload=" + needUpload +
                ", changeLog='" + changeLog + '\'' +
                ", appTestVersionCodeText='" + appTestVersionCodeText + '\'' +
                ", needAtPeopleMobiles='" + needAtPeopleMobiles + '\'' +
                ", apiToken='" + apiToken + '\'' +
                ", packageName='" + packageName + '\'' +
                ", robotToken='" + robotToken + '\'' +
                ", appName='" + appName + '\'' +
                ", appIconPath='" + appIconPath + '\'' +
                ", firAppName='" + firAppName + '\'' +
                ", platform='" + platform + '\'' +
                ", pgyApiKey='" + pgyApiKey + '\'' +
                '}'
    }
}