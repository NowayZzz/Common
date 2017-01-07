package com.util.类;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.util.辅助类.C;

public class 拦截器 extends HandlerInterceptorAdapter {
	
	private static final Logger log = Logger.getLogger(拦截器.class);
	
	private List<String> noFilters;
	private String loginPage;
	
	public void setNoFilters(List<String> noFilters) {
		this.noFilters = noFilters;
	}
	
	public List<String> getNoFilters() {
		return noFilters;
	}

	public String getLoginPage() {
		return loginPage;
	}
	
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
/*	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object o, Exception e)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object o, ModelAndView mv)
			throws Exception {
		
	}*/

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object o) throws Exception {
		
		String uri = request.getRequestURI();
		
		//log.debug("验证URI："+uri);
		boolean beFilter = true;
        for (String s : noFilters) {
            if (StringUtils.contains(uri, s)) {
                beFilter = false;
                break;
            }
        }
        
        //log.debug("是否要检查Session："+beFilter);
        if (beFilter) {
        	log.debug("检查Session："+uri);
        	Object obj = null;
        	if(null == (obj = request.getSession().getAttribute(C.SESSION_KEY)) || request.getHeader("REFERER")==null){
        		
        		//log.debug("Session为空");
        		response.setStatus(401);
        		if (request.getHeader("X-Requested-With") != null) {
        			//TODO ajax 401
        			log.debug("非法请求,强制登录ajax");
        			ajax401Return(response);
        		} else {
        			log.debug("非法请求,强制登录");
        			response.sendRedirect(request.getContextPath() + loginPage);
        		}
        		return false;
        	}
        }
        
        //log.debug("AccessInterceptor 通过");
		return true;
	}
	
	private void ajax401Return(HttpServletResponse response) {
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write("{title:'登录超时',message:'请重新登录'}");
			out.flush();
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
}
