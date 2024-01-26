package com.springboot.main.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.library.model.Category;

import com.springboot.main.library.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins=("http://localhost:3000"))
public class CategoryController {
	@Autowired
	private Logger logger;
	@Autowired
	private CategoryService categoryService;
	@PostMapping("/add")
	public Category postCategory(@RequestBody Category category) {
		 System.out.println(category);
		 logger.info("successfully posted");
		return categoryService.postCategory(category);
	}
	@GetMapping("/getall")
	public List<Category> getAll(@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		Pageable pageable =  PageRequest.of(page, size);
	
	    return categoryService.getAll(pageable) ;
	}


}
