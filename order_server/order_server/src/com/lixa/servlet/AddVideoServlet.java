package com.lixa.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lixa.bean.Video;
import com.lixa.dao.VideoDao;
import com.lixa.dao.impl.VideoDaoImpl;

public class AddVideoServlet extends HttpServlet {
	private static final long serialVersionUID = -2801273997474096868L;
	
	private VideoDao dao = new VideoDaoImpl();
	
	private String VIDEO_PATH = "file/videoes/";
	
	/**
	 * 添加视频
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		DiskFileItemFactory factory=new DiskFileItemFactory(); 
		
		ServletFileUpload file = new ServletFileUpload(factory);
		Video video = new Video();
		try {
			List list = file.parseRequest(request); 
			Iterator<ServletFileUpload> it = list.iterator();
			while(it.hasNext()){ 
				
				FileItem  fileItem=(FileItem)it.next(); 
				if(fileItem.isFormField()){
					if("videoName".equals(fileItem.getFieldName())){
						video.setName(fileItem.getString("UTF-8"));
					} else if("videoDesc".equals(fileItem.getFieldName())){
						video.setDescription(fileItem.getString("UTF-8"));
					}
				} else {
					if(fileItem.getName()!=null&&!fileItem.getName().equals("")){
						
						String filename = fileItem.getName();
						String ext = filename.substring(filename.lastIndexOf(".") + 1);
						
						if(!"3gpmp4".contains(ext)){
							out.println("视频格式必须为：3gp、mp4");
							return ;
						}
						if(fileItem.getSize() > 1024 * 1024 * 5){ // 5 M 
							out.println("视频不能大于5M");
							return ;
						}
						
						String newname = System.currentTimeMillis() + "." + ext;
						
						String str = this.getClass().getResource("/").getPath();
						str = str.replace("WEB-INF/classes/", "").substring(1);
						
						String imagesPath = str + VIDEO_PATH;
						
						File dir = new File(imagesPath);
						if(!dir.exists() && !dir.isDirectory())
							dir.mkdirs();
						
						File realFile=new File(imagesPath, newname);
						fileItem.write(realFile);
						video.setPath(VIDEO_PATH + newname);
					}
				}
			}

			if(dao.addVideo(video) > 0)
				out.println("添加成功");
			else
				out.println("添加失败");
		} catch (FileUploadException e) {
			out.println("添加失败");
			e.printStackTrace();
		} catch (Exception e) {
			out.println("添加失败");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
}
