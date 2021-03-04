package GeekTextApp;

//import javax.persistence.*;
/**
*  Title: Book
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the author object for the GeekText application group
* project for group 9
* 
*/

public class Author
{
	private Integer authorID;
	
	// internal private variables //
	private String firstName;
	private String lastName;
	private Character middle;
	private String authorName;
	private String bio;
	
	// constructors //
	public Author()
	{
		
	}
	
	public Author(int authorID, String firstName, String lastName, char middle, String authorName, String bio)
	{
		this.authorID = authorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middle = middle;
		this.authorName = authorName;
		this.bio = bio;
	}
	
	// get/set methods //
	// author ID
	public Integer getAuthorID()
	{
		return authorID;
	}
	
	public void setAuthorID(int authorID)
	{
		this.authorID = authorID;
	}
	
	// first name
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	// last name
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	// middle
	public Character getMiddle()
	{
		return middle;
	}
	
	public void setMiddle(char middle)
	{
		this.middle = middle;
	}
	
	// author name
	public String getAuthorName()
	{
		return authorName;
	}
	
	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}
	
	// bio
	public String getBio()
	{
		return bio;
	}
	
	public void setBio(String bio)
	{
		this.bio = bio;
	}
	
	
}
