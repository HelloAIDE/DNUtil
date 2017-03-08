package cn.dn.util.spider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.dn.util.spider.bean.LinkData;
import cn.dn.util.spider.exception.RuleException;

/**
 * @author 大牛哥
 * @E-mail: 201309512@qq.com
 * @date 创建时间：2017年3月7日 上午12:21:17
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class SpiderService {
	/**
	 * @param rule
	 * @return
	 * @throws IOException 
	 */
	public static List<LinkData> extract(Rule rule) throws IOException {
		String host = "";
		// 进行对rule的必要校验
		validateRule(rule);

		List<LinkData> datas = new ArrayList<LinkData>();
		LinkData data = null;
			/**
			 * 解析rule
			 */
			String url = rule.getUrl();
			host = new URL(url).getHost();
			System.out.println("host:" + host);
			String[] params = rule.getParams();
			String[] values = rule.getValues();
			String resultTagName = rule.getResultTagName();
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();
			Connection conn = Jsoup.connect(url).header("Upgrade-Insecure-Requests", "0").header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").userAgent(
					"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			// 设置查询参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values[0]);
				}
			}
			// 设置请求类型
			Document doc = null;
			switch (requestType) {
			case Rule.GET:
				doc = conn.timeout(100000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(100000).post();
				break;
			}
			// 处理返回数据
			Elements results = new Elements();
			switch (type) {
			case Rule.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				// 当resultTagName为空时默认去body标签
				if (resultTagName.isEmpty()) {
					results = doc.getElementsByTag("body");
				}
			}
			// System.out.println(results.html());
			for (Element result : results) {
				Elements links = result.select("a");
				for (Element link : links) {
					// 必要的筛选
					String linkHref = link.attr("href");
					String linkText = link.text();
					if (!linkHref.startsWith("http")) {
						if (linkHref.startsWith("/")) {
							linkHref = "http://" + host + linkHref;
						} else {
							linkHref = "http://" + host + "/" + linkHref;
						}

					}
					data = new LinkData();
					data.setLinkHref(linkHref);
					data.setLinkText(linkText);

					datas.add(data);
				}
			}

		return datas;
	}

	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule) {
		String url = rule.getUrl();
		if (url.isEmpty()) {
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://")) {
			throw new RuleException("url的格式不正确！");
		}

		if (rule.getParams() != null && rule.getValues() != null) {
			if (rule.getParams().length != rule.getValues().length) {
				throw new RuleException("参数的键值对个数不匹配！");
			}
		}

	}

}