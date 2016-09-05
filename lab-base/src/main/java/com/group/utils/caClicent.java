package com.group.utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class caClicent {
  public static void main(String[] args) {
   /*1.IP地址查询*/
    StringBuffer soapRequestData = new StringBuffer();
    soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    soapRequestData.append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
    soapRequestData.append("<soap12:Body>");
    soapRequestData.append("    <getCountryCityByIp xmlns=\"http://WebXml.com.cn/\">");
    soapRequestData.append("        <theIpAddress>220.181.111.148</theIpAddress>");
    soapRequestData.append("    </getCountryCityByIp>");
    soapRequestData.append("</soap12:Body>");
    soapRequestData.append("</soap12:Envelope>");
    
    String url = "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl";//请求Webservice地址
    
   /*2.查询省份下的城市*/
    soapRequestData = new StringBuffer();
    soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    soapRequestData.append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
    soapRequestData.append("<soap12:Body>");
    soapRequestData.append("    <getSupportCity xmlns=\"http://WebXml.com.cn/\">");
    soapRequestData.append("        <byProvinceName>湖南</byProvinceName>");
    soapRequestData.append("    </getSupportCity>");
    soapRequestData.append("</soap12:Body>");
    soapRequestData.append("</soap12:Envelope>");
    
    url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";//请求Webservice地址
    
    getWebService(soapRequestData.toString(),url);
  }

  public static void getWebService(String soapRequestData,String url) {

    PostMethod postMethod = new PostMethod(url);//请求连接

    // 然后把Soap请求数据添加到PostMethod中
    try {
      byte[] b = soapRequestData.getBytes("utf-8");
      InputStream is = new ByteArrayInputStream(b, 0, b.length);
      RequestEntity re = new InputStreamRequestEntity(is, b.length,"application/soap+xml; charset=utf-8");
      postMethod.setRequestEntity(re);

      // 最后生成一个HttpClient对象，并发出postMethod请求
      HttpClient httpClient = new HttpClient();
      int statusCode = httpClient.executeMethod(postMethod);
      String soapResponseData = postMethod.getResponseBodyAsString();
      
      System.out.println(statusCode);
      System.out.print(soapResponseData);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (HttpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
