package com.codingdojo.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.exam.models.Rating;
import com.codingdojo.exam.models.Show;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.repositories.RatingRepository;


@Service
public class RatingService {
	private final RatingRepository ratingRepository;
	public RatingService(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}
	
	public List<Rating> allRatings() {
		return ratingRepository.findAll();
	}
	
	public List<Rating> ascRatings(long id) {
		return ratingRepository.findRatingByScoreAsc(id);
	}
	
	public Rating createRating(Rating r) {
		return ratingRepository.save(r);
	}
	
	public Rating findRating(Long id) {
		Optional<Rating> optionalRating = ratingRepository.findById(id);
		if(optionalRating.isPresent()) {
			return optionalRating.get();
		} else {
			return null;
		}
	}
	
	public void addRatingToShow(Rating r, Show s) {	
		r.setShow(s);
		this.ratingRepository.save(r);
	}
	
	public void addRatingToUser(Rating r, User u) {	
		r.setUser(u);
		this.ratingRepository.save(r);
	}
	
	public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
