package GeekTextApp;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
*  Title: GeekTextApp
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the main code points to access each feature area of GeekText
* 
*/

// main entry point
public class GeekTextApp
{
	// private variables
	private String rootURL;
	private BookBrowser booksList;
	
	// constructors
	public GeekTextApp(String rootURL)
	{
		// root URL
		this.rootURL = rootURL;
		booksList = new BookBrowser(rootURL);
	}
	
	// browsing
	public JTable BrowseTopSellers()
	{
		JTable returnTable;
		
		booksList.ListBooksByTopSellers();
		
		returnTable = booksList.BrowseBooksByPage();
		
		return returnTable;
	}
	
	// user profile
	
	
	// shopping cart
	
	
	// book details
	
	
	// book rating and comments
	
	
	// wish list
	
	
}
