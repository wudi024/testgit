package com.ck.aopTest.utils;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 重写log日志
 * 
 * @see:
 * @Company:江苏鸿信系统集成有限公司微信开发组
 * @author 杨坚
 * @Time 2016年10月3日
 * @version 1.0v
 */
public class LogAppender extends DailyRollingFileAppender {

	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		// 只判断是否相等，而不判断优先级
		return this.getThreshold().equals(priority);
	}
}
