package com.codingdojo.exam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codingdojo.exam.models.Show;

public interface ShowRepository extends CrudRepository <Show, Long> {
	List<Show> findAll();
    
	Show findByTitle(String search);
	
	Show findByNetwork(String search);
	
    @Query(value="SELECT * from shows ORDER BY avg desc", nativeQuery=true)
    List<Show> findByTitleByAvgDesc();
    
    Long deleteByTitleStartingWith(String search);
}
