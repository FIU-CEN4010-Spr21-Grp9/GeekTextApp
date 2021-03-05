package GeekTextApp;

import javax.swing.JFrame;
/**
*  Title: GeekTextMain
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the entry point into the GeekText GUI 
* 
*/

// main entry point
public class GeekTextMain
{
	private static String ROOT_URL = "http://localhost:8080";
	
	public static void main(String[] args)
	{
		// Creates ConverterFrame and its components
		GeekTextFrame geekFrame = new GeekTextFrame(ROOT_URL);

		geekFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		geekFrame.setSize(400,400);
		geekFrame.pack();
		geekFrame.setVisible(true);
	}
}
