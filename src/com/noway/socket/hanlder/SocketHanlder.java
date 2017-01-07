package com.noway.socket.hanlder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

public class SocketHanlder implements Runnable{
	protected final Logger logger = Logger.getLogger(this.getClass());

	private Socket client;
	
	public SocketHanlder(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		logger.debug(client + "开始读取网络数据");
		int reportLen = 0;// 报文的预期长度
		int bufferlen = 0;// 总共已从网络数据流中读取的数据长度
		Boolean readCompleted = false;// 是否将报文全部读取完毕
		char[] buf = new char[4096];// 从网络缓冲区的读取数据后的缓存buf
		StringBuilder reportmsg = new StringBuilder();
		InputStream istream;
		try {
			istream = client.getInputStream();// 获取网络输入数据流
			client.setSoTimeout(3000);
			InputStreamReader reader = new InputStreamReader(istream, "utf-8");
			int readlen = 0;
			while ((readlen = reader.read(buf, 0, 1024)) != -1) {
				if (readlen > 0) {
					reportmsg.append(buf);
					bufferlen += readlen;
					if (reportLen == 0 && reportmsg.indexOf("<") > 0) {
						int index = reportmsg.indexOf("<");
						// 获取报文长度总长度
						reportLen = Integer.parseInt(reportmsg.substring(0,
								index).trim())
								+ index;
					}
					if (reportLen != 0 && reportLen <= bufferlen) {
						readCompleted = true;
						break;
					}
					if (readlen > 10 && reportLen == 0) {// 不符合规约的报文
						readCompleted = false;
						break;
					}
				}
			}
		} catch (SocketTimeoutException e) {
			logger.debug("读取超时");
		} catch (SocketException e) {
			logger.error(e.getMessage());
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
		// 返回输出结果
		if (readCompleted) {
			//exchange(reportmsg.toString());
		} else {
			logger.info(client + "报文格式不正确\n" + reportmsg.toString());
			//exchange("");
		}
		
	}

	
}