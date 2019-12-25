package org.java.service;

import org.java.common.pojo.EasyUIDataGrideResult;
import org.java.common.pojo.ItripResult;
import org.java.pojo.Item;

public interface HotelService {
	/**
	 * 根据编号查对象
	 * @param id   酒店的编号
	 * @return     酒店对象
	 */
	Item getById(Long id);
	/**
	 * 分页查询
	 * @param pageNow      当前要展示的页码
	 * @param pageSize     每页显示的信息条数
	 * @return             封装好的对象
	 */
	EasyUIDataGrideResult getHotelList(Integer pageNow,Integer pageSize);
	/**
	 * 添加酒店对象
	 * @param item
	 * @return
	 */
	ItripResult saveHotel(Item item);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	ItripResult deleteHotel(long[] ids);
	/**
	 * 下架
	 * @param ids
	 * @return
	 */
	ItripResult xiaJia(long[] ids);
	/**
	 * 上架
	 * @param ids
	 * @return
	 */
	ItripResult shangJia(long[] ids);
	/**
	 * 根据编号修改商品对象
	 * @param item
	 * @return
	 */
	ItripResult update(Item item);
}
