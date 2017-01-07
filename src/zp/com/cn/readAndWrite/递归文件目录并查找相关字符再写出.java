package zp.com.cn.readAndWrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

public class 递归文件目录并查找相关字符再写出 {

	static String path = "e:\\workspace\\cp";//需要递归的目录
	static String filePathStart = "F:/ttall.txt";//第一次递归目录完成后，表名有重复
	static String filePathEnd = "f:\\table.txt";//最终输出文件，表不重复
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		dieDaiWenJianJia(path);
		readFileByLines(filePathEnd);
	}
	
	/**
	 * 递归文件夹目录，并得到字符
	 * @param path
	 */
	static void dieDaiWenJianJia(String path){
		File file= new File(path);
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for (File f : files) {
				//System.out.println(f.getName());
				if(f.isDirectory()){
					dieDaiWenJianJia(f.getAbsolutePath());//递归
				}else{
					//判断文件是不是以java/xml/jsp结尾
					String houzhui = f.getName().substring(f.getName().lastIndexOf(".")+1, f.getName().length());
					if(houzhui.equalsIgnoreCase("jsp") || houzhui.equalsIgnoreCase("java") || houzhui.equalsIgnoreCase("xml")){
						//读取文件指定字符，并写入指定文件
						readFileByLines(f);
					}
				}
			}
		}
	}

	/*
	 * 以行读取。如果表名换行，就嗝屁了
	 */
	static void readFileByLines(File file) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(filePathStart,true));
			String tempString = "";
			int line = 1;
			//System.out.println(file.getName()+"----------");
			while ((tempString = reader.readLine())!=null) {
				//同一行数据用空格和逗号分别判断一次
				String[] strSpace = tempString.split("\\s+");//以空格分离
				String[] strComma = tempString.split(",");//以逗号分离
				for (String s : strSpace) {
					if(s.length()>8) {
						//将表名分离部分  三个：ttiitm  240  560
						String s1 = s.substring(0, 6);//s1以t开头，并且6个字符都是字母
						String s2 = s.substring(6, 9);//s2全为数字
						String s3 = s.substring(9, s.length());//s3以具体情况而定,s.length()或者12
						Pattern patStr = Pattern.compile("[a-zA-Z]+");
						Pattern patNum = Pattern.compile("[0-9]*");
						boolean boolStr = patStr.matcher(s1).matches();
						boolean boolNum = patNum.matcher(s2).matches();
						if(s1.startsWith("t") && boolStr && boolNum){
							writer.write(s1+s2);
							writer.write("\r\n");
						}
					}
				}
				for (String s : strComma) {
					if(s.length()>8) {
						//将表名分离部分  三个：ttiitm  240  560
						String s1 = s.substring(0, 6);//s1以t开头，并且6个字符都是字母
						String s2 = s.substring(6, 9);//s2全为数字
						String s3 = s.substring(9, s.length());//s3以具体情况而定,s.length()或者12
						Pattern patStr = Pattern.compile("[a-zA-Z]+");
						Pattern patNum = Pattern.compile("[0-9]*");
						boolean boolStr = patStr.matcher(s1).matches();
						boolean boolNum = patNum.matcher(s2).matches();
						if(s1.startsWith("t") && boolStr && boolNum){
							writer.write(s1+s2);
							writer.write("\r\n");
						}
					}
				}
				writer.flush();
				line++;
			}
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					writer.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	/**
	 * 过滤重复的字符串
	 * @param filePathEnd
	 */
	static void readFileByLines(String filePathEnd) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		HashMap<String, String> map = new HashMap<String, String>(); 
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(filePathStart));
			writer = new BufferedWriter(new FileWriter(filePathEnd));
			String tempString = "";
			while ((tempString = reader.readLine())!=null) {
				map.put(tempString, tempString);
			}
			Set<String> set = map.keySet();
			Iterator<String> i =  set.iterator();
			while(i.hasNext()){
				String s = i.next();
				//System.out.println(s+"+++++");
				writer.write(s);
				writer.write("\r\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					writer.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
}
