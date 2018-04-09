package com.moyou.moyouRms.controller.task;

import java.util.Map;

import javax.annotation.Resource;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.task.ScheduleJobService;
import com.moyou.moyouRms.service.task.TaskSetingService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * @describe 后台定时任务启动使用的controller
 * @author liuxinyi
 * @date 2017年3月2日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "h5token/task")
public class TaskController extends BaseController {
	@Resource
	private UserService userService;
	@Resource
	private ScheduleJobService scheduleJobService;
	@Resource
	private TaskSetingService taskSetingService;
	@Autowired
	private Scheduler scheduler;
	/**
	 * 初始化群总数和今日新增群数
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/talk_praise", method = RequestMethod.POST)
	public @ResponseBody ApiResult talk_praise() {
		ApiResult ar = new ApiResult(ResponseEnum.SUCCESS);
		return ar;
	}

	/*
	 * 
	 * 初始化机器人设置
	 */
	@RequestMapping(value = "/queryTaskSetingList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTaskSetingList() {
		return new ApiResult(RESPONSE.SUCCESS, "请求成功",
				taskSetingService.queryTaskSetingList());
	}

	/**
	 * 机器人设置修改
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateByModeType", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateByModeType(
			@RequestBody TaskSeting record) {
		return new ApiResult(RESPONSE.SUCCESS, "请求成功",
				taskSetingService.updateByModeType(record));
	}

	@RequestMapping(value = "/updateStartOrStop", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateStartOrStop(
			@RequestBody ScheduleJob record) {
		if (record.getStatus() == ScheduleJob.SCHEDULEJOB_END) {
			scheduleJobService.resumeJob(record);
		} else {
			scheduleJobService.pauseJob(record);// 暂停任务 更新数据库
		}
		return new ApiResult(RESPONSE.SUCCESS, "请求成功");
	}
	@RequestMapping(value = "/task_status", method = RequestMethod.GET)
	public @ResponseBody ApiResult task_status(@RequestBody Map<String,Object> param) throws SchedulerException {
		
		JobKey jobKey = new JobKey(param.get("name").toString(), param.get("group").toString());
		JobDetail jb = scheduler.getJobDetail(jobKey);
//		System.out.println("==============:"+scheduler.checkExists(jobKey));
		return new ApiResult(RESPONSE.SUCCESS, JsonUtil.toJson(jb));
	}
}
