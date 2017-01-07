package ftp上传下载;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Comnet {
	
	private FTPClient fc;
	
	public Comnet(){
		fc = new FTPClient();
	}
	
	/**
	 * 获得FTPClient
	 * @return
	 */
	public FTPClient getFTPClient(){
		return fc;
	}
	
	/**
	 * 获得路径下所有文件，包含文件夹
	 * @param pathname 路径,""表示根路径
	 * @return
	 * @throws IOException
	 */
	public FTPFile[] getFTPFile(String pathname) throws IOException{
		FTPFile[] ff = fc.listFiles(pathname);
		return ff;
	}
	
	/**
	 * 服务器返回码，230是ok
	 * @param fc
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public int getReply(String host, int port, String username, String password) throws SocketException, IOException{
		int reply = 0;
		fc.connect(host, port);
		fc.login(username, password);
		reply = fc.getReply();
		return reply;
	}
	
	/**
	 * 是否正确连接,建议用getReply连接判断
	 * @param host ftp服务器
	 * @param port 端口
	 * @param username 用户名
	 * @param password 密码
	 * @return true or false
	 * @throws SocketException
	 * @throws IOException
	 */
	public boolean getFTPConnection(String host, int port, String username, String password) throws SocketException, IOException{
		int reply;
		fc.connect(host, port);
		fc.login(username, password);
		reply = fc.getReplyCode();
		return reply==230;
	}
	
	/**
	 * 上传文件  ，uploadFileDefault方法可设置默认值
	 * @param fc
	 * @param is
	 * @param bufSize
	 * @param encoding
	 * @param fileType 文件类型
	 * @param path 存储路径
	 * @param filename 存储文件名
	 * @return
	 * @throws IOException
	 */
	public boolean uploadFile(InputStream is, int bufSize, String encoding, int fileType, String path, String filename) throws IOException{
		boolean bool = false;
		fc.setBufferSize(bufSize);
		fc.setControlEncoding(encoding);
		if(fc.changeWorkingDirectory(path) && fc.setFileType(fileType) ){
			bool = fc.storeFile(filename, is);
		}
		return bool;
	}
	
	/**
	 * 上传文件 ，默认值（BufferSize=1024，Encoding=utf-8）
	 * @param fc
	 * @param is
	 * @param path 存储路径
	 * @param filename 存储文件名
	 * @return
	 * @throws IOException
	 */
	public boolean uploadFileDefault(InputStream is, String path, String filename) throws IOException{
		boolean bool = false;
		fc.setBufferSize(1024);
		fc.setControlEncoding("UTF-8");
		if(fc.changeWorkingDirectory(path)){
			bool = fc.storeFile(filename, is);
		}
		return bool;
	}
	
	/**
	 * 下载文件，downloadFileDefault方法可设置默认值
	 * @param fc
	 * @param os
	 * @param bufSize 
	 * @param encoding
	 * @param fileType 文件类型
	 * @param remoteFile 文件名，包含路径
	 * @return
	 * @throws IOException
	 */
	public boolean downloadFile(OutputStream os, int bufSize, String encoding,int fileType, String remoteFile) throws IOException{
		boolean bool = false;
		fc.setBufferSize(bufSize);
		fc.setControlEncoding(encoding);
		bool = fc.retrieveFile(remoteFile, os);
		return bool;
	}
	
	/**
	 * 下载文件,默认值（BufferSize=1024，Encoding=utf-8）
	 * @param fc
	 * @param os
	 * @param fileType
	 * @param remoteFile
	 * @return
	 * @throws IOException
	 */
	public boolean downloadFileDefault(OutputStream os, String remoteFile) throws IOException{
		boolean bool = false;
		fc.setBufferSize(1024);
		fc.setControlEncoding("UTF-8");
		bool = fc.retrieveFile(remoteFile, os);
		return bool;
	}
	
	/**
	 * 退出并关闭
	 * @throws IOException
	 */
	public void close() throws IOException{
		fc.logout();
		fc.disconnect();
	}
}
