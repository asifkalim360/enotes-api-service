package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer>
{
	
}