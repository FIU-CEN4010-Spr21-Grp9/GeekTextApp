package GeekTextApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
	private String rootURL;
	private JPanel topNav;
	private JPanel bottomPane;
	private JPanel bottomNav;
	
	private GridBagConstraints layoutConst = null;
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
	public GeekTextFrame(String rootURL)
	{
		// root URL
		this.rootURL = rootURL;

		// set layout items
		setTitle("Geek Text");

		// make it visible
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		
		GeekTextPanel = new JPanel();
		getContentPane().add(GeekTextPanel, BorderLayout.NORTH);
		GridBagLayout gbl_GeekTextPanel = new GridBagLayout();
		gbl_GeekTextPanel.columnWidths = new int[]{0, 0};
		gbl_GeekTextPanel.rowHeights = new int[]{0, 0, 0};
		gbl_GeekTextPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_GeekTextPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		GeekTextPanel.setLayout(gbl_GeekTextPanel);
		
		TopNavPanel = new JPanel();
		GridBagConstraints gbc_TopNavPanel = new GridBagConstraints();
		gbc_TopNavPanel.insets = new Insets(0, 0, 5, 0);
		gbc_TopNavPanel.fill = GridBagConstraints.BOTH;
		gbc_TopNavPanel.gridx = 0;
		gbc_TopNavPanel.gridy = 0;
		GeekTextPanel.add(TopNavPanel, gbc_TopNavPanel);
		
		labelGeekTextApp = new JLabel("Geek Text Application");
		TopNavPanel.add(labelGeekTextApp);
		
		BottomPanel = new JPanel();
		GridBagConstraints gbc_BottomPanel = new GridBagConstraints();
		gbc_BottomPanel.fill = GridBagConstraints.BOTH;
		gbc_BottomPanel.gridx = 0;
		gbc_BottomPanel.gridy = 1;
		GeekTextPanel.add(BottomPanel, gbc_BottomPanel);
		GridBagLayout gbl_BottomPanel = new GridBagLayout();
		gbl_BottomPanel.columnWidths = new int[]{120, 105, 0};
		gbl_BottomPanel.rowHeights = new int[]{111, 0};
		gbl_BottomPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_BottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		BottomPanel.setLayout(gbl_BottomPanel);
		
		LeftNavPanel = new JPanel();
		GridBagConstraints gbc_LeftNavPanel = new GridBagConstraints();
		gbc_LeftNavPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_LeftNavPanel.insets = new Insets(0, 0, 0, 5);
		gbc_LeftNavPanel.gridx = 0;
		gbc_LeftNavPanel.gridy = 0;
		BottomPanel.add(LeftNavPanel, gbc_LeftNavPanel);
		GridBagLayout gbl_LeftNavPanel = new GridBagLayout();
		gbl_LeftNavPanel.columnWidths = new int[]{99, 0};
		gbl_LeftNavPanel.rowHeights = new int[]{23, 0, 0, 0, 0};
		gbl_LeftNavPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_LeftNavPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		LeftNavPanel.setLayout(gbl_LeftNavPanel);
		
		btnBrowseBooks = new JButton("Browse Books");
		btnBrowseBooks.setPreferredSize(new Dimension(120, 24));
		GridBagConstraints gbc_btnBrowseBooks = new GridBagConstraints();
		gbc_btnBrowseBooks.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowseBooks.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBrowseBooks.gridx = 0;
		gbc_btnBrowseBooks.gridy = 0;
		LeftNavPanel.add(btnBrowseBooks, gbc_btnBrowseBooks);
		
		btnUserProfile = new JButton("User Profile");
		btnUserProfile.setPreferredSize(new Dimension(120, 24));
		GridBagConstraints gbc_btnUserProfile = new GridBagConstraints();
		gbc_btnUserProfile.insets = new Insets(0, 0, 5, 0);
		gbc_btnUserProfile.gridx = 0;
		gbc_btnUserProfile.gridy = 1;
		LeftNavPanel.add(btnUserProfile, gbc_btnUserProfile);
		
		btnShopCart = new JButton("Shopping Cart");
		btnShopCart.setPreferredSize(new Dimension(120, 24));
		btnShopCart.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnShopCart = new GridBagConstraints();
		gbc_btnShopCart.insets = new Insets(0, 0, 5, 0);
		gbc_btnShopCart.gridx = 0;
		gbc_btnShopCart.gridy = 2;
		LeftNavPanel.add(btnShopCart, gbc_btnShopCart);
		
		btnWishList = new JButton("Wish List");
		btnWishList.setPreferredSize(new Dimension(120, 24));
		GridBagConstraints gbc_btnWishList = new GridBagConstraints();
		gbc_btnWishList.gridx = 0;
		gbc_btnWishList.gridy = 3;
		LeftNavPanel.add(btnWishList, gbc_btnWishList);
		
		MainDataPanel = new BookBrowsePane(rootURL);
		MainDataPanel.setSize(800, 600);
		GridBagConstraints gbc_MainDataPanel = new GridBagConstraints();
		gbc_MainDataPanel.anchor = GridBagConstraints.WEST;
		gbc_MainDataPanel.gridx = 1;
		gbc_MainDataPanel.gridy = 0;
		BottomPanel.add(MainDataPanel, gbc_MainDataPanel);
		this.pack();
		this.setVisible(true);
		//this.setSize(width, height);
	}
	
	// button code
	@Override
	public void actionPerformed(ActionEvent event)
	{

	}
}
