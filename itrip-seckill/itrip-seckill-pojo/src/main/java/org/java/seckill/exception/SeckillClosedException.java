package org.java.seckill.exception;
/**
 *不在秒杀时间内异常
 * @author licai
 *
 */
public class SeckillClosedException extends RuntimeException{
	public SeckillClosedException(String message){
		super(message);
	}
	public SeckillClosedException(String message,Throwable throwable){
		super(message,throwable);
	}
}
