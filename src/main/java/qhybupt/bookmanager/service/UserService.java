package qhybupt.bookmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qhybupt.bookmanager.dao.UserDAO;
import qhybupt.bookmanager.model.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public void addUser(User user){
		userDAO.addUser(user);
	}
	
	public User getUser(String name) {
		return userDAO.selectByName(name);
	}

//	public User getUser(String email) {
//		return userDAO.selectByEmail(email);
//	}

	public User getUser(long uid) {
		return userDAO.selectById(uid);
	}
	
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
}
