package org.java.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.java.item.pojo.ItemDetail;
import org.java.pojo.ItemDesc;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestFreemarker {
	@Test
	public void helloWorld() throws Exception{
		//1.创建一个模板文件（F:\itrip\itripworkspace\itrip-item-web\src\main\webapp\WEB-INF\ftl\hello.ftl）
		File file = new File("F:/itrip/itripworkspace/itrip-item-web/src/main/webapp/WEB-INF/ftl/");
		//2.创建一个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		//3.设置模板所在的路径
		configuration.setDirectoryForTemplateLoading(file);
		//4.设置模板的字符集(UTF-8)
		configuration.setDefaultEncoding("utf-8");
		//5.使用Configuration对象加载一个模板文件
		Template template = configuration.getTemplate("hello.ftl");
		//6.创建一个数据集（数据集就是用来向模板中填充数据的，可以是Map也可以是pojo，实际开发中80%用的是map）
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("hello","Hello Freemarker");
		//7.创建一个Writer对象，指定输出文件的路径及文件名
		Writer writer = new FileWriter(new File("E:\\hello.html"));
		//8.使用模板对象的process方法输出文件
		template.process(map,writer);
		//9.关闭流
		writer.close();
	}
	@Test
	public void testStudent() throws Exception{
		//1.创建一个模板文件（F:\itrip\itripworkspace\itrip-item-web\src\main\webapp\WEB-INF\ftl\hello.ftl）
		File file = new File("F:/itrip/itripworkspace/itrip-item-web/src/main/webapp/WEB-INF/ftl/");
		//2.创建一个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		//3.设置模板所在的路径
		configuration.setDirectoryForTemplateLoading(file);
		//4.设置模板的字符集(UTF-8)
		configuration.setDefaultEncoding("utf-8");
		//5.使用Configuration对象加载一个模板文件
		Template template = configuration.getTemplate("student.ftl");
		//6.创建一个数据集（数据集就是用来向模板中填充数据的，可以是Map也可以是pojo，实际开发中80%用的是map）
		Map<String,Object> map = new HashMap<String, Object>();
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("1001","王宝强","18"));
		list.add(new Student("1002","马蓉","19"));
		list.add(new Student("1003","宋喆","20"));
		//引用页面
		map.put("hello","Hello Freemarker");
		//list添加到数据集中
		map.put("mylist",list);
		//日期
		map.put("mydate",new Date());
		//7.创建一个Writer对象，指定输出文件的路径及文件名
		Writer writer = new FileWriter(new File("E:\\student.html"));
		//8.使用模板对象的process方法输出文件
		template.process(map,writer);
		//9.关闭流
		writer.close();
	}
	@Test
	public void testItem() throws Exception{
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
		ItemDetail item = new ItemDetail();
		item.setId(1l);
		item.setTitle("测试freemarker");
		item.setSellpoint("快速测试成功");
		item.setPrice(new BigDecimal(100));
		item.setImage("C:/Users/Public/Pictures/Sample Pictures/Chrysanthemum.jpg,C:/Users/Public/Pictures/Sample Pictures/Koala.jpg");
		map.put("item",item);
		ItemDesc itemDesc = new ItemDesc();
		map.put("itemDesc",itemDesc);
		//7.创建一个Writer对象，指定输出文件的路径及文件名
		Writer writer = new FileWriter(new File("E:\\item.html"));
		//8.使用模板对象的process方法输出文件
		template.process(map,writer);
		//9.关闭流
		writer.close();
	}
}
