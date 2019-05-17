package cn.hncu.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hncu.mapper.UsersMapper;
import cn.hncu.pojo.Users;
import cn.hncu.pojo.UsersExample;
import cn.hncu.pojo.UsersExample.Criteria;
import cn.hncu.service.UsersService;
import cn.hncu.utils.PagedResult;

@Service
public class UsersServiceImpl implements UsersService{
	//引入mapper
	@Autowired
	private UsersMapper userMapper;

	/**
	 * 实现查询用户列表
	 */
	@Override
	public PagedResult queryUsers(Users user, Integer page, Integer pageSize) {
		String username = "";
		String nickname = ""; 	
		if (user != null) {
			username = user.getUsername(); 
			nickname = user.getNickname();
		}
	
		PageHelper.startPage(page, pageSize);//分页
		
		///////以下是数据库操作////////////
		UsersExample userExample = new UsersExample(); 
		Criteria userCriteria = userExample.createCriteria();
		if (StringUtils.isNotBlank(username)) {
			userCriteria.andUsernameLike("%" + username + "%");
		}
		if (StringUtils.isNotBlank(nickname)) {
			userCriteria.andNicknameLike("%" + nickname + "%");
		}
   
		List<Users> userList = userMapper.selectByExample(userExample);

		PageInfo<Users> pageList = new PageInfo<Users>(userList);

		PagedResult grid = new PagedResult();
		grid.setTotal(pageList.getPages());//设置页数s
		grid.setRows(userList);//设置行信息
		grid.setPage(page);//设置当前页数
		grid.setRecords(pageList.getTotal());//设置总记录数

		return grid;
	}

}
