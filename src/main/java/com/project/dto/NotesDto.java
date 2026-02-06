package com.project.dto;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor 
@NoArgsConstructor
public class NotesDto {
	
	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	private String title; 
	
	@NotBlank(message = "Description is required")
	@Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters")
	private String description; 
	
	@NotNull(message = "Category is required")
	@Valid   // Nested object validation ke liye
	private CategoryDto category;
	
//	@NotNull(message = "CreatedBy is required")
	@Positive(message = "CreatedBy must be a valid user id")
	private Integer createdBy; 
	
	// 🔥 Ye normally client se nahi lena chahiye
	// Ye backend automatically set karega
	private LocalDateTime createdOn; 
	
	private Integer updatedBy;
	
	private LocalDateTime updatedOn;
	
	
	@Getter 
	@Setter
	@AllArgsConstructor 
	@NoArgsConstructor
	public static class CategoryDto {
		
		@NotNull(message = "Category id is required")
		@Positive(message = "Category id must be greater than 0")
		private Integer id; 
	}
}
