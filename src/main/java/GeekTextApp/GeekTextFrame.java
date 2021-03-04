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
	// private char userOpt;
	private GeekTextApp geekText;
	private String rootURL;
	
	// frame stuff
	private JLabel geekLabel;
	private JButton browseButton;
	
	// constructors
	public GeekTextFrame(String rootURL)
	{
		// root URL
		this.rootURL = rootURL;
		
		// declare new GeekTextApp
		geekText = new GeekTextApp(rootURL);
	      
		// declare layout manager
		GridBagConstraints layoutConst = null;
	      
		// set layout items
		setTitle("Geek Text");
	      
		// labels
		geekLabel = new JLabel("Geek Text Test");
		
		// buttons
		browseButton = new JButton("Browse Books");
		browseButton.addActionListener(this);
		
		// build layout
		setLayout(new GridBagLayout());
	      
		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 10, 10, 1);
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		add(geekLabel, layoutConst);
		
		// browse button
		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 10, 10, 10);
		layoutConst.gridx = 0;
		layoutConst.gridy = 3;
		add(browseButton, layoutConst);
		
	}
	
	// button code
	@Override
	public void actionPerformed(ActionEvent event)
	{
		
	}
}
