package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

	public void deleteByName(String id);
	
	

}
