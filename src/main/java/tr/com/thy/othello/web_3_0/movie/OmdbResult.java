package tr.com.thy.othello.web_3_0.movie;

import tr.com.thy.othello.web_3_0.entity.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Data
@ToString
@NoArgsConstructor
public class OmdbResult implements Serializable
{
	
	@JsonProperty( "Search" )
	private List< Movie > search;
	
	private String totalResults;
	
	@JsonProperty( "Response" )
	private String response;
}
