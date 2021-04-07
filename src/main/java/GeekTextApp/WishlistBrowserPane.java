package GeekTextApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import java.awt.Component;

public class WishlistBrowserPane extends JPanel implements ActionListener, ItemListener {

	/**
	 * Create the panel.
	 */
	public WishlistBrowserPane() {
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{148, 59, 12, 0, 73, 0};
		gridBagLayout.rowHeights = new int[]{24, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel TopPanel = new JPanel();
		GridBagConstraints gbc_TopPanel = new GridBagConstraints();
		gbc_TopPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_TopPanel.insets = new Insets(0, 0, 5, 5);
		gbc_TopPanel.gridx = 2;
		gbc_TopPanel.gridy = 3;
		add(TopPanel, gbc_TopPanel);
		TopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblWishlist = new JLabel("Wish List: ");
		TopPanel.add(lblWishlist);
		
		JPanel DataPanel = new JPanel();
		GridBagConstraints gbc_DataPanel = new GridBagConstraints();
		gbc_DataPanel.anchor = GridBagConstraints.WEST;
		gbc_DataPanel.insets = new Insets(0, 0, 5, 5);
		gbc_DataPanel.gridx = 2;
		gbc_DataPanel.gridy = 4;
		add(DataPanel, gbc_DataPanel);
		DataPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane wishlistBooksPane = new JScrollPane();
		DataPanel.add(wishlistBooksPane);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setMaximumSize(new Dimension(2147483647, 10));
		GridBagConstraints gbc_bottomPanel = new GridBagConstraints();
		gbc_bottomPanel.insets = new Insets(0, 0, 5, 5);
		gbc_bottomPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_bottomPanel.gridx = 2;
		gbc_bottomPanel.gridy = 5;
		add(bottomPanel, gbc_bottomPanel);
		
		JLabel labelRowsPerPage = new JLabel("Rows:");
		bottomPanel.add(labelRowsPerPage);
		
		JLabel labelPageNumber = new JLabel("Page:");
		bottomPanel.add(labelPageNumber);

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
