package spider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SpiderFromBaidu {  
	public static void main(String[] args){
		String str=httpRequest("http://www.gsdata.cn/query/wx?q=%E5%90%88%E5%B7%A5%E5%A4%A7%E6%9D%90%E6%96%99&search_field=undefined");
		handleConcent(str);
	}
	public static void handleConcent(String httpHtml){
		Pattern p = Pattern.compile("微信号：<span>(.*?)<[\\s\\S]*?最新排名：[\\s\\S]*?>(.*?)<[\\s\\S]*?活跃粉丝：<span>(.*?)<[\\s\\S]*?WCI</a>：<span>(.*?)<[\\s\\S]*?最近收录：[\\s\\S]*?\"_blank\">(.*?)</a>[\\s\\S]*?\"wx-time\">(.*?)<");  
		Matcher m = p.matcher(httpHtml)	;
		while(m.find()){
			System.out.println("微信号："+m.group(1)+" 最新排名："+m.group(2)+" 等价活跃粉丝数："+m.group(3)+" WCI："+m.group(4)+" 最后收录："+m.group(5)+" 时间："+m.group(6));
		}
	}
	public static String httpRequest(String requestUrl){
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
			httpUrlConn.setRequestMethod("GET");
			
			inputStream=httpUrlConn.getInputStream();
			inputStreamReader=new InputStreamReader(inputStream,"utf-8");
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
