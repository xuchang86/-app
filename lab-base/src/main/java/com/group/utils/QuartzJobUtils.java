package com.group.utils;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzJobUtils {
	
	/**
	 * 添加定时任务
	 * @param jobName
	 * @param cls
	 * @param time
	 * @param jobDataMap
	 * @throws Exception 
	 */
	public static void addJob(String jobName, Class<?> cls, String time,JobDataMap jobDataMap) throws Exception {
		StdSchedulerFactory scheduler = new StdSchedulerFactory();
		Scheduler sched = scheduler.getScheduler();
		//先删除旧的同名的定时任务
		sched.deleteJob(jobName, Scheduler.DEFAULT_GROUP);
		
		JobDetail jobDetail = new JobDetail(jobName,Scheduler.DEFAULT_GROUP, cls);// 任务名，任务组，任务执行类
		jobDataMap.put("sched", sched);
		jobDetail.setJobDataMap(jobDataMap);
		// 触发器
		CronTrigger trigger = new CronTrigger(jobName,Scheduler.DEFAULT_GROUP);// 触发器名,触发器组
		trigger.setCronExpression(time);// 触发器时间设定
		sched.scheduleJob(jobDetail, trigger);
		// 启动
		if (!sched.isShutdown()) {
			sched.start();
		}
	}

}
