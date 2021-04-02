package GeekTextApp;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
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
	private int genreID;
	private int authorID;
	private int rating;
	private int curPage;
	private int maxPage;
	private int rowsPerPage;
	private int sortIndex;
	private String rootURL;
	private String fullURL;
	private List<Book> books;
	private BookTable booksTable;
	private GenreList genreList;
	private final RestTemplate restTemplate = new RestTemplate();
	private String[] sortOptions = {"", "Title", "Title (desc)", "Author", "Author (desc)", "Price", "Price (desc)"
			, "Rating", "Rating (desc)", "Date", "Date (desc)"}; // declare here so indexes are always the same
	
	// constructors
	public BookBrowser()
	{
		// internal values
		this.rowsPerPage = 20;
		this.curPage = 1;
		this.genreID = 0;
		this.rating = 0;
		this.authorID = 0;
		this.sortIndex = 0;
				
		// root URL
		this.rootURL = GeekTextApp.ROOT_URL;
		
		// get genres list for browsing
		if(genreList == null)
		{
			genreList = new GenreList();
		}
		
		// default to top sellers until another option is picked
		if(books == null)
		{
			ListBooksByTopSellers();
			
			// Test other book browsing (before GUI code is fully implemented)
			// this.genreID = 1; ListBooksByGenreId();
			// this.authorID = 179677; ListBooksByAuthorId(); // Chris' code not merged yet
			// this.rating = 3; ListBooksByRating();
		}
	}
	
	// functions
	// main browsing return
	public JTable GetBooksTable()
	{
		JTable returnTable = new JTable(booksTable);
		
		returnTable.setRowHeight(100);
		resizeColumnWidth(returnTable);
		returnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		return returnTable;
	}
	
	private void BrowseBooksByPage()
	{
		List<Book> curPageBooks = GetBooksForCurPage();

		booksTable = null;
		
		booksTable = new BookTable(curPageBooks);
	}
	
	// get page #
	private List<Book> GetBooksForCurPage()
	{
		int x, y;
		List<Book> subListBooks = new ArrayList<Book>();
		
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
			x = ((curPage - 1) * rowsPerPage) + 1;
			y = books.size();
		}
		else
		{
			x = ((curPage - 1) * rowsPerPage) + 1;
			y = (curPage) * rowsPerPage;
		}
		
		// offset "rows numbers" to zero-based list values
		x--;
		//y--;
		
		//subListBooks = books.subList(x, y);
		for(int i = 0; i < rowsPerPage; i++)
		{
			if(x < y)
			{
				subListBooks.add(books.get(x));
				
				x++;
			}
		}
		
		return subListBooks;
	}
	
	// RESIZER
	public void resizeColumnWidth(JTable table)
	{
	    final TableColumnModel columnModel = table.getColumnModel();
	    int[] columnWidths = new int[]{100, 300, 100, 100, 50, 200, 50, 60, 50, 100, 100, 20};
	    
	    for (int column = 0; column < table.getColumnCount(); column++)
	    {
	        int width = columnWidths[column]; // Min width
	        
	        for (int row = 0; row < table.getRowCount(); row++)
	        {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        
	        if(width > 300)
	        {
	            width = 300;
	        }
	        
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	    
	    //return columnModel.getTotalColumnWidth();
	}
	
	// return genre list to filter drop down
	public String[] GetGenreList()
	{
		return genreList.FillGenreComboBox();
	}
	
	// return sort options array
	public String[] GetSortOptions()
	{
		return sortOptions;
	}
	
	// return sort index
	public int GetSortIndex()
	{
		return sortIndex;
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
	
	/// SORTING ///
	// main sorting method
	public void SetSorting(int optIndex)
	{
		/*
		* Sort Index Hint
		* 0 - None
		* 1, 2 - Title
		* 3, 4 - Author
		* 5, 6 - Price
		* 7, 8 - Rating
		* 9, 10 - Date
		* 
		* Evens are DESC sort (0 is not even)
		*/
		
		// HANDLE OUT OF RANGE
		if(optIndex < 0)
		{
			optIndex = 0;
		}
		else if(optIndex > 10)
		{
			optIndex = 0;
		}
		
		sortIndex = optIndex;
		
		// DO SORTING
		if(sortIndex > 0)
		{
			switch(sortIndex)
			{
				// TITLE
				case 1:
				case 2:
					SortByTitle();
					break;
				// AUTHOR
				case 3:
				case 4:
					SortByAuthor();
					break;
				// PRICE
				case 5:
				case 6:
					SortByPrice();
					break;
				// RATING
				case 7:
				case 8:
					SortByRating();
					break;
				// DATE
				case 9:
				case 10:
					SortByDate();
					break;
				default:
					break;
			}
			
			// reverse order for DESC
			if((sortIndex % 2) == 0 )
			{
				Collections.reverse(books);
			}
		}
		
		BrowseBooksByPage();
	}
	
	// sort by author
	private void SortByAuthor()
	{
		Collections.sort(books, Book.AuthorComparator);
	}
	
	// sort by title
	private void SortByTitle()
	{
		Collections.sort(books, Book.TitleComparator);
	}
	
	// sort by rating
	private void SortByRating()
	{
		Collections.sort(books, Book.RatingComparator);
	}
	
	// sort by price
	private void SortByPrice()
	{
		Collections.sort(books, Book.PriceComparator);
	}
	
	// sort by publish date
	private void SortByDate()
	{
		Collections.sort(books, Book.DateComparator);
	}
	
	// list top sellers
	public void ListBooksByTopSellers()
	{
		this.genreID = 0;
		this.authorID = 0;
		this.rating = 0;
		this.sortIndex = 0;
		
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
		
		BrowseBooksByPage();
	}
	
	// list by author
	public void ListBooksByAuthorId()
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
		
		BrowseBooksByPage();
	}
	
	// list by genre
	public void ListBooksByGenreId()
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
		
		BrowseBooksByPage();
	}
	
	
	// list by rating
	public void ListBooksByRating()
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
		
		BrowseBooksByPage();
	}
	
	/// get/set ///
	// rows per page
	public void SetRowsPerPage(int rows)
	{
		this.rowsPerPage = rows;
		maxPage = GetMaxPageNum();
		
		if(curPage > maxPage)
		{
			curPage = 1;
		}
		
		BrowseBooksByPage();
	}
	
	public Integer GetRowsPerPage()
	{
		return rowsPerPage;
	}
	
	// current page
	public void SetCurrentPage(int pageNum)
	{
		if(curPage == pageNum)
		{
			// do nothing
		}
		else if(pageNum > maxPage)
		{
			curPage = maxPage;
		}
		else if(pageNum < 1)
		{
			curPage = 1;
		}
		else
		{
			curPage = pageNum;
		}
		
		BrowseBooksByPage();
	}
	
	public Integer GetCurrentPage()
	{
		return curPage;
	}
	
	// rating
	public void SetRating(int ratingVal)
	{
		this.genreID = 0;
		this.authorID = 0;
		this.rating = ratingVal;
		this.sortIndex = 0;
		
		ListBooksByRating();
	}
	
	public int GetRating()
	{
		return this.rating;
	}
	
	// genreID
	public void SetGenreId(int genreID)
	{
		this.genreID = genreID;
		this.authorID = 0;
		this.rating = 0;
		this.sortIndex = 0;
		
		ListBooksByGenreId();
	}
	
	public int GetGenreId()
	{
		return this.genreID;
	}
	
	// authorID
	public void SetAuthorId(int authorID)
	{
		this.genreID = 0;
		this.authorID = authorID;
		this.rating = 0;
		this.sortIndex = 0;
		
		ListBooksByAuthorId();
	}
	
	public int GetAuthorId()
	{
		return this.authorID;
	}
}
