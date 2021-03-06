package GeekTextApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

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
	private String rootURL;
	
	// frame stuff
	private JLabel geekLabel;
	private JButton browseButton;
	private JPanel mainViewPane;
	
	private GridBagConstraints layoutConst = null;
	
	// constructors
	public GeekTextFrame(String rootURL)
	{
		// root URL
		this.rootURL = rootURL;

		// set layout items
		setTitle("Geek Text");
	      
		// labels
		geekLabel = new JLabel("Geek Text Application");
		
		/// buttons panel ///
		
		// buttons
		browseButton = new JButton("Browse Books");
		browseButton.addActionListener(this);
		
		// browsing pane
		mainViewPane = new BookBrowsePane(rootURL);
		
		// WYSIWYG CODE
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(geekLabel))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(browseButton)
					.addGap(20)
					.addComponent(mainViewPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(geekLabel)
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(browseButton))
						.addComponent(mainViewPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		getContentPane().setLayout(groupLayout);
		
		// make it visible
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		//this.setSize(width, height);
	}
	
	// button code
	@Override
	public void actionPerformed(ActionEvent event)
	{
		mainViewPane = new BookBrowsePane(rootURL);
		
		// booksTbl
		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 10, 10, 10);
		layoutConst.gridx = 1;
		layoutConst.gridy = 1;
		getContentPane().add(mainViewPane, layoutConst);
	}
}
