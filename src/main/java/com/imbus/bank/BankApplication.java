package com.imbus.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.imbus.bank.*"})
@SpringBootApplication
public class BankApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
}

// todo:
/*
	1、尾箱管理
	2、所有判断现金改为判断尾箱
	3、个人业务开户从尾箱读取银行卡
 */
/*
	利率查询，设置
 	交易流水查询
 	尾箱管理
 	现金管理
 	excel下载
 	头像修改
 	轧账
 	日志查询
 */