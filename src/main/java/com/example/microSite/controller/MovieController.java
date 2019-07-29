package com.example.microSite.controller;


import com.example.microSite.entity.Movie;
import com.example.microSite.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@AllArgsConstructor
@RequestMapping( "/movie" )
@CrossOrigin( origins = "http://localhost:4200", maxAge = 3600 )
public class MovieController
{
	@Autowired
	private final MovieService movieService;
	
	@GetMapping( "/findAllMovies" )
	ResponseEntity< Iterable< Movie > > getMovies()
	{
		
		final Iterable< Movie > movies = movieService.findAll();
		
		return ResponseEntity.ok( movies );
		
	}
	
	@GetMapping( "/findByTitle/{title}" )
	ResponseEntity< Collection< Movie > > getMoviesByTitle( @Validated @PathVariable( "title" ) String title )
	{
		
		final Collection< Movie > moviesByTitle = movieService.findByMovieTitle( title );
		
		return ResponseEntity.ok( moviesByTitle );
		
	}
	
	
	@DeleteMapping( "/deleteMovie/{imdbID}" )
	void deleteMovie( @Validated @PathVariable( "imdbID" ) String imdbID )
	{
		movieService.deleteMovie( imdbID );
		
	}
}
