package tr.com.thy.othello.web_3_0.repository;

import tr.com.thy.othello.web_3_0.entity.Movie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;


@Repository
public interface MovieRepository extends CrudRepository< Movie, Long >
{
	
	@Query( "SELECT m FROM Movie m WHERE UPPER(m.title) LIKE CONCAT('%',UPPER(:title),'%')" )
	Collection< Movie > findMoviesByTitle( @Param( "title" ) String title );
	
	@Transactional
	@Modifying
	@Query( "DELETE FROM Movie m WHERE m.imdbId = :imdbID" )
	void deleteMovieByImdbId( @Param( "imdbID" ) String imdbID );
}