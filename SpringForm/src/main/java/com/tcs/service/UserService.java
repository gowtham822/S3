package com.tcs.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tcs.entity.User;

import jakarta.mail.MessagingException;

public interface UserService {
	
	public boolean saveData(User user);
	
	public List<User> getData();
	
	public boolean deleteData(Integer id);
	
	public boolean updateData(Integer id,User user);
	
	public User showData(Integer id);
	
	public void sendEmail(String to , String subject , String text) throws MessagingException;
	
	public boolean uploadFile(MultipartFile file, String bucketName,User user) throws IOException;

}
