package cn.zcp.wechartapi.base.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * 网络请求工具类
 */
public class RestTemplateUtil {
    private static Logger logger = LogManager.getLogger(RestTemplateUtil.class);

    public static String get(String url) {
        // 核心返回结果报文字符串
        String returnJson = "";

        try {
            //复杂构造函数的使用
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(1000);// 设置超时
            requestFactory.setReadTimeout(1000);

            //利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            //进行网络请求,访问url接口
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            //根据返回值进行后续操作
            if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
                returnJson = responseEntity.getBody();
//                returnJson = new String(returnJson.getBytes("ISO-8859-1"), "utf-8");
                return returnJson;
            }


        } catch (Exception e) {
            e.printStackTrace();
            logger.info("----------------------------------------");
            logger.info(returnJson);
            logger.info("----------------------------------------");
        }return returnJson;
    }
    public static String post(String reqParam, String url) {
        // 核心返回结果报文字符串
        String returnJson = "";

        try {
            //复杂构造函数的使用
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(1000);// 设置超时
            requestFactory.setReadTimeout(1000);

            //利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            //设置HTTP请求头信息，实现编码等
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            //利用容器实现数据封装，发送
            HttpEntity<String> entity = new HttpEntity<String>(reqParam, headers);
            returnJson = restTemplate.postForObject(url, entity, String.class);

            // 转码原因：RestTemplate默认是使用org.springframework.http.converter.StringHttpMessageConverter来解析
            // StringHttpMessageConverter 默认用的 ISO-8859-1来编码的
            returnJson = new String(returnJson.getBytes("ISO-8859-1"), "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("----------------------------------------");
            logger.info(returnJson);
            logger.info("----------------------------------------");
        }return returnJson;
    }
}
