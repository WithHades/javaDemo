package com.xiaodui.Modular;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class Modular {
	public static void main(String[] args){
		System.out.println(ReverseString("这是一段用于测试的字符串"));
	}
	/**
	 * 截取一段文本中间的文本
	 * @param 全部文本
	 * @param 前面字符
	 * @param 后面字符
	 * @return 
	 */
	public static String getMidOfString(String str,String frontStr,String behindStr){
		int beginIndex=str.indexOf(frontStr);
		if(beginIndex==-1) return "";
		int endIndex=str.indexOf(behindStr);
		if(endIndex==-1)  return "";
		return str.substring(beginIndex+1, endIndex);
	}
	/**
	 * 截取文本左边的字符
	 * @param 全部文本
	 * @param 关键字符
	 * @return
	 */
	public static String getleftOfString(String str,String behindStr){
		int beginIndex=str.indexOf(behindStr);
		return str.substring(0,beginIndex);
	}
	/**
	 * 截取文本右边的字符
	 * @param 全部文本
	 * @param 关键字符
	 * @return
	 */
	public static String getRightOfString(String str,String frontStr){
		int beginIndex=str.lastIndexOf(frontStr);
		return str.substring(beginIndex+1);
	}
	/**
	 * 反转字符串
	 * @param 被操作文本
	 * @return
	 */
	public static String ReverseString(String str){
	    String reverse = new StringBuffer(str).
	    reverse().toString();
	    return reverse;
	}
	/**
	 * 获取指定主机名的IP地址
	 * @param 主机名
	 * @return
	 */
	public static String getHostAddress(String hostName){
		InetAddress address=null;
		try{
			address=InetAddress.getByName(hostName);
		}
		catch(UnknownHostException e){
			System.exit(2);
		}
		return address.getHostAddress();
	}
	public static String httpRequest(String requestUrl,String method,String encodeType){
		StringBuffer buffer=null;
		BufferedReader bufferedReader=null;
		InputStreamReader inputStreamReader=null;
		InputStream inputStream=null;
		HttpURLConnection httpUrlConn=null;
		try{
			URL url=new URL(requestUrl);
			httpUrlConn=(HttpURLConnection)url.openConnection();
			httpUrlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36");
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod(method);
			
			inputStream=httpUrlConn.getInputStream();
			inputStreamReader=new InputStreamReader(inputStream,encodeType);
			bufferedReader=new BufferedReader(inputStreamReader);
			
			buffer=new StringBuffer();
			String str=null;
			while((str=bufferedReader.readLine())!=null){
				buffer.append(str);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			if(bufferedReader!=null){
				try{
					bufferedReader.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
			if(inputStreamReader!=null){
				try{
					inputStreamReader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(inputStream!=null){
				try{
					inputStream.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(httpUrlConn!=null){
				httpUrlConn.disconnect();
			}
		}
		return buffer.toString();
	}
}