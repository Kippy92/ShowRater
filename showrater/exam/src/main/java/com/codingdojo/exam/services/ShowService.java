package com.codingdojo.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.exam.controllers.Ratings;
import com.codingdojo.exam.models.Show;
import com.codingdojo.exam.models.Rating;
import com.codingdojo.exam.repositories.ShowRepository;

@Service
public class ShowService {
	private final ShowRepository showRepository;
	
	public ShowService(ShowRepository showRepository) {
		this.showRepository = showRepository;
	}
	
	public List<Show> allShows() {
		return showRepository.findAll();
	}
	public List<Show> showAvgs(){
		return showRepository.findByTitleByAvgDesc();
	}
	public Show createShow(Show s) {
		return showRepository.save(s);
	}
	public Show findShow(long id) {
		Optional<Show> optionalShow = showRepository.findById(id);
		if(optionalShow.isPresent()) {
			return optionalShow.get();
		}
		else {
			return null;
		}
	}
	public Show findByTitle(String title) {
		return showRepository.findByTitle(title);
	}
	
	public Show updateShow(Long id, String title, String network) {
    	Optional<Show> optionalShow = showRepository.findById(id); 
         if(optionalShow.isPresent()) {
        	 Show show = optionalShow.get();
        	 show.setTitle(title);
        	 show.setNetwork(network);
             return showRepository.save(show);
         } else {
             return null;
         }
    }
	public Show updateAvg(Long id, List<Rating> ratings) {
		Optional<Show> optionalShow = showRepository.findById(id); 
        if(optionalShow.isPresent()) {
	       	Show show = optionalShow.get();
	       	double sum = 0;
			for(int i = 0; i < ratings.size(); i++) {
				sum += ratings.get(i).getScore();
		}
			show.setAvg(sum/ratings.size());
			return showRepository.save(show);
        } else {
            return null;
        }
	}
	
	public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
}
