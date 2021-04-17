package GeekTextApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTable;

public class WishlistBrowserFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1886236604813847L;
	private JPanel contentPane;
	private JList<String> WishlistItems;
	private JPopupMenu menu;
	private WishlistController controller = new WishlistController();
	private JComboBox WishlistBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WishlistBrowserFrame frame = new WishlistBrowserFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WishlistBrowserFrame() {
		
		int userID = getID();
		List<WishlistName> list = controller.allLists(userID);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{100, 0, 400, 100, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 125, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel TopPanel = new JPanel();
		GridBagConstraints gbc_TopPanel = new GridBagConstraints();
		gbc_TopPanel.gridwidth = 4;
		gbc_TopPanel.fill = GridBagConstraints.BOTH;
		gbc_TopPanel.insets = new Insets(0, 0, 5, 5);
		gbc_TopPanel.gridx = 0;
		gbc_TopPanel.gridy = 0;
		contentPane.add(TopPanel, gbc_TopPanel);
		TopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewList = new JButton("New Wishlist");
		TopPanel.add(btnNewList);
		
		JLabel lblWishlist = new JLabel("Wish List: ");
		TopPanel.add(lblWishlist);
		
		
		
//		private class GenreListBox extends JComboBox<String>
//		{
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 2049325020252386788L;
//
//			public GenreListBox(final String[] genreList)
//			{
//				SetItemList(genreList);
//			};
//			
//			public void SetItemList(final String[] genreList)
//			{
//				for(int i = 0; i < genreList.length; i++)
//				{
//					addItem(genreList[i]);
//				}
//			}
//		private class wishlistLists() {
		List<String> names = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++)
		       {
				System.out.println(list.get(i).toString());
		    	names.add(list.get(i).getWishlistName());
		       }
		
		WishlistBox = new JComboBox(names.toArray());
		
//		}
		TopPanel.add(WishlistBox);
		
		JButton btnDeleteList = new JButton("Delete Wishist");
		btnDeleteList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(WishlistBox.getSelectedIndex());
				System.out.println(list.get(WishlistBox.getSelectedIndex()).getWishlistID());
				int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete: " + list.get(WishlistBox.getSelectedIndex()).getWishlistName());
				System.out.println(action);
				if(action == 0)
				{
					System.out.println("Deleting wishlist with ID: " + list.get(WishlistBox.getSelectedIndex()).getWishlistID());
					controller.deleteList(userID,list.get(WishlistBox.getSelectedIndex()).getWishlistID());
					refreshWishlists();
				}
			}
		});
		btnNewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<WishlistName> list2 = controller.allLists(userID);
//				refreshWishlists();
				System.out.println(list2.size());
				if(list2.size()>=3)
					JOptionPane.showMessageDialog(null, "Too many wishlists, please delete a list first.");
				else {
				String name = JOptionPane.showInputDialog(null, "Enter new Wishlist name.");
				if (name != null)
				{
					controller.newList(userID, name);
				}
				System.out.println("wishlist name: " + name);
				}
				
			}
			});
		TopPanel.add(btnDeleteList);
		
		JLabel label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 1;
		contentPane.add(label_1, gbc_label_1);
		
		JPanel DataPanel = new JPanel();
		GridBagConstraints gbc_DataPanel = new GridBagConstraints();
		gbc_DataPanel.gridheight = 2;
		gbc_DataPanel.gridwidth = 4;
		gbc_DataPanel.fill = GridBagConstraints.BOTH;
		gbc_DataPanel.insets = new Insets(0, 0, 5, 5);
		gbc_DataPanel.gridx = 0;
		gbc_DataPanel.gridy = 2;
		contentPane.add(DataPanel, gbc_DataPanel);
		DataPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		WishlistItems = new JList();
		DataPanel.add(WishlistItems);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		contentPane.add(panel, gbc_panel);
		
		JButton DeleteItem = new JButton("Delete Item");
		
		panel.add(DeleteItem);
		
		JButton MoveItem = new JButton("Move Item");
		
		panel.add(MoveItem);
		
		JButton SendToCart = new JButton("Send to Cart");
		panel.add(SendToCart);
		
		JButton LoadList = new JButton("Load Wishlist");
		LoadList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel<String> DLM = new DefaultListModel<String>();
				List<Wishlist> items = controller.list(userID, list.get(WishlistBox.getSelectedIndex()).getWishlistID());
				for(int i = 0; i < items.size(); i++)
				{
					DLM.addElement(items.get(i).toString());
				}
				WishlistItems.setModel(DLM);
			}
		});
		
		SendToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<WishlistName> list = controller.allLists(userID);
				List<Wishlist> items = controller.list(userID, list.get(WishlistBox.getSelectedIndex()).getWishlistID());
				JOptionPane.showMessageDialog(null, "Sending Book with ID: " + items.get(WishlistItems.getSelectedIndex()).getBookID() + " to Shopping cart");
			}
		});
		MoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<WishlistName> list = controller.allLists(userID);
		
				List<Wishlist> items = controller.list(userID, list.get(WishlistBox.getSelectedIndex()).getWishlistID());
				
				 int response = JOptionPane.showOptionDialog(null, "Move Item to what list?", "Move",
					        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					        null, list.toArray(), list.get(0).getWishlistName());
				System.out.println("Response: " + response);
				if (response > -1)
				{
					controller.move(userID,items.get(WishlistItems.getSelectedIndex()).getWishlistItemID(),
							list.get(response).getWishlistID());
					System.out.println(userID + "|" + list.get(response).getWishlistID() + "|" +
							items.get(WishlistItems.getSelectedIndex()).getWishlistItemID());
				}
			}
		});
		DeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Wishlist> items = controller.list(userID, list.get(WishlistBox.getSelectedIndex()).getWishlistID());
				int delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete: " + items.get(WishlistItems.getSelectedIndex()).getBookName());
				if (delete == 0) {
					System.out.println("Deleting book with ID: " + items.get(WishlistItems.getSelectedIndex()).getBookID());
					controller.delete(userID, items.get(WishlistItems.getSelectedIndex()).getWishlistItemID());
				}
			}
		});
		panel.add(LoadList);
		
		JButton btnNewButton = new JButton("Add Book By ID");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
////				List<WishlistName> list = controller.allLists(userID);
////				List<String> names = new ArrayList<String>();
////				
////				for(int i = 0; i < list.size(); i++)
////				       {
////						System.out.println(list.get(i).toString());
////				    	names.add(list.get(i).getWishlistName());
////				       }
////				
////				WishlistBox = new JComboBox(names.toArray());
////				
//				
				String input = JOptionPane.showInputDialog(null, "Add book");
				int i=Integer.parseInt(input);
				addItem(i, userID);
			}
		});
		panel.add(btnNewButton);
	}
	public int getID() {return 2;}
	public void refreshWishlists() 
	{
		System.out.println("Refreshing Frame");
		revalidate();
		repaint();
	}
	public void addItem(int bookID, int userID)
	{
		List<WishlistName> list = controller.allLists(userID);
		 int response = JOptionPane.showOptionDialog(null, "Add item to what list?", "Add",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, list.toArray(), list.get(0).getWishlistName());
		System.out.println("Response: " + response);
		if (response > -1)
		{
			controller.insert(userID,bookID,list.get(response).getWishlistID());

		}
	}
	
}

