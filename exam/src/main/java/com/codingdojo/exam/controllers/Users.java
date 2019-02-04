package com.codingdojo.exam.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.exam.models.Show;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.services.UserService;
import com.codingdojo.exam.services.ShowService;
import com.codingdojo.exam.validations.UserValidator;

@Controller
public class Users {
	
	private UserValidator userValidator;
	private UserService userService;
	private ShowService showService;
	
	public Users (UserValidator userValidator, UserService userService, ShowService showService) {
		this.userValidator = userValidator;
		this.userService = userService;
		this.showService = showService;
	}

	@RequestMapping("/")
	public String logreg() {
		return "logreg.jsp";
	}
	
	@PostMapping("/register")
	public String register(@RequestParam Map<String, String> body, RedirectAttributes flash, HttpSession session) {
		HashMap<String, Object> response = this.userValidator.validate(body);
		
		if ((boolean) response.get("valid")) {
			User user = this.userService.createUser(body);
			session.setAttribute("user", user);
			return "redirect:/main";
		} else {
			flash.addFlashAttribute("errors", response);
			return "redirect:/";
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> body, HttpSession session, RedirectAttributes flash) {
		HashMap<String, Object> response = this.userValidator.authenticate(body);
		if ((boolean) response.get("valid")) {
			User user = (User) response.get("user");
			session.setAttribute("user", user);
			return "redirect:/main";
		} else {
			flash.addFlashAttribute("errors", response);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/main")
	public String index(Model model, HttpSession session, RedirectAttributes flash, @ModelAttribute("show") Show show) {
		User user = (User) session.getAttribute("user");
		List<Show> shows = showService.allShows();
        model.addAttribute("shows", shows);
		if (user == null) {
			HashMap<String, Object> map = new HashMap<String, Object> ();
			map.put("login", "You must log in first");
			flash.addFlashAttribute("errors", map);
			return "redirect:/";
		}
		System.out.println(user.getEmail());
		model.addAttribute("user", user);

		return "index.jsp";
	}
	
}