package GeekTextApp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.BorderLayout;

/**
*  Title: GeekTextApp
*  Semester: CEN4010 - Spring 2021
*  @author 
* 
* I affirm that this program is entirely my own work and none of it is the work
* of any other person. 
* 
* This class provides the nested book details frame class for the GeekText GUI 
* 
*/
public class BookDetailPane extends JDialog implements ActionListener, ItemListener
{
	private class AuthorLabel extends JLabel
	{
		private int AuthorID;
		private String AuthorName;
		
		public AuthorLabel(Author author)
		{
			this.AuthorID = author.getAuthorID();
			this.AuthorName = author.getAuthorName();
			this.setText(AuthorName);
			this.setForeground(Color.blue.darker());
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		};
		
		public int getAuthorID()
		{
			return AuthorID;
		}
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3282502659216550761L;
	
	// private variables
	private BookDetail bookDetails;
	private JPanel mainPanel;
	private int authors;
	private int[] retAuthorID;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtGenre;
	private JTextField txtPublisher;
	private JTextField txtPublishingDate;
	private JTextField txtIsbn;
	private JTextField txtRating;
	private JTextField txtPrice;
	private  JTextArea  title;
	private JTextArea genre;
	private JTextArea publisher;
	private JTextArea publishingDate;
	private JTextArea isbn;
	private JTextArea rating;
	private JTextArea price;
	private JPanel BioPanel;
	private JTextField txtAuthorBio;
	private JTextArea bio;
	private JPanel CommentPanel;
	private JTextField txtComments;
	private JTextArea commentBlock1;
	private JTextArea commentBlock2;
	private JTextArea commentBlock3;
	private JTextArea commentBlock4;
	private JTextArea emptyBlock1;
	private JTextArea emptyBlock2;
	private JTextArea emptyBlock3;
	private JTextArea emptyBlock4;
	private JLabel enlargedCover;
	private JLabel enlargeLabel;
	private JPanel panel;
	private AuthorLabel[] lblAuthors;
	private int authorIDClicked;
	private JButton Back;
	private JButton Next;
	
	// constructor
	public BookDetailPane(Integer bookID)
	{
		super(null, Dialog.ModalityType.APPLICATION_MODAL);
		getContentPane().setBackground(Color.WHITE);
		this.bookDetails = new BookDetail(bookID);
		this.setTitle(bookDetails.getTitle());
		
		// HYPERLINK STUFF
		authors = bookDetails.getAuthorCount();
		
		retAuthorID = new int[authors];
		for(int x = 0; x < authors; x++)
		{
			retAuthorID[x] = bookDetails.getAuthor(x).getAuthorID();
		}
				
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_mainPanel.rowHeights = new int[]{356, 0, 311, 38};
		gbl_mainPanel.columnWidths = new int[]{274, 0, 407};
		mainPanel.setLayout(gbl_mainPanel);
		
		Image image = (Image)bookDetails.getBook().getCoverImage();
		Image scale = image.getScaledInstance(270, 350, Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(scale);
	
		JLabel lblNewLabel = new JLabel(img);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		mainPanel.add(lblNewLabel, gbc_lblNewLabel);
		

		
		JPanel DetailsPanel = new JPanel();
		DetailsPanel.setLayout(null);
		
		GridBagConstraints gbc_DetailsPanel = new GridBagConstraints();
		gbc_DetailsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_DetailsPanel.fill = GridBagConstraints.BOTH;
		gbc_DetailsPanel.gridx = 2;
		gbc_DetailsPanel.gridy = 0;
		mainPanel.add(DetailsPanel, gbc_DetailsPanel);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setText("Title");
		txtTitle.setBounds(0, 0, 102, 45);
		DetailsPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setText("Author(s)");
		txtAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAuthor.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(0, 44, 102, 45);
		DetailsPanel.add(txtAuthor);
		
		txtGenre = new JTextField();
		txtGenre.setText("Genre");
		txtGenre.setHorizontalAlignment(SwingConstants.CENTER);
		txtGenre.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGenre.setColumns(10);
		txtGenre.setBounds(0, 87, 102, 45);
		DetailsPanel.add(txtGenre);
		
		txtPublisher = new JTextField();
		txtPublisher.setText("Publisher");
		txtPublisher.setHorizontalAlignment(SwingConstants.CENTER);
		txtPublisher.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPublisher.setColumns(10);
		txtPublisher.setBounds(0, 131, 102, 45);
		DetailsPanel.add(txtPublisher);
		
		txtPublishingDate = new JTextField();
		txtPublishingDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtPublishingDate.setText("Publishing Date");
		txtPublishingDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPublishingDate.setColumns(10);
		txtPublishingDate.setBounds(0, 175, 102, 45);
		DetailsPanel.add(txtPublishingDate);
		
		txtIsbn = new JTextField();
		txtIsbn.setText("ISBN");
		txtIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		txtIsbn.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(0, 219, 102, 45);
		DetailsPanel.add(txtIsbn);
		
		txtRating = new JTextField();
		txtRating.setText("Rating");
		txtRating.setHorizontalAlignment(SwingConstants.CENTER);
		txtRating.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtRating.setColumns(10);
		txtRating.setBounds(0, 263, 102, 45);
		DetailsPanel.add(txtRating);
		
		txtPrice = new JTextField();
		txtPrice.setText("Price");
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(0, 306, 102, 45);
		DetailsPanel.add(txtPrice);
		
		
		//Title
		title = new JTextArea();
		title.setLineWrap(true);
		title.setFont(new Font("Monospaced", Font.BOLD, setTextSize(bookDetails.getBook().getTitle().length())));
		title.setText(bookDetails.getBook().getTitle());
		title.setBounds(101, 0, 306, 45);
		DetailsPanel.add(title);
		
		
		
		genre = new JTextArea();
		genre.setLineWrap(true);
		genre.setForeground(new Color(0, 0, 0));
		genre.setFont(new Font("Monospaced", Font.BOLD, 16));
		genre.setText(bookDetails.getBook().getGenreList());
		genre.setBounds(101, 87, 306, 45);
		DetailsPanel.add(genre);
		
		publisher = new JTextArea();
		publisher.setLineWrap(true);
		publisher.setFont(new Font("Monospaced", Font.BOLD, 18));
		publisher.setText(bookDetails.getBook().getPublisher());
		publisher.setBounds(101, 131, 306, 45);
		DetailsPanel.add(publisher);
		
		publishingDate = new JTextArea();
		publishingDate.setLineWrap(true);
		publishingDate.setFont(new Font("Monospaced", Font.BOLD, 18));
		publishingDate.setText(bookDetails.getBook().getPublishDate());
		publishingDate.setBounds(101, 175, 306, 45);
		DetailsPanel.add(publishingDate);
		
		isbn = new JTextArea();
		isbn.setLineWrap(true);
		isbn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
		isbn.setText(bookDetails.getBook().getIsbn());
		isbn.setBounds(101, 219, 306, 45);
		DetailsPanel.add(isbn);
		
		rating = new JTextArea();
		rating.setLineWrap(true);
		rating.setFont(new Font("Monospaced", Font.BOLD, 20));
		rating.setText(String.valueOf(bookDetails.getBook().getRating()));
		rating.setBounds(101, 263, 306, 45);
		DetailsPanel.add(rating);
		
		price = new JTextArea();
		price.setLineWrap(true);
		price.setFont(new Font("Monospaced", Font.BOLD, 20));
		price.setText(String.valueOf(bookDetails.getBook().getPrice()));
		price.setBounds(101, 306, 306, 45);
		DetailsPanel.add(price);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		mainPanel.add(panel, gbc_panel);
		
		BioPanel = new JPanel();
		BioPanel.setLayout(null);
		GridBagConstraints gbc_BioPanel = new GridBagConstraints();
		gbc_BioPanel.insets = new Insets(0, 0, 5, 5);
		gbc_BioPanel.fill = GridBagConstraints.BOTH;
		gbc_BioPanel.gridx = 0;
		gbc_BioPanel.gridy = 2;
		mainPanel.add(BioPanel, gbc_BioPanel);
		
		txtAuthorBio = new JTextField();
		txtAuthorBio.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtAuthorBio.setHorizontalAlignment(SwingConstants.CENTER);
		txtAuthorBio.setText("Author Bio");
		txtAuthorBio.setBounds(0, 0, 269, 47);
		BioPanel.add(txtAuthorBio);
		txtAuthorBio.setColumns(10);
		
		bio = new JTextArea();
		bio.setFont(new Font("Monospaced", Font.BOLD, 16));
		bio.setText(bookDetails.getAuthorBio(0));
		bio.setBounds(0, 47, 269, 264);
		BioPanel.add(bio);
		
		
		CommentPanel = new JPanel();
		CommentPanel.setLayout(null);
		GridBagConstraints gbc_CommentPanel = new GridBagConstraints();
		gbc_CommentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_CommentPanel.fill = GridBagConstraints.BOTH;
		gbc_CommentPanel.gridx = 2;
		gbc_CommentPanel.gridy = 2;
		mainPanel.add(CommentPanel, gbc_CommentPanel);
		
		txtComments = new JTextField();
		txtComments.setBounds(0, 0, 407, 48);
		txtComments.setText("Comments");
		txtComments.setHorizontalAlignment(SwingConstants.CENTER);
		txtComments.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtComments.setColumns(10);
		CommentPanel.add(txtComments);
		
		
		
	    int reviews = bookDetails.getReviewCount();
		int comNum1 = 0;
		int comNum2 = 1;
		int comNum3 = 2;
		int comNum4 = 3;
		
		
		if(reviews > 0) {
			commentBlock1 = new JTextArea((comNum1 + 1) + ".) " + bookDetails.getReviews(comNum1).getDescription());
			commentBlock1.setBounds(0, 46, 407, 67);
			CommentPanel.add(commentBlock1);
		}else {
			emptyBlock1 = new JTextArea((comNum1 + 1) + ".) " +"{Empty}");
			emptyBlock1.setBounds(0, 46, 407, 67);
			CommentPanel.add(emptyBlock1);
		}	
		
		if(reviews > 1) {
			commentBlock2 = new JTextArea((comNum2 + 1) + ".) " +bookDetails.getReviews(comNum2).getDescription());
			commentBlock2.setBounds(0, 114, 407, 67);
			CommentPanel.add(commentBlock2);
		}else {
			emptyBlock2 = new JTextArea((comNum2 + 1) + ".) " +"{Empty}");
			emptyBlock2.setBounds(0, 114, 407, 67);
			CommentPanel.add(emptyBlock2);
		}
		
		if(reviews > 2) {
			commentBlock3 = new JTextArea((comNum3 + 1) + ".) " +bookDetails.getReviews(comNum3).getDescription());
			commentBlock3.setBounds(0, 181, 407, 67);
			CommentPanel.add(commentBlock3);
		}else {
			emptyBlock3 = new JTextArea((comNum3 + 1) + ".) " +"{Empty}");
			emptyBlock3.setBounds(0, 181, 407, 67);
			CommentPanel.add(emptyBlock3);
		}
		
		if(reviews > 3) {
			commentBlock4 = new JTextArea((comNum4 + 1) + ".) " +bookDetails.getReviews(comNum4).getDescription());
			commentBlock4.setBounds(0, 244, 407, 67);
			CommentPanel.add(commentBlock4);
		}else {
			emptyBlock4 = new JTextArea((comNum4 + 1) + ".) " +"{Empty}");
			emptyBlock4.setBounds(0, 244, 407, 67);
			CommentPanel.add(emptyBlock4);
		}
		
		ImageIcon ex = new ImageIcon(image);
		enlargeLabel = new JLabel(ex);
		enlargeLabel.setBounds(0, 0, 483, 566);
		panel.add(enlargeLabel);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(null);
		GridBagConstraints gbc_ButtonPanel = new GridBagConstraints();
		gbc_ButtonPanel.insets = new Insets(0, 0, 0, 5);
		gbc_ButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_ButtonPanel.gridx = 0;
		gbc_ButtonPanel.gridy = 3;
		mainPanel.add(ButtonPanel, gbc_ButtonPanel);
		
		JButton AddToCart = new JButton("Add To Cart");
		AddToCart.setForeground(Color.WHITE);
		AddToCart.setFont(new Font("Tahoma", Font.BOLD, 12));
		AddToCart.setBackground(Color.BLUE);
		AddToCart.setBounds(0, 0, 135, 38);
		ButtonPanel.add(AddToCart);
		
		JButton AddToWishlist = new JButton("Add To Wishlist");
		AddToWishlist.setForeground(Color.WHITE);
		AddToWishlist.setFont(new Font("Tahoma", Font.BOLD, 12));
		AddToWishlist.setBackground(Color.GREEN);
		AddToWishlist.setBounds(134, 0, 135, 38);
		ButtonPanel.add(AddToWishlist);
		
		JPanel NextCommentTabs = new JPanel();
		NextCommentTabs.setLayout(null);
		GridBagConstraints gbc_NextCommentTabs = new GridBagConstraints();
		gbc_NextCommentTabs.fill = GridBagConstraints.BOTH;
		gbc_NextCommentTabs.gridx = 2;
		gbc_NextCommentTabs.gridy = 3;
		mainPanel.add(NextCommentTabs, gbc_NextCommentTabs);
		
		
		
		
		
		
		
		
		
		panel.setVisible(false);
		
		lblNewLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				CommentPanel.setVisible(false);
				DetailsPanel.setVisible(false);
				BioPanel.setVisible(false);
				lblNewLabel.setVisible(false);
				panel.setVisible(true);
				
				panel.setFocusable(true);
				panel.requestFocus();
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				CommentPanel.setVisible(true);
				DetailsPanel.setVisible(true);
				BioPanel.setVisible(true);
				lblNewLabel.setVisible(true);
				panel.setVisible(false);
				
			}
		});
					
		if(authors > 3)
			authors = 3;
		else if(authors < 1)
			authors = 1;
		
		lblAuthors = new AuthorLabel[authors]; // Actual Labels to be called in the Constructor to create the window panels

		
		
		for(int i = 0; i < authors; i++)
		{
			lblAuthors[i] = new AuthorLabel(bookDetails.getAuthor(i));
			final int mi = (int)i;
		   	lblAuthors[i].addMouseListener(
					new java.awt.event.MouseAdapter()
					{
						@Override
						public void mouseClicked(java.awt.event.MouseEvent evt)
						{
							authorIDClicked = retAuthorID[mi];
							dispose();
						}
					}
				);
		}
		
		
		Back = new JButton("<<  Back");
		Back.setFont(new Font("Tahoma", Font.BOLD, 12));
		Back.setBounds(0, 0, 203, 38);
		NextCommentTabs.add(Back);
		Back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if((comNum1 - 4) >= 0) {
				CommentPanel.removeAll();
				commentBlock1 = null;
				commentBlock2 = null;
				commentBlock3 = null;
				commentBlock4 = null;
				emptyBlock1 = null;
				emptyBlock2 = null;
				emptyBlock3 = null;
				emptyBlock4 = null;
				dicramentOut(comNum1, comNum2, comNum3, comNum4, reviews);
				//}
				
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				CommentPanel.removeAll();
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
		
		Next = new JButton("Next >>");
		Next.setFont(new Font("Tahoma", Font.BOLD, 12));
		Next.setBounds(204, 0, 203, 38);
		NextCommentTabs.add(Next);
		Next.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if((comNum1 + 4) <= reviews) {
				//incrament(comNum1, comNum2, comNum3, comNum4);
				CommentPanel.removeAll();
				commentBlock1 = null;
				commentBlock2 = null;
				commentBlock3 = null;
				commentBlock4 = null;
				emptyBlock1 = null;
				emptyBlock2 = null;
				emptyBlock3 = null;
				emptyBlock4 = null;
				incramentOut(comNum1, comNum2, comNum3, comNum4, reviews);
				//}
				/*
				if(comNum1 < reviews)
				commentBlock1.setText((comNum1 + 1) + ".) " +bookDetails.getReviews(comNum1).getDescription()); 
				else
				commentBlock1.setText((comNum1 + 1) + ".) " +"{Empty}"); 	
				if(comNum2 < reviews)
				commentBlock2.setText((comNum2 + 1) + ".) " +bookDetails.getReviews(comNum2).getDescription()); 
				else
				commentBlock2.setText((comNum2 + 1) + ".) " +"{Empty}");
				if(comNum3 < reviews)
				commentBlock3.setText((comNum3 + 1) + ".) " +bookDetails.getReviews(comNum3).getDescription()); 
				else
				commentBlock3.setText((comNum3 + 1) + ".) " +"{Empty}");
				if(comNum4 < reviews)
				commentBlock4.setText((comNum4 + 1) + ".) " +bookDetails.getReviews(comNum4).getDescription()); 
				else
				commentBlock4.setText((comNum4 + 1) + ".) " +"{Empty}");
				*/
				
			}

			public void incrament(int comNum1, int comNum2, int comNum3, int comNum4) {
				
				comNum1 += 4;
				comNum2 += 4;
				comNum3 += 4;
				comNum4 += 4;
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				CommentPanel.removeAll();
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});

		
		//Create panels
		
		if(authors == 1)
		{
			lblAuthors[0].setBounds(101, 44, 306, 45);
			lblAuthors[0].setHorizontalAlignment(SwingConstants.CENTER);
			DetailsPanel.add(lblAuthors[0]);		
			lblAuthors[0].setVisible(true);
		}
		else  if(authors == 2)
		{
			lblAuthors[0].setBounds(101, 44, 153, 45);
			lblAuthors[0].setHorizontalAlignment(SwingConstants.CENTER);
			DetailsPanel.add(lblAuthors[0]);		
			lblAuthors[0].setVisible(true);
			
			lblAuthors[1].setBounds(254, 44, 153, 45);
			lblAuthors[1].setHorizontalAlignment(SwingConstants.CENTER);
			DetailsPanel.add(lblAuthors[1]);		
			lblAuthors[1].setVisible(true);
		}
		else  if(authors == 3)
		{
			lblAuthors[0].setBounds(101, 44, 102, 45);
			lblAuthors[0].setHorizontalAlignment(SwingConstants.CENTER);
			DetailsPanel.add(lblAuthors[0]);		
			lblAuthors[0].setVisible(true);
			
			lblAuthors[1].setBounds(202, 44, 102, 45);
			lblAuthors[1].setHorizontalAlignment(SwingConstants.CENTER);
			DetailsPanel.add(lblAuthors[1]);		
			lblAuthors[1].setVisible(true);
			
			lblAuthors[2].setHorizontalAlignment(SwingConstants.CENTER);
			lblAuthors[2].setBounds(305, 44, 102, 45);
			DetailsPanel.add(lblAuthors[2]);		
			lblAuthors[2].setVisible(true);
		}
		
				
		pack();
		setVisible(true);	
		
	};// End of Constructor
	

	

	public int setTextSize(int i) {
		if(i < 7) {
			return 26;
		}
		else if(i >= 10 && i < 20)
		{
			return 18;
		}
		else if(i >= 20 && i < 30)
		{
			return 14;
		}
		else 
		{
			return 12;
		}
		
			
		
	}




	// RETURN INFO
	public int getRetAuthorID()
	{
		// If this is not 0, then BookBrowser will search by the returned authorID
		// This defaults to 0, if the user clicks on the author name then this ID will be set and the window will be closed
		return authorIDClicked;
	}
	
	// action code
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object eventSource = event.getSource();
		
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
	
	public void incramentOut(int comNum1, int comNum2, int comNum3, int comNum4, int reviews) {
		
		comNum1 += 4;
		comNum2 += 4;
		comNum3 += 4;
		comNum4 += 4;
		System.out.println(comNum1);
		
		txtComments = new JTextField();
		txtComments.setBounds(0, 0, 407, 48);
		txtComments.setText("Comments");
		txtComments.setHorizontalAlignment(SwingConstants.CENTER);
		txtComments.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtComments.setColumns(10);
		CommentPanel.add(txtComments);
		reviews = bookDetails.getReviewCount();
		bookDetails.printAll();
		System.out.println("REVIEWS: " + reviews);
		System.out.println("comNUM1: " + comNum1);
		
		if(reviews > comNum1) {
			commentBlock1 = new JTextArea((comNum1 + 1) + ".) " + bookDetails.getReviews(comNum1).getDescription());
			commentBlock1.setBounds(0, 46, 407, 67);
			CommentPanel.add(commentBlock1);
		}else {
			emptyBlock1 = new JTextArea((comNum1 + 1) + ".) " +"{Empty}");
			emptyBlock1.setBounds(0, 46, 407, 67);
			CommentPanel.add(emptyBlock1);
		}	
		
		if(reviews > comNum2) {
			commentBlock2 = new JTextArea((comNum2 + 1) + ".) " +bookDetails.getReviews(comNum2).getDescription());
			commentBlock2.setBounds(0, 114, 407, 67);
			CommentPanel.add(commentBlock2);
		}else {
			emptyBlock2 = new JTextArea((comNum2 + 1) + ".) " +"{Empty}");
			emptyBlock2.setBounds(0, 114, 407, 67);
			CommentPanel.add(emptyBlock2);
		}
		
		if(reviews > comNum3) {
			commentBlock3 = new JTextArea((comNum3 + 1) + ".) " +bookDetails.getReviews(comNum3).getDescription());
			commentBlock3.setBounds(0, 181, 407, 67);
			CommentPanel.add(commentBlock3);
		}else {
			emptyBlock3 = new JTextArea((comNum3 + 1) + ".) " +"{Empty}");
			emptyBlock3.setBounds(0, 181, 407, 67);
			CommentPanel.add(emptyBlock3);
		}
		
		if(reviews > comNum4) {
			commentBlock4 = new JTextArea((comNum4 + 1) + ".) " +bookDetails.getReviews(comNum4).getDescription());
			commentBlock4.setBounds(0, 244, 407, 67);
			CommentPanel.add(commentBlock4);
		}else {
			emptyBlock4 = new JTextArea((comNum4 + 1) + ".) " +"{Empty}");
			emptyBlock4.setBounds(0, 244, 407, 67);
			CommentPanel.add(emptyBlock4);
		}
		

	}
	
	public void dicramentOut(int comNum1, int comNum2, int comNum3, int  comNum4, int reviews) {
		if((comNum1 - 4) >= 0) {
		comNum1 -= 4;
		comNum2 -= 4;
		comNum3 -= 4;
		comNum4 -= 4;
		}
		
		txtComments = new JTextField();
		txtComments.setBounds(0, 0, 407, 48);
		txtComments.setText("Comments");
		txtComments.setHorizontalAlignment(SwingConstants.CENTER);
		txtComments.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtComments.setColumns(10);
		CommentPanel.add(txtComments);
		reviews = bookDetails.getReviewCount();
		
		if(reviews > comNum1) {
			commentBlock1 = new JTextArea((comNum1 + 1) + ".) " + bookDetails.getReviews(comNum1).getDescription());
			commentBlock1.setBounds(0, 46, 407, 67);
			CommentPanel.add(commentBlock1);
		}else {
			emptyBlock1 = new JTextArea((comNum1 + 1) + ".) " +"{Empty}");
			emptyBlock1.setBounds(0, 46, 407, 67);
			CommentPanel.add(emptyBlock1);
		}	
		
		if(reviews > comNum2) {
			commentBlock2 = new JTextArea((comNum2 + 1) + ".) " +bookDetails.getReviews(comNum2).getDescription());
			commentBlock2.setBounds(0, 114, 407, 67);
			CommentPanel.add(commentBlock2);
		}else {
			emptyBlock2 = new JTextArea((comNum2 + 1) + ".) " +"{Empty}");
			emptyBlock2.setBounds(0, 114, 407, 67);
			CommentPanel.add(emptyBlock2);
		}
		
		if(reviews > comNum3) {
			commentBlock3 = new JTextArea((comNum3 + 1) + ".) " +bookDetails.getReviews(comNum3).getDescription());
			commentBlock3.setBounds(0, 181, 407, 67);
			CommentPanel.add(commentBlock3);
		}else {
			emptyBlock3 = new JTextArea((comNum3 + 1) + ".) " +"{Empty}");
			emptyBlock3.setBounds(0, 181, 407, 67);
			CommentPanel.add(emptyBlock3);
		}
		
		if(reviews > comNum4) {
			commentBlock4 = new JTextArea((comNum4 + 1) + ".) " +bookDetails.getReviews(comNum4).getDescription());
			commentBlock4.setBounds(0, 244, 407, 67);
			CommentPanel.add(commentBlock4);
		}else {
			emptyBlock4 = new JTextArea((comNum4 + 1) + ".) " +"{Empty}");
			emptyBlock4.setBounds(0, 244, 407, 67);
			CommentPanel.add(emptyBlock4);
		}
		
		
	}
	
	
}