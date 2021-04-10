package GeekTextApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Objects;


/**
*  Title: GeekTextApp
*  Semester: CEN4010 - Spring 2021
*  @author 
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the nested book details class for the GeekTextApp; REST calls, logic, etc.
* 
*/
public class BookDetail
{
	// private variables
	private String rootURL;
	private String fullURL;
	private final RestTemplate restTemplate = new RestTemplate();
	private Book book;
	private List<Author> authors;
	private List<Review> reviews;
	
	// CHRIS' CODE GOES HERE
	public BookDetail(Integer bookID)
	{
		this.rootURL = GeekTextApp.ROOT_URL;
		GetBookInfo(bookID);
		GetAuthorInfo(bookID);		
		GetReviewInfo(bookID); 		//ERROR IS HERE wont let window open
		// do constructor
	}
	
	// REST AND OTHER LOGIC/METHODS
	
	// REST call for Book object
	private void GetBookInfo(int bookID)
	{
		fullURL = rootURL + "/books/query/viaproc/byBookId?bookID={id}";
		
		ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Book>>() {}
				, bookID
			  );
		
		// empty the book in case it is not our first search
		if(book != null)
		{
			book = null;
		}
		
		book = responseEntity.getBody().get(0);
	}
	
	
	
	
	private void GetReviewInfo(int bookID)
	{
		fullURL = rootURL + "/review/query/viaproc/bybookid?bookID={id}";
		
		ResponseEntity<List<Review>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Review>>() {}
				, bookID
			  );
		
		// empty the book in case it is not our first search
		if(reviews != null)
		{
			reviews = null;
		}
		
		reviews = responseEntity.getBody();
	}
	
	
	
	
	private void GetAuthorInfo(int bookID)
	{
		fullURL = rootURL + "/authors/query/viaproc/byBookID?bookID={id}";
		
		ResponseEntity<List<Author>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Author>>() {}
				, bookID
			  );
		
		// empty the book in case it is not our first search
		if(authors != null)
		{
			authors = null;
		}
		
		authors = responseEntity.getBody();
	}
	
	// get title
	public String getTitle()
	{
		return book.getTitle();
	}
	
	// Author info
	public int getAuthorCount()
	{
		return authors.size();
	}
	
	public Author getAuthor(int i)
	{
		return authors.get(i);
	}
	
	public String getAuthorName(int i)
	{
		return authors.get(i).getAuthorName();
	}
	
	public String getAuthorBio(int i)
	{
		return authors.get(i).getBio();
	}

	public Book getBook() {
		return book;
	}

	public Review getReviews(int i) {
		return reviews.get(i);
	}
	
	public int  getReviewCount() {	
		return reviews.size();	
	}
	
	public void printAll() {
		for(int i = 0; i < reviews.size() && i < 25; i++) {
			System.out.println(reviews.get(i).toString());
		}
	}
	
	
}