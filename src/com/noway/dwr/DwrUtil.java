package com.noway.dwr;

import org.apache.log4j.Logger;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessionFilter;

public class DwrUtil {

	protected static final Logger logger = Logger.getLogger(DwrUtil.class);
	
	public static void pushMessage(ScriptSessionFilter filter, Runnable task) {
		try {
			Browser.withAllSessionsFiltered(filter , task );
		} catch (Exception e) {
			logger.error("推送消息失败:" + e.getMessage());
		}
	}

}