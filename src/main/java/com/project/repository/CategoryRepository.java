package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findByIsActiveAndIsDeletedFalse(Boolean isActive);

	Optional<Category> findByIdAndIsDeletedFalse(Integer id);

	List<Category> findAllByIsDeletedFalse();

	
}
