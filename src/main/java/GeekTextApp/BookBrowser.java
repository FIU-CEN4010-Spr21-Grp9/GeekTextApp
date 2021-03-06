package GeekTextApp;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

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
	//private int genreID;
	//private int authorID;
	//private int rating;
	private int curPage;
	private int maxPage;
	private int rowsPerPage;
	private String rootURL;
	private String fullURL;
	private List<Book> books;
	private GenreList genreList;
	private final RestTemplate restTemplate = new RestTemplate();
	
	// constructors
	public BookBrowser(String rootURL)
	{
		// internal values
		this.rowsPerPage = 20;
		this.curPage = 1;
		//this.genreID = 0;
		//this.rating = 0;
				
		// root URL
		this.rootURL = rootURL;
		
		// get genres list for browsing
		genreList = new GenreList(rootURL);
		
		// default to top sellers until another option is picked
		ListBooksByTopSellers();
		
		// Test other book browsing (before GUI code is fully implemented)
		// ListBooksByGenreId(1);
		// ListBooksByAuthorId(179677); // Chris' code not merged yet
		// ListBooksByRating(3);
	}
	
	// functions
	// main browsing return
	public JTable BrowseBooksByPage()
	{
		JTable returnTable;
		
		List<Book> curPageBooks = GetBooksForCurPage();
		
		returnTable = new JTable(new BookTable(curPageBooks));
		//resizeColumnWidth(returnTable);
		//returnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		return returnTable;
	}
	
	// RESIZER
	/*
	public void resizeColumnWidth(JTable table)
	{
	    final TableColumnModel columnModel = table.getColumnModel();
	    
	    for (int column = 0; column < table.getColumnCount(); column++)
	    {
	        int width = 15; // Min width
	        
	        for (int row = 0; row < table.getRowCount(); row++)
	        {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        
	        if(width > 300)
	        {
	            width=300;
	        }
	        
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	    
	    int totalWidth = columnModel.getTotalColumnWidth();
	}
	*/
	
	// return genre list to filter drop down
	public String[] GetGenreList()
	{
		return genreList.FillGenreComboBox();
	}
	
	// return page list to filter drop down
	public String[] GetPageList()
	{
		String[] pageListArray= new String[GetMaxPageNum()];
		
		for(int i = 1; i <= GetMaxPageNum(); i++)
		{
			pageListArray[(i - 1)] = String.valueOf(i);
		}
		
		return pageListArray;
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
			
			if(curPage == maxPage)
			{
				y = books.size();
			}
			else
			{
				y = rowsPerPage;
			}
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
		
		// offset "rows numbers" to zero-based list values
		x--;
		//y--;
		
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
	public void ListBooksByAuthorId(Integer authorID)
	{
		fullURL = rootURL + "/books/query/viaproc/byauthor?authorID={id}";
		
		ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Book>>() {}
				, authorID
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
	
	// list by genre
	public void ListBooksByGenreId(Integer genreID)
	{
		fullURL = rootURL + "/books/query/viaproc/bygenre?genreID={id}";
		
		ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Book>>() {}
				, genreID
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
	
	
	// list by rating
	public void ListBooksByRating(Integer rating)
	{
		fullURL = rootURL + "/books/query/viaproc/byrating?rating={rating}";
		
		ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Book>>() {}
				, rating
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
	
	// get/set
	public void setRowsPerPage(int rows)
	{
		this.rowsPerPage = rows;
		maxPage = GetMaxPageNum();
		
		if(curPage > maxPage)
		{
			curPage = 1;
		}
		
		//GetBooksForCurPage();
	}
	
	public int getRowsPerPage()
	{
		return rowsPerPage;
	}
}