package game;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopScreen {

	private JFrame window;
	public Game game;
	public Team team;
	public String[] item_names;
	public String[] inventory_item_names;
	public int itemSelection;
	public Shop shop;

	/**
	 * the shop screen constructor
	 * @param new_game	the Game object
	 */
	public ShopScreen(Game new_game) {
		game = new_game;
		team = game.team;
		City current_city = game.cities.get(game.current_city);
		shop = (Shop) current_city.locations.get(current_city.current_destination);

		//Get all the names of the items in stock at the shop, to be used in list model
		item_names = new String[shop.stock.size()];
		for (int i = 0; i < shop.stock.size(); i++) {
			item_names[i] = shop.stock.get(i).name;
		}
		
		//Get all the names of the items in the team inventory, to be used in list model
		inventory_item_names = new String[team.inventory.size()];
		for (int i = 0; i < team.inventory.size(); i++) {
			inventory_item_names[i] = team.inventory.get(i).name;
		}
		
		initialize();
		window.setVisible(true);
	}
	/**
	 * close the window
	 */
	public void closeWindow() {
		window.dispose();
	}
	/**
	 * call the Game class method which will destroy the window instance
	 */
	public void finishedWindow() { 
		game.closeShopScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setBounds(100, 100, 521, 445);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
			
		JLabel stockTitle = new JLabel("Shop stock:");
		stockTitle.setBounds(184, 62, 112, 14);
		window.getContentPane().add(stockTitle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(121, 276, 364, 64);
		window.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel itemNameTitle = new JLabel();
		itemNameTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		itemNameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		itemNameTitle.setBounds(10, 11, 354, 14);
		panel.add(itemNameTitle);
		
		JLabel itemDescription = new JLabel("Please select an item");
		itemDescription.setHorizontalAlignment(SwingConstants.CENTER);
		itemDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
		itemDescription.setBounds(10, 36, 354, 14);
		panel.add(itemDescription);
		
		
		JLabel costLabel = new JLabel();
		costLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		costLabel.setBounds(339, 144, 66, 14);
		window.getContentPane().add(costLabel);
		
		JLabel itemCost = new JLabel();
		itemCost.setBounds(405, 145, 80, 14);
		window.getContentPane().add(itemCost);
		
		JLabel Title = new JLabel("Shop");
		Title.setFont(new Font("Tahoma", Font.BOLD, 13));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(161, 11, 348, 23);
		window.getContentPane().add(Title);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(396, 363, 89, 23);
		window.getContentPane().add(backButton);
		
		JLabel teamMoneyLabel = new JLabel("Team money: $");
		teamMoneyLabel.setBounds(329, 119, 112, 14);
		window.getContentPane().add(teamMoneyLabel);
		
		JLabel teamMoneyValue = new JLabel(Integer.toString(team.money));
		teamMoneyValue.setBounds(453, 119, 46, 14);
		window.getContentPane().add(teamMoneyValue);
		
		JButton buyButton = new JButton("Purchase");
		buyButton.setFont(new Font("Dialog", Font.BOLD, 11));
		buyButton.setBounds(359, 228, 105, 23);
		window.getContentPane().add(buyButton);
		
		//the list model which holds all the item names of the shop stock to choose from
		DefaultListModel<String> stock_model = new DefaultListModel<String>();
		JList<String> stockList = new JList<String>(stock_model);
		//add each item name to the list model
		for (int i = 0; i < item_names.length; i++) {
			stock_model.addElement(item_names[i]);
		}
		
		JLabel discountText = new JLabel();
		discountText.setHorizontalAlignment(SwingConstants.CENTER);
		discountText.setForeground(new Color(0, 128, 0));
		discountText.setBounds(329, 184, 170, 33);
		window.getContentPane().add(discountText);
		stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//the listener which updates the value selected in the list model
		stockList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				itemSelection = stockList.getSelectedIndex();
				if (itemSelection != -1) {
					//update the item description, and cost labels to reflect the newly selected item's info
					double item_cost = shop.stock.get(itemSelection).cost;
					if (team.hasPriceDiscount()) {
						double old_cost = item_cost;
						item_cost *= 0.7;
						discountText.setText(String.format("<html><center>Merchant discount:<br>$%.2f</center><html>", old_cost - item_cost));
					}
					itemNameTitle.setText(item_names[itemSelection]);
					itemDescription.setText(shop.stock.get(itemSelection).description);
					itemCost.setText(Double.toString(item_cost));
					costLabel.setText("Cost: $");
					
					//disable the buy button if the team can't afford the selected item
					if (team.money < item_cost) {
						buyButton.setEnabled(false);
					} else {
						buyButton.setEnabled(true);
					}
				}
			}
		});
		stockList.setBorder(new LineBorder(new Color(0, 0, 0)));
		stockList.setBounds(184, 92, 135, 159);
		window.getContentPane().add(stockList);
		
		DefaultListModel<String> inventory_model = new DefaultListModel<String>();
		JList<String> inventoryList = new JList<String>(inventory_model);
		inventoryList.setBorder(new LineBorder(new Color(0, 0, 0)));
		inventoryList.setBounds(23, 93, 135, 159);
		window.getContentPane().add(inventoryList);
		inventoryList.setEnabled(false);
		
		JLabel teamInventoryLabel = new JLabel("Team inventory:");
		teamInventoryLabel.setBounds(23, 62, 149, 14);
		window.getContentPane().add(teamInventoryLabel);
		
		for (int i = 0; i < inventory_item_names.length; i++) {
			inventory_model.addElement(inventory_item_names[i]);
		}
	
		//event listener for the buy button
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//call the buyItem method on the selected item
				if (shop.buyItem(itemSelection, team)) {
					//if bought successfully remove the item from the shop stock list model and add to inventory list model
					teamMoneyValue.setText(Integer.toString(team.money));
					inventory_model.addElement(stock_model.getElementAt(itemSelection));
					stock_model.remove(itemSelection);
					stockList.setSelectedIndex(0);
				}
			}
		});
				
	}
}
