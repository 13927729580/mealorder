package com.lixa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lixa.bean.Video;
import com.lixa.dao.VideoDao;
import com.lixa.util.DBUtil;

public class VideoDaoImpl implements VideoDao {

	/**
	 * 添加视频
	 */
	public int addVideo(Video video) {
		int flag = 0;
		String sql = "insert into videos(name,description,path)values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{video.getName(), video.getDescription(), video.getPath()});
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
	 * 获取菜单列表
	 */
	public List<Video> getAllVideo() {
		List<Video> list = new ArrayList<Video>();
		String sql = "select * from videos where overdue=0 order by create_at desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Video o = new Video();
				o.setId(rs.getString("id"));
				o.setName(rs.getString("name"));
				o.setDescription(rs.getString("description"));
				o.setPath(rs.getString("path"));
				list.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return list;
	}

	/**
	 * 设置过期
	 * @param id
	 */
	public int overdueVideo(String id) {
		int flag = 0;
		String sql = "update videos set overdue=1 where id=" + id;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
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
	 * 修改视频
	 */
	public int updateVideo(Video video) {
		int flag = 0;
		String sql = "update videos set name=?,description=?,path=?,update_at=NOW() where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{video.getName(),video.getDescription(),video.getPath(),video.getId()});
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {			
			System.out.println(video.getName() + " ：修改出错！");
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return flag;
	}

	/**
	 * 查询一个视频
	 * @param videoId
	 * @return
	 */
	public Video getVideoById(String videoId) {
		Video video = null;
		String sql = "select * from videos where overdue=0 and id =" + videoId + " order by create_at desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				video = new Video();
				video.setId(rs.getString("id"));
				video.setName(rs.getString("name"));
				video.setDescription(rs.getString("description"));
				video.setPath(rs.getString("path"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		return video;
	}

}
