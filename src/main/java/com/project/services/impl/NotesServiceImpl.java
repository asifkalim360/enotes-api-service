package com.project.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.dto.NotesDto;
import com.project.dto.NotesDto.CategoryDto;
import com.project.entity.Category;
import com.project.entity.Notes;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.CategoryRepository;
import com.project.repository.NotesRepository;
import com.project.services.NotesService;

@Service 
public class NotesServiceImpl implements NotesService {

	private final NotesRepository notesRepository;
	private final CategoryRepository categoryRepository;
	private final ModelMapper mapper; 

    public NotesServiceImpl(NotesRepository notesRepository, ModelMapper mapper, CategoryRepository categoryRepository) {
        this.notesRepository = notesRepository;
        this.categoryRepository = categoryRepository;
        this.mapper=mapper;
    }
    
	// CREATE NOTES.
	@Override
	public NotesDto saveNotes(NotesDto notesDto) {
		
		// category validation. 
//		Integer categoryId = notesDto.getCategory().getId();
		checkCategoryExist(notesDto.getCategory());
		Notes notes = mapper.map(notesDto, Notes.class); 
		Notes noteSaved = notesRepository.save(notes);
		
		return mapper.map(noteSaved, NotesDto.class);
	}

	private void checkCategoryExist(CategoryDto category) {
		
		Category orElseThrow = categoryRepository.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("category", "id", category));
	}

	@Override
	public List<NotesDto> getAllNotes() {
		
		return notesRepository.findAll().stream().map(note -> mapper.map(note, NotesDto.class)).toList();
	
	}

	@Override
	public List<NotesDto> getActiveNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotesDto getNotesById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotesDto deleteNotesById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}