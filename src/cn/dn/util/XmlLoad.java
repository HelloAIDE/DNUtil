package cn.dn.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.dn.util.empty.Job;

/**
 * Xml文件解析
 * 
 * @author 大牛哥
 * @E-mail: 201309512@qq.com
 * @date 创建时间：2017年3月6日 下午2:26:12
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class XmlLoad {
	private String url = "http://www.zhipin.com/c101270100-p100101/?ka=sel-city-101270100";
	private String encoding = "utf8";
	private List<Job> list = new ArrayList<Job>();
	String host = "http://www.zhipin.com";
	Map<String, String> cookies;
	Response res = null;
	private String ua = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
	
	public XmlLoad() {
		try {
			res = Jsoup.connect(host).timeout(30000).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cookies= res.cookies();
		cookies.put("wt", "mPFuUkJ2uht7twgs");
	}

	public List<Job> getList() {
		try {
			Document doc = Jsoup.connect(url).cookies(cookies).timeout(30000).header("Referer", "https://www.zhipin.com").userAgent(ua).get();
			try{
				String key = doc.select("input[name=randomKey]").first().val();
				System.out.println(key);
				Element code = doc.select("#captcha").first();
				System.out.println(code.html());
				System.out.println("请输入验证码(https://www.zhipin.com/captcha?randomKey="+key+")：");
				Scanner scan = new Scanner(System.in);
				String cd = scan.nextLine();
				Map<String,String> data = new HashMap<String,String>();
				data.put("randomKey", "4wQSfKG33aswSEwqlV9mVZYHE2G6CMr0");
				data.put("captcha", cd);
				String verifyUrl = "https://www.zhipin.com/captcha/verifyCaptcha?redirect="+url;
				Document post = Jsoup.connect(verifyUrl).cookies(cookies).timeout(30000).data(data).header("Referer", "https://www.zhipin.com").userAgent(ua).post();
				System.out.println(post.html());
				getList();
			}catch(Exception e){
				System.out.println("没有验证码");
			}
			
			Element el = doc.select("div.job-list").first();
			Element ul = el.select("ul").first();
			Elements as = ul.select("li>a");
			for (Element a : as) {
				String url = a.attr("href");
				String jobname = a.select("div.job-primary>div.info-primary>h3.name").first().text();
				String req = a.select("div.job-primary>div.info-primary>p").first().text();
				String salary = a.select("div.job-primary>div.info-primary>h3.name>span").first().text();
				String name = a.select("div.job-primary>div.info-comapny>div.company-text>h3.name").first().text();
				Job job = new Job();
				job.setJobname(jobname);
				job.setName(name);
				job.setReq(req);
				job.setSalary(salary);
				job.setUrl(url);
				
//				System.out.println(job);
				
				list.add(job);
			}
//			System.out.println(ul.text());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public void submit() {
		
		if (list == null) {
			return;
		}
		System.out.println("thread---create");
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (Job job : list) {
					try {
						System.out.println(host+job.getUrl());
						Connection conn = Jsoup
								.connect(host + job.getUrl())
								.header("Referer", "https://www.zhipin.com")
								.cookies(cookies)
								.timeout(30000)
								.userAgent(ua);
						Element doc = conn.get();
				
						Element el  = doc.select("div.btns>a").first();
						String post = el.html();
						System.out.println(post);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();

	}

	public static void main(String[] args) {
		XmlLoad xml = new XmlLoad();
		List<Job> list = xml.getList();
		xml.submit();

	}
}
