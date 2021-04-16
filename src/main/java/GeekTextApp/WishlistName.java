package GeekTextApp;

public class WishlistName
{
	private Integer wishlistID;

//	private Integer userID;

	private String wishlistName;
	public String toString()
	{
		return String.format("Wishlist_ID: %d, Name: %s.",wishlistID, wishlistName);
	}
	
	public WishlistName(int wishlistID,  String wishlistName)
	{
		
		this.wishlistID = wishlistID;
//		this.userID = userID;
		this.wishlistName = wishlistName;
		
	}
	public WishlistName() {}

	public int getWishlistID()
	{
		return wishlistID;
	}
	
//	public int getUserID()
//	{
//		return userID;
//	}
	
	public String getWishlistName()
	{
		return wishlistName;
	}
	
	public void setWishListID(int wishlistID)
	{
		this.wishlistID = wishlistID;
	}
	
//	public void setUserID(int userID)
//	{
//		this.userID = userID;
//	}

	public void setWishListName(String wishlistName)
	{
		this.wishlistName = wishlistName;
	}
	
	
}