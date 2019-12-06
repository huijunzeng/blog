package com.teeya.user.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * http请求相关工具类
 * @author Administrator
 *
 */
public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	/**
	 * 请求公共类
	 * @param urlIn 请求路径
	 * @param params 参数
	 * @param requestMethod 请求方式 post get
	 * @return
	 */
	public static String sengRequest(String urlIn,String params,String requestMethod){
		
		logger.info("HttpClientUtils_sengRequest param:urlIn:params:requestMethod{}"+urlIn+":"+params+":"+requestMethod);
		String result="";
	     HttpURLConnection httpURLConnection = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            URL url = new URL(urlIn);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type","application/json;charset=utf-8");
            httpURLConnection.setRequestProperty("Accept-Charset","UTF-8");
//            httpURLConnection.setRequestProperty("contentType","UTF-8");
            //发送POST请求参数
            out = new PrintWriter(httpURLConnection.getOutputStream());
            out.write(params);
            out.flush();
         //读取响应
            logger.info("httpURLConnection.getResponseCode()"+httpURLConnection.getResponseCode());
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuffer content = new StringBuffer();
                String tempStr = null;
                in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while ((tempStr=in.readLine()) != null){
                    content.append(tempStr);
                }
                result=content.toString();
                logger.info("HttpClientUtils_sengRequest{}result:"+result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("HttpClientUtils_sengRequest{}Exception:result:error"+result+":"+e.getMessage());
        }finally {
            if (out != null) {
                out.close();
            }
            httpURLConnection.disconnect();
        }
		return result;
	}
	 /**
     * 向指定 URL 发送POST方法的请求     
     * @param url 发送请求的 URL    
     * @param params 请求的参数集合     
     * @return 远程资源的响应结果
     */
	public static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;        
        StringBuilder result = new StringBuilder(); 
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //conn.setRequestProperty("kdniao-nocache", "true");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数            
            if (params != null) {
		          StringBuilder param = new StringBuilder(); 
		          for (Map.Entry<String, String> entry : params.entrySet()) {
		        	  if(param.length()>0){
		        		  param.append("&");
		        	  }	        	  
		        	  param.append(entry.getKey());
		        	  param.append("=");
		        	  param.append(entry.getValue());		        	  
		        	  System.out.println(entry.getKey()+":"+entry.getValue());
		          }
		          System.out.println("param:"+param.toString());
		          out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {            
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return 返回微信服务器响应的信息
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
    	
    	 HttpURLConnection connection = null;
         InputStream is = null;
         OutputStream os = null;
         BufferedReader br = null;
         String result = null;
         try {
             URL url = new URL(requestUrl);
             // 通过远程url连接对象打开连接
             connection = (HttpURLConnection) url.openConnection();
             // 设置连接请求方式
             connection.setRequestMethod(requestMethod);
             // 设置连接主机服务器超时时间：15000毫秒
             connection.setConnectTimeout(15000);
             // 设置读取主机服务器返回数据超时时间：60000毫秒
             connection.setReadTimeout(60000);

             // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
             connection.setDoOutput(true);
             // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
             connection.setDoInput(true);
             // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
             connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
             // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
             connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
             // 通过连接对象获取一个输出流
             os = connection.getOutputStream();
             // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
             os.write(outputStr.getBytes());
             // 通过连接对象获取一个输入流，向远程读取
             if (connection.getResponseCode() == 200) {

                 is = connection.getInputStream();
                 // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                 br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                 StringBuffer sbf = new StringBuffer();
                 String temp = null;
                 // 循环遍历一行一行读取数据
                 while ((temp = br.readLine()) != null) {
                     sbf.append(temp);
                     sbf.append("\r\n");
                 }
                 result = sbf.toString();
                 logger.info("====以post请求的方式调用统一下单接口|微信结果返回：返回报文：===="+result);
             }
         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             // 关闭资源
             if (null != br) {
                 try {
                     br.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if (null != os) {
                 try {
                     os.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if (null != is) {
                 try {
                     is.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             // 断开与远程地址url的连接
             connection.disconnect();
         }
         return result;
    }
    /**
     * 调用对方接口方法
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static String interfaceUtil(String path,String data) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            
            /**设置URLConnection的参数和普通的请求属性****start***/
           
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"); 
            
            /**设置URLConnection的参数和普通的请求属性****end***/
            
            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            conn.setRequestMethod("POST");//GET和POST必须全大写
            
            /***POST方法请求****start*/
            
           out = new PrintWriter(conn.getOutputStream());//获取URLConnection对象对应的输出流 
          
            out.print(data);//发送请求参数即数据
           
            out.flush();//缓冲数据
                       
            /***POST方法请求****end*/
            
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
            	str=new String(str.getBytes(),"UTF-8");//解决中文乱码问题
                System.out.println(str);
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param url 接口地址
     * @param params 请求参数
     * @return
     */
    public static String getWebServiceData(String url,String params) throws IOException {
        //接受返回报文
        String result = new String();
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setDoInput(true);
        //允许对外输出数据
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setDefaultUseCaches(false);
        conn.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
        conn.setRequestMethod("POST");
        //定义输出流
        OutputStream output = conn.getOutputStream();
        if(null != params){
            byte[] b = params.getBytes("utf-8");
            //发送soap请求报文
            output.write(b,0,b.length);
            output.flush();
            output.close();
            //定义输入流，获取soap报文
            InputStream input = conn.getInputStream();
            //设置编码格式
            result = IOUtils.toString(input,"UTF-8");
            input.close();
        }
        System.out.println("请求返回报文：" + result);
        return result;
    }

   
    /*public static void main(String[] args) {
//    	 InCommon inCommon=new InCommon("192.168.0.132","19999","http","内置服务器");
//		 InInsertUserParamDto inInsertUserParamDto=BeanConverter.copy(inCommon, InInsertUserParamDto.class);
//		 if(inInsertUserParamDto!=null) {
//			 inInsertUserParamDto.setInvitationCode("000000000000");
//			 inInsertUserParamDto.setUserLoginId("000000");
//			 logger.info("HisGateWayService_InInsertUser inInsertUserParamDto{}",JsonUtils.toJson(inInsertUserParamDto));
//			// String url=InUtils.request_url+InUtils.user_methodName;
//			  String url="http://yishantong.f3322.net:19999/serverip/insertDoctor";
//			 logger.info("HisGateWayService_InInsertUser url{}",url);
//			 String result=sengRequest(url, JsonUtils.toJson(inInsertUserParamDto),"POST");
//			 logger.info("HisGateWayService_InInsertUser result{}",result);
//		 }
		 InCommon inCommon=new InCommon("192.168.0.132","19999","http","内置服务器");
		 InInsertPatParamDto inInsertPatParamDto=BeanConverter.copy(inCommon, InInsertPatParamDto.class);
		 if(inInsertPatParamDto!=null) {
			 inInsertPatParamDto.setMobile("111122222");
			 inInsertPatParamDto.setOpenid("");
			 logger.info("HisGateWayService_InInsertPat inInsertPatParamDto{}",JsonUtils.toJson(inInsertPatParamDto));
			  String url="http://yishantong.f3322.net:19999/serverip/insertPatuser";
		 logger.info("HisGateWayService_InInsertUser url{}",url);
			 String result=sengRequest(url, JsonUtils.toJson(inInsertPatParamDto),"POST");
			 logger.info("HisGateWayService_InInsertPat result{}",result);
		 }
    }*/
}
