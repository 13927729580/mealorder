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

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = -2801273997474096868L;
	
	private UserDao dao = new UserDaoImpl();
	
	/**
	 * 登陆
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		String loginid = request.getParameter("loginId");
		String password = request.getParameter("password");

		User u = dao.getUserByIdAndPwd(loginid, password);
		
		if(null != u){
			out.print(user2String(u));
		} else {
			out.print("-1");
		}

		out.flush();
		out.close();
	}

	private String user2String(User u) {
		StringBuilder s = new StringBuilder();
		s.append(u.getId()).append(",")
			.append(u.getLoginid()).append(",")
			.append(u.getPassword()).append(",")
			.append(u.getNikename()==null?"昵称":u.getNikename()).append(",")
			.append(u.getPhone()==null?"手机":u.getPhone()).append(",")
			.append(u.getEmail()).append(",")
			.append(u.getGender()).append(",")
			.append(u.getCreate_at().substring(0, u.getCreate_at().lastIndexOf(".")));

		return s.toString();
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
