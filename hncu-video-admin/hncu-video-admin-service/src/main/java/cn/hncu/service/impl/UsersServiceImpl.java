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
	
	@Autowired
	private UsersMapper userMapper;

	@Override
	public PagedResult queryUsers(Users user, Integer page, Integer pageSize) {
		String username = "";
		String nickname = ""; 	
		if (user != null) {
			username = user.getUsername();
			nickname = user.getNickname();
		}
	
		PageHelper.startPage(page, pageSize);
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
		grid.setTotal(pageList.getPages());
		grid.setRows(userList);
		grid.setPage(page);
		grid.setRecords(pageList.getTotal());

		return grid;
	}

}
