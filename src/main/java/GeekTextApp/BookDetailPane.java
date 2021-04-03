package GeekTextApp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JLabel labelTitle;
	private JLabel labelAuthors;
	private JLabel lblBookTitle;
	private int authors;
	private int retAuthorID;
	
	// constructor
	public BookDetailPane(Integer bookID)
	{
		super(null, Dialog.ModalityType.APPLICATION_MODAL);
		this.bookDetails = new BookDetail(bookID);
		this.setTitle(bookDetails.getTitle());
		
		// defaults
		this.retAuthorID = 0;
				
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		mainPanel.setLayout(gbl_mainPanel);
		
		// Title
		labelTitle = new JLabel("Title: ");
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.gridx = 0;
		gbc_labelTitle.gridy = 0;
		mainPanel.add(labelTitle, gbc_labelTitle);
		
		lblBookTitle = new JLabel(bookDetails.getTitle());
		GridBagConstraints gbc_lblBookTitle = new GridBagConstraints();
		gbc_lblBookTitle.gridx = 1;
		gbc_lblBookTitle.gridy = 0;
		mainPanel.add(lblBookTitle, gbc_lblBookTitle);
		
		// Authors
		labelAuthors = new JLabel("Author(s): "); 
		GridBagConstraints gbc_labelAuthors = new GridBagConstraints();
		gbc_labelAuthors.gridx = 0;
		gbc_labelAuthors.gridy = 1;
		mainPanel.add(labelAuthors, gbc_labelAuthors);
		
		authors = bookDetails.getAuthorCount();
		for(int i = 0; i < authors; i++)
		{
			AuthorLabel lblAuthor = new AuthorLabel(bookDetails.getAuthor(i));
			GridBagConstraints gbc_lblAuthors = new GridBagConstraints();
			gbc_lblAuthors.gridx = i + 1;
			gbc_lblAuthors.gridy = 1;
			lblAuthor.addMouseListener(
					new java.awt.event.MouseAdapter()
					{
						@Override
						public void mouseClicked(java.awt.event.MouseEvent evt)
						{
							retAuthorID = lblAuthor.getAuthorID();
							dispose();
						}
					}
				);
			mainPanel.add(lblAuthor, gbc_lblAuthors);
		}
		
		pack();
		setVisible(true);
	};
	
	
	// RETURN INFO
	public int getRetAuthorID()
	{
		// If this is not 0, then BookBrowser will search by the returned authorID
		// This defaults to 0, if the user clicks on the author name then this ID will be set and the window will be closed
		return retAuthorID;
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
}