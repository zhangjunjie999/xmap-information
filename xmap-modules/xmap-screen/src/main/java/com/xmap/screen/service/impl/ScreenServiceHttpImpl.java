package com.xmap.screen.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
//import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;
public class ScreenServiceHttpImpl {
//    static Logger log = Logger.getLogger(HttpUtil.class);
    public static void main(String[] args) {
        String url = "http://localhost:5000/xpublish-web/service/rs/v1/operationService/refreshMaterialContent";
        String getResult = ScreenServiceHttpImpl.getData(url);
        System.out.println("getResult: "+getResult);

        url = "http://localhost:5000/xpublish-web/service/rs/v1/operationService/refreshMaterialContent";
        JSONObject json = new JSONObject();
        //无ID属性的JSON压缩数据
        String materialContent = "{\"terminalUniqueFieldType\":\"idType\",\"terminalUniqueFieldValues\":[\"919\"],\"operatorType\":\"\",\"businessData\":[{\"materialUniqueFieldType\":\"idType\",\"materialUniqueFieldValue\":\"40\",\"resourceType\":\"document\",\"resourceList\":[{\"resourceValue\":\"第一个test字符串\",\"playTime\":20,\"materialNo\":1},{\"resourceValue\":\"第二个测试文字字符串\",\"playTime\":20,\"materialNo\":2},{\"resourceValue\":\"第san个测试文字字符串\",\"playTime\":20,\"materialNo\":3}]},{\"materialUniqueFieldType\":\"idType\",\"materialUniqueFieldValue\":\"44\",\"resourceType\":\"picture\",\"resourceList\":[{\"playTime\":20,\"resourceValue\":\"http://10.120.9.144:8989/kms/services/rest/dataInfoService/downloadFile?id=74c093f30faa47038ec9e750cdd7237f\",\"materialNo\":1},{\"playTime\":20,\"resourceValue\":\"http://10.120.9.144:8989/kms/services/rest/dataInfoService/downloadFile?id=d9da1c6da6634aa7aa122115ba5c9e63\",\"materialNo\":2},{\"playTime\":20,\"resourceValue\":\"http://10.120.9.144:8989/kms/services/rest/dataInfoService/downloadFile?id=f81f2db369ee4c36a4dec0e25e5fccbd\",\"materialNo\":3}]},{\"materialUniqueFieldType\":\"idType\",\"materialUniqueFieldValue\":\"45\",\"resourceType\":\"web\",\"resourceList\":[{\"playTime\":20,\"resourceValue\":\"https://www.xunlei.com\",\"materialNo\":1},{\"playTime\":20,\"resourceValue\":\"https://www.hikvision.com/cn/\",\"materialNo\":2},{\"playTime\":20,\"resourceValue\":\"https://www.baidu.com\",\"materialNo\":3}]},{\"materialUniqueFieldType\":\"idType\",\"materialUniqueFieldValue\":\"46\",\"resourceType\":\"RTSP\",\"resourceList\":[{\"playTime\":20,\"resourceValue\":\"rtsp://admin:Hik1234567@10.196.76.80:554/h264/ch1/main/av_stream\",\"materialNo\":1},{\"playTime\":20,\"resourceValue\":\"rtsp://admin:Hik12345@10.196.76.33:554/h264/ch1/main/av_stream\",\"materialNo\":2},{\"playTime\":20,\"resourceValue\":\"rtsp://admin:Hik12345@10.196.76.196:554/h264/ch1/main/av_stream\",\"materialNo\":3}]}]}";
        //有ID属性的JSON压缩数据
//        String materialContent = "{\"terminalUniqueFieldType\":\"idType\",\"terminalUniqueFieldValues\":[\"919\"],\"operatorType\":\"\",\"businessData\":[{\"materialUniqueFieldType\":\"idType\",\"materialUniqueFieldValue\":\"40\",\"resourceType\":\"document\",\"resourceList\":[{\"resourceValue\":\"第一个test字符串\",\"playTime\":20,\"materialNo\":1},{\"resourceValue\":\"第二个测试文字字符串\",\"playTime\":20,\"materialNo\":2},{\"resourceValue\":\"第san个测试文字字符串\",\"playTime\":20,\"materialNo\":3}]},{\"materialUniqueFieldType\":\"idType\",\"materialUniqueFieldValue\":\"44\",\"resourceType\":\"picture\",\"resourceList\":[{\"playTime\":20,\"resourceValue\":\"http://10.120.9.144:8989/kms/services/rest/dataInfoService/downloadFile?id=74c093f30faa47038ec9e750cdd7237f\",\"materialNo\":1},{\"playTime\":20,\"resourceValue\":\"http://10.120.9.144:8989/kms/services/rest/dataInfoService/downloadFile?id=d9da1c6da6634aa7aa122115ba5c9e63\",\"materialNo\":2},{\"playTime\":20,\"resourceValue\":\"http://10.120.9.144:8989/kms/services/rest/dataInfoService/downloadFile?id=f81f2db369ee4c36a4dec0e25e5fccbd\",\"materialNo\":3}]},{\"materialUniqueFieldType\":\"idType\",\"materialUniqueFieldValue\":\"45\",\"resourceType\":\"web\",\"resourceList\":[{\"playTime\":20,\"resourceValue\":\"https://www.xunlei.com\",\"materialNo\":1},{\"playTime\":20,\"resourceValue\":\"https://www.hikvision.com/cn/\",\"materialNo\":2},{\"playTime\":20,\"resourceValue\":\"https://www.baidu.com\",\"materialNo\":3}]},{\"materialUniqueFieldType\":\"idType\",\"materialUniqueFieldValue\":\"46\",\"resourceType\":\"RTSP\",\"resourceList\":[{\"playTime\":20,\"resourceValue\":\"rtsp://admin:Hik1234567@10.196.76.80:554/h264/ch1/main/av_stream\",\"materialNo\":1},{\"playTime\":20,\"resourceValue\":\"rtsp://admin:Hik12345@10.196.76.33:554/h264/ch1/main/av_stream\",\"materialNo\":2},{\"playTime\":20,\"resourceValue\":\"rtsp://admin:Hik12345@10.196.76.196:554/h264/ch1/main/av_stream\",\"materialNo\":3}]}],\"id\":12}";
//        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
//        filter.getExcludes().add("params"); // 排除字段名为params的字段
        //        JSON.toJSONString(materialContent, filter);
        JSONObject jsonObject =  JSON.parseObject(materialContent);


//        json.put("key", "==g43sEvsUcbcunFv3mHkIzlHO4iiUIT R7WwXuSVKTK0yugJnZSlr6qNbxsL8OqCUAFyCDCoRKQ882m6cTTi0q9uCJsq JJvxS+8mZVRP/7lWfEVt8/N9mKplUA68SWJEPSXyz4MDeFam766KEyvqZ99d");
        String postResult = ScreenServiceHttpImpl.postData(url, jsonObject);
        System.out.println("postResult: "+postResult);

//        url = "http://localhost:8082/api/test/testSendForm?format=json";
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "测试表单请求");
//        String formResult = ScreenServiceHttpImpl.sendxwwwForm(url, map);
//        System.out.println(formResult);

    }


    public static String getData(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    /*
     * 向指定URL发送POST方法的请求
     * @param url 发送请求的URL
     * @param json 请求参数，请求参数是 Map<String, Object> 的形式。
     * @return string 所代表远程资源的响应结果
     */

    public static String postData(String url, Map<String, Object> paramMap) {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpPost post = new HttpPost(url);
        String result = "";
        try (CloseableHttpClient closeableHttpClient = httpClientBuilder.build()) {
            // HttpEntity entity = new StringEntity(jsonStrData);
            // 修复 POST json 导致中文乱码
            HttpEntity entity = new StringEntity(paramMap.toString(), "UTF-8");
            post.setEntity(entity);
            post.setHeader("Content-type", "application/json");
            HttpResponse resp = closeableHttpClient.execute(post);
            try {
                InputStream respIs = resp.getEntity().getContent();
                byte[] respBytes = IOUtils.toByteArray(respIs);
                result = new String(respBytes, Charset.forName("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * form表单提交
     * @param url
     * @param paramMap
     * @return
     */
    public static String sendxwwwform(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
