//package com.project.utils;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import com.project.dto.CategoryDto;
//
//@Component
//public class Validation {
//	
//	Map<String, Object> error = new LinkedHashMap<>();
//
//    public void categoryValidation(CategoryDto categoryDto) {
//
//        if (ObjectUtils.isEmpty(categoryDto)) 
//        {
//            throw new IllegalArgumentException("category Object/Json should not be null or empty");
//        }
//        else 
//        { 
//        	// VALIDATION NAME FIELD.
//        	if (ObjectUtils.isEmpty(categoryDto.getName())) {
//                error.put("name", "name field is empty or null");
//            }
//        	else 
//        	{
//        		if(categoryDto.getName().length() < 3)
//        		{
//        			error.put("name", "name length min 10");
//        		}
//        		if(categoryDto.getName().length() > 100)
//        		{
//        			error.put("name", "name length max 50");
//        		}
//        	}
//        	
//        	// VALIDATION DESCRIPTION FIELD.
//        	if (ObjectUtils.isEmpty(categoryDto.getDescription())) {
//                error.put("description", "description field is empty or null");
//            }
////        	else 
////        	{
////        		if(categoryDto.getDescription().length() < 5)
////        		{
////        			error.put("description", "description length min 10");
////        		}
////        		if(categoryDto.getDescription().length() > 5000)
////        		{
////        			error.put("description", "description length max 50");
////        		}
////        	}
//        	
//        	// VALIDATION iSACTIVE FIELD.
//        	if (ObjectUtils.isEmpty(categoryDto.getIsActive())) {
//                error.put("isActive", "isActive field is empty or null");
//            }
//        	else 
//        	{
//        		if(!categoryDto.getIsActive() == Boolean.TRUE || !categoryDto.getIsActive() == Boolean.FALSE)
//        		{
//        			 error.put("isActive", "isActive field is invalid");
//        		}
//        	}
//        	
//        }
//        
////        if(!error.isEmpty())
////        {
////        	throw new IllegalArgumentException()
////        }
//        
//    }
//}
//
