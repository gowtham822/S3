package com.tcs.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.entity.User;
import com.tcs.service.UserService;

import jakarta.mail.MessagingException;

@Controller
public class FormCapturing {
	
	boolean saveFile = true;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/Form16")
	public String showForm(Model model)
	{
		model.addAttribute("user",new User());
		
		return "user-form";
	}
	
	@PostMapping("/Form16")
	public String saveUser(@ModelAttribute User user,Model model, @RequestParam("file") MultipartFile file) throws MessagingException
	{
		boolean saveData = userService.saveData(user);
		
		 try {
			  
			  saveFile = userService.uploadFile(file,"resumesmycompany",user);
				
			} catch (IOException e) {
								
				System.out.println("File not found");
			}
		                  
		
		if((saveData==true) && (saveFile==true))
		
		{   
			
			String subject = "Welcome to Ashok IT  Mr./Ms. "+user.getName();
			
			String message = "<h1>Dear User,</h1><h2>Thank you for registering with us.Please Pay 1,00,000 Rs</h2>";
			
			String to = user.getEmail();
			
			userService.sendEmail(to, subject, message);
			
			model.addAttribute("msg", "User Saved Successfully , Please check your mail and File Uploaded SuccessFully");
			
			return "user-form";
		}
		
		else 
		{
			model.addAttribute("msg", "User Not Saved , Sorry!");
			
			return "user-form";
		}
			
	}
	
	@GetMapping("/getForm16")
	public String displayUsers(Model model)
	{
	   model.addAttribute("users", userService.getData());
	   
	   return "userList";
	}
    
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	
	public String deleteDetails( @PathVariable("id") Integer id,Model model)
	{
		boolean deleteData = userService.deleteData(id);
		
		if(deleteData)
			
			model.addAttribute("m1", "Data Deleted Successfully");
			
			else
				{
				model.addAttribute("m1", "Data Not Deleted Successfully");
				}
				
		       model.addAttribute("users", userService.getData());
		       
		       return "userList";
				
	}
	
	
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Integer id , Model model)
	{
		   User showData = userService.showData(id);
		   
		   model.addAttribute("user", showData);
		   
		   return "edit";
	}
	
	 @PostMapping("/edit/{id}")
	public String editForm(@PathVariable("id") Integer id,@ModelAttribute("user") User user,Model model)
	{
		
		user.setId(id);
		
		boolean saveData = userService.saveData(user);
		
		if(saveData)
			
		{
			model.addAttribute("m2", "Data Modified Successfully");
		}
		
		else
			
		{
			model.addAttribute("m2", "Data not Modified");
		}
		
		model.addAttribute("users", userService.getData());
		
		return "userList";
	}
}
