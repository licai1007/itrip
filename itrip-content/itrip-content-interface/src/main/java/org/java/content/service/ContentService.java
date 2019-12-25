package org.java.content.service;

import java.util.List;

import org.java.common.pojo.Ad;
import org.java.common.pojo.EasyUIDataGrideResult;
import org.java.common.pojo.ItripResult;
import org.java.pojo.Content;

public interface ContentService {
	/**
	 * 根据分类id查询分类内容信息列表
	 * @param categoryid    分类id
	 * @param pageNow       当前页码
	 * @param pageSize      每页显示的信息条数
	 * @return              easyUI对象
	 */
	EasyUIDataGrideResult getContents(int categoryid,int pageNow,int pageSize);
	/**
	 * 根据内容id删除内容
	 * @param ids   内容id的数组
	 * @return
	 */
	ItripResult deleteContents(int[] ids);
	/**
	 * 添加内容对象
	 * @param content
	 * @return
	 */
	ItripResult saveContent(Content content);
	/**
	 * 修改内容对象
	 * @param content
	 * @return
	 */
	ItripResult updateContent(Content content);
	//首页大广告位的展示(categoryid=89)
	List<Ad> getAd1();
}
