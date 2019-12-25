package org.java.item.listener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.java.item.pojo.ItemDetail;
import org.java.pojo.Item;
import org.java.pojo.ItemDesc;
import org.java.service.HotelDescService;
import org.java.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
public class ItemAddListener implements MessageListener{
	@Autowired
	private HotelService hotelService;
	@Autowired
	private HotelDescService hotelDescService;
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		//获取到message中的内容
		String text = null;
		try {
			text = textMessage.getText();
			Thread.sleep(60000);
			//根据id查询对象
			Item item = hotelService.getById(Long.parseLong(text));
			ItemDetail itemDetail = new ItemDetail(item);
			ItemDesc itemDesc = hotelDescService.getDescByItemId(Long.parseLong(text));
			//生成静态html页面
			//1.创建一个模板文件（F:\itrip\itripworkspace\itrip-item-web\src\main\webapp\WEB-INF\ftl\hello.ftl）
			File file = new File("F:/itrip/itripworkspace/itrip-item-web/src/main/webapp/WEB-INF/ftl/");
			//2.创建一个Configuration对象
			Configuration configuration = new Configuration(Configuration.getVersion());
			//3.设置模板所在的路径
			configuration.setDirectoryForTemplateLoading(file);
			//4.设置模板的字符集(UTF-8)
			configuration.setDefaultEncoding("utf-8");
			//5.使用Configuration对象加载一个模板文件
			Template template = configuration.getTemplate("item.ftl");
			//6.创建一个数据集（数据集就是用来向模板中填充数据的，可以是Map也可以是pojo，实际开发中80%用的是map）
			Map<String,Object> map = new HashMap<String, Object>();
			//#####################################################################################
			map.put("item",itemDetail);
			map.put("itemDesc",itemDesc);
			//#####################################################################################
			//7.创建一个Writer对象，指定输出文件的路径及文件名
			Writer writer = new FileWriter(new File("F:\\itrip\\html\\"+text+".html"));
			//8.使用模板对象的process方法输出文件
			template.process(map,writer);
			//9.关闭流
			writer.close();
		} catch (JMSException e) {
			e.printStackTrace(); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
