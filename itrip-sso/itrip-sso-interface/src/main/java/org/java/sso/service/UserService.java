package org.java.sso.service;

import org.java.common.pojo.ItripResult;
import org.java.pojo.User;

public interface UserService {
	/**
	 * 检查数据是否可用
	 * @param param      校验的数据
	 * @param type       类型，可选参数1、2、3分别代表username、phone、email
	 * @return           data: false // 返回数据，true：数据可用，false：数据不可用
	 */
	ItripResult checkData(String param,Integer type);
	/**
	 * 注册
	 * @param user     要注册的用户对象
	 * @return         返回的status是200表示注册成功，400表示注册失败
	 */
	ItripResult register(User user);
	/**
	 * 登录验证
	 * @param username       用户名
	 * @param password       密码
	 * @return				   返回status是200并且data中包含了token，否则返回status是400
	 */
	ItripResult login(String username,String password);
	/**
	 * 通过token查询用户信息
	 * @param token   通行证
	 * @return        status=200并且data中放的是用户对象
	 */
	ItripResult getUserByToken(String token);
	/**
	 * 退出登录
	 * @param token
	 * @return
	 */
	ItripResult logout(String token);
	/**
	 * 激活账号（根据激活码修改用户的state，从0修改为1）
	 * @param code    激活码
	 * @return
	 */
	ItripResult jihuo(String code);
	/**
	 * 发送验证码
	 * @param phone       用户手机号
	 * @return
	 */
	ItripResult sendCode(String phone);
}
