package qhybupt.bookmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qhybupt.bookmanager.dao.RolePermissionDAO;

@Service
public class RolePermissionService {
	
	@Autowired
	RolePermissionDAO rolePermissionDAO;
	
	List<Long> getPermissionByRole(long id) {
		return rolePermissionDAO.selectPermissionIdByRoleId(id);
	}
}
