/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.heitian.ssm.utils.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * 〈消息推送服务〉 <p>
 * 〈
 *  调用消息推送业务
 *  〉
 * @author lxb
 * @date 2017/6/5
 * @time 16:01
 */
public class MsgPushJob implements Job {
	
	@Override
	public void execute (JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("执行时间："+new Date ());
		JobDataMap map=jobExecutionContext.getJobDetail().getJobDataMap();
		for(String key:map.keySet()){
			System.out.println("参数："+map.get(key));
		}
	}
}
