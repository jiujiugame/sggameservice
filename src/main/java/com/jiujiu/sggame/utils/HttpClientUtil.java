package com.jiujiu.sggame.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
	
	
	public static InputStream sendGet(String url, String param) {
        InputStream inputStream = null;
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            if (param != null) {
            	urlNameString = url + "?" + param;
			}else{
				urlNameString = url;
			}
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();;
           
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            return connection.getInputStream();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return inputStream;
    }

    
    public static String doPost(String token, String requestUrl, Map<String,Object> params) {
		// 创建SSLContext
		StringBuffer buffer = null;
		System.out.println("======url:" + requestUrl);
		try {
			URL url = new URL(requestUrl);
			URLConnection conn = url.openConnection();

			// 设置通用的请求属性
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.addRequestProperty("Authorization", token);
			conn.connect();
			// 往服务器端写内容
			String outputStr = GsonUtil.gson.toJson(params);
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}
			
			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
    
    public static String doPost(String requestUrl, Map<String,Object> params) {
		// 创建SSLContext
		StringBuffer buffer = null;
		System.out.println("======url:" + requestUrl);
		try {
			URL url = new URL(requestUrl);
			URLConnection conn = url.openConnection();

			// 设置通用的请求属性
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			// 往服务器端写内容
			String outputStr = GsonUtil.gson.toJson(params);
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}
			
			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
    
    public static String doPostJsonData(String requestUrl,Object object) {
		// 创建SSLContext
		StringBuffer buffer = null;
		System.out.println("======url:" + requestUrl);
		try {
			URL url = new URL(requestUrl);
			URLConnection conn = url.openConnection();

			
			// 设置通用的请求属性
			conn.setConnectTimeout(30000);
			conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			// 往服务器端写内容
			String outputStr = GsonUtil.gson.toJson(object);
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}
			
			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
    
   
    public static void main(String[] args) {
    	
    	String token = "f3fe22c6-e59e-4569-8666-4615265a4252";
    	String tokenType = "bearer";
    	String requestUrl = "https://jiujiuapp.cn/app/api/userinfo";
    	String result = doPost(tokenType+" "+token, requestUrl, null);
		System.out.println(result);
//		String url = "https://api.entts.com/post/";
//		String cookie = "__51cke__=; __tins__2191978=%7B%22sid%22%3A%201565944814172%2C%20%22vd%22%3A%201%2C%20%22expires%22%3A%201565946614172%7D; __51laig__=5";
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//    	
//    	params.put("content", "服务器负载问题已经修复，请大家继续放心使用");
//    	params.put("speed", "0");
//    	params.put("accent", "zh-tw");
//    	
//    	String content = doPost(cookie, url, params);
    	
//    	System.out.println(content);
	}
}