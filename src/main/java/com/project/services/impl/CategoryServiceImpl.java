package com.project.services.impl;

import java.util.Date;
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
	
	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		
//		Category category = new Category(); 
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription()); 
//		category.setIsActive(categoryDto.getIsActive());
		
		Category category = mapper.map(categoryDto, Category.class);
		if(ObjectUtils.isEmpty(category.getId()))
		{
			category.setIsDeleted(false);
			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
		}
		else 
		{
			updateCategory(category);
		}

		Category saveCategory = categoryRepository.save(category);
		if(ObjectUtils.isEmpty(saveCategory))
		{
			return false;
		}	
		return true;
	}	

	private void updateCategory(Category category) {
		Optional<Category> findById = categoryRepository.findById(category.getId());
		if(findById.isPresent())
		{
			Category existCategory = findById.get(); 
			category.setCreatedBy(existCategory.getCreatedBy()); 
			category.setCreatedOn(existCategory.getCreatedOn());
			category.setIsDeleted(existCategory.getIsDeleted());
		
			category.setUpdatedBy(1);
			category.setUpdatedOn(new Date());
		}
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepository.findAllByIsDeletedFalse();
		List<CategoryDto> categoryDtoList = categories.stream().map(cat-> mapper.map(cat, CategoryDto.class)).toList();
;		return categoryDtoList;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = categoryRepository.findByIsActiveAndIsDeletedFalse(true); 
		List<CategoryResponse> categoryResponseList = categories
			.stream()
			.map(cat -> mapper.map(cat, CategoryResponse.class))
			.toList();
		return categoryResponseList;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) throws Exception {
		Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found with id" + id));
		if(!ObjectUtils.isEmpty(category)) {
			category.getName().toUpperCase();
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}

	@Override
	public Boolean deleteCategoryById(Integer id) {
		Optional<Category> findByIdCategory = categoryRepository.findById(id);
		if(findByIdCategory.isPresent()) {
			Category category = findByIdCategory.get(); 
			category.setIsDeleted(true); 
			categoryRepository.save(category); 
			return true;
		}
		return false;
	}




}
