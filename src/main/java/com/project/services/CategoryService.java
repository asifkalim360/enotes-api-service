package com.project.services;

import java.util.List;

import com.project.dto.CategoryDto;
import com.project.dto.CategoryResponse;


public interface CategoryService {
	
	public Boolean saveCategory(CategoryDto categoryDto); 
	
	public List<CategoryDto> getAllCategory();

	public List<CategoryResponse> getActiveCategory();

	public CategoryDto getCategoryById(Integer id) throws Exception;

	public Boolean deleteCategoryById(Integer id);

	

	
	
}
