package GeekTextApp;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
*  Title: GenreList
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the genre list for the GeekText application group
* project for group 9
* 
*/

@SpringBootApplication
public class GenreList
{
	// private variables
	private String rootURL;
	private String fullURL;
	private List<Genre> genres;
	private final RestTemplate restTemplate = new RestTemplate();
	
	// constructor
	public GenreList(String rootURL)
	{
		this.rootURL = rootURL;
		ListGenres();
	}
	
	// get list from REST service
	public void ListGenres()
	{
		fullURL = rootURL + "/genre/query/viaproc/genrenames";
		
		ResponseEntity<List<Genre>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Genre>>() {}
			  );

		// empty the list in case it is not our first search
		if(genres != null)
		{
			genres.clear();
		}
		
		genres = responseEntity.getBody();
	}
	
	// get List
	public List<Genre> ListGenreNames()
	{
		return genres;
	}
	
	// get String array with blank/0 at the top (for combo box)
	public String[] FillGenreComboBox()
	{
		int size = genres.size();
		
		String[] genreArray = new String[size];
		
		for(int i = 0; i < size; i++)
		{
			genreArray[i] = genres.get(i).getGenreName();
		}		
		
		return genreArray;
	}
}