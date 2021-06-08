	package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {

}
