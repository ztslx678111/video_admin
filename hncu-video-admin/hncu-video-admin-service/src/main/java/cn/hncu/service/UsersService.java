package cn.hncu.service;

import cn.hncu.pojo.Users;
import cn.hncu.utils.PagedResult;

public interface UsersService {
	
	/**
	 * 查询用户信息接口
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PagedResult queryUsers(Users user, Integer page, Integer pageSize);
}
