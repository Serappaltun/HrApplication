package tr.com.thy.othello.web_3_0.controller;


import tr.com.thy.othello.web_3_0.entity.Movie;
import tr.com.thy.othello.web_3_0.movie.MovieDto;
import tr.com.thy.othello.web_3_0.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tr.com.thy.othello.web_3_0.request.MovieSaveRequest;

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
	
	
	@GetMapping( "/findByType/{type}" )
	ResponseEntity< Collection< MovieDto > > findMovieByMovieType( @Validated @PathVariable( "type" ) String type )
	{
		
		final Collection< MovieDto > moviesByTitle = movieService.findMovieByMovieType( type );
		
		return ResponseEntity.ok( moviesByTitle );
		
	}
	
	
	@DeleteMapping( "/deleteMovie/{imdbID}" )
	void deleteMovie( @Validated @PathVariable( "imdbID" ) String imdbID )
	{
		movieService.deleteMovie( imdbID );
		
	}
	
	@PostMapping
	ResponseEntity< Movie > saveMovie( @Validated @RequestBody MovieSaveRequest saveRequest )
	{
		final Movie saveMovie = movieService.saveMovie( saveRequest );
		return ResponseEntity.ok( saveMovie );
	}
}
