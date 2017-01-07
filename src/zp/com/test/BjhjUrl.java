package zp.com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BjhjUrl {
	public static void main(String[] args) throws Exception {
		// XJD000035293   XJD000036388   XJD000036954
		String xjid = "XJD000036954";
		String comp = "400";
		//delBjhj(xjid, comp);//删除所有数据
		doBjhj(xjid, comp);// 写入17和18表
		// delBjhj(xjid, comp);//删除所有数据
	}

	/**
	 * 
	 * @param xjid
	 * @param comp
	 *            目前强制转换为400，
	 * @author 741059
	 * @date 2014-12-24下午4:44:08
	 */
	public static void doBjhj(String xjid, String comp) {
		comp = "400";
		StringBuffer s = new StringBuffer();
		String url = "http://172.16.16.116:8080/bjhj/getSm17And18.do";
		// String url = "http://10.2.8.140:8083/bjhj/getSm17And18.do";
		URL u;
		try {
			u = new URL(url);
			HttpURLConnection uc = (HttpURLConnection) u.openConnection();
			uc.setRequestProperty("system", "bjhj");
			uc.setRequestMethod("POST");
			uc.setDoOutput(true);
			String param = "xjid=" + xjid + "&comp=" + comp;
			uc.getOutputStream().write(param.getBytes());
			uc.connect();
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				s.append(str);
			}
			System.out.println("运行结果:" + s);
		} catch (Exception e) {
			System.out.println("----------------远程访问异常，请检查----------------");
			e.printStackTrace();
		}
	}

	public static void delBjhj(String xjid, String comp) {
		comp = "400";
		StringBuffer s = new StringBuffer();
		String url = "http://172.16.16.116:8080/bjhj/delBjhj.do";
		// String url = "http://10.2.8.140:8083/bjhj/delBjhj.do";
		URL u;
		try {
			u = new URL(url);
			HttpURLConnection uc = (HttpURLConnection) u.openConnection();
			uc.setRequestProperty("system", "bjhj");
			uc.setRequestMethod("POST");
			uc.setDoOutput(true);
			String param = "xjid=" + xjid + "&comp=" + comp;
			uc.getOutputStream().write(param.getBytes());
			uc.connect();
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				s.append(str);
			}
			System.out.println("运行结果:" + s);
		} catch (Exception e) {
			System.out.println("----------------远程访问异常，请检查----------------");
			e.printStackTrace();
		}
	}
}
