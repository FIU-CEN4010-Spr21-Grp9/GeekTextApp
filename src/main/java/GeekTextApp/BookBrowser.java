package GeekTextApp;

import java.net.HttpURLConnection;
import java.net.URL;
//import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import GeekTextRest.Book;

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
public class BookBrowser
{
	// private variables
	private String rootURL;
	private String fullURL;
	private URL restURL;
	private HttpURLConnection conn;
	
	// constructors
	public BookBrowser(String rootURL)
	{
		// root URL
		this.rootURL = rootURL;
	}
	
	// functions
	// top sellers
	public List<Book> BrowseBooksByTopSellers()
	{
		int rc;
		List<Book> books;
		fullURL = rootURL + "/books/query/viaproc/topsellers";
		
		try
		{
			restURL = new URL(fullURL);
			
			conn = (HttpURLConnection)restURL.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			rc = conn.getResponseCode();
			
			books = booksService.browseByTopSellersViaProc();
		}
		catch(Exception exp)
		{
			
		}
		
		if(books == null)
		{
            return new ArrayList<Book>();
        } else
        {
            return books;
        }
	}
}