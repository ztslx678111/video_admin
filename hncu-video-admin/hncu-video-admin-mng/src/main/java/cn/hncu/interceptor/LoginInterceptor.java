package cn.hncu.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	private List<String> unCheckUrls;//白名单 放行
	
	//访问前拦截 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String requestUrl = request.getRequestURI();//获取url地址
		requestUrl=requestUrl.replaceAll(request.getContextPath(), "");
		// 判断是否针对匿名路径需要拦截，如果包含，则表示匿名路径，需要拦截，否则通过拦截器
		if (unCheckUrls.contains(requestUrl)){
			// 包含公开url，直接跳过
			return true;
		}
		
		//如果获取不到用户的session
		if(null == request.getSession().getAttribute("sessionUser")){
			response.sendRedirect(request.getContextPath()+"/users/login.action"); //重定向到登陆界面
			
			return false;
		}
		
		// 放行
		return true;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	public List<String> getUnCheckUrls() {
		return unCheckUrls;
	}

	public void setUnCheckUrls(List<String> unCheckUrls) {
		this.unCheckUrls = unCheckUrls;
	}

}
