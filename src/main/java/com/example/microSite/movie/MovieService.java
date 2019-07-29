package com.example.microSite.movie;

import com.example.microSite.entity.Movie;
import com.example.microSite.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@CacheConfig( cacheNames = { "movie" } )
public class MovieService
{
	
	@Value( "${tr.com.movie.omdbApiKey}" )
	String API_KEY;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	public Iterable< Movie > findAll()
	{
		return movieRepository.findAll();
	}
	
	@Cacheable
	public Collection< Movie > findByMovieTitle( String title )
	{
		
		Collection< Movie > moviesByTitle = new ArrayList<>();
		
		
		moviesByTitle = movieRepository.findMoviesByTitle( title );
		if ( !CollectionUtils.isEmpty( moviesByTitle ) )
		{
			log.info( "get from db. key:" + title + "| count:" + moviesByTitle.size() );
			return moviesByTitle;
		}
		
		moviesByTitle = getMovieFromOmdb( title );
		if ( !CollectionUtils.isEmpty( moviesByTitle ) )
		{
			log.info( "get from api. key:" + title + "| count:" + moviesByTitle.size() );
			movieRepository.saveAll( moviesByTitle );
			return moviesByTitle;
		}
		
		
		return new ArrayList<>();
		
	}
	
	private Collection< Movie > getMovieFromOmdb( String keyword )
	{
		
		final String uri = "http://www.omdbapi.com/?apikey={apikey}&s={s}";
		
		final Map< String, String > params = new HashMap<>();
		params.put( "s", keyword );
		params.put( "apikey", API_KEY );
		
		try
		{
			final RestTemplate restTemplate = new RestTemplate();
			final ResponseEntity< String > response = restTemplate.getForEntity( uri, String.class, params );
			final OmdbResult omdbResult = objectMapper.readValue( response.getBody(), OmdbResult.class );
			
			return omdbResult.getSearch();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public void deleteMovie( String imdbID )
	{
		movieRepository.deleteMovieByImdbId( imdbID );
		
	}
}

