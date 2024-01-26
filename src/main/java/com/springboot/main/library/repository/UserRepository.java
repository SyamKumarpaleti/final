package com.springboot.main.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.springboot.main.library.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}