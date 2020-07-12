package qhybupt.bookmanager.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qhybupt.bookmanager.dao.RoleDAO;
import qhybupt.bookmanager.model.Role;
import qhybupt.bookmanager.model.User;
//import qhybupt.bookmanager.model.UserRole;
import qhybupt.bookmanager.service.UserService;
import qhybupt.bookmanager.service.UserRoleService;
import qhybupt.bookmanager.service.RoleService;

@Service
public class RoleService {
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleService roleService;
	
	private Role getById(long rid) {
		return roleDAO.selectById(rid);
	}  
	
	public List<Role> listRoles(String userName) {
        List<Role> roles = new ArrayList<>();
        User user = userService.getUser(userName);
        if(null==user)
            return roles;
         
        roles = listRoles(user);
        return roles;
    }
	
	public List<Role> listRoles(User user) {
        List<Role> roles = new ArrayList<>();

        List<Long> roleIds= userRoleService.getRoleByUser(user.getId());
         
        for (long rid : roleIds) {
            Role role=roleService.getById(rid);
            roles.add(role);
        }
        return roles;
    }

	public Set<String> listRoleNames(String userName) {
		Set<String> result = new HashSet<>();
		List<Role> roles = listRoles(userName);
		for (Role role : roles) {
			result.add(role.getName());
		}
		return result;
	}
}
