package com.xmap.screen.service.impl;


import okhttp3.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;


import java.io.IOException;
@Service
public class ScreenServiceImpl {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private volatile static okhttp3.OkHttpClient client;
    private static Logger logger = LoggerFactory.getLogger(ScreenServiceImpl.class);
//   final OkHttpClient client = new OkHttpClient();

    /*
     * 向指定URL发送POST方法的请求
     * @param url 发送请求的URL
     * @param json 请求参数，请求参数应该是 String 的形式。
     * @return string 所代表远程资源的响应结果
     */
    public static String syncPost(String url, String json) throws IOException {
//        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            System.out.println(" result: "+result);

            return response.body().string();
        }
//        try {
//            Response response = client.newCall(request).execute();
//            Response response = OkHttpClient.getInstance().newCall(request).execute();
//            if (response.isSuccessful()) {
//                String result = response.body().string();
//
//                logger.info("syncPost response = {}, responseBody= {}", response, result);
//                return result;
//            }
//            String result = response.body().string();
//            System.out.println(" response: ");
//            System.out.println(response);
//            System.out.println(" result: ");
//            System.out.println(result);
//            logger.info("syncPost response = {}, responseBody= {}", response, result);
//            throw new IOException("三方接口返回http状态码为" + response.code());
//        } catch (Exception e) {
//
//            logger.error("syncPost() url:{} have a ecxeption {}", url, e);
//            System.out.println(url);
//            throw new RuntimeException("syncPost() have a ecxeption {}" + e.getMessage());

    }



}

