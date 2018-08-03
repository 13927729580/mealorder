package com.lixa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lixa.bean.User;
import com.lixa.dao.UserDao;
import com.lixa.dao.impl.UserDaoImpl;

public class UserBaseModifyServlet extends HttpServlet{
	private static final long serialVersionUID = -4701632877664998115L;
	private UserDao dao = new UserDaoImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String loginId = request.getParameter("loginid");
		String nikeName = request.getParameter("nikename");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");

		User u = new User();
		u.setLoginid(loginId);
		u.setNikename(nikeName);
		u.setEmail(email);
		u.setPhone(phone);
		u.setGender(gender);
		
		/**
		 *  -1 修改错误
		 *  0 修改成功
		 *  1 邮箱已经存在
		 */
		int res = -1;		
		if(dao.isEmailExists(u))
			res = 1;
		else if(dao.modifyUserByLoginid(u) != -1)
			res = 0;
		else 
			res = -1;

		out.print(res);
		out.flush();
		out.close();
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
