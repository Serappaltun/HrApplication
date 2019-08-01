package tr.com.thy.othello.web_3_0.request;

import lombok.Data;


@Data
public class MovieSaveRequest {
	
	private String imdbID;
	
	private String title;
	
	private String year;
	
	private String type;
	
	private String poster;
}
