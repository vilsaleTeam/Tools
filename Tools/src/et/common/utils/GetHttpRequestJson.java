package et.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;


/**
 * 请求远程服务器
 * @author 李雅翔
 * @date 2017年12月5日
 */
public class GetHttpRequestJson {
	
	 public enum HttpType {
		   JSON, XML
	 }
	 
	/**
	 * 发送HttpGet请求 
	 * @param urlPath 请求地址
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String urlPath) { 
        OtherToolKit.setRunTime();
		Reader reader = null;
		HttpURLConnection connection = null; 
		InputStream inputStream = null;
		try {
			URL url = new URL(urlPath);  
	        connection = (HttpURLConnection) url.openConnection();  
	        connection.connect();  
	      
	        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
	        	//判断返回的状态码是否是 200和201
	        	inputStream = connection.getInputStream();  
			}else {
				//返回错误码
				inputStream = connection.getErrorStream();
			}
	        //对应的字符编码转换  
	        reader = new InputStreamReader(inputStream, "UTF-8");  
	        BufferedReader bufferedReader = new BufferedReader(reader);  
	        String str = null;  
	        StringBuilder sb = new StringBuilder();  
	        while ((str = bufferedReader.readLine()) != null) {  
	            sb.append(str);  
	        }  
	        reader.close();  
//	        log.info(urlPath.split("\\?")[0]+",  请求响应时间为:"+OtherToolKit.getRunTime()+" ms");
	        return sb.toString();  
		}catch (Exception e) {
			e.printStackTrace();
//    		JSONObject jsonObject = new JSONObject();
//    		jsonObject.put("code", 500);
//    		jsonObject.put("message", "查询失败,原因可能为请求解析错误");
//    		log.error("GET请求出错，错误信息为:{},请求地址为：{}", e.getMessage(), urlPath);
//    		return jsonObject.toString();
		} finally {
			
	        connection.disconnect();  
		}
		return urlPath;
    }  
	
	
    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params json字符串
     *  
     * @return 成功:返回json字符串
     * @throws Exception 
     */  
    public static String sendPost(String urlPath, String json) throws Exception { 
    	return sendPost(urlPath, json, HttpType.JSON, HttpType.JSON);
    }  
    
    /**
     * 发送HttpPost请求 
     * @param urlPath		服务地址
     * @param json			输入数据
     * @param sendType		发送数据类型 xml,json
     * @param receiveType	接收数据类型 xml,json
     * @return			
     * @throws Exception
     */
    public static String sendPost(String urlPath, String json, HttpType sendType, HttpType receiveType ) throws Exception { 
    	OtherToolKit.setRunTime();
        BufferedReader in = null;
        OutputStreamWriter out = null;
    	try {
	        URL url = new URL(urlPath);// 创建连接  
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
	        
	        connection.setDoOutput(true);  // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在  http正文内，因此需要设为true, 默认情况下是false;    
	        connection.setDoInput(true);  // 设置是否从httpUrlConnection读入，默认情况下是true;   
	        connection.setUseCaches(false);// Post 请求不能使用缓存   
	        connection.setInstanceFollowRedirects(true); //设置自动处理重定向 
	        connection.setRequestMethod("POST"); // 设置请求方式  
	        connection.setRequestProperty("Charset", "UTF-8");
	        connection.setRequestProperty("Accept-Encoding", "gzip");;
	        
	        String sendTypeStr = "json";
	        String receiveTypeStr = "json";
	        if (HttpType.XML.equals(sendType)) {
	        	sendTypeStr = "xml";
			}
	        if (HttpType.XML.equals(receiveType)) {
	        	receiveTypeStr = "xml";
			}
	        
	        connection.setRequestProperty("Content-Type", "application/"+sendTypeStr); // 设置发送数据的格式  
	        connection.setRequestProperty("Accept", "application/"+receiveTypeStr); // 设置接收数据的格式  
	        connection.connect();  
	        out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
	        out.append(json);  
	        out.flush();  
	        out.close();  
	        //状态码 500 时 使用此方法会直接报错，以下方法为了解决这个问题
	        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
	        	//判断返回的状态码是否是 200和201
	        	in = new BufferedReader(
	 	                new InputStreamReader(connection.getInputStream(),"UTF-8"));
			}else {
				 in = new BufferedReader(
			                new InputStreamReader(connection.getErrorStream(),"UTF-8"));
			}
	        String line = null;
	        StringBuilder sb = new StringBuilder();
	        while ((line = in.readLine()) != null) {
	        	sb.append(line);
	        }
	        return sb.toString();
    	}catch (Exception e) {
    		e.printStackTrace();
//    		JsonObject jsonObject = new JSONObject();
//    		jsonObject.put("code", 500);
//    		jsonObject.put("message", "查询失败,原因可能为请求解析错误");
//    		log.error("POST请求出错，错误信息为:{},请求地址为：{}，请求内容为：{}", e.getMessage(), urlPath, json);
//    		return jsonObject.toString();
		} finally {
    		if(out!=null){
                out.close();
            }
            if(in!=null){
                in.close();
            }
    	}
    	return "";
    }  
    
    /**
     * 发送Post请求 并压缩请求内容
     * @param urlPath		服务地址
     * @param json			输入数据
     * @param sendType		发送数据类型 xml,json 
     * @param receiveType	接收数据类型 xml,json
     * @return			
     * @throws Exception
     */
    public static String sendPostByGZIP(String urlPath, String json) throws Exception { 
    	return sendPostByGZIP(urlPath, json, HttpType.JSON, HttpType.JSON);
    }
    
    /**
     * 发送HttpPost请求 并压缩请求内容
     * @param urlPath		服务地址
     * @param json			输入数据
     * @param sendType		发送数据类型 xml,json 
     * @param receiveType	接收数据类型 xml,json
     * @return			
     * @throws Exception
     */
    public static String sendPostByGZIP(String urlPath, String json, HttpType sendType, HttpType receiveType ) throws Exception { 
    	OtherToolKit.setRunTime();
        BufferedReader in = null;
//        OutputStreamWriter out = null;
        GZIPOutputStream out = null;
    	try {
	        URL url = new URL(urlPath);// 创建连接  
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
	        
	        connection.setDoOutput(true);  // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在  http正文内，因此需要设为true, 默认情况下是false;    
	        connection.setDoInput(true);  // 设置是否从httpUrlConnection读入，默认情况下是true;   
	        connection.setUseCaches(false);// Post 请求不能使用缓存   
	        connection.setInstanceFollowRedirects(true); //设置自动处理重定向 
	        connection.setRequestMethod("POST"); // 设置请求方式  
	        connection.setRequestProperty("Charset", "UTF-8");
	        connection.setRequestProperty("Accept-Encoding", "gzip");;
	        
	        String sendTypeStr = "json";
	        String receiveTypeStr = "json";
	        if (HttpType.XML.equals(sendType)) {
	        	sendTypeStr = "xml";
			}
	        if (HttpType.XML.equals(receiveType)) {
	        	receiveTypeStr = "xml";
			}
	        
	        connection.setRequestProperty("Content-Type", "application/"+sendTypeStr); // 设置发送数据的格式  
	        connection.setRequestProperty("Accept", "application/"+receiveTypeStr); // 设置接收数据的格式  
	        connection.connect();  
	        out = new GZIPOutputStream(connection.getOutputStream());// utf-8编码  
	        out.write(json.getBytes("UTF-8"));  //设置编码格式
	        out.flush();  
	        out.close();  
	        //状态码 500 时 使用此方法会直接报错，以下方法为了解决这个问题
	        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
	        	//判断返回的状态码是否是 200和201
	        	in = new BufferedReader(
	 	                new InputStreamReader(connection.getInputStream(),"UTF-8"));
			}else {
				 in = new BufferedReader(
			                new InputStreamReader(connection.getErrorStream(),"UTF-8"));
			}
	        String line = null;
	        StringBuilder sb = new StringBuilder();
	        while ((line = in.readLine()) != null) {
	        	sb.append(line);
	        }
//	        log.info("请求响应时间为:"+OtherToolKit.getRunTime()+" ms");
	        return sb.toString();
    	}catch (Exception e) {
    		e.printStackTrace();
//    		JSONObject jsonObject = new JSONObject();
//    		jsonObject.put("code", 500);
//    		jsonObject.put("message", "查询失败,原因可能为请求解析错误");
//    		log.error("POST请求出错，错误信息为:{},请求地址为：{}，请求内容为：{}", e.getMessage(), urlPath, json);
//    		return jsonObject.toString();
		} finally {
    		if(out!=null){
                out.close();
            }
            if(in!=null){
                in.close();
            }
    	}
		return json;
    } 
    
    public static void main(String[] args) throws Exception {
    	String userUrl = "https://redmine.vilsale.com/users/19.json";
    	sendGet(userUrl);
    	
//    	String urlPath = "http://scyx.vilsale.com/MVW/api/payOrder/checkSignature";//pay/orderquery
		
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("out_trade_no", "");
//		String json = "<xml>\r\n" + 
//				"<appid><![CDATA[wx27824dcc293eec81]]></appid>\r\n" + 
//				"<bank_type><![CDATA[CFT]]></bank_type>\r\n" + 
//				"<cash_fee><![CDATA[1]]></cash_fee>\r\n" + 
//				"<fee_type><![CDATA[CNY]]></fee_type>\r\n" + 
//				"<is_subscribe><![CDATA[Y]]></is_subscribe>\r\n" + 
//				"<mch_id><![CDATA[1493064512]]></mch_id>\r\n" + 
//				"<nonce_str><![CDATA[zsgdkvytgdgetvcaac8rm9quz69ufdsf]]></nonce_str>\r\n" + 
//				"<openid><![CDATA[oTZWo0Yo7NI5igA6zhZYtjJo9Duk]]></openid>\r\n" + 
//				"<out_trade_no><![CDATA[WXW18011814170214363]]></out_trade_no>\r\n" + 
//				"<result_code><![CDATA[SUCCESS]]></result_code>\r\n" + 
//				"<return_code><![CDATA[SUCCESS]]></return_code>\r\n" + 
//				"<sign><![CDATA[39149EAE7C9A69A7FE88D3FC327ABFC61]]></sign>\r\n" + 
//				"<time_end><![CDATA[20180125141350]]></time_end>\r\n" + 
//				"<total_fee>1</total_fee>\r\n" + 
//				"<trade_type><![CDATA[JSAPI]]></trade_type>\r\n" + 
//				"<transaction_id><![CDATA[4200000098201801250521127901]]></transaction_id>\r\n" + 
//				"</xml>";
//		String string = GetHttpRequestJson.sendPost(urlPath, json);
//		String string = HttpUtil.post(urlPath, json, null).toString();
//		System.out.println(BeautifyJson.formatJson(string));
	}
}
