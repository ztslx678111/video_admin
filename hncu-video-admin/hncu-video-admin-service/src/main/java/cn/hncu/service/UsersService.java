package cn.hncu.service;

import cn.hncu.pojo.Users;
import cn.hncu.utils.PagedResult;

public interface UsersService {
	public PagedResult queryUsers(Users user, Integer page, Integer pageSize);
}
