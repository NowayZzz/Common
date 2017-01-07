package com.util.类;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.util.辅助类.C;
import com.util.辅助类.User;

public class session监听器 implements HttpSessionListener,HttpSessionAttributeListener{

	private Logger log = Logger.getLogger(this.getClass());
	
	public static final HashMap<String, Object> onLineUsers = new HashMap<String, Object>();
	
	public void sessionCreated(HttpSessionEvent event) {
		log.info("=====================开始产生session");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		log.info("=====================开始删除session");
		HttpSession session = event.getSession();
		User user = (User) session.getAttribute(C.SESSION_KEY);
		if(user != null ){
			String id = session.getId();
			onLineUsers.remove(id+user.getUsid());
		}
	}

	public void attributeAdded(HttpSessionBindingEvent event) {
		log.info("=====================session绑定数据");
		HttpSession session = event.getSession();
		String id = session.getId();
		User user = (User) session.getAttribute(C.SESSION_KEY);
		if(user==null) return ;
		onLineUsers.put(id+user.getUsid(),user);
		ServletContext sc = session.getServletContext();
		sc.setAttribute("onLineUsers", onLineUsers);
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		log.info("=====================session解绑数据");
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		log.info("=====================session变换数据");
	}

}
