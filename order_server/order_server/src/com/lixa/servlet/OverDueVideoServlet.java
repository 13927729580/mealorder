package com.lixa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lixa.dao.VideoDao;
import com.lixa.dao.impl.VideoDaoImpl;

public class OverDueVideoServlet extends HttpServlet {
	private static final long serialVersionUID = -2801273997474096868L;
	
	private VideoDao dao = new VideoDaoImpl();
	
	/**
	 * 设置过期
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String id = request.getParameter("id");
		
		dao.overdueVideo(id);

//		response.sendRedirect("/manageServlet/listOrder");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manageServlet/listVideo");
		dispatcher.forward(request, response);
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
