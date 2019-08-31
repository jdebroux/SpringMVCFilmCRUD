package com.skilldistillery.film.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	FilmDAO dao;
	
	@RequestMapping(path = "getFilmsID.do", params = "id", method = RequestMethod.GET)
	public ModelAndView getFilmByID(int id) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.findFilmById(id);
		System.err.println(film);
		mv.addObject("filmById", film);
		mv.setViewName("WEB-INF/filmIdResult.jsp");
		return mv;
	}
	@RequestMapping(path = "getFilmsKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView getFilmByKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		List <Film> films = dao.findFilmsByKeyword(keyword);
		mv.addObject("filmByKeyword", films);
		mv.setViewName("WEB-INF/filmKeywordResult.jsp");
		return mv;
	}
	@RequestMapping(path = "ADDFILM.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		dao.createFilm(film);
		redir.addFlashAttribute("filmAdd", film);
		mv.setViewName("redirect:filmAdded.do");
		return mv;
	}
	
	@RequestMapping(path = "filmAdded.do", method = RequestMethod.GET)
	public ModelAndView filmAdded() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		return mv;
	}
}
