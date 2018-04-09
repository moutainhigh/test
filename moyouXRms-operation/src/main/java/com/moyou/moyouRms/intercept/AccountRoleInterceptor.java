package com.moyou.moyouRms.intercept;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.moyou.moyouRms.service.syssessionmanager.SysSessionManagerService;
import com.moyou.moyouRms.service.system.account.AccountShiroUtil;
import com.moyou.moyouRms.service.system.resource.ResourcesService;

public class AccountRoleInterceptor implements HandlerInterceptor{
	@Resource
	private ResourcesService resourcesService;
	@Resource
	private SysSessionManagerService sysSessionManagerService;
	@Override
	@SuppressWarnings("all")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String accountId=null;
		try {
			 accountId = AccountShiroUtil.getCurrentUser()
					.getAccountId();
		} catch (Exception e) {
//				e.printStackTrace();
				return false;
		}
		
		/**
		 * 更新操作时间
		 */
		sysSessionManagerService.updateSessionSetTime(AccountShiroUtil.getCurrentUser().getLoginName());
		//
		
		//超管
		if(AccountShiroUtil.getCurrentUser().getLoginName().equals("admin")){
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;  
//		url级限制 限制到controller下
		if(resourcesService.validata(accountId,handlerMethod.getMethod().getDeclaringClass().toString())){
	    	return true;
	    }
//		按钮级别的限制  限制到每一个controller下的每一个方法
		if(resourcesService.validata(accountId,handlerMethod.getMethod().toString())){
	    	return true;
	    }
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
