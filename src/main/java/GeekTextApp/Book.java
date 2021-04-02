package GeekTextApp;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
*  Title: Book
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the book object for the GeekText application group
* project for group 9
* 
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book
{
	private Integer bookID;
	
	//// INTERNAL PRIVATE VALUES ////
	private String title;
	private String publishDate;
	private String publisher;
	private Float price;
	private String description;
	private String isbn;
	private Integer isbn13;
	private String coverUrl;
	private Float rating;
	private String authorList;
	private String genreList;
	// private List<Author> authors;
	// private List<Genre> genres;
	
	//// CONSTRUCTORS ////
	// default //
	public Book()
	{
		
	};
	
	// the real one //
	public Book(int bookID, String title, String publishDate, String publisher, float price
				, String description, String isbn, int isbn13, String coverUrl, float rating
				, String authorList, String genreList)
	{
		this.bookID = bookID;
		this.title = title;
		this.publishDate = publishDate;
		this.publisher = publisher;
		this.price = price;
		this.description = description;
		this.isbn = isbn;
		this.isbn13 = isbn13;
		this.coverUrl = coverUrl;
		this.rating = rating;
		this.authorList = authorList;
		this.genreList = genreList;		
	}
	
	//// GET/SET OBJECT CODE ////
	// bookID //
	public Integer getBookID()
	{
		return bookID;
	}
	
	public void setBookID(int bookID)
	{
		this.bookID = bookID;
	}
	
	// title //
	public String getTitle()
	{
		// Do something to remove the leading and trailing double quote
		//title = title.replaceAll("^\"|\"$", "");
		
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	// publish date //
	public String getPublishDate()
	{
		return publishDate;
	}
	
	public void setPublishDate(String publishDate)
	{
		this.publishDate = publishDate;
	}
	
	// publisher //
	public String getPublisher()
	{
		return publisher;
	}
	
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}
	
	// price //
	public Float getPrice()
	{
		return price;
	}
	
	public void setPrice(float price)
	{
		this.price = price;
	}
	
	// description //
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	// isbn //
	public String getIsbn()
	{
		return isbn;
	}
	
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	
	// isbn13 //
	public Integer getIsbn13()
	{
		return isbn13;
	}
	
	public void setIsbn13(int isbn13)
	{
		this.isbn13 = isbn13;
	}
	
	// cover url //
	public String getCoverUrl()
	{
		return coverUrl;
	}
	
	public BufferedImage getCoverImage()
	{
		BufferedImage bufImage;
		
		try
		{
			URL coverImageURL = new URL(coverUrl);
			bufImage = ImageIO.read(coverImageURL);
		}
		catch(Exception exp)
		{
			bufImage = new BufferedImage(200,200,BufferedImage.TYPE_BYTE_GRAY);
		}
		
		return bufImage;
	}
	
	public ImageIcon getCoverThumb()
	{
		BufferedImage bufImage;
		ImageIcon returnIcon;
		
		bufImage = resize(getCoverImage(), 100, 100);
		
		returnIcon = new ImageIcon(bufImage);
		
		return returnIcon;
	}
	
	public void setCoverUrl(String coverUrl)
	{
		this.coverUrl = coverUrl;
	}
	
	// rating //
	public Float getRating()
	{
		return rating;
	}
	
	public void setRating(float rating)
	{
		this.rating = rating;
	}
	
	// author list //
	public String getAuthorList()
	{
		return authorList;
	}
	
	public void setAuthorList(String authorList)
	{
		this.authorList = authorList;
	}
	
	// author array //
	// need to clear array and assign values for SET
	
	// return pointer to an array for get or a concatenated string?
	
	
	// genre list //
	public String getGenreList()
	{
		return genreList;
	}
	
	public void setGenreList(String genreList)
	{
		this.genreList = genreList;
	}
	
	// genre array //
	// need to clear array and assign values for SET
	
	// return pointer to an array for get or a concatenated string?
	
	// image resizer
	public static BufferedImage resize(BufferedImage img, int newW, int newH)
	{ 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}
	
	// Comparator for Title
	public static Comparator<Book> TitleComparator = new Comparator<Book>()
	{
		public int compare(Book b1, Book b2)
		{
			String Title1 = b1.getTitle().toUpperCase();
			String Title2 = b2.getTitle().toUpperCase();
			
			return Title1.compareTo(Title2);
		}
	};
	
	// Comparator for Author
	public static Comparator<Book> AuthorComparator = new Comparator<Book>()
	{
		public int compare(Book b1, Book b2)
		{
			String Author1 = b1.getAuthorList().toUpperCase();
			String Author2 = b2.getAuthorList().toUpperCase();
			
			return Author1.compareTo(Author2);
		}
	};
	
	// Comparator for Price
	public static Comparator<Book> PriceComparator = new Comparator<Book>()
	{
		public int compare(Book b1, Book b2)
		{
			Float Price1 = b1.getPrice();
			Float Price2 = b2.getPrice();
			
			return Price1.compareTo(Price2);
		}
	};
	
	// Comparator for Rating
	public static Comparator<Book> RatingComparator = new Comparator<Book>()
	{
		public int compare(Book b1, Book b2)
		{
			Float Rating1 = b1.getRating();
			Float Rating2 = b2.getRating();
			
			return Rating1.compareTo(Rating2);
		}
	};
	
	// Comparator for Publish Date
	public static Comparator<Book> DateComparator = new Comparator<Book>()
	{
		public int compare(Book b1, Book b2)
		{
			String Date1 = b1.getPublishDate();
			String Date2 = b2.getPublishDate();
			
			return Date1.compareTo(Date2);
		}
	};
}
