package com.springboot.main.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.library.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.name =?1")
	Category getbyname(String category);

	

}
