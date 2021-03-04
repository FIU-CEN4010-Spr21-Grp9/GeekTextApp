package GeekTextApp;

import javax.swing.JFrame;
/**
*  Title: GeekTextApp
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
public class GeekTextApp
{
	public static void main(String[] args)
	{
		// Creates ConverterFrame and its components
		GeekTextFrame convFrame = new GeekTextFrame();

		convFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		convFrame.pack();
		convFrame.setVisible(true);
	}
}
