package com.ShankarBrand.controller;

import com.ShankarBrand.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by horijon on 12/08/2017.
 */
@Controller
@SessionAttributes("movie")
@RequestMapping(value = "/")
public class DefaultController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@ModelAttribute("movie") Movie movie, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("default");
        modelAndView.addObject("movie", movie);
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public void handleRequestPOST(Movie movie, HttpSession httpSession){
        httpSession.setAttribute("movie", movie);
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ModelAndView handleRequestPOST(Movie movie, HttpSession httpSession){
//        httpSession.setAttribute("movie", movie);
//        ModelAndView modelAndView = new ModelAndView("default");
//        modelAndView.addObject("movie", movie);
//        return modelAndView;
//    }

    @ModelAttribute("movie")
    public Movie getMovie (HttpServletRequest request) {
        Movie movie =  new Movie();
        movie.setTitle("People Places Things");
        movie.setReleaseDate("14/08/2015");
        movie.setDuration("85 minutes");
        movie.setGenre("Comedy");
        movie.setSynopsis("Will Henry is a newly single graphic novelist balancing parenting his  young twin daughters and a classroom full of students while exploring  and navigating the rich complexities of new love and letting go of the  woman who left him.");
        return movie;
    }
}
