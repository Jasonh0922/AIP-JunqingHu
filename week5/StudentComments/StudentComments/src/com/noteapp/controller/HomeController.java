package com.noteapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noteapp.model.Comment;
import com.noteapp.model.Student;
import com.noteapp.service.*;
import com.noteapp.serviceImpl.*;

@Controller
public class HomeController {
	
	private LoginService loginService = new LoginServiceImpl();
	private DownloadCommentService downlodCommentService = new DownloadCommentServiceImpl();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){
		System.out.println("home");
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, List<Comment>> login(@RequestBody Student user){
		System.out.println(loginService.getUserData().get(1).getUserName());
		System.out.println(user.getPassword());
		
		Map<String, List<Comment>> map = new HashMap<String, List<Comment>>();
		
		for(Student userFromData : loginService.getUserData()){
			if(user.getUserName().equalsIgnoreCase(userFromData.getUserName()) && 
					user.getPassword().equals(userFromData.getPassword())){
				
				map.put(user.getUserName(), downlodCommentService.getCommentList());
				
				return map;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody 
	public void deleteNote(@RequestBody Comment comment){
		downlodCommentService.deleteCommentList(comment);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody 
	public void createNote(@RequestBody Comment comment){
		downlodCommentService.createNewComment(comment);
	}
}
