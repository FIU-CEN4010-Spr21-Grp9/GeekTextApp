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
public class BookBrowsePane extends JPanel implements ActionListener, ItemListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8408321676732254210L;

	// nested classes (buttons/drop boxes)
	private class TopSellerButton extends JButton
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1661541205619521569L;

		public TopSellerButton(String buttonText)
		{
			setText(buttonText);
		};
	}
	
	private class GenreListBox extends JComboBox<String>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2049325020252386788L;

		public GenreListBox(final String[] genreList)
		{
			SetItemList(genreList);
		};
		
		public void SetItemList(final String[] genreList)
		{
			for(int i = 0; i < genreList.length; i++)
			{
				addItem(genreList[i]);
			}
		}
	}
	
	private class RatingListBox extends JComboBox<String>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 5958497223515196397L;
		private String[] ratings = {"Any", "1", "2", "3", "4", "5"};
		
		public RatingListBox()
		{
			for(int i = 0; i < ratings.length; i++)
			{
				addItem(ratings[i]);
			}
		};
	}
	
	private class RowsPerPageBox extends JComboBox<String>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4335331721676422736L;
		private String[] rowsPerPageList = {"20", "10"};

		public RowsPerPageBox()
		{
			for(int i = 0; i < rowsPerPageList.length; i++)
			{
				addItem(rowsPerPageList[i]);
			}
		};
	}
	
	private class CurrentPageBox extends JComboBox<String>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7148097855665687725L;

		public CurrentPageBox(final String[] pageList)
		{
			SetItemList(pageList);
		};
		
		public void SetItemList(final String[] pageList)
		{
			for(int i = 0; i < pageList.length; i++)
			{
				addItem(pageList[i]);
			}
		}
	}
	
	// private values
	private String rootURL;
	private BookBrowser booksBrowser;
	private JScrollPane booksListPane;
	private TopSellerButton btnTopSellers;
	private GenreListBox cmbGenreList;
	private RatingListBox cmbRating;
	private RowsPerPageBox cmbRowsPerPage;
	private CurrentPageBox cmbPage;
	private JTable booksTable;
	private JPanel topPanel;
	private JPanel dataPanel;
	private JPanel bottomPanel;
	private JLabel labelRowsPerPage;
	private JLabel labelPageNumber;
	private JLabel labelGenreBox;
	private JLabel labelRating;
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbc_topPanel;
	private GridBagConstraints gbc_dataPanel;
	private GridBagConstraints gbc_bottomPanel;
	
	// constructor
	public BookBrowsePane(String rootURL)
	{
		// local variables
		this.rootURL = rootURL;
		this.booksBrowser = new BookBrowser(this.rootURL);
		
		// this panel stuff
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		// top panel
		BuildTopPanel();
		
		// data panel
		BuildDataPanel();
		
		// bottom panel
		BuildBottomPanel();
		
		// add panels
		add(topPanel, gbc_topPanel);
		add(bottomPanel, gbc_bottomPanel);		
		add(dataPanel, gbc_dataPanel);
		
		// Add Item Listeners
		AddItemListeners();
	};
	
	private void BuildTopPanel()
	{
		topPanel = new JPanel();
		
		btnTopSellers = new TopSellerButton("Top Sellers");
		btnTopSellers.addActionListener(this);
		topPanel.add(btnTopSellers);
		
		labelGenreBox = new JLabel("Genre:");
		topPanel.add(labelGenreBox);
		
		cmbGenreList = new GenreListBox(booksBrowser.GetGenreList());
		cmbGenreList.setSelectedIndex(booksBrowser.GetGenreId());
		topPanel.add(cmbGenreList);
		
		labelRating = new JLabel("Rating:");
		topPanel.add(labelRating);
		
		cmbRating = new RatingListBox();
		cmbRating.setSelectedIndex(booksBrowser.GetRating());
		topPanel.add(cmbRating);
		
		gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 5);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		
		topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (btnTopSellers.getMaximumSize().height + 10)));
	}
	
	private void BuildDataPanel()
	{
		dataPanel = null;
		gbc_dataPanel = null;
		booksTable = null;
		booksListPane = null;
		
		dataPanel = new JPanel();	
		gbc_dataPanel = new GridBagConstraints();
		
		gbc_dataPanel.fill = GridBagConstraints.BOTH;
		gbc_dataPanel.gridx = 0;
		gbc_dataPanel.gridy = 1;
		
		booksTable = booksBrowser.GetBooksTable();
		booksListPane = new JScrollPane(booksTable);
		
		booksListPane.setPreferredSize(new Dimension(1000, 550));
		
		dataPanel.add(booksListPane);
	}
	
	private void BuildBottomPanel()
	{
		bottomPanel = new JPanel();
		
		labelRowsPerPage = new JLabel("Rows:");
		bottomPanel.add(labelRowsPerPage);
		
		cmbRowsPerPage = new RowsPerPageBox();
		bottomPanel.add(cmbRowsPerPage);
		
		labelPageNumber = new JLabel("Page:");
		bottomPanel.add(labelPageNumber);
		
		cmbPage = new CurrentPageBox(booksBrowser.GetPageList());
		bottomPanel.add(cmbPage);
		
		gbc_bottomPanel = new GridBagConstraints();
		gbc_bottomPanel.fill = GridBagConstraints.BOTH;
		gbc_bottomPanel.insets = new Insets(0, 0, 5, 5);
		gbc_bottomPanel.gridx = 0;
		gbc_bottomPanel.gridy = 2;
		
		bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (cmbPage.getMaximumSize().height + 10)));
	}
	
	// action code
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object eventSource = event.getSource();
		
		if(eventSource instanceof TopSellerButton)
		{
			booksBrowser.ListBooksByTopSellers();
			refreshPanel();
		}
	}
	
	// item code
	@Override
	public void itemStateChanged(ItemEvent event)
	{
		int c;
		
		if(event.getStateChange() == ItemEvent.SELECTED)
		{
			Object eventSource = event.getSource();
			
			if(eventSource instanceof GenreListBox)
			{
				GenreListBox soureBox = (GenreListBox) eventSource;
				
				booksBrowser.SetGenreId(soureBox.getSelectedIndex());
			}
			else if (eventSource instanceof RatingListBox)
			{
				RatingListBox soureBox = (RatingListBox) eventSource;
				
				booksBrowser.SetRating(soureBox.getSelectedIndex());
			}
			else if (eventSource instanceof RowsPerPageBox)
			{
				RowsPerPageBox soureBox = (RowsPerPageBox) eventSource;
				c = Integer.parseInt(soureBox.getSelectedItem().toString());
				
				booksBrowser.SetRowsPerPage(c);
			}
			else if (eventSource instanceof CurrentPageBox)
			{
				CurrentPageBox soureBox = (CurrentPageBox) eventSource;
				c = Integer.parseInt(soureBox.getSelectedItem().toString());
				
				booksBrowser.SetCurrentPage(c);
			}
			
			refreshPanel();
		}
	}

	// refresh entire panel
	private void refreshPanel()
	{
		// Handle the interactive bits
		RemoveItemListeners();
		
		cmbGenreList.setSelectedIndex(booksBrowser.GetGenreId());
		cmbRating.setSelectedIndex(booksBrowser.GetRating());
		cmbRowsPerPage.setSelectedItem(booksBrowser.GetRowsPerPage().toString());
		
		cmbPage.removeAllItems();
		cmbPage.SetItemList(booksBrowser.GetPageList());
		cmbPage.setSelectedItem(booksBrowser.GetCurrentPage().toString());
		
		AddItemListeners();
		
		// Handle the table
		remove(dataPanel);
		BuildDataPanel();
		add(dataPanel, gbc_dataPanel);
	}
	
	// handle item listeners
	private void RemoveItemListeners()
	{
		cmbGenreList.removeItemListener(this);
		cmbRating.removeItemListener(this);
		cmbPage.removeItemListener(this);
		cmbRowsPerPage.removeItemListener(this);
	}
	
	private void AddItemListeners()
	{
		cmbGenreList.addItemListener(this);
		cmbRating.addItemListener(this);
		cmbPage.addItemListener(this);
		cmbRowsPerPage.addItemListener(this);
	}
}