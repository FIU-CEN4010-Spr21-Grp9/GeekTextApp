package GeekTextApp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
*  Title: GeekTextApp
*  Semester: CEN4010 - Spring 2021
*  @author Shawn Welsh
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the frame point class for the GeekText GUI 
* 
*/
public class GeekTextFrame extends JFrame implements ActionListener
{
	// private variables
	private char userOpt;
	
	// constructors
	public GeekTextFrame()
	{
		
	}
	
	public GeekTextFrame(char userOpt)
	{
		this.userOpt = userOpt;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		
	}
}
