package com.util.辅助类;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.nutz.json.Json;

public class C {
	
	public static ResourceBundle rb = null;
	
	static {
		rb = ResourceBundle.getBundle("config");
	}
	
	public static final String SESSION_KEY = "_CP_SESSION_KEY_";
	public static final String SESSION_KEY_IS_ADMIN = "_CP_SESSION_KEY_IS_ADMIN_";
	public static final String COOKIE_KEY = "_CP_COOKIE_KEY_";
	public static final String DBNOFILE_PATH = "D:\\dbnofile\\";
	public static final String[] COMPS = {"400","339","300","500","560","751","358","960","850","308","510","355","600","980","610"};
	
	public static final String MAIL_SEND = rb.getString("mail_send");
	public static final int MCDER_PID = Integer.valueOf(rb.getString("mcder_pid"));
	public static final String SUPER_PSWD = rb.getString("super_pswd");
	
	public static String getCompDesc(int comp){
		String compdesc = "";
		switch (comp) {
		case 400:
			compdesc = "珠海";
			break;
		case 300:
			compdesc = "合肥";
			break;
		case 500:
			compdesc = "重庆";
			break;
		case 339:
			compdesc = "芜湖";
			break;
		case 358:
			compdesc = "郑州";
			break;
		case 560:
			compdesc = "武汉";
			break;
		case 751:
			compdesc = "长沙";
			break;
		case 610:
			compdesc = "石家庄";
			break;
		case 960:
			compdesc = "石家庄小家电";
			break;
		case 980:
			compdesc = "中山小家电";
			break;
		case 600:
			compdesc = "珠海凌达";
			break;
		case 850:
			compdesc = "珠海凯邦";
			break;
		case 308:
			compdesc = "合肥凯邦";
			break;
		case 510:
			compdesc = "重庆凯邦";
			break;
		case 355:
			compdesc = "河南凯邦";
			break;
		case 700:
			compdesc = "格力新元";
			break;
		default:
			break;
		}
		return compdesc;
	}
	
	public static int getSblxInt(String sblx_){
		int sblx = -1;
		if("LQ".equals(sblx_)){
			sblx = 1;
		}else if("BJ".equals(sblx_)){
			sblx = 2;
		}else if("ZS".equals(sblx_)){
			sblx = 3;
		}else if("KZ".equals(sblx_)){
			sblx = 4;
		}
		return sblx;
	}
	/*private static Properties prop = null;
	static {
		prop = new Properties();
		try {
			prop.load(C.class.getClassLoader()
					.getResourceAsStream("config.properties"));
			String s = prop.getProperty("mail_send");
			System.out.println("----------"+s);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	
	private static Properties prop = null;
	private static final String ENV_DEV = "dev";
	private static final String ENV_PRODUCT = "product";
	private static final String PROFILE_KEY = "erpapiprofile";
	
	public static final String API_URL_LIST = "API_URL_LIST";
	
	static {
		prop = new Properties();
		try {
			//获取常量
			prop.load(C.class.getClassLoader()
					.getResourceAsStream("c.properties"));
			
			//获取环境变量
			final String env = System.getProperty(PROFILE_KEY, ENV_PRODUCT );
			prop.setProperty("env", env);
			
			//System.out.println("env:"+env);
			
			if(env.equals(ENV_DEV)){
				prop.setProperty("env", ENV_DEV);
				prop.setProperty("resURL", prop.getProperty("resurl_dev"));
				
				prop.setProperty("memcachedHosts", prop.getProperty("memcached_host_dev"));
				prop.setProperty("memcachedWeights", prop.getProperty("memcached_weights_dev"));
				
			}else if (env.equals(ENV_PRODUCT)) {
				prop.setProperty("env", ENV_PRODUCT);
				prop.setProperty("resURL", prop.getProperty("resurl_product"));
				
				prop.setProperty("memcachedHosts", prop.getProperty("memcached_host_product"));
				prop.setProperty("memcachedWeights", prop.getProperty("memcached_weights_product"));
			}
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static final String ENV = prop.getProperty("env");
	public static final String RESURL = prop.getProperty("resURL");
	public static final String SYSTEM_PROPERTIES_JSON = Json.toJson(System.getProperties());
	
	public static final String[] MEMCACHED_HOSTS = prop.getProperty("memcachedHosts").split(",");
	public static final String[] MEMCACHED_WEIGHTS = prop.getProperty("memcachedWeights").split(",");
}
