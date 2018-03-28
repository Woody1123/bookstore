package test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;

import org.junit.Test;

public class Demo1 {
	@Test
	public void fun1(){
		/*
		 * 包含了占位符的字符串就是模版
		 * 占位符：{0}、{1}、{2}
		 * 可变参数，需要指定模版中的占位符的值，有几个占位符就要提供几个参数
		 */
		String s = MessageFormat.format("{0}或{1}错误", "用户名","密码","");
		System.out.println(s);
	}
	
	@Test
	public void fun2(){
		System.out.println(2.0-1.1);//0.8999999999999999
	}
	/**
	 * 100的阶乘
	 */
	@Test
	public void fun3(){
		BigInteger sum = BigInteger.valueOf(1);
		for(int i = 1;i <= 100; i++){
			BigInteger bi = BigInteger.valueOf(i);
			sum = sum.multiply(bi);
		}
		System.out.println(sum);
	}
	/**
	 * BigDecimal
	 * 可以处理二进制运算导致的误差
	 */
	@Test
	public void fun4(){
		/*
		 * 1、创建BigDecimal对象时，必须使用String构造器
		 */
		BigDecimal d1 = new BigDecimal("2.0");
		BigDecimal d2 = new BigDecimal("1.1");
		BigDecimal d3 = d1.subtract(d2);
		System.out.println(d3);
	}
	/*
	 * https:/www.yeepay.com/app-merchant-proxy/node?p0_Cmd=Buy&p1_MerId=10001126856&p2_Order=123456&p3_Amt=100&p4_Cur=CNY&p5_Pid=&p6_Pcat=&p7_Pdesc=&p8_Url=http://localhost:8080/bookstore/OrderServlet?method=back&p9_SAF=&pa_MP=&pd_FrpId=ICBC-NET-B2C&pr_NeedResponse=1&hmac=008e95ff1b1928d243e71a47ac48790c
	 */
	@Test
	public void fun5(){
		String hmac = PaymentUtil.buildHmac("Buy", "10001126856", "123456", "100", "CNY", 
				"", "", "", "http://localhost:8080/bookstore/OrderServlet?method=back", 
				"", "", "ICBC-NET-B2C","1", "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
		System.out.println(hmac);
	}
}

