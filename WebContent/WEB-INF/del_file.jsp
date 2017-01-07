<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.io.File" %>
<meta http-equiv="Refresh" content="10">
<%
	String filePath = "d:/";
	String fileName = "dataupload";
	try {
		delFile(filePath, fileName);
	} catch (Exception e) {
		System.out.println("É¾³ýÊ§°Ü£º"+e.getMessage());
		e.printStackTrace();
	}
%>
<%!
	public void delFile(String filePath, String fileName) throws Exception{
		File file = new File(filePath);
		if( file.isDirectory() ){
			File[] files = file.listFiles();
			for (File f : files) {
				if( !f.isDirectory() ){
					String fName = f.getName();
					if(fName.startsWith(fileName, 0)){
						f.delete();
					} 
				} 
			}
		}
	}
%>