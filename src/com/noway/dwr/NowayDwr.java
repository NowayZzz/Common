package com.noway.dwr;

import java.util.Collection;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

public class NowayDwr {

//	private static String funName = "monitorMsg";//js中的接收推送消息的function
	
	/**
	 * 
	 * @param funName js中的接收推送消息的function
	 * @param type 针对哪个组件
	 * @param data 根据type更新该组件的数据
	 */
	public static void pushMessage(final String funName, final String data) {
		
		//自定义过滤条件
		ScriptSessionFilter scriptSessionFilter = new ScriptSessionFilter() {
			public boolean match(ScriptSession session) {
				/*
				 * if (session.getAttribute("userId") == null) return false;
				 * else return (session.getAttribute("userId")).equals(userId);
				 */
				return true;
			}
		};
		
		Runnable task = new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();
			public void run() {
				script.appendCall(funName, data);
//				script.appendCall("showMessage",  data);
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		};
		
		DwrUtil.pushMessage(scriptSessionFilter , task );
	}

}
