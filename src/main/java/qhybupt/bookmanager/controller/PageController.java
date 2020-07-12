package qhybupt.bookmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import qhybupt.bookmanager.service.BookService;

//显示页面的控制器
@Controller
@RequestMapping("")
public class PageController {
	@Autowired
	private BookService bookService;
     
    @RequestMapping("index")
    public String listBooks(Model model){
    	model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }
    
    @RequestMapping(value="/login",method=RequestMethod.GET) 
    public String login(){
        return "login";
    }
    
    @RequestMapping(value="/register",method=RequestMethod.GET) 
    public String register(){
        return "register";
    }
    
    @RequestMapping("unauthorized")
    public String noPerms(){
        return "unauthorized";
    }
}
