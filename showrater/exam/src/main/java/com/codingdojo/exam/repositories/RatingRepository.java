package com.codingdojo.exam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codingdojo.exam.models.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long> {
	List<Rating> findAll();
	@Query(value="SELECT * from ratings WHERE show_id = ?1 ORDER BY score asc", nativeQuery=true)
    List<Rating> findRatingByScoreAsc(long id);
}
