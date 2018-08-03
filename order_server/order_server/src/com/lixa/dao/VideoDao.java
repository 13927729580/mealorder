package com.lixa.dao;

import java.util.List;

import com.lixa.bean.Video;

public interface VideoDao {
	
	/**
	 * 添加视频
	 */
	int addVideo(Video video);

	/**
	 * 视频列表
	 * @return
	 */
	List<Video> getAllVideo();

	/**
	 * 设置过期
	 * @param id
	 */
	int overdueVideo(String id);

	/**
	 * 修改视频
	 * @param video
	 */
	int updateVideo(Video video);

	/**
	 * 查询一个视频
	 * @param videoId
	 * @return
	 */
	Video getVideoById(String videoId);

}
