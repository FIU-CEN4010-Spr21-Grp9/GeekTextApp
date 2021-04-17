package GeekTextApp;


//model
public class Wishlist
{

	private Integer wishlist_itemID;
	
	
	private Integer wishlistID;

	private Integer userID;

	private String wishlistName;

	private String title;

	private int bookID;
	

	public String toString()
	{
		return String.format("Title: %s, BookID: %d.", title, bookID);
	}
	
	public Wishlist(int wishlist_itemID, int wishlistID, int userID,  String wishlistName, String title, int bookID)
	{
		this.wishlist_itemID = wishlist_itemID;
		this.wishlistID = wishlistID;
		this.userID = userID;
		this.wishlistName = wishlistName;
		this.title = title;
		this.bookID = bookID;
		
	}
	public Wishlist()
	{
	
	}
	public int getWishlistID()
	{
		return wishlistID;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public String getWishlistName()
	{
		return wishlistName;
	}
	
	public int getBookID()
	{
		return bookID;
	}
	
	public String getBookName()
	{
		return title;
	}
	public void setWishListID(int wishlistID)
	{
		this.wishlistID = wishlistID;
	}
	
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	public void setWishListName(String wishlistName)
	{
		this.wishlistName = wishlistName;
	}
	
	public void setBookID(int bookID)
	{
		this.bookID = bookID;
	}

	public void setBookName(String bookName)
	{
		this.title = bookName;
	}
	public int getWishlistItemID()
	{
		return wishlist_itemID;
	}
	public void setWishlistItemID(int wishlist_itemID)
	{
		this.wishlist_itemID = wishlist_itemID;
	}
}