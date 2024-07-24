package com.Watchlist.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Watchlist.application.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}

