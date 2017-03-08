package cn.dn.util.spider.bean;

import java.io.Serializable;

/**
 * 数据实体类
 * 
 * @author 大牛哥
 * @E-mail: 201309512@qq.com
 * @date 创建时间：2017年3月7日 上午12:19:47
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class LinkData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	/**
	 * 链接的地址
	 */
	private String linkHref;
	/**
	 * 链接的标题
	 */
	private String linkText;
	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 内容
	 */
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLinkHref() {
		return linkHref;
	}

	public void setLinkHref(String linkHref) {
		this.linkHref = linkHref;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
