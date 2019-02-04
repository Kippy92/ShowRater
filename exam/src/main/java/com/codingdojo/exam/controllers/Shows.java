package com.codingdojo.exam.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.exam.services.RatingService;
import com.codingdojo.exam.services.ShowService;
import com.codingdojo.exam.services.UserService;
import com.codingdojo.exam.models.Show;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.models.Rating;

@Controller
public class Shows {
	private final ShowService showService;
	private final RatingService ratingService;
	private final UserService userService;
	public Shows(ShowService showService, RatingService ratingService, UserService userService) {
		this.showService = showService;
		this.ratingService = ratingService;
		this.userService = userService;
	}
	@RequestMapping("/new")
    public String newShow(@ModelAttribute("show") Show show) {
        return "newShow.jsp";
    }
	@RequestMapping(value="/shows", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("show") Show show, BindingResult result, RedirectAttributes flash) {
		if (result.hasErrors()) {
            return "newShow.jsp";
        } 
		else {
            showService.createShow(show);
            return "redirect:/main";
    	}
    }
	@RequestMapping("/shows/{id}")
    public String showShow(Model model, @PathVariable("id") long id, @ModelAttribute("rating") Rating rating,  HttpSession session) {
    	Show show = showService.findShow(id);
    	List<Rating> r = ratingService.ascRatings(id);
    	User u = (User) session.getAttribute("user");
    	model.addAttribute("show", show);
    	model.addAttribute("rating", rating);
    	model.addAttribute("r", r);
    	session.setAttribute("user", u);
        return "show.jsp";
    }
	@RequestMapping("/shows/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Show show = showService.findShow(id);
        model.addAttribute("show", show);
        return "editShow.jsp";
    }
	@RequestMapping(value="/shows/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("shows") Show show, BindingResult result) {
        if (result.hasErrors()) {
            return "editShow.jsp";
        } else {
            showService.updateShow(show.getId(), show.getTitle(), show.getNetwork());
            return "redirect:/main";
        }
    }
	@RequestMapping(value="/shows/{id}", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id) {
        showService.deleteShow(id);
        return "redirect:/main";
    }
	
	@RequestMapping(value="/ratings", method=RequestMethod.POST)
	public String createRating(Model model, HttpSession session, @Valid @ModelAttribute("rating") Rating rating, @RequestParam(value="show_id") Long show_id, BindingResult result) {
		ratingService.createRating(rating);
		Rating r = this.ratingService.findRating(rating.getId());
		Show s = this.showService.findShow(show_id);
		User u = (User) session.getAttribute("user");
		session.setAttribute("user", u);
		ratingService.addRatingToShow(r, s);
		ratingService.addRatingToUser(r, u);
		userService.addUserToShow(u, s);
		showService.updateAvg(s.getId(), s.getRatings());
		return "redirect:/main";
	}
}
