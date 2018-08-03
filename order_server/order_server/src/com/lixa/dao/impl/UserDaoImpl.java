package com.lixa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lixa.bean.User;
import com.lixa.dao.UserDao;
import com.lixa.util.DBUtil;

public class UserDaoImpl implements UserDao {

	/**
	 * 判断登陆账号是否存在
	 * @param loginid
	 * @return User
	 */
	public boolean isLoginIdExists(String loginid) {
		boolean res = false;
		String sql = "select * from users where loginid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{loginid});
			rs = pstmt.executeQuery();
			if(rs.next())
				res = true;
		} catch (SQLException e) {
			res = false;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return res;
	}

	/**
	 * 判断邮箱是否存在
	 * @param email
	 * @return boolean
	 */
	public boolean isEmailExists(String email) {
		boolean res = false;
		String sql = "select * from users where email=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{email});
			rs = pstmt.executeQuery();
			if(rs.next())
				res = true;
		} catch (SQLException e) {
			res = false;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return res;
	}

	/**
	 * 注册用户
	 * @param u
	 * @return int
	 */
	public int addUsers(User u) {
		int flag = 0;
		String sql = "insert into users(loginid,password,email,gender)values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{u.getLoginid(),u.getPassword(),u.getEmail(),u.getGender()});
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			flag = 0;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return flag;
	}
	
	/**
	 * 登陆
	 * @param loginid
	 * @param password
	 * @return User
	 */
	public User getUserByIdAndPwd(String loginid, String password) {
		User u = null;
		String sql = "select * from users where loginid=? and password =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{loginid,password});
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				u = new User();
				u.setId(rs.getString("id"));
				u.setLoginid(rs.getString("loginid"));
				u.setPassword(rs.getString("password"));
				u.setNikename(rs.getString("nikename"));
				u.setEmail(rs.getString("email"));
				u.setPhone(rs.getString("phone"));
				u.setGender(rs.getString("gender"));
				u.setCreate_at(rs.getString("create_at"));
			}
			
		} catch (SQLException e) {
			u = null;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		return u;
	}

	/**
	 * 判断邮箱是否存在
	 * @param u
	 * @return boolean
	 */
	public boolean isEmailExists(User u) {
		boolean res = false;
		String sql = "select * from users where email=? and loginid<>?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{u.getEmail(),u.getLoginid()});
			rs = pstmt.executeQuery();
			if(rs.next())
				res = true;
		} catch (SQLException e) {
			res = false;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return res;
	}

	/**
	 * 修改用户
	 * @param u
	 * @return int
	 */
	public int modifyUserByLoginid(User u) {
		
		String sql = "update users set nikename=?,email=?,phone=?,gender=? where loginid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res=-1;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{u.getNikename(),u.getEmail(),u.getPhone(),u.getGender(),u.getLoginid()});
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return res;
	}

	/**
	 * 判断原密码是否错误
	 * @param loginid
	 * @param oldpwd
	 * @return
	 * @throws SQLException 
	 */
	public boolean isOldPasswordError(String loginid, String oldpwd) {
		boolean res = true;
		String sql = "select count(*) count from users where loginid=? and password=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnForMySql();
		pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{loginid,oldpwd});
		int c = 0;
		try {			
			rs = pstmt.executeQuery();			
			if(rs.next())
				c = rs.getInt("count");
		} catch (SQLException e) {
			res = true;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);			
		}	
		if(c > 0)
			res = false;
		
		return res;
	}

	/**
	 * 修改密码
	 * @param loginid
	 * @param newpwd
	 * @return
	 */
	public int modifyUserPassword(String loginid, String newpwd) {
		String sql = "update users set password=? where loginid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res=-1;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{newpwd,loginid});
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return res;
	}

}
