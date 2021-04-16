package GeekTextApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class WishlistBrowserFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1886236604813847L;
	private JPanel contentPane;

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
		WishlistController controller = new WishlistController();
		int userID = getID();
		List<WishlistName> list = controller.allLists(userID);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{100, 400, 100, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 125, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel TopPanel = new JPanel();
		GridBagConstraints gbc_TopPanel = new GridBagConstraints();
		gbc_TopPanel.fill = GridBagConstraints.BOTH;
		gbc_TopPanel.insets = new Insets(0, 0, 5, 5);
		gbc_TopPanel.gridx = 1;
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
//		public comboBox() {
		List<String> names = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++)
		       {
				System.out.println(list.get(i).toString());
		    	names.add(list.get(i).getWishlistName());
		       }
		
		JComboBox WishlistBox = new JComboBox(names.toArray());
		
		//}
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
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		contentPane.add(label_1, gbc_label_1);
		
		JPanel DataPanel = new JPanel();
		GridBagConstraints gbc_DataPanel = new GridBagConstraints();
		gbc_DataPanel.fill = GridBagConstraints.BOTH;
		gbc_DataPanel.insets = new Insets(0, 0, 5, 5);
		gbc_DataPanel.gridx = 1;
		gbc_DataPanel.gridy = 2;
		contentPane.add(DataPanel, gbc_DataPanel);
		DataPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane wishlistBooksPane = new JScrollPane();
		DataPanel.add(wishlistBooksPane);
	}
	public int getID() {return 2;}
	public void refreshWishlists() 
	{
		System.out.println("Refreshing Frame");
		revalidate();
		repaint();
	}
}

