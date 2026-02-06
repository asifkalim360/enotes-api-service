package com.project.services;

import java.util.List;

import com.project.dto.NotesDto;


public interface NotesService {
	
	public NotesDto saveNotes(NotesDto notesDto);
	
	public List<NotesDto> getAllNotes();

	public List<NotesDto> getActiveNotes();

	public NotesDto getNotesById(Integer id);

	public NotesDto deleteNotesById(Integer id);	
	
}
