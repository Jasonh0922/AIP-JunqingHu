package com.noteapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.noteapp.service.LoginService;
import com.noteapp.model.Student;

public class LoginServiceImpl implements LoginService{
	/*
	 * implement loginService class function
	 * listing user data
	 */
	private List<Student> userData = new ArrayList<Student>();
	
	/*
	 * Initializing username  and password
	 */
	public LoginServiceImpl(){
		Student william = new Student();
		william.setId(1);
		william.setPassword("william");
		william.setUserName("william");
		
		Student petter = new Student();
		petter.setId(2);
		petter.setPassword("petter");
		petter.setUserName("petter");
		
		Student yuvia = new Student();
		yuvia.setId(3);
		yuvia.setPassword("yuvia");
		yuvia.setUserName("yuvia");
		
		Student tom = new Student();
		tom.setId(4);
		tom.setPassword("tom");
		tom.setUserName("tom");
		
		userData.add(william);
		userData.add(petter);
		userData.add(yuvia);
		userData.add(tom);
	}

	public List<Student> getUserData() {
		return userData;
	}

	public void setUserData(List<Student> userData) {
		this.userData = userData;
	}
}
