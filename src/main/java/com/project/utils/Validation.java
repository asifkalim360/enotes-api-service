package com.project.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import com.project.dto.CategoryDto;

@Component
public class Validation {

    public void categoryValidation(CategoryDto categoryDto) {

        if (ObjectUtils.isEmpty(categoryDto)) {
            throw new IllegalArgumentException("category Object/Json should not be null or empty");
        }

        if (ObjectUtils.isEmpty(categoryDto.getName())) {
            throw new IllegalArgumentException("name field is empty or null");
        }
    }
}

