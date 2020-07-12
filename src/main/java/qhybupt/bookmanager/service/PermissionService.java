package qhybupt.bookmanager.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qhybupt.bookmanager.model.Role;
//import qhybupt.bookmanager.model.RolePermission;
import qhybupt.bookmanager.dao.PermissionDAO;
import qhybupt.bookmanager.model.Permission;

@Service
public class PermissionService {
	
	@Autowired
	private PermissionDAO permissionDAO;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePermissionService rolePermissionService;
	
	public List<Permission> listPermission() {
		return permissionDAO.selectAll();
	}
	
	private String getNameById(Long id) {
		return permissionDAO.selectNameById(id);
	}
	
	private String getUrlById(Long id) {
		return permissionDAO.selectUrlById(id);
	}
	
	public boolean needInterceptor(String requestURI) {
        List<Permission> ps = listPermission();
        for (Permission p : ps) {
            if (p.getUrl().equals(requestURI))
                return true;
        }
        return false;
    }
	
	public Set<String> listPermissionURLs(String userName) {
		
		Set<String> result = new HashSet<>();
		
        List<Role> roles = roleService.listRoles(userName);
        List<Long> permissionIds= new ArrayList<>();
        
        for (Role role : roles) {
        	List<Long> pIds = rolePermissionService.getPermissionByRole(role.getId());
        	permissionIds.addAll(pIds);
        }
 
        for (Long pid : permissionIds) {
            result.add(getUrlById(pid));
        }
 
        return result;
    }

	public Set<String> listPermissions(String userName) {
		
		Set<String> result = new HashSet<>();
		
		List<Role> roles = roleService.listRoles(userName);
		List<Long> permissionIds= new ArrayList<>();
        
        for (Role role : roles) {
        	List<Long> pIds = rolePermissionService.getPermissionByRole(role.getId());
        	permissionIds.addAll(pIds);
        }

        for (Long pid : permissionIds) {
            result.add(getNameById(pid));
        }

		return result;
	}
}
