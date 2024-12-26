package com.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.User;

public interface UserRepo extends JpaRepository<User,Integer> {

	public User getUserById(Integer id);

}
