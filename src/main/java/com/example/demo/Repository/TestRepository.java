package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

	public void deleteByTestname(String id);

}
