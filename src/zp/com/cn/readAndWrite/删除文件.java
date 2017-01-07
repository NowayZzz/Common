package zp.com.cn.readAndWrite;

import java.io.File;

public class 删除文件 {
	public static void main(String[] args) {
		
		String filePath = "d:/";
		try {
			File file = new File(filePath);
			if( file.isDirectory() ){
				File[] files = file.listFiles();
				for (File f : files) {
					if( !f.isDirectory() ){
						String fName = f.getName();
						if(fName.startsWith("upload", 0)){
							f.delete();
						} 
					} 
				}
			}
		} catch (Exception e) {
			System.out.println("删除失败："+e.getMessage());
			e.printStackTrace();
		}
		
	}
}
