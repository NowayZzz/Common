package com.util.类;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.util.辅助类.C;

import net.sf.json.JSONObject;

/**
 * 所有controller的基类 公共事件的处理
 * 
 * @author cql
 * @param <JSONObject>
 * 
 */
public abstract class cotroller基类 {

	/***************************************************************************
	 * 日志功能
	 */
	protected final Logger log = Logger.getLogger(this.getClass());

	/**
	 * 将参数放入作用域中
	 */
	protected void addParamToModel(Model model, Object valObjects,
			String nameObjects) {
		model.addAttribute(nameObjects, valObjects);
		
//		org.mybatis.spring.mapper.MapperFactoryBean a;
	}
	
	@ExceptionHandler
	public void exceptionHandler(HttpServletResponse response, Exception ex){
		log.error(ex.toString());
		JSONObject json = new JSONObject();
		json.accumulate("success", false);
		ex.printStackTrace();
		if(ex instanceof SQLException){
			json.accumulate("message", "数据库访问异常,请联系管理员!");
		}else {
			json.accumulate("message", "系统异常,请联系管理员!");
		}
		ajaxReturn(response, json.toString());
		return ;
	}

//	/**
//	 * 用于ajax
//	 * 
//	 * @param response
//	 *            HttpServletResponse
//	 * @param content
//	 */
//	public boolean ajaxForward(HttpServletResponse response, String content) {
//		response.setContentType("text/json;charset=UTF-8");
//		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			out.write(content);
//			out.flush();
//		} catch (IOException e) {
//			log.error("ajax出现异常：" + e.getLocalizedMessage());
//			return false;
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//		return true;
//	}
	  
	public void ajaxReturn(HttpServletResponse response, String content) {
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(content);
			out.flush();
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public void uploadAjaxReturn(HttpServletResponse response, String content) {
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(content);
			out.flush();
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public void setCookie(HttpServletResponse response, String key,
			String value, int timeInSeconds) {
		
		Cookie cookie = new Cookie(C.COOKIE_KEY+key, value);
		cookie.setMaxAge(timeInSeconds);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public String getCookie(HttpServletRequest request, String key) {
		if(request.getCookies()==null){
			return "";
		}
		for(Cookie c :request.getCookies()){
			if((C.COOKIE_KEY+key).equals(c.getName())){
				return c.getValue();
			}
		}
		
		return "";
		
	}

	/**
	 * 设置页面不被缓存
	 * 
	 * @param response
	 */
	public void setNoBuffer(HttpServletResponse response) {
		response.reset();
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Control", "no-store");
		response.addDateHeader("Expires", 0);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml");
	}

}
