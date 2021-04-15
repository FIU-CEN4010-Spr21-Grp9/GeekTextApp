package GeekTextApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.Dialog;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.RootPaneContainer;
import javax.swing.JFrame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.BorderLayout;

public class ReviewInputPane extends JPanel implements ActionListener, ItemListener
{
	private class BookButton extends JButton
	{
		private int BookID;
		private String BookName;
		
		public BookButton(Book book)
		{
			this.BookID = book.getBookID();
			this.BookName = book.getTitle();
			this.setText(BookName);
			this.setForeground(Color.blue.darker());
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		};
		
		public int getBookID()
		{
			return BookID;
		}
	}
	
//	private static final long serialVersionUID = -8408321676732254210L;
	
	private static final long serialVersionUID = 3282502659216550761L;
	
	private class EnterButton extends JButton
	{
		
		  
		 
		private static final long serialVersionUID = 1661541205619521569L;

		public EnterButton(String enterText)
		{
			setText(enterText);
		};
	}
	
	private class AnonButton extends JButton
	{
		private static final long serialVersionUID = 1661541205619521569L;

		public AnonButton(String anonText)
		{
			setText(anonText);
		};
	}
	
	private class UserButton extends JButton
	{
		private static final long serialVersionUID = 1661541205619521569L;

		public UserButton(String nameText)
		{
			setText(nameText);
		};
	}
	
	
	
	private static final long serilaVersionUID = 2561792659216550784L;

	// private values
	private int reviewID;
	private int rating;
	private int bookID;
	private int books;
	private String rootURL;
	private JPanel mainPanel;
	private JComboBox<String> raiting;
	private JLabel title;
	private JLabel output;
	private EnterButton enterbtn;
	private UserButton userbtn;
	private AnonButton anonbtn;
	private JTextArea input;
	private ReviewInput reviewInput;
	private int[] retBookID;
		
	// constructor
	public ReviewInputPane(Integer reviewID)
	{
		/*
		//vars
		setBounds(100, 100, 610, 803);
		getContentPane().setBackground(Color.WHITE);
		this.reviewInput = new ReviewInput(reviewID);
		this.setTitle(ReviewInupt.getTitl);
		books = ReviewInput.getReviewCount();
		
		retBookID = new int[books];
		for(int x = 0; x < books; x++)
		{
			retBookID[x] = reviewInput.getReview(x).getReviewID();
		}
				
		mainPanel = new JPanel();
		
		getRootPane().add(mainPanel, BorderLayout.NORTH);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_mainPanel.rowHeights = new int[]{356, 0, 311, 38};
		gbl_mainPanel.columnWidths = new int[]{274, 0, 407};
		mainPanel.setLayout(gbl_mainPanel);
		this.retBookID = 0;
		
		//this.reviewInput = new ReviewInput();
			*/
		JPanel mainPanel = new JPanel();
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(null);
		GridBagConstraints gbc_ButtonPanel = new GridBagConstraints();
		gbc_ButtonPanel.insets = new Insets(0, 0, 0, 5);
		gbc_ButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_ButtonPanel.gridx = 0;
		gbc_ButtonPanel.gridy = 3;
		mainPanel.add(ButtonPanel, gbc_ButtonPanel);
		
		JButton AddToCart = new JButton("Anonymous");
		AddToCart.setForeground(Color.WHITE);
		AddToCart.setFont(new Font("Tahoma", Font.BOLD, 12));
		AddToCart.setBackground(Color.BLUE);
		AddToCart.setBounds(0, 0, 120, 38);
		ButtonPanel.add(AddToCart);
		
		// stuff
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		anonbtn = new AnonButton("UserName");
		anonbtn.addActionListener(this);
		mainPanel.add(enterbtn);
		
		userbtn = new UserButton("Anonymous");
		userbtn.addActionListener(this);
		mainPanel.add(userbtn);
		
		JPanel topPanel = new JPanel();
		
		input = new JTextArea();
		topPanel.add(input);
		
		enterbtn = new EnterButton("Enter");
		enterbtn.addActionListener(this);
		topPanel.add(enterbtn);
			
		JLabel labelRating = new JLabel("Rating:");
		topPanel.add(labelRating);
			
		String[] ratings = {"1", "2", "3", "4", "5"};
		JComboBox cmbRating = new JComboBox(ratings);
		topPanel.add(cmbRating);
			
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 5);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
			
		JPanel dataPanel = new JPanel();
			
		
	}
		@Override
		public void actionPerformed(ActionEvent event)
		{
			Object eventSource = event.getSource();
			
			String s = event.getActionCommand();
			if (s.equals("Enter")) {
				output.setText(input.getText());
			}
			// Do stuff based on event source
		}
		
		// item code
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			if(event.getStateChange() == ItemEvent.SELECTED)
			{
				Object eventSource = event.getSource();
				
				// do stuff based on event source
			}
		}
		
}
