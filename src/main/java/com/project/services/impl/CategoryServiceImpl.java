package com.project.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.dto.CategoryDto;
import com.project.dto.CategoryResponse;
import com.project.entity.Category;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.CategoryRepository;
import com.project.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	// CREATE CATEGORY:	
	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		
		Category category = mapper.map(categoryDto, Category.class);
		if(ObjectUtils.isEmpty(category.getId()))
		{
			category.setIsDeleted(false);
		}
		else 
		{
			updateCategory(category);
		}
		Category saveCategory = categoryRepository.save(category);	
		return mapper.map(saveCategory, CategoryDto.class);
	}	

	private void updateCategory(Category category) {
		Optional<Category> findById = categoryRepository.findById(category.getId());
		if(findById.isPresent())
		{
			Category existCategory = findById.get(); 
			category.setCreatedBy(existCategory.getCreatedBy()); 
			category.setCreatedOn(existCategory.getCreatedOn());
			category.setIsDeleted(existCategory.getIsDeleted());
		}
	}
	
	// GET ALL CATEGORY:
	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepository.findAllByIsDeletedFalse();
		List<CategoryDto> categoryDtoList = categories.stream().map(cat-> mapper.map(cat, CategoryDto.class)).toList();
;		return categoryDtoList;
	}
	
	// GET ACTIVE CATEGORY:
	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = categoryRepository.findByIsActiveAndIsDeletedFalse(true); 
		List<CategoryResponse> categoryResponseList = categories
			.stream()
			.map(cat -> mapper.map(cat, CategoryResponse.class))
			.toList();
		return categoryResponseList;
	}

	// GET CATEGORY	BY ID:
	@Override
	public CategoryDto getCategoryById(Integer id) {
		
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
				return mapper.map(category, CategoryDto.class);
	}

	// DETELE CATEGORY BY ID:
	@Override
	public CategoryDto deleteCategoryById(Integer id) {

	    Category category = categoryRepository
	            .findByIdAndIsDeletedFalse(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Category", "id", id)
	            );

	    // 🔥 actual soft delete
	    category.setIsDeleted(true);
	    // Then save all category in DB.
	    Category deletedCategory = categoryRepository.save(category);

	    return mapper.map(deletedCategory, CategoryDto.class);
	}

	
	
}
