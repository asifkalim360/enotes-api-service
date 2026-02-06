package com.project.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.dto.NotesDto;
import com.project.response.ApiResponse;
import com.project.services.NotesService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("api/v1/notes")
public class NotesController {
	
	private final NotesService notesService; 
	public NotesController(NotesService notesService)
	{
		this.notesService = notesService;
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<NotesDto> saveNotes(@Valid @RequestBody NotesDto notesDto) 
	{
		NotesDto saveNotes = notesService.saveNotes(notesDto); 
		return new ResponseEntity<>(saveNotes, HttpStatus.OK);
		
//		ApiResponse<NotesDto> apiResponse = new ApiResponse<>(true, "Data saved Successfully", saveNotes); 
//		
//		return ResponseEntity
//				.status(HttpStatus.CREATED)
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(apiResponse);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<NotesDto>>  getAllNotes()
	{
		List<NotesDto> allNotes = notesService.getAllNotes(); 
		return new ResponseEntity<>(allNotes, HttpStatus.OK);
	}
	
}