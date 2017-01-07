package ftp上传下载;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;


public class TestFTP {
	static String lujing = "F:\\log.txt";
	static String outputfile = "F:\\outputfile.txt";
	static String fileName = "ttt.txt";
	
	public static void main(String[] args) throws IOException {
		upload();
//		download();
//		List<String[]> sl = getFileList();
//		for (String[] strings : sl) {
//			System.out.println(strings[0]+"------------------------------------"+strings[1]);
//		}
	}
	
	static List<String[]> getFileList() {
		List<String[]> fileList = new ArrayList<String[]>();
		Comnet cn = new Comnet();
		boolean bool;
		try {
			bool = cn.getFTPConnection("172.16.16.116", 20, "admin", "123456");
			System.out.println("连接结果："+bool);
			
			FTPFile[] ff = cn.getFTPFile("par");
			for (FTPFile f : ff) {
				/*System.out.println("-----文件名：----"+f.getName()+"------组：---"+f.getGroup()+
						"------目录:---"+f.isDirectory()+"---文件：------"+f.isFile());*/
				if(f.isDirectory()){
					String[] s = {"目录",f.getName()};
					fileList.add(s);
				}else if(f.isFile()){
					String[] s = {"文件",f.getName()};
					fileList.add(s);
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileList;
	}
	
	static void upload(){
		try {
			Comnet cn = new Comnet();
			boolean bool = cn.getFTPConnection("172.16.16.116", 20, "admin", "123456");
			System.out.println("连接结果："+bool);
			//上传
			FileInputStream fis = new FileInputStream(new File(lujing));
			cn.uploadFileDefault( fis, "", fileName);
			fis.close();
			cn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void download(){
		try {
			Comnet cn = new Comnet();
			boolean bool = cn.getFTPConnection("172.16.16.116", 20, "admin", "123456");
			System.out.println("连接结果："+bool);
			//下载
			FileOutputStream os = new FileOutputStream(new File(outputfile));
			cn.downloadFileDefault( os, "ttt.txt");
			os.close();
			cn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
