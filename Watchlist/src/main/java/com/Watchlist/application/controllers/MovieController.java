package com.Watchlist.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.Watchlist.application.entity.Movie;
import com.Watchlist.application.service.DatabaseService;

import jakarta.validation.Valid;

@Controller
public class MovieController {

    @Autowired
    DatabaseService databaseService;

    @GetMapping("/watchlistItemForm")
    public String showWatchListForm(@RequestParam(required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("watchlistItem", new Movie());
        } else {
            model.addAttribute("watchlistItem", databaseService.getMovieById(id));
        }
        return "watchlistItemForm";
    }

    @PostMapping("/watchlistItemForm")
    public String submitWatchListForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "watchlistItemForm";
        }
        Integer id = movie.getId();
        if (id == null) {
            databaseService.create(movie);
        } else {
            databaseService.update(movie, id);
        }
        return "redirect:/watchlist";
    }


    @GetMapping("/watchlist")
    public String getWatchlist(Model model) {
        List<Movie> movieList = databaseService.getAllMovies();
        model.addAttribute("watchlistrows", movieList);
        model.addAttribute("noofmovies", movieList.size());
        return "watchlist";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Integer id) {
        databaseService.delete(id);
        return "redirect:/watchlist";
    }
}
