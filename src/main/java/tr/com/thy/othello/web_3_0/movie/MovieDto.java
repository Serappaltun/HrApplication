package tr.com.thy.othello.web_3_0.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto implements Serializable
{
	private String title;
	
	private String year;
	
}
