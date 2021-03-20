package GeekTextApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import java.util.List;

import javax.swing.*;
//import net.miginfocom.swing.MigLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.LayoutStyle.ComponentPlacement;
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
public class BookBrowsePane extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8408321676732254210L;

	// nested classes (buttons/drop boxes)
	private class TopSellerButton extends JButton implements ActionListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1661541205619521569L;

		public TopSellerButton(String buttonText)
		{
			setText(buttonText);
		};
		
		// action code
		@Override
		public void actionPerformed(ActionEvent event)
		{
			booksBrowser.ListBooksByTopSellers();
		}
	}
	
	private class GenreListBox extends JComboBox<String> implements ItemListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2049325020252386788L;

		public GenreListBox(final String[] genreList)
		{
			for(int i = 0; i < genreList.length; i++)
			{
				addItem(genreList[i]);
			}
		};
		
		// action code
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			booksBrowser.ListBooksByGenreId(getSelectedIndex());
		}
	}
	
	private class RatingListBox extends JComboBox<String> implements ItemListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 5958497223515196397L;

		public RatingListBox(final String[] ratingList)
		{
			for(int i = 0; i < ratingList.length; i++)
			{
				addItem(ratingList[i]);
			}
		};
		
		// action code
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			booksBrowser.ListBooksByRating(getSelectedIndex());
		}
	}
	
	private class RowsPerPageBox extends JComboBox<String> implements ItemListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4335331721676422736L;

		public RowsPerPageBox(final String[] pageSizeList)
		{
			for(int i = 0; i < pageSizeList.length; i++)
			{
				addItem(pageSizeList[i]);
			}
		};
		
		// action code
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			int rowsPerPage;
			
			if(getSelectedIndex() == 0)
			{
				rowsPerPage = 20;
			}
			else
			{
				rowsPerPage = 10;
			}
			
			booksBrowser.SetRowsPerPage(rowsPerPage);
		}
	}
	
	private class CurrentPageBox extends JComboBox<String> implements ItemListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7148097855665687725L;

		public CurrentPageBox(final String[] pageList)
		{
			for(int i = 0; i < pageList.length; i++)
			{
				addItem(pageList[i]);
			}
		};
		
		// action code
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			booksBrowser.SetCurrentPage(getSelectedIndex() + 1);
		}
	}
	
	// private values
	private int genreID;
	private int authorID;
	private int rating;
	private int rowsPerPage;
	private int curPage;
	private String rootURL;
	private BookBrowser booksBrowser;
	private JScrollPane booksListPane;
	
	// constructor
	public BookBrowsePane(String rootURL)
	{
		// vars
		this.rootURL = rootURL;
		this.booksBrowser = new BookBrowser(this.rootURL);
		this.curPage = booksBrowser.GetCurrentPage();
		this.rowsPerPage = booksBrowser.GetRowsPerPage();
		this.genreID = 0;
		this.authorID = 0;
		this.rating = 0;
		
		// panel stuff
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		// top panel
		JPanel topPanel = new JPanel();
		
		TopSellerButton btnTopSellers = new TopSellerButton("Top Sellers");
		btnTopSellers.addActionListener(btnTopSellers);
		topPanel.add(btnTopSellers);
		
		JLabel labelGenreBox = new JLabel("Genre:");
		topPanel.add(labelGenreBox);
		
		GenreListBox cmbGenreList = new GenreListBox(booksBrowser.GetGenreList());
		cmbGenreList.addItemListener(cmbGenreList);
		topPanel.add(cmbGenreList);
		
		JLabel labelRating = new JLabel("Rating:");
		topPanel.add(labelRating);
		
		String[] ratings = {"Any", "1", "2", "3", "4", "5"};
		RatingListBox cmbRating = new RatingListBox(ratings);
		cmbRating.addItemListener(cmbRating);
		topPanel.add(cmbRating);
		
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 5);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		
		topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (btnTopSellers.getMaximumSize().height + 10)));
		
		// data panel
		JPanel dataPanel = new JPanel();
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[]{43, 2, 0};
		gbl_dataPanel.rowHeights = new int[]{2, 0};
		gbl_dataPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_dataPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		dataPanel.setLayout(gbl_dataPanel);
		GridBagConstraints gbc_dataPanel = new GridBagConstraints();
		gbc_dataPanel.fill = GridBagConstraints.BOTH;
		gbc_dataPanel.gridx = 0;
		gbc_dataPanel.gridy = 1;
		
		// bottom panel
		JPanel bottomPanel = new JPanel();
		
		JLabel labelRowsPerPage = new JLabel("Rows:");
		bottomPanel.add(labelRowsPerPage);
		
		String[] rowsPerPageList = {"20", "10"};
		RowsPerPageBox cmbRowsPerPage = new RowsPerPageBox(rowsPerPageList);
		cmbRowsPerPage.addItemListener(cmbRowsPerPage);
		
		bottomPanel.add(cmbRowsPerPage);
		
		JLabel labelPageNumber = new JLabel("Page:");
		bottomPanel.add(labelPageNumber);
		
		CurrentPageBox cmbPage = new CurrentPageBox(booksBrowser.GetPageList());
		bottomPanel.add(cmbPage);
		cmbPage.addActionListener(cmbGenreList);
		
		GridBagConstraints gbc_bottomPanel = new GridBagConstraints();
		gbc_bottomPanel.fill = GridBagConstraints.BOTH;
		gbc_bottomPanel.insets = new Insets(0, 0, 5, 5);
		gbc_bottomPanel.gridx = 0;
		gbc_bottomPanel.gridy = 2;
		
		bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (cmbPage.getMaximumSize().height + 10)));
		
		// add panels
		add(topPanel, gbc_topPanel);
		add(bottomPanel, gbc_bottomPanel);		
		add(dataPanel, gbc_dataPanel);
		
		this.booksListPane = new JScrollPane(booksBrowser.booksTable);
		
		GridBagConstraints gbc_booksListPane = new GridBagConstraints();
		gbc_booksListPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_booksListPane.gridx = 1;
		gbc_booksListPane.gridy = 0;
		dataPanel.add(booksListPane, gbc_booksListPane);
	};

}