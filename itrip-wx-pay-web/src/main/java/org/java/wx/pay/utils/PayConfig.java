package org.java.wx.pay.utils;


import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

/**
 * 微信支付配置对象
 */
public class PayConfig implements WXPayConfig{

    //获取 App ID（企业方公众号Id）
    public String getAppID() {
        return "wxe63938dcf2c24faf";
    }
    //获取 Mch ID（商户账号）
    public String getMchID() {
        return "1500230872";
    }
    //获取 API 密钥
    public String getKey() {
        return "JpjwyhWoD247nBnyVL4RTadu3dhCbstI";
    }
    //HTTP(S) 连接超时时间，单位毫秒
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }
    //HTTP(S) 读数据超时时间，单位毫秒
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
    //回调URL(该回调的地址一定要是通过微信官方认证的)
    public String getNotifyurl() {
    	return "http://127.0.0.1:8091/callback.action";
    }
	@Override
	//获取商户证书内容（我们这里不需要证书）
	public InputStream getCertStream() {
		return null;
	}
}