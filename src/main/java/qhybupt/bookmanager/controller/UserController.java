package qhybupt.bookmanager.controller;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import qhybupt.bookmanager.model.User;
import qhybupt.bookmanager.model.enums.RoleEnum;
import qhybupt.bookmanager.service.UserRoleService;
import qhybupt.bookmanager.service.UserService;
 
@Controller
@RequestMapping("")
public class UserController {
    @Autowired UserRoleService userRoleService;
    @Autowired UserService userService;
    
    @RequestMapping("register")
    public String add(Model model,String name, String email, String password){
         
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
          
        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
         
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(encodedPassword);
        u.setSalt(salt);
        userService.addUser(u);
        
        userRoleService.initRoles(userService.getUser(name).getId(), RoleEnum.User.getValue());//…Ë÷√ƒ¨»œΩ«…´
         
        return "redirect:index";
    }
 
}
