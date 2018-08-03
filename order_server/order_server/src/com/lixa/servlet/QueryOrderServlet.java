package com.lixa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lixa.bean.Order;
import com.lixa.dao.OrderDao;
import com.lixa.dao.impl.OrderDaoImpl;

public class QueryOrderServlet extends HttpServlet {
	private static final long serialVersionUID = -2801273997474096868L;
	
	private OrderDao dao = new OrderDaoImpl();
	
	/**
	 * 查询菜
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		String orderId = request.getParameter("orderId");
		
		Order order = dao.getOrderById(orderId);
		
		request.setAttribute("order", order);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateOrder.jsp");
		
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
