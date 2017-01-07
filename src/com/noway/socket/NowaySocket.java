package com.noway.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.noway.socket.hanlder.SocketHanlder;

public class NowaySocket implements Runnable{
	ExecutorService executor = Executors.newCachedThreadPool();
	boolean threadRun = false;// 线程运行标识
	ServerSocket ss ;
	
	@Override
	public void run() {
		try {
			threadRun = true;
			ss = new ServerSocket(9091);
			while(threadRun){
				Socket client = ss.accept();//等待
				processData(client);
				threadRun = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void processData(Socket client){
		SocketHanlder sh = new SocketHanlder(client);
		executor.execute(sh);
	}
}