package com.cdzyx.pushnotice


import com.google.gson.Gson
import okhttp3.*

import java.util.concurrent.TimeUnit

class OkHttpUtil {
    private OkHttpClient okHttpClient
    private Gson gson

    OkHttpUtil() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build()
        gson = new Gson()
    }
    /**
     * 获取fir的上传凭证
     * @return 上传凭证
     */
    AppInFirInfo getCert(String packageName, String apiToken) {
        FormBody.Builder build = new FormBody.Builder()
        build.add("bundle_id", packageName)
        build.add("api_token", apiToken)
        build.add("type", "android")
        Request request = new Request.Builder().url("http://api.appmeta.cn/apps").post(build.build()).build()
        Response response = okHttpClient.newCall(request).execute()
        String result = response.body.string()
        return gson.fromJson(result, AppInFirInfo.class)
    }

    /**
     * 获取蒲公英平台上传的 token
     * @param apiKey
     * @param buildUpdateDescription
     * @return
     */
    PgyTokenBean getPgyToken(String apiKey,String buildUpdateDescription) {
        FormBody.Builder build = new FormBody.Builder()
        build.add("_api_key", apiKey)
        build.add("buildType", "android")
        build.add("buildUpdateDescription",buildUpdateDescription)
        Request request = new Request.Builder().url("https://api.pgyer.com/apiv2/app/getCOSToken").post(build.build()).build()
        Response response = okHttpClient.newCall(request).execute()
        String result = response.body.string()
        return gson.fromJson(result, PgyTokenBean.class)
    }

    //上传apk文件
    String uploadApk(File apkFile, String uploadKey, String uploadToken, String appVersionName, Integer appVersionCode, String uploadUrl, String changeLog, String firAppName) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), apkFile)
        MultipartBody body = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"))
                .addFormDataPart("key", uploadKey)
                .addFormDataPart("token", uploadToken)
                .addFormDataPart("x:name", firAppName)
                .addFormDataPart("x:version", appVersionName)
                .addFormDataPart("x:build", String.valueOf(appVersionCode))
                .addFormDataPart("file", apkFile.name, fileBody)
                .addFormDataPart("x:changelog", changeLog)
                .build()
        Request requestApk = new Request.Builder().url(uploadUrl).post(body).build()
        Response responseApk = okHttpClient.newCall(requestApk).execute()
        return responseApk.body.string()
    }


    //上传apk文件
    Response pgyUploadApk(String uploadUrl, String key, String signature, String token, File apkFile) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), apkFile)
        MultipartBody body = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"))
                .addFormDataPart("key", key)
                .addFormDataPart("signature", signature)
                .addFormDataPart("x-cos-security-token", token)
                .addFormDataPart("file", apkFile.name, fileBody)
                .build()
        Request requestApk = new Request.Builder().url(uploadUrl).post(body).build()
        Response responseApkResult = okHttpClient.newCall(requestApk).execute()
        return responseApkResult
    }


    UploadResultInfo uploadIcon(File apkIconFile, String uploadKey, String uploadToken, String uploadUrl) {
        RequestBody fileBodyIcon = RequestBody.create(MediaType.parse("application/octet-stream"), apkIconFile)
        MultipartBody bodyIcon = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"))
                .addFormDataPart("key", uploadKey)
                .addFormDataPart("token", uploadToken)
                .addFormDataPart("file", "icon.png", fileBodyIcon)
                .build()
        Request requestIcon = new Request.Builder().url(uploadUrl).post(bodyIcon).build()
        Response responseIcon = okHttpClient.newCall(requestIcon).execute()
        return gson.fromJson(responseIcon.body.string(), UploadResultInfo.class)
    }

    AppDownloadInfo getAppDownloadInfo(String packageName, String apiToken) {
        // 获取成功连接
        String queryurl =
                "http://api.appmeta.cn/apps/latest/$packageName?api_token=$apiToken&type=android"
        Request requestUrl = new Request.Builder().url(queryurl).get().build()
        Response responseUrl = okHttpClient.newCall(requestUrl).execute()
        String result = responseUrl.body.string()
        return gson.fromJson(result, AppDownloadInfo.class)
    }

    /**
     * 检测应用是否发布完成，并获取发布应用的信息
     * @param apiKey
     * @param buildKey
     * @return
     */
    PgyUploadResultInfo queryUpLoadPgyMessage(String apiKey, String buildKey) {
        String queryurl =
                "https://api.pgyer.com/apiv2/app/buildInfo?_api_key=$apiKey&buildKey=$buildKey"
        Request requestUrl = new Request.Builder().url(queryurl).get().build()
        Response response = okHttpClient.newCall(requestUrl).execute()
        String result = response.body.string()
        return gson.fromJson(result, PgyUploadResultInfo.class)
    }


    String sendDingMessageToTalk(DingTalkBean bean, String robotToken) {
        println("发送给钉钉消息:" + gson.toJson(bean))
        String uploadUrl = "https://oapi.dingtalk.com/robot/send?access_token=" + robotToken
        return sendMessageToTalk(uploadUrl, gson.toJson(bean))
    }

    String sendWeiXinMessageToTalk(WeiXinTalkBean bean, String robotToken) {
        println("发送给微信消息:" + gson.toJson(bean))
        String uploadUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=" + robotToken
        return sendMessageToTalk(uploadUrl, gson.toJson(bean))
    }

    String sendMessageToTalk(String uploadUrl, String json) {
        RequestBody markdownBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        Request mdDingTalk = new Request.Builder().url(uploadUrl)
                .post(markdownBody).build()
        Response responseLink = okHttpClient.newCall(mdDingTalk).execute()
        return responseLink.body.string()
    }

}