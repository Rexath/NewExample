package com.spring.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.login.entity.User;
import com.spring.login.repository.UserRepository;

@Controller
public class UserController {
	// Constructor based Dependency Injection
	private UserRepository userRepository;

	public UserController() {

	}

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView hello(HttpServletResponse response, @ModelAttribute User user, BindingResult result) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView displayNewUserForm() {
		ModelAndView mv = new ModelAndView("addUser");
		mv.addObject("headerMessage", "Aggiungi dettagli nuovo utente");
		mv.addObject("user", new User());
		return mv;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView saveNewUser(@ModelAttribute User user, BindingResult result) {
		ModelAndView mv = new ModelAndView("home");

		System.out.println("STAMPAMI NOME " + user.getNome() + " E COGNOME " + user.getCognome());
		
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		User userCheck = userRepository.save(user);
		if (userCheck != null) {
			mv.addObject("message", "Nuovo utente aggiunto con successo");
		} else {
			return new ModelAndView("error");
		}

		return mv;
	}

}
