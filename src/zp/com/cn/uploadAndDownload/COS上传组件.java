package zp.com.cn.uploadAndDownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class COS上传组件 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3766833619489404459L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			 {
		MultipartRequest mr;
		try {
			long date1=new Date().getTime();
			mr = new MultipartRequest(request, "f:\\temp", 50*500*1024*1024, "ISO-8859-1");
			long date2=new Date().getTime();
			System.out.println(date2-date1);
			Enumeration eFiles= mr.getFileNames();
			while(eFiles.hasMoreElements()){
				String str=(String) eFiles.nextElement();
				String name=mr.getOriginalFileName(str);
				File file=mr.getFile(str);
				if(null!=file){
					file.delete();
					FileInputStream fis=new FileInputStream(file);
					FileOutputStream fos=new FileOutputStream("f:\\temp\\haha\\hh.txt");
					byte[] buffer=new byte[1024];
					int temp=0;
					while((temp=fis.read(buffer))!=-1){
						fos.write(buffer, 0, temp);
					}
				}
				System.out.println("name==="+name);
			}
			Enumeration<String> ep=mr.getParameterNames();
			while(ep.hasMoreElements()){
				String str=(String) ep.nextElement();
				System.out.println("str=="+mr.getParameter(str));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.write("!@#$%^&*(");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			 }

}
