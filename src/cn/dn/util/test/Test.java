package cn.dn.util.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cn.dn.util.spider.Rule;
import cn.dn.util.spider.SpiderService;
import cn.dn.util.spider.bean.LinkData;

/**
 * @author 大牛哥
 * @E-mail: 201309512@qq.com
 * @date 创建时间：2017年3月7日 上午12:25:44
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class Test {
	private static int sleepTime = 10;
	private static int page = 999;
	public static void main(String[] args) throws Exception {
		System.out.println("开始爬取数据");
		for (int i = 1; i < page; i++) {
			System.out.println("\n\n\n\n\n\n\n\n");
			System.out.println("------------------------正在抓取第"+ i+"页-------------------------------\n\n");
			
			Rule rule = new Rule("http://www.xiaodao.la/index_sc.asp", new String[] { "page" }, new String[] { "" + i },
					"div.BiaoTiZiShiYing", Rule.SELECTION, Rule.GET);
			try{
				List<LinkData> extracts = SpiderService.extract(rule);
				printf(extracts);
				System.out.println("------------------------第"+ i+"页抓取完毕-------------------------------");
				sleep(sleepTime);
			}catch(Exception e){
				e.printStackTrace();
				sleep(50);
			}
		}
		System.out.println("数据抓取完成");
	}

	public static void printf(List<LinkData> datas) {
		String path = "E:/output/xiaodao.txt";
		File file = new File(path);
		if (!file.exists()) {
			File pf = file.getParentFile();
			System.out.println(pf.getPath());
			pf.mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (LinkData data : datas) {
			System.out.println(data.getLinkText());
			System.out.println(data.getLinkHref());
			System.out.println("***********************************");
			String content = data.getLinkText();
			String temp = "";
			System.out.println("length:"+content.toCharArray().length);
			for (int i = 0; i < 30 - content.toCharArray().length; i++) {
				temp += "--";
			}
			content+=temp+=data.getLinkHref()+"\n\n";
			
			try {
				// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
				FileWriter writer = new FileWriter(path, true);
				writer.write(content);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void clear() throws AWTException {

		// Robot r = new Robot();
		// r.mousePress(InputEvent.BUTTON3_MASK); // 按下鼠标右键
		// r.mouseRelease(InputEvent.BUTTON3_MASK); // 释放鼠标右键
		// r.keyPress(KeyEvent.VK_CONTROL); // 按下Ctrl键
		// r.keyPress(KeyEvent.VK_R); // 按下R键
		// r.keyRelease(KeyEvent.VK_R); // 释放R键
		// r.keyRelease(KeyEvent.VK_CONTROL); // 释放Ctrl键
		// r.delay(100);

	}
	public static void sleep(int time) throws InterruptedException{
		for (int j = 0; j < time; j++) {
			Thread.sleep(1000L);
			System.out.println("倒计时：" + (time - j) + "秒");

		}
	}
}
