/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.heitian.ssm.utils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author lxb
 * @date 2017/6/5
 * @time 15:35
 */
public class QuartzUtils  {
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory ();
	private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

	static {
		//宕机重启加载没有按时推送的消息
	}

	@SuppressWarnings("unchecked")
	public static void addJob(String jobName, Class cls, Date time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			//			Date runTime = org.quartz.DateBuilder.evenMinuteDate(time);
			// 通过过JobDetail封装HelloJob，同时指定Job在Scheduler中所属组及名称，这里，组名为group1，而名称为job1。
			JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();
			JobDataMap map=jobDetail.getJobDataMap();
			map.put("jobId",1);
			map.put("jobName",jobName);
			map.put("time",time);
			// 创建一个SimpleTrigger实例，指定该Trigger在Scheduler中所属组及名称。
			// 接着设置调度的时间规则.当前时间运行
			//			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", TRIGGER_GROUP_NAME).startAt(time).build();
			//
			//			循环调用；40代表的是毫秒数
						Trigger trigger = TriggerBuilder.newTrigger()
								.withIdentity("trigger1", "group1")
								.startNow()
								.withSchedule(SimpleScheduleBuilder.simpleSchedule()
										              .withIntervalInSeconds(40)
										              .repeatForever())
								.build();

			/**
			 * 如若传入字符串格式
			 */
//			Trigger trigger = TriggerBuilder.newTrigger()
//					.withIdentity("trigger1", "group1")
//					.startNow()
//					.withSchedule(CronScheduleBuilder.cronSchedule ("0 1 20 * * ?"))
//					.build();
			
			//			sched.scheduleJob(jobDetail, trigger);
			sched.scheduleJob (jobDetail,trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 *
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 */
	public static void modifyJobTime(String triggerName,
	                                 String triggerGroupName, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(new TriggerKey (triggerName,triggerGroupName));
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTrigger ct = (CronTrigger) trigger;
				// 修改时间
				//				ct.setCronExpression(time);
				
				// 重启触发器
				sched.resumeTrigger(new TriggerKey (triggerName, triggerGroupName));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static void removeJob(String jobName, String jobGroupName,
	                             String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(new TriggerKey (triggerName,triggerGroupName));// 停止触发器
			sched.unscheduleJob (new TriggerKey (triggerName,triggerGroupName));// 移除触发器
			sched.pauseJob(new JobKey (jobName));//停止任务
			sched.deleteJob(new JobKey (jobName, jobGroupName));// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 开始所有定时任务
	 */
	public static void startJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 关闭所有定时任务
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				System.out.println("关闭");
				sched.shutdown(true);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
