package GeekTextApp;


//model
public class Wishlist
{

	private Integer wishlist_itemID;
	
	
	private Integer wishlistID;

	private Integer userID;

	private String name;

	private String title;

	private int bookID;
	

	public String toString()
	{
		return String.format("Wishlist_ItemID: %d, UserID: %d, Name: %s, Title: %s, BookID: %d.",wishlist_itemID, userID, name, title, bookID);
	}
	
	public Wishlist(int wishlist_itemID, int wishlistID, int userID,  String wishlistName, String title, int bookID)
	{
		this.wishlist_itemID = wishlist_itemID;
		this.wishlistID = wishlistID;
		this.userID = userID;
		this.name = wishlistName;
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
		return name;
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
		this.name = wishlistName;
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