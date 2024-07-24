package com.Watchlist.application.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Watchlist.application.entity.Movie;
import com.Watchlist.application.repository.MovieRepository;

@Service
public class DatabaseService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RatingService ratingService;

    public void create(Movie movie) {
        String ratingString = ratingService.getMovieRating(movie.getTitle());
        Float rating = null;

        if (ratingString != null) {
            try {
                rating = Float.parseFloat(ratingString);
            } catch (NumberFormatException e) {
                // Log the exception if necessary
                rating = null;
            }
        }

        if (rating == null || rating < 5.0 || rating > 10.0) {
            // If rating is missing or out of range, keep the user-provided rating
            Float userRating = movie.getRating();
            if (userRating == null || userRating < 5.0 || userRating > 10.0) {
                throw new IllegalArgumentException("Please provide a valid rating between 5.0 and 10.0.");
            }
        } else {
            movie.setRating(rating);
        }

        movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void update(Movie movie, Integer id) {
        Movie toBeUpdated = getMovieById(id);
        if (toBeUpdated != null) {
            toBeUpdated.setTitle(movie.getTitle());
            toBeUpdated.setComment(movie.getComment());
            toBeUpdated.setPriority(movie.getPriority());

            String ratingString = ratingService.getMovieRating(movie.getTitle());
            Float rating = null;

            if (ratingString != null) {
                try {
                    rating = Float.parseFloat(ratingString);
                } catch (NumberFormatException e) {
                    // Log the exception if necessary
                    rating = null;
                }
            }

            if (rating == null || rating < 5.0 || rating > 10.0) {
                // If rating is missing or out of range, keep the user-provided rating
                Float userRating = movie.getRating();
                if (userRating == null || userRating < 5.0 || userRating > 10.0) {
                    throw new IllegalArgumentException("Please provide a valid rating between 5.0 and 10.0.");
                } else {
                    toBeUpdated.setRating(userRating);
                }
            } else {
                toBeUpdated.setRating(rating);
            }

            movieRepository.save(toBeUpdated);
        }
    }

    public void delete(Integer id) {
        movieRepository.deleteById(id);
    }
}
