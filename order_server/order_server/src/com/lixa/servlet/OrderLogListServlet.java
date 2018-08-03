package com.lixa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lixa.bean.OrderLog;
import com.lixa.dao.OrderLogDao;
import com.lixa.dao.impl.OrderLogDaoImpl;

public class OrderLogListServlet extends HttpServlet{
	private static final long serialVersionUID = -4701632877664998115L;
	private OrderLogDao dao = new OrderLogDaoImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		List<OrderLog> list = dao.getAllOrderLog();
		request.setAttribute("orderLogList", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/orderLogList.jsp");		
		dispatcher .forward(request, response);
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
