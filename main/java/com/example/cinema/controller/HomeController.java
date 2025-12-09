package com.example.cinema.controller;

import com.example.cinema.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(\"/\")
    public String home(@RequestParam(value = \"q\", required = false) String query, Model model) {
        model.addAttribute(\"movies\", movieService.searchMovies(query));
        model.addAttribute(\"query\", query == null ? \"\" : query);
        return \"index\";
    }
}
