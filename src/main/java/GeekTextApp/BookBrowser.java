package GeekTextApp;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
*  Title: BookBrowser
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the book browser for the GeekText application group
* project for group 9
* 
*/

@SpringBootApplication
public class BookBrowser
{
	// private variables
	private int curPage;
	private int maxPage;
	private int rowsPerPage;
	private String rootURL;
	private String fullURL;
	private List<Book> books;
	private final RestTemplate restTemplate = new RestTemplate();
	
	// constructors
	public BookBrowser(String rootURL)
	{
		// root URL
		this.rootURL = rootURL;
		
		this.rowsPerPage = 20;
		this.curPage = 1;
		
		// default to top sellers until other option is picked
		ListBooksByTopSellers();
	}
	
	// functions
	// main browsing return
	public JTable BrowseBooksByPage()
	{
		JTable returnTable;
		
		List<Book> curPageBooks = GetBooksForCurPage();
		
		returnTable = new JTable(new BookTable(curPageBooks));
		
		return returnTable;
	}
	
	// get max page #
	public int GetMaxPageNum()
	{
		// if more than one page of books
		if(books.size() > rowsPerPage)
		{
			// if return count divides without remainder, return that even division
			if((books.size() % rowsPerPage) == 0)
			{
				return (books.size() / rowsPerPage);
			}
			// handle remainder
			else
			{
				return ((books.size() / rowsPerPage) + 1);
			}
		}
		// only 1 page, or null books list
		else
		{
			return 1;
		}
	}
	
	// get page #
	public List<Book> GetBooksForCurPage()
	{
		int x, y;
		
		if(curPage == 1)
		{
			x = 1;
			y = rowsPerPage;
		}
		else if (curPage == maxPage)
		{
			x = (curPage * rowsPerPage) + 1;
			y = books.size();
		}
		else
		{
			x = (curPage * rowsPerPage) + 1;
			y = (curPage +1) * rowsPerPage;
		}
		
		return books.subList(x, y);
	}
	
	// sort by author
	
	
	// sort by title
	
	
	// sort by ...
	
	
	// list top sellers
	public void ListBooksByTopSellers()
	{
		fullURL = rootURL + "/books/query/viaproc/topsellers";
		
		ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Book>>() {}
			  );

		// empty the list in case it is not our first search
		if(books != null)
		{
			books.clear();
		}
		
		books = responseEntity.getBody();
		
		curPage = 1;
		maxPage = GetMaxPageNum();
	}
	
	// list by author
	
	
	// list by genre
	
	
	// list by rating
	
	
}