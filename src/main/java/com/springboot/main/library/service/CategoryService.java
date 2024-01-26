package com.springboot.main.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.library.model.Category;
import com.springboot.main.library.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Category postCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	public List<Category> getAll(Pageable pageable) {
		
		return categoryRepository.findAll();
	}

	public Category getOne(int catid) {
		Optional<Category> optional=categoryRepository.findById(catid);
		return optional.get() ;
	}

	public Category getbyname(String category) {
		
		return categoryRepository.getbyname(category);
	}

	

	
}
