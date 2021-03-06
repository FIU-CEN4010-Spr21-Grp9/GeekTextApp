package GeekTextApp;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

/**
*  Title: BookTable
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class extends AbstractTableModel to load book lists into a JTable
* 
*/

public class BookTable extends AbstractTableModel
{
	// private variables
	private List<Book> books;
	private String[] columns;
	
	// constructor
	public BookTable(List<Book> books)
	{
		this.books = books;
		columns = new String[]{"coverURL", "Title", "publishDate", "publisher", "price", "description"
				, "isbn", "isbn13", "rating", "authorList", "genreList"};
	}
	
	// interface functions
	public int getColumnCount()
	{
		return columns.length;
	}
	
	public int getRowCount()
	{
		return books.size();
	}
	
	public Object getValueAt(int row, int col)
	{
		Book book = books.get(row);
		
		switch(col)
		{
			case 0: return book.getCoverUrl();
			case 1: return book.getTitle();
			case 2: return book.getPublishDate();
			case 3: return book.getPublisher();
			case 4: return book.getPrice();
			case 5: return book.getDescription();
			case 6: return book.getIsbn();
			case 7: return book.getIsbn13();
			//case 8: return book.getCoverUrl();
			case 8: return book.getRating();
			case 9: return book.getAuthorList();
			case 10: return book.getGenreList();
			
			// default
			default: return null;
				
		}
	}
	
	public String getColumnName(int columnIndex) {
	    //System.out.println("in");
	    return columns[columnIndex];
	}
}