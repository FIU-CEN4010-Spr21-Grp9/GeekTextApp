package GeekTextApp;

//import javax.persistence.*;
/**
*  Title: Genre
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the Genre object for the GeekText application group
* project for group 9
* 
*/
public class Genre
{
	private Integer genreID;
	
	// Private variables //
	private String genreName;
	
	// Constructors //
	public Genre()
	{
		
	}
	
	public Genre(int genreID, String genreName)
	{
		this.genreID = genreID;
		this.genreName = genreName;
	}
	
	// Get/set functions //
	public Integer getGenreID()
	{
		return genreID;
	}
	
	public void setGenreID(int genreID)
	{
		this.genreID = genreID;
	}
	
	public String getGenreName()
	{
		return genreName;
	}
	
	public void setGenreName(String genreName)
	{
		this.genreName = genreName;
	}
}
