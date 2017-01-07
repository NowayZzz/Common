package zp.com.cn.uploadAndDownload;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUpload上传组件 extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2017681126484251321L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		DiskFileItemFactory dfif=new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);
		ServletFileUpload sfu=new ServletFileUpload(dfif);
		sfu.setSizeMax(4096*1000*1000);
		if(sfu.isMultipartContent(req)){
		try {
			List<FileItem> fiList=sfu.parseRequest(req);
			FileItem fi=(FileItem) fiList.get(0);
			String context=fi.getString();
			System.out.println("opt=========="+req.getParameter("opt"));
			System.out.println("a=========="+req.getParameter("a"));
			System.out.println("context======="+context);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	}
}
