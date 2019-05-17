package cn.hncu.mapper;

import cn.hncu.pojo.Videos;
import cn.hncu.pojo.VideosExample;
import cn.hncu.pojo.vo.VideosVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideosMapper {
    int countByExample(VideosExample example);

    int deleteByExample(VideosExample example);

    int deleteByPrimaryKey(String id);

    int insert(Videos record);

    int insertSelective(Videos record);

    List<Videos> selectByExample();

    Videos selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Videos record, @Param("example") VideosExample example);

    int updateByExample(@Param("record") Videos record, @Param("example") VideosExample example);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);
    
    List<VideosVO> selectAllVideos(@Param("videoDesc") String videoDesc, @Param("username") String username);
    
}