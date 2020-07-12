package qhybupt.bookmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qhybupt.bookmanager.dao.UserRoleDAO;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	public void initRoles(Long userId, Long roleId) {
		userRoleDAO.initRole(userId, roleId);
	}

	public List<Long> getRoleByUser(long userId) {
		return userRoleDAO.selectRoleIdByUserId(userId);
	}
}
