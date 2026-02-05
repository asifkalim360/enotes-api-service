package com.project.controllers;


import org.springframework.http.MediaType;
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
import com.project.response.ApiResponse;
import com.project.services.CategoryService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
@RequestMapping(
		value = "/api/v1/category",
	    produces = MediaType.APPLICATION_JSON_VALUE
)
public class CategoryController {
	
	private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
	

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<CategoryDto>> saveCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto savedDto = categoryService.saveCategory(categoryDto);
		
		ApiResponse<CategoryDto> response =
	            new ApiResponse<>(true, "Data Saved Successfully", savedDto);

		return ResponseEntity
		        .status(HttpStatus.CREATED)
		        .contentType(MediaType.APPLICATION_JSON)
		        .body(response);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory()
	{
		List<CategoryDto> allCategory = categoryService.getAllCategory();

	    ApiResponse<List<CategoryDto>> response =
	            new ApiResponse<>(true, "Data fetched successfully", allCategory);

//	    return new ResponseEntity<>(response, HttpStatus.OK);
	    return ResponseEntity
		        .status(HttpStatus.OK)
		        .contentType(MediaType.APPLICATION_JSON)
		        .body(response);
		
	}
	
	@GetMapping("/active")
	public ResponseEntity<?> getActiveCategory()
	{		
		List<CategoryDto> allActiveCategory = categoryService.getAllCategory();

	    ApiResponse<List<CategoryDto>> response =
	            new ApiResponse<>(true, "Data fetched successfully", allActiveCategory);

//	    return new ResponseEntity<>(response, HttpStatus.OK);
	    return ResponseEntity
		        .status(HttpStatus.OK)
		        .contentType(MediaType.APPLICATION_JSON)
		        .body(response);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> getCategoryDetailById(@PathVariable Integer id) throws Exception
	{
	    CategoryDto categoryById = categoryService.getCategoryById(id);

	    ApiResponse<CategoryDto> response =
	    		new ApiResponse<>(true, "Category Get successfully with id : " + id, categoryById);
	    return ResponseEntity
		        .status(HttpStatus.OK)
		        .contentType(MediaType.APPLICATION_JSON)
		        .body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Object>> deleteCategoryById(@PathVariable Integer id)
	{
	    CategoryDto deleteCategoryById = categoryService.deleteCategoryById(id);

	    ApiResponse<Object> response =
	            new ApiResponse<>(true, "Category deleted successfully with id : "+deleteCategoryById, null);

	    return ResponseEntity
		        .status(HttpStatus.OK)
		        .contentType(MediaType.APPLICATION_JSON)
		        .body(response);
	}
	
}
