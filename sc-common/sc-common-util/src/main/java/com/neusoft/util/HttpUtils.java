package com.neusoft.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpUtils {

	public static String HttpGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpEntity entity = null;
		String respText = null;

		try {
			HttpGet httpget = new HttpGet(url);
			System.out.println("executing request " + httpget.getURI());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				entity = response.getEntity();
				System.out.println("--------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					respText = EntityUtils.toString(entity, HTTP.UTF_8);
					System.out.println("Response content length: "
							+ entity.getContentLength());
					System.out.println("Response content: " + respText);
				}
				System.out.println("------------------------------------");
			} finally {
				response.close();
				// ADD BY W_CHEN 20150803-START
				httpget.releaseConnection();
				// ADD BY W_CHEN 20150803-END
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return respText;
	}
	
	
	public static String httpPost(String url, List<NameValuePair> formparams) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity uefEntity;
		String respText = null;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					respText = EntityUtils.toString(entity, HTTP.UTF_8);
					System.out.println("--------------------------------------");
					System.out.println("Response content: "+ respText);
					System.out.println("--------------------------------------");
				}
				
				
			} finally {
				response.close();
				// ADD BY W_CHEN 20150803-START
				httppost.releaseConnection();
				// ADD BY W_CHEN 20150803-END
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return respText;
	}
	
	 public static List<NameValuePair> getPostForm(Map<String, String> params){  
         
	        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
	        Set<String> keySet = params.keySet();  
	        for(String key : keySet) {  
	            nvps.add(new BasicNameValuePair(key, params.get(key)));  
	        }  
	          
	        return nvps;  
	    }

}
