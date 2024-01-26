package com.springboot.main.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.library.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer>{

	@Query("select a from Admin a where a.user.id=?1")
	Admin getAdmin(int id);
	


}