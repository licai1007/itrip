package org.java.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.java.common.pojo.ItripResult;
import org.java.common.util.CookieUtils;
import org.java.common.util.JsonUtils;
import org.java.pojo.Item;
import org.java.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//在编码的时候我们都喜欢叫Controller，在说原理的时候往往都称之为handler
@Controller
public class CartController {
	@Autowired
	private HotelService hotelService;
	@Value("${ITRIP_CART}")
	private String ITRIP_CART;
	@Value("${CART_TIME}")
	private int CART_TIME;

	/**
	 * 添加到购物车中
	 * 
	 * @param itemId
	 * @param num
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}")
	public String addToCart(
			@PathVariable("itemId") long itemId,
			@RequestParam(value = "num", required = true, defaultValue = "1") int num,
			HttpServletRequest request, HttpServletResponse response) {
		// 1.从cookie取出购物车（存在或者是不存在）
		List<Item> list = getCartList(request);
		// 2.添加商品到购物车（如果是该商品已经存在，那么只需要更新数量，如果不存在那么添加到购物车中）
		boolean isExist = false;
		for (Item item : list) {
			if (item.getId() == itemId) {
				// 要添加的商品是在购物车中已经存在的
				// 只需要修改数量就行了
				item.setNum(item.getNum() + num);
				isExist = true; // 表示要添加的是存在的
				// 修改数量后结束循环
				break;
			}
		}
		if (!isExist) {
			// 表示要添加的商品是不存在
			Item item = hotelService.getById(itemId);
			item.setNum(num); // 存储购买的数量
			//肯定是从无到有，那么我们就在第一次添加的时候就设置好要显示的图片
			String image = item.getImage();
			if(StringUtils.isNotBlank(image)){
				//按照逗号分隔
				String[] images = image.split(",");
				item.setImage(images[0]);
			}
			list.add(item);
		}
		// 3.添加完成后需要将购物车重新放入到cookie中
		CookieUtils.setCookie(request, response, ITRIP_CART,
				JsonUtils.objectToJson(list), CART_TIME, true);
		return "cartSuccess";
	}

	/**
	 * 获取到购物车中的商品列表
	 * 
	 */
	public List<Item> getCartList(HttpServletRequest request) {
		// 从cookie取出购物车（存在或者是不存在）
		String json = CookieUtils.getCookieValue(request, ITRIP_CART, true);
		List<Item> list = new ArrayList<Item>();
		if (StringUtils.isNoneBlank(json)) {
			// 取出了购物车
			// 将json格式的字符串转换成集合
			list = JsonUtils.jsonToList(json, Item.class);
		}
		return list;
	}

	@RequestMapping("/cart/cart")
	public String showCart(Model model,HttpServletRequest request) {
		// 从cookie取出购物车（存在或者是不存在）
		List<Item> list = getCartList(request);
		// 展示购物车中的商品信息
		model.addAttribute("cartList",list);
		return "cart";
	}
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public ItripResult updateNum(@PathVariable("itemId") long itemId,@PathVariable("num") Integer num,
			HttpServletRequest request,HttpServletResponse response){
		//修改购物车中的商品数量
		//1.取出购物车
		List<Item> list = getCartList(request);
		//2.遍历
		for (Item item : list) {
			//查找要修改的商品
			if(item.getId() == itemId){
				//修改数量
				item.setNum(num);
				break;//因为商品是不重复的，所以修改完一个后就结束循环。
			}
		}
		//3.更新到cookie中
		CookieUtils.setCookie(request, response, ITRIP_CART,
				JsonUtils.objectToJson(list), CART_TIME, true);
		return ItripResult.ok();
	}
	
	@RequestMapping("/cart/delete/{cartId}")
	public String delete(@PathVariable("cartId") long cartId,HttpServletRequest request,
			HttpServletResponse response){
		//1.取出购物车
		List<Item> list = getCartList(request);
		//2.遍历
		for (Item item : list) {
			if(item.getId() == cartId){
				//删除
				list.remove(item);
				break;
			}
		}
		//3.更新到cookie中
		CookieUtils.setCookie(request, response, ITRIP_CART,
				JsonUtils.objectToJson(list), CART_TIME, true);
		return "redirect:/cart/cart";
	}

}
