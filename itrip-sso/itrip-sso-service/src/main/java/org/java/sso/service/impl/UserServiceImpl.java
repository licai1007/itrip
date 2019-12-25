package org.java.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.java.code.util.SendSms;
import org.java.common.pojo.ItripResult;
import org.java.common.util.JsonUtils;
import org.java.mapper.UserMapper;
import org.java.pojo.User;
import org.java.pojo.UserCriteria;
import org.java.pojo.UserCriteria.Criteria;
import org.java.redis.JedisClient;
import org.java.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private MailSender mailSender;
	@Value("${USER_SESSION}")
	private String USER_SESSION;
	@Value("${SESSION_EXPIRE}")
	private int SESSION_EXPIRE;
	@Value("${PHONE_CODE}")
	private String PHONE_CODE;
	@Value("${CODE_EXPIRE}")
	private int CODE_EXPIRE;
	@Override
	public ItripResult checkData(String param, Integer type) {
		//类型，可选参数1、2、3分别代表username、phone、email
		UserCriteria example = new UserCriteria();
		Criteria criteria = example.createCriteria();
		//做条件判断
		if(type == 1){
			criteria.andUsernameEqualTo(param);
		}else if(type == 2){
			criteria.andPhoneEqualTo(param);
		}else if(type == 3){
			criteria.andEmailEqualTo(param);
		}else{
			return ItripResult.build(400,"请求的参数错误");
		}
		//执行查询
		List<User> list = userMapper.selectByExample(example);
		//判断是否查询到了结果
		if(list != null && list.size() > 0){
			//表示要查询的数据在数据库中是存在的
			return ItripResult.ok(false);
		}
		return ItripResult.ok(true);
	}
	@Override
	public ItripResult register(User user) {
		//判断用户名是否为空
		if(StringUtils.isBlank(user.getUsername())){
			return ItripResult.build(400,"注册失败. 用户名不能为空");
		}
		//验证是否可以注册
		ItripResult result = checkData(user.getUsername(),1);
		if((boolean)result.getData() == false){
			//表示不能注册
			return ItripResult.build(400,"注册失败. 用户名已经存在");
		}
		if(StringUtils.isBlank(user.getPhone())){
			return ItripResult.build(400,"注册失败. 电话不能为空");
		}
		result = checkData(user.getPhone(),2);
		if((boolean)result.getData() == false){
			//表示不能注册
			return ItripResult.build(400,"注册失败. 手机号已经存在");
		}
		if(StringUtils.isBlank(user.getEmail())){
			return ItripResult.build(400,"注册失败. 邮箱不能为空");
		}
		result = checkData(user.getEmail(),3);
		if((boolean)result.getData() == false){
			//表示不能注册
			return ItripResult.build(400,"注册失败. 邮箱已经存在");
		}
		//判断验证码是否为空
		if(StringUtils.isBlank(user.getValicode())){
			return ItripResult.build(400,"验证码不能为空");
		}
		//判断验证码是否正确
		String valicode = jedisClient.get(PHONE_CODE+user.getPhone());
		if(!valicode.equals(user.getValicode())){
			return ItripResult.build(400,"验证码不正确");
		}
		//-----------------------下面表示合法的-----------------------
		//生成一个激活码
		String code = UUID.randomUUID().toString();//随机生成一个激活码
		user.setCode(code);
		//需要对密码进行md5加密
		String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(pwd);
		user.setState(0);//刚注册的账号默认是未激活
		user.setCreated(new Date());
		user.setUpdated(new Date());
		userMapper.insertSelective(user);
		//-----------------------注册成功后-----------------------
		//将生成的激活码发送一封邮件到注册的邮箱
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom("254522287@qq.com");
		simpleMessage.setTo(user.getEmail());//注册的邮箱
		simpleMessage.setSubject("欢迎注册爱旅行");
		//内容实际上就是将用户状态从0更改为1的url
		simpleMessage.setText("http://localhost:8084/user/jihuo?code="+code);
		mailSender.send(simpleMessage);
		return ItripResult.ok();
	}
	@Override
	public ItripResult login(String username, String password) {
		UserCriteria example = new UserCriteria();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isBlank(username)){
			return ItripResult.build(400,"用户名不能为空");
		}
		//添加查询条件
		criteria.andUsernameEqualTo(username);
		//根据用户名进行查询
		List<User> list = userMapper.selectByExample(example);
		if(list == null || list.size() == 0){
			//用户名是不存在的
			return ItripResult.build(400,"用户名不存在");
		}
		//能到达这一步表示用户名是存在的
		User user = list.get(0);
		if(StringUtils.isBlank(password)){
			return ItripResult.build(400,"密码不能为空");
		}
		if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
			//表示密码错误
			return ItripResult.build(400,"密码错误");
		}
		//用户名和密码正确
		//判断用户的状态是否是可登录状态（state:0表示未激活，1表示已经激活）
		if(user.getState()==0){
			//表示用户未激活账号
			return ItripResult.build(400,"对不起，您的账号未激活");
		}
		//生成一个token
		String token = UUID.randomUUID().toString();
		//1.将用户信息放入到redis中
		jedisClient.set(USER_SESSION+token,JsonUtils.objectToJson(user));
		//设置存在的时间，设置时间为半小时
		jedisClient.expire(USER_SESSION+token,SESSION_EXPIRE);
		//2.将token放入到cookie中（可以在controller中写入）
		return ItripResult.ok(token);
	}
	@Override
	public ItripResult getUserByToken(String token) {
		//从redis中查询key-token
		String json = jedisClient.get(USER_SESSION+token);
		//判断key是否存在
		if(StringUtils.isNotBlank(json)){
			//表示存在，需要重新设置session存在的时间
			jedisClient.expire(USER_SESSION+token,SESSION_EXPIRE);
			//将json转换成对象
			User user = JsonUtils.jsonToPojo(json,User.class);
			//密码应该设置为null
			user.setPassword(null);
			return ItripResult.ok(user);
		}
		//表示不存在
		return ItripResult.build(400,"session已经过期");
	}
	@Override
	public ItripResult logout(String token) {
		//从redis中移除
		jedisClient.expire(USER_SESSION+token,0);
		return ItripResult.ok();
	}
	@Override
	public ItripResult jihuo(String code) {
		UserCriteria example = new UserCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		//根据激活码查询用户
		List<User> list = userMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			//表示查询到了
			User user = list.get(0);
			//修改用户状态
			user.setState(1);
			user.setCode("");//一旦激活后，激活码就让它消失
			//修改用户状态为激活状态
			userMapper.updateByPrimaryKeySelective(user);
			return ItripResult.ok();
		}
		return ItripResult.build(400,"激活码不存在");
	}
	@Override
	public ItripResult sendCode(String phone) {
		//生成四位随机数作为验证码
		int code=(int)(Math.random()*9000)+1000;
		//将验证码放入redis中
		jedisClient.set(PHONE_CODE+phone,String.valueOf(code));
		//验证码有效期为5分钟
		jedisClient.expire(PHONE_CODE+phone,CODE_EXPIRE);
		//发送验证码到手机
		String method = "sendSMS"; //值为sendSMS发送短信
		String username = "24957907_1"; //明文用户名
	    String password = "5n5blrbk"; //密码
		String mobile = phone; //手机号码采用英文状态下逗号,分割, 最大2万个号码
		String content = "【爱旅行】您的验证码为："+code+"，请不要告诉别人！";//为发送内容
		String isLongSms = "0"; //0-普通短信 1-加长短信
		String extenno = ""; //为通道扩展子号码，可以为空
		String parm = "method="+method+"&username="+username+"&password="+password+"&mobile="+mobile+"&content="+content+"&isLognSms="+isLongSms+"&extenno="+extenno;
		String host = "http://sms.smsyun.cc:9012/servlet/UserServiceAPIUTF8";
		//返回值：如果成功返回success;批号ID   否则返回failure;错误提示
        String sr = SendSms.sendPost(host, parm);
        System.out.println(sr);
		return ItripResult.ok();
	}

}
