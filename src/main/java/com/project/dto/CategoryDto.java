package com.project.dto;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class CategoryDto {
	
	private Integer id; 
	
	@NotBlank(message = "Category name is required")
//	@Min(value=2)
//	@Max(value=110)
	private String name;
	
//	@NotBlank 
//	@Min(value=2)
//	@Max(value=500)
	private String description;
	
//	@NotNull
	private Boolean isActive;
	
	private Integer createdBy; 
	
	private Date createdOn; 
	
	private Integer updatedBy;
	
	private Date updatedOn;
}


/**
DTO sirf data carry karta hai

@NotBlank → validation

DTO me ApiResponse kabhi nahi daalna chahye.
*/
