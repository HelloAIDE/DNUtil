package cn.dn.util.empty;

import java.io.Serializable;

/**
 * @author  大牛哥 
 * @E-mail: 201309512@qq.com 
 * @date 创建时间：2017年3月6日 下午2:57:38
 * @version 1.0
 * @parameter
 * @since
 * @return  */

public class Job implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String jobname;
	private String salary;
	private String req;
	private String url;
	public Job() {
	}
	
	public Job(String name, String jobname, String salary, String req, String url) {
		super();
		this.name = name;
		this.jobname = jobname;
		this.salary = salary;
		this.req = req;
		this.url = url;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Job [name=" + name + ", jobname=" + jobname + ", salary=" + salary + ", req=" + req + ", url=" + url
				+ "]";
	}

}
