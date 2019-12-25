package org.java.seckill.exception;
/**
 *执行秒杀过程中代码出现了异常
 * @author licai
 *
 */
public class SeckillException extends RuntimeException{
	public SeckillException(String message){
		super(message);
	}
	public SeckillException(String message,Throwable throwable){
		super(message,throwable);
	}
}
