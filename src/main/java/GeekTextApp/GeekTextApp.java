package GeekTextApp;

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
private static String ROOT_URL = "http://localhost:8080";
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		// Creates new GeekTextApp
		GeekTextFrame geekText = new GeekTextFrame(ROOT_URL);
	}	
}
