package cn.hncu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hncu.enums.BGMOperatorTypeEnum;
import cn.hncu.mapper.BgmMapper;
import cn.hncu.mapper.UsersReportMapperCustom;
import cn.hncu.mapper.VideosMapper;
import cn.hncu.pojo.Bgm;
import cn.hncu.pojo.BgmExample;
import cn.hncu.pojo.Videos;
import cn.hncu.pojo.vo.Reports;
import cn.hncu.pojo.vo.VideosVO;
import cn.hncu.service.VideoService;
import cn.hncu.utils.JsonUtils;
import cn.hncu.utils.PagedResult;
import cn.hncu.web.util.ZKCurator;

@Service
public class VideoServiceImpl implements VideoService {
    
	//引入mapper
	@Autowired
	private VideosMapper videosMapper;
		
	@Autowired
	private BgmMapper bgmMapper;
	
	//引入zookeeper客户端
	@Autowired
	private ZKCurator zkCurator;
	
	@Autowired
	private UsersReportMapperCustom usersReportMapperCustom;
	
	/**
	 * 实现查找举报列表接口
	 */
	@Override
	public PagedResult queryReportList(Integer page, Integer pageSize) {

		PageHelper.startPage(page, pageSize);//分页
 
		
		////////////////////以下是数据库操作///////////////////////
		List<Reports> reportsList = usersReportMapperCustom.selectAllVideoReport();//获取举报列表数据

		PageInfo<Reports> pageList = new PageInfo<Reports>(reportsList);

		PagedResult grid = new PagedResult();
		grid.setTotal(pageList.getPages());
		grid.setRows(reportsList);
		grid.setPage(page);
		grid.setRecords(pageList.getTotal());

		return grid;
	}
   
	
	/**
	 * 实现视频状态更新
	 */
	@Override
	public void updateVideoStatus(String videoId, Integer status) {
		
		Videos video = new Videos();
		video.setId(videoId);
		video.setStatus(status);
		videosMapper.updateByPrimaryKeySelective(video);
	}
   
	/**
	 * 实现查询bgm列表
	 */
	@Override
	public PagedResult queryBgmList(Integer page, Integer pageSize) {
		
		PageHelper.startPage(page, pageSize);//分页
		
		
		/////////////////以下是数据库操作/////////////////////
		BgmExample example = new BgmExample();
		List<Bgm> list = bgmMapper.selectByExample(example);
		
		PageInfo<Bgm> pageList = new PageInfo<>(list);
		//设置分页信息
		PagedResult result = new PagedResult();
		result.setTotal(pageList.getPages());
		result.setRows(list);
		result.setPage(page);
		result.setRecords(pageList.getTotal());
		
		return result;
	}
    
	/**
	 * 实现添加bgm
	 */
	@Override
	public void addBgm(Bgm bgm) {
		String bgmId = UUID.randomUUID().toString();//获取uuid
		bgm.setId(bgmId);
		bgmMapper.insert(bgm);
		
		Map<String, String> map = new HashMap<>();
		map.put("operType", BGMOperatorTypeEnum.ADD.type);//设置枚举类型 
		map.put("path", bgm.getPath());//设置路径
		
		zkCurator.sendBgmOperator(bgmId, JsonUtils.objectToJson(map));// 监听事件 发给zookeeper客户端  json类型数据 
	}
	
	/**
	 * 实现删除bgm
	 */
	@Override
	public void deleteBgm(String id) {
		Bgm bgm = bgmMapper.selectByPrimaryKey(id);
		
		bgmMapper.deleteByPrimaryKey(id);
		
		Map<String, String> map = new HashMap<>();
		map.put("operType", BGMOperatorTypeEnum.DELETE.type);
		map.put("path", bgm.getPath());
		
		zkCurator.sendBgmOperator(id, JsonUtils.objectToJson(map));//监听事件，发给zookeeper客户端  json类型数据 
		
	}


	@Override
	public PagedResult selectAllVideos(String videoDesc, String username, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		List<VideosVO> videoList = videosMapper.selectAllVideos(videoDesc, username);

		PageInfo<VideosVO> pageList = new PageInfo<VideosVO>(videoList);

		PagedResult grid = new PagedResult();
		grid.setTotal(pageList.getPages());
		grid.setRows(videoList);
		grid.setPage(page);
		grid.setRecords(pageList.getTotal());

		return grid;
	}


	/*@Override
	public PagedResult queryVideoList(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	/*@Override
	public PagedResult queryVideoList(Integer page, Integer pageSize) {

		PageHelper.startPage(page, pageSize);

		List<Videos> videosList = videosMapper.selectAllVideos();

		PageInfo<Videos> pageList = new PageInfo<Videos>(videosList);

		PagedResult grid = new PagedResult();
		grid.setTotal(pageList.getPages());
		grid.setRows(videosList);
		grid.setPage(page);
		grid.setRecords(pageList.getTotal());

		return grid;
	}
*/
}
