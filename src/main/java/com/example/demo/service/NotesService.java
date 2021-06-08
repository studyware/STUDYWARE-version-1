package com.example.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.Notes;
import com.example.demo.Repository.NotesRepository;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;


@Service
public class NotesService {
	
	
	@Autowired
	public NotesRepository notesRepository;
	
	
	public Notes store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    Notes FileDB = new Notes(fileName, file.getContentType(), file.getBytes());

	    return notesRepository.save(FileDB);
	  }
	
	public Notes storesub(MultipartFile file,int grade,String subject) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    Notes FileDB = new Notes(fileName, file.getContentType(), file.getBytes(),grade,subject);

	    return notesRepository.save(FileDB);
	  }

	
	
		  public Stream<Notes> getAllFiles() {
		    return notesRepository.findAll().stream();
		  }



		  public Notes getFile(String id) {
			    return notesRepository.findById(Integer.parseInt(id)).get();
			  }

		public void deleteNote(String id) {
			
			notesRepository.deleteByName(id);
			
		}
}
