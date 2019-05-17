package cn.hncu.service;

import cn.hncu.pojo.Bgm;
import cn.hncu.utils.PagedResult;

public interface VideoService {
    /**
     * 添加bgm接口
     * @param bgm
     */
	public void addBgm(Bgm bgm);
	
	/**
	 * 查询bgm列表接口
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PagedResult queryBgmList(Integer page, Integer pageSize);
	
	/**
	 * 删除bgm接口
	 * @param id
	 */
	public void deleteBgm(String id);
	
	/**
	 * 查询举报列表接口
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PagedResult queryReportList(Integer page, Integer pageSize);
	
	/**
	 * 更新视频状态接口
	 * @param videoId
	 * @param status
	 */
	public void updateVideoStatus(String videoId, Integer status);
	
	
	public PagedResult selectAllVideos(String videoDesc, String username, Integer page, Integer pageSize);
	
	//public PagedResult queryVideoList(Integer page, Integer pageSize);
}
