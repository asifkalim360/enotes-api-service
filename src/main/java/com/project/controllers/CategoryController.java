package com.project.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.CategoryDto;
import com.project.dto.CategoryResponse;
import com.project.entity.Category;
import com.project.exception.ResourceNotFoundException;
import com.project.services.CategoryService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/v1/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		Boolean saveCategory = categoryService.saveCategory(categoryDto);
		if(saveCategory)
		{
			return new ResponseEntity<>("Data Saved Successfully", HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<>("Something Went Wrong | Data do't save", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllCategory()
	{
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		
		if(CollectionUtils.isEmpty(allCategory))
		{
			return ResponseEntity.noContent().build();
		}
		else
		{
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}		
	}
	
	@GetMapping("/active")
	public ResponseEntity<?> getActiveCategory()
	{
		List<CategoryResponse> allActiveCategory = categoryService.getActiveCategory();
		
		if(CollectionUtils.isEmpty(allActiveCategory))
		{
			return ResponseEntity.noContent().build();
		}
		else
		{
			return new ResponseEntity<>(allActiveCategory, HttpStatus.OK);
		}		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDetailById(@PathVariable Integer id) throws Exception
	{
//		CategoryDto categoryDto = categoryService.getCategoryById(id);
//		if(ObjectUtils.isEmpty(categoryDto)) 
//		{
//			return new ResponseEntity<>("Category not found with ID: " + id, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(categoryDto, HttpStatus.OK);
//---------------------------------------------------------------------------------------------------------		
		
		try
		{
			CategoryDto categoryDtoById = categoryService.getCategoryById(id); 
			if(ObjectUtils.isEmpty(categoryDtoById)) 
			{
				return new ResponseEntity<>("category Not found with ID: " + id, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(categoryDtoById, HttpStatus.OK);
		}
		catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (Exception e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
//---------------------------------------------------------------------------------------------------------
		
//		CategoryDto categoryDtoById = categoryService.getCategoryById(id); 
//		if(ObjectUtils.isEmpty(categoryDtoById)) 
//		{
//			return new ResponseEntity<>("Internal Server Error", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(categoryDtoById, HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
		Boolean deleteCategoryById = categoryService.deleteCategoryById(id);
		if(ObjectUtils.isEmpty(deleteCategoryById))
		{
			return new ResponseEntity<>("Category Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Category Partially Deleted Successfully id: "+id, HttpStatus.OK);
		
		
//		Boolean deleteById = categoryService.deleteCategoryById(id); 
//		if(deleteById) {
//			return new ResponseEntity<>("Category Deleted Successfully for this: " + id, HttpStatus.OK);
//		}
//		return new ResponseEntity<>("Category Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	}
}
