package GeekTextApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.List;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = -7438737158179176083L;
	
	// private variables
	private JPanel GeekTextPanel;
	private JPanel TopNavPanel;
	private JPanel BottomPanel;
	private JPanel LeftNavPanel;
	private JPanel MainDataPanel;
	private JLabel labelGeekTextApp;
	private JButton btnBrowseBooks;
	private JButton btnUserProfile;
	private JButton btnShopCart;
	private JButton btnWishList;
	
	// constructors
	public GeekTextFrame()
	{
		// set layout items
		setTitle("Geek Text");

		GridBagLayout gridBagLayout = new GridBagLayout();
		getContentPane().setLayout(gridBagLayout);
		
		GeekTextPanel = new JPanel();
		GridBagConstraints gbc_GeekTextPanel = new GridBagConstraints();
		gbc_GeekTextPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_GeekTextPanel.gridx = 0;
		gbc_GeekTextPanel.gridy = 0;
		getContentPane().add(GeekTextPanel, gbc_GeekTextPanel);
		GridBagLayout gbl_GeekTextPanel = new GridBagLayout();
		GeekTextPanel.setLayout(gbl_GeekTextPanel);
		
		TopNavPanel = new JPanel();
		GridBagConstraints gbc_TopNavPanel = new GridBagConstraints();
		gbc_TopNavPanel.insets = new Insets(0, 0, 5, 0);
		gbc_TopNavPanel.fill = GridBagConstraints.BOTH;
		gbc_TopNavPanel.gridx = 0;
		gbc_TopNavPanel.gridy = 0;
		GeekTextPanel.add(TopNavPanel, gbc_TopNavPanel);
		GridBagLayout gbl_TopNavPanel = new GridBagLayout();
		TopNavPanel.setLayout(gbl_TopNavPanel);
		
		labelGeekTextApp = new JLabel("Geek Text Application");
		GridBagConstraints gbc_labelGeekTextApp = new GridBagConstraints();
		gbc_labelGeekTextApp.anchor = GridBagConstraints.NORTHWEST;
		gbc_labelGeekTextApp.gridx = 1;
		gbc_labelGeekTextApp.gridy = 0;
		TopNavPanel.add(labelGeekTextApp, gbc_labelGeekTextApp);
		
		BottomPanel = new JPanel();
		GridBagConstraints gbc_BottomPanel = new GridBagConstraints();
		gbc_BottomPanel.fill = GridBagConstraints.BOTH;
		gbc_BottomPanel.gridx = 0;
		gbc_BottomPanel.gridy = 1;
		GeekTextPanel.add(BottomPanel, gbc_BottomPanel);
		GridBagLayout gbl_BottomPanel = new GridBagLayout();
		BottomPanel.setLayout(gbl_BottomPanel);
		
		LeftNavPanel = new JPanel();
		GridBagConstraints gbc_LeftNavPanel = new GridBagConstraints();
		gbc_LeftNavPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_LeftNavPanel.insets = new Insets(0, 0, 0, 5);
		gbc_LeftNavPanel.gridx = 0;
		gbc_LeftNavPanel.gridy = 0;
		BottomPanel.add(LeftNavPanel, gbc_LeftNavPanel);
		GridBagLayout gbl_LeftNavPanel = new GridBagLayout();
		LeftNavPanel.setLayout(gbl_LeftNavPanel);
		
		btnBrowseBooks = new JButton("Browse Books");
		btnBrowseBooks.setPreferredSize(new Dimension(120, 24));
		btnBrowseBooks.setMinimumSize(new Dimension(120, 24));
		btnBrowseBooks.setMaximumSize(new Dimension(120, 24));
		GridBagConstraints gbc_btnBrowseBooks = new GridBagConstraints();
		gbc_btnBrowseBooks.insets = new Insets(0, 5, 5, 0);
		gbc_btnBrowseBooks.gridx = 0;
		gbc_btnBrowseBooks.gridy = 0;
		LeftNavPanel.add(btnBrowseBooks, gbc_btnBrowseBooks);
		
		btnUserProfile = new JButton("User Profile");
		btnUserProfile.setPreferredSize(new Dimension(120, 24));
		btnUserProfile.setMinimumSize(new Dimension(120, 24));
		btnUserProfile.setMaximumSize(new Dimension(120, 24));
		GridBagConstraints gbc_btnUserProfile = new GridBagConstraints();
		gbc_btnUserProfile.insets = new Insets(0, 5, 5, 0);
		gbc_btnUserProfile.gridx = 0;
		gbc_btnUserProfile.gridy = 1;
		LeftNavPanel.add(btnUserProfile, gbc_btnUserProfile);
		
		btnShopCart = new JButton("Shopping Cart");
		btnShopCart.setPreferredSize(new Dimension(120, 24));
		btnShopCart.setMinimumSize(new Dimension(120, 24));
		btnShopCart.setMaximumSize(new Dimension(120, 24));
		GridBagConstraints gbc_btnShopCart = new GridBagConstraints();
		gbc_btnShopCart.insets = new Insets(0, 5, 5, 0);
		gbc_btnShopCart.gridx = 0;
		gbc_btnShopCart.gridy = 2;
		LeftNavPanel.add(btnShopCart, gbc_btnShopCart);
		
		btnWishList = new JButton("Wish List");
		btnWishList.setPreferredSize(new Dimension(120, 24));
		btnWishList.setMinimumSize(new Dimension(120, 24));
		btnWishList.setMaximumSize(new Dimension(120, 24));
		GridBagConstraints gbc_btnWishList = new GridBagConstraints();
		gbc_btnWishList.insets = new Insets(0, 5, 5, 0);
		gbc_btnWishList.gridx = 0;
		gbc_btnWishList.gridy = 3;
		LeftNavPanel.add(btnWishList, gbc_btnWishList);
		
		// main data panel
		MainDataPanel = new JPanel();
		GridBagConstraints gbc_MainDataPanel = new GridBagConstraints();
		gbc_MainDataPanel.anchor = GridBagConstraints.WEST;
		gbc_MainDataPanel.gridx = 1;
		gbc_MainDataPanel.gridy = 0;
		GridBagLayout gbl_MainDataPanel = new GridBagLayout();
		MainDataPanel.setLayout(gbl_MainDataPanel);
		
		BottomPanel.add(MainDataPanel, gbc_MainDataPanel);
		
		// by default, browse books on launch
		BrowseBooks();		
		
		// make it visible
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void BrowseBooks()
	{
		MainDataPanel.removeAll();
		JPanel booksBrowserPane = new BookBrowsePane();
		GridBagConstraints gbc_booksBrowserPane = new GridBagConstraints();
		gbc_booksBrowserPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_booksBrowserPane.gridx = 0;
		gbc_booksBrowserPane.gridy = 0;
		MainDataPanel.add(booksBrowserPane, gbc_booksBrowserPane);
	}
	
	// button code
	@Override
	public void actionPerformed(ActionEvent event)
	{

	}
}
