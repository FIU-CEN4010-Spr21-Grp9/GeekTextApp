package GeekTextApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

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
public class BookBrowsePane extends JPanel implements ActionListener
{
	// private values
	private int genreID;
	private int authorID;
	private int rating;
	private String rootURL;
	private BookBrowser booksBrowser;
	private JTable booksTable;
	private JScrollPane booksListPane;
	
	// constructor
	public BookBrowsePane(String rootURL)
	{
		// vars
		this.rootURL = rootURL;
		this.booksBrowser = new BookBrowser(this.rootURL);
		this.booksTable = booksBrowser.BrowseBooksByPage();
		this.booksListPane = new JScrollPane(booksTable);
		
		// stuff
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		JPanel topPanel = new JPanel();
		
		JButton btnTopSellers = new JButton("Top Sellers");
		topPanel.add(btnTopSellers);
		
		JLabel labelGenreBox = new JLabel("Genre:");
		topPanel.add(labelGenreBox);
		
		JComboBox comboBox = new JComboBox(booksBrowser.GetGenreList());
		topPanel.add(comboBox);
		
		JLabel labelRating = new JLabel("Rating:");
		topPanel.add(labelRating);
		
		String[] ratings = {"Any", "1", "2", "3", "4", "5"};
		JComboBox cmbRating = new JComboBox(ratings);
		topPanel.add(cmbRating);
		
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 5);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		
		JPanel dataPanel = new JPanel();
		
		dataPanel.add(booksListPane);
		GridBagConstraints gbc_dataPanel = new GridBagConstraints();
		gbc_dataPanel.fill = GridBagConstraints.BOTH;
		gbc_dataPanel.gridx = 0;
		gbc_dataPanel.gridy = 1;
		
		JPanel bottomPanel = new JPanel();
		JLabel labelRowsPerPage = new JLabel("Rows:");
		bottomPanel.add(labelRowsPerPage);
		
		String[] rowsPerPageList = {"20", "10"};
		JComboBox cmbRowsPerPage = new JComboBox(rowsPerPageList);
		
		cmbRowsPerPage.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				int item = cmbRowsPerPage.getSelectedIndex();
				int curItem;
				
				if(booksBrowser.getRowsPerPage() == 20)
				{
					curItem = 0;
				}
				else
				{
					curItem = 1;
				}
				
				if(item != curItem)
				{
					if(item == 0)
					{
						booksBrowser.setRowsPerPage(20);
					}
					else
					{
						booksBrowser.setRowsPerPage(10);
					}
					
					// redraw table
					booksTable = booksBrowser.BrowseBooksByPage();
					booksListPane.revalidate();
				}
			}
		});
		bottomPanel.add(cmbRowsPerPage);
		
		JLabel labelPageNumber = new JLabel("Page:");
		bottomPanel.add(labelPageNumber);
		
		JComboBox cmbPage = new JComboBox(booksBrowser.GetPageList());
		bottomPanel.add(cmbPage);
		GridBagConstraints gbc_bottomPanel = new GridBagConstraints();
		gbc_bottomPanel.fill = GridBagConstraints.BOTH;
		gbc_bottomPanel.insets = new Insets(0, 0, 5, 5);
		gbc_bottomPanel.gridx = 0;
		gbc_bottomPanel.gridy = 2;
		
		// add panels
		add(topPanel, gbc_topPanel);
		add(bottomPanel, gbc_bottomPanel);		
		add(dataPanel, gbc_dataPanel);
	};
	
	// button/box code
	@Override
	public void actionPerformed(ActionEvent event)
	{
		
	}

}