package cn.dn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author  大牛哥 
 * @E-mail: 201309512@qq.com 
 * @date 创建时间：2017年3月2日 下午8:49:21
 * @version 1.0
 * @parameter
 * @since
 * @return  */

public class DataDownUtil {
	/**
	 * 获取网页源码
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String getHtmlResourceByUrl(String url,String encoding){
		URL urlObj= null;
		URLConnection uc = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			//建立网络连接
			urlObj = new URL(url);
			uc = urlObj.openConnection();
			isr = new InputStreamReader(uc.getInputStream(),encoding);	
			br = new BufferedReader(isr);
			String temp;
			sb = new StringBuffer();
			while((temp=br.readLine())!=null){
				sb.append(temp+"\n");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				isr.close();
				br.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return sb.toString();
	}
}
