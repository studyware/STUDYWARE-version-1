package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Model.Notes;
import com.example.demo.Model.NotesForm;
import com.example.demo.Model.Residue;
import com.example.demo.Model.Test;
import com.example.demo.Repository.NotesRepository;
import com.example.demo.message.ResponseFile;
import com.example.demo.message.ResponseMessage;
import com.example.demo.service.NotesService;

@Controller
public class NotesController {
	
	
	@Autowired
	public  NotesService notesService;
	
	
	@Autowired
	public NotesRepository notesRepository;
	
	
	
	@RequestMapping("/neet")
	public String class9(Model model) {
		
		  Stream<Notes> language =notesService.getAllFiles();
		List<Notes> allNotes = language.collect(Collectors.toList());
		List<Notes> class9notes=new ArrayList<>();
		
		for(Notes note : allNotes)
		{
			if(note.getGrade()==9)
			{
				class9notes.add(note);
				
				System.out.println(note.getName());
			}
		}
		
		
		 model.addAttribute("notes", class9notes);
		  
	         
		
		return "class9";
	}
	
	
	@RequestMapping("/class10")
	public String c10(Model model) {

		  Stream<Notes> language =notesService.getAllFiles();
		List<Notes> allNotes = language.collect(Collectors.toList());
		List<Notes> class10notes=new ArrayList<>();
		
		for(Notes note : allNotes)
		{
			if(note.getGrade()==10)
			{
				class10notes.add(note);
				
				System.out.println(note.getName());
			}
		}
		
		
		 model.addAttribute("notes", class10notes);
		  
	         
		
		return "smp";
	}
	
	
	
	@RequestMapping("/c11c")
	public String c11c(Model model) {
		

		  Stream<Notes> language =notesService.getAllFiles();
		List<Notes> allNotes = language.collect(Collectors.toList());
		List<Notes> class11cnotes=new ArrayList<>();
		
		for(Notes note : allNotes)
		{
			if(note.getGrade()==11 &&note.getSubject().equalsIgnoreCase("Commerce"))
			{
				class11cnotes.add(note);
				
				System.out.println(note.getName());
			}
		}
		
		
		 model.addAttribute("notes", class11cnotes);
		  
	         
	         
		
		return "class11com";
	}
	
	
	@RequestMapping("/c11s")
	public String c1s(Model model) {
		
		  Stream<Notes> language =notesService.getAllFiles();
			List<Notes> allNotes = language.collect(Collectors.toList());
			List<Notes> class11snotes=new ArrayList<>();
			
			for(Notes note : allNotes)
			{
				if(note.getGrade()==11 &&note.getSubject().equalsIgnoreCase("Science"))
				{
					class11snotes.add(note);
					
					System.out.println(note.getName());
				}
			}
			
			
			 model.addAttribute("notes", class11snotes);
			  
		         
	         
		
		return "class11sci";
	}
	
	
	@RequestMapping("/c12s")
	public String c12s(Model model) {
		

		  Stream<Notes> language =notesService.getAllFiles();
			List<Notes> allNotes = language.collect(Collectors.toList());
			List<Notes> class12snotes=new ArrayList<>();
			
			for(Notes note : allNotes)
			{
				if(note.getGrade()==12 &&note.getSubject().equalsIgnoreCase("Science"))
				{
					class12snotes.add(note);
					
					System.out.println(note.getName());
				}
			}
			
			
			 model.addAttribute("notes", class12snotes);
			  
		         
		return "class12sci";
	}
	
	@RequestMapping("/c12c")
	public String c12c(Model model) {
		
		 Stream<Notes> language =notesService.getAllFiles();
			List<Notes> allNotes = language.collect(Collectors.toList());
			List<Notes> class12cnotes=new ArrayList<>();
			
			for(Notes note : allNotes)
			{
				if(note.getGrade()==12 &&note.getSubject().equalsIgnoreCase("Commerce"))
				{
					class12cnotes.add(note);
					
					System.out.println(note.getName());
				}
			}
			
			
			 model.addAttribute("notes", class12cnotes);
			  
		
		return "class12com";
	}
	
	
	/*
	 * @PostMapping("/upload") public ResponseEntity<ResponseMessage>
	 * uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("grade")
	 * int grade,@RequestParam("sub") String subject) { String message = ""; try {
	 * notesService.storesub(file,grade,subject);
	 * 
	 * message = "Uploaded the file successfully: " + file.getOriginalFilename();
	 * return ResponseEntity.status(HttpStatus.OK).body(new
	 * ResponseMessage(message)); } catch (Exception e) { message =
	 * "Could not upload the file: " + file.getOriginalFilename() + "!";
	 * System.out.println(e.getLocalizedMessage()); return
	 * ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
	 * ResponseMessage(message)); } }
	 */
	
	  @PostMapping("/upload")
	  public String uploadFile(@ModelAttribute("notes") NotesForm note,Model model,RedirectAttributes attributes) {
	    String message = "";
	    
	  
	    try {
	      notesService.storesub(note.getFile(),note.getGrade(),note.getSubject());

	      attributes.addFlashAttribute("message", "You successfully uploaded " + note.getFile().getOriginalFilename() + '!');
	      return "redirect:/";
	    } catch (Exception e) {
	      message = "Could not upload the file: " + note.getFile().getOriginalFilename() + "!";
	      System.out.println(e.getLocalizedMessage());
			/*
			 * return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
			 * ResponseMessage(message));
			 */
	      
	      attributes.addFlashAttribute("message", "Could not upload the file" + note.getFile().getOriginalFilename() + '!');
	      
	      return "redirect:/addNotes";
	    }
	  }
	  
	  
	  @GetMapping("/files")
	  public ResponseEntity<List<ResponseFile>> getListFiles() {
	    List<ResponseFile> files = notesService.getAllFiles().map(dbFile -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .path(dbFile.getId().toString())
	          .toUriString();

	      return new ResponseFile(
	          dbFile.getName(),
	          fileDownloadUri,
	          dbFile.getType(),
	          dbFile.getData().length,
	          dbFile.getGrade(),
	          dbFile.getSubject());
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }

	  
	  @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
	    Notes fileDB = notesService.getFile(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
	        .body(fileDB.getData());
	  }

	  @RequestMapping("/addNotes")
		public String addNotes(Model model) {
		  NotesForm noteForm = new NotesForm();
		  model.addAttribute("notes", noteForm);
			
			return "addNotes";
		}
	  
	  
	  
		 @GetMapping("/delteNote")
			public String getDeletetest(Model model) {
				
				Residue testname = new Residue();
			   model.addAttribute("name", testname);

				return "delete_Note";
			}
			
			@PostMapping("/deleteNote")
			@Transactional
			public  String deleteTest(@ModelAttribute("name") Residue notename) {
				
				notesService.deleteNote(notename.getId());
				
				return "redirect:/admin";
			}


	  
	  
}
