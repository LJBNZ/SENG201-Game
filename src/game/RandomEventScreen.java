package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

public class RandomEventScreen {

	private JFrame window;
	public Game game;
	public int event_type;
	public Item new_item;
	public Item stolen_item;
	public JLabel new_item_label;
	public JPanel new_item_panel;
	public JLabel stolen_item_label;
	public JPanel stolen_item_panel;

	/**
	 * the Constructor for the RandomEventScreen
	 * @param new_game	the Game object
	 */
	public RandomEventScreen(Game new_game) {
		game = new_game;
		Random random_event = new Random();
	    event_type = random_event.nextInt(2);
		initialize();
		randomEvent();
		window.setVisible(true);
		updateLabels();
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
		game.closeRandomEventScreen(this);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.getContentPane().setForeground(new Color(0, 0, 0));
		window.setTitle("Heroes and Villains");
		window.setResizable(false);
		window.setBounds(100, 100, 437, 306);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JPanel stolenItemPanel = new JPanel();
		stolenItemPanel.setBounds(10, 11, 411, 255);
		window.getContentPane().add(stolenItemPanel);
		stolenItemPanel.setLayout(null);
		stolen_item_panel = stolenItemPanel;
		
		JButton stolenOKButton = new JButton("OK");
		stolenOKButton.setBounds(162, 210, 88, 23);
		stolenItemPanel.add(stolenOKButton);
		stolenOKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		
		JLabel stolenItemTitle = new JLabel("Item Stolen!");
		stolenItemTitle.setBounds(10, 31, 391, 19);
		stolenItemPanel.add(stolenItemTitle);
		stolenItemTitle.setForeground(new Color(255, 0, 0));
		stolenItemTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		stolenItemTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel stolenItemLabel = new JLabel();
		stolenItemLabel.setBounds(10, 83, 391, 49);
		stolenItemPanel.add(stolenItemLabel);
		stolenItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		stolenItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		stolen_item_label = stolenItemLabel;
		
		JPanel newItemPanel = new JPanel();
		newItemPanel.setLayout(null);
		newItemPanel.setBounds(10, 11, 411, 255);
		window.getContentPane().add(newItemPanel);
		new_item_panel = newItemPanel;
		
		JLabel itemReceivedTitle = new JLabel("Item Received!");
		itemReceivedTitle.setHorizontalAlignment(SwingConstants.CENTER);
		itemReceivedTitle.setForeground(new Color(0, 100, 0));
		itemReceivedTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		itemReceivedTitle.setBounds(10, 31, 391, 19);
		newItemPanel.add(itemReceivedTitle);
		
		JLabel receivedItemLabel = new JLabel();
		receivedItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		receivedItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		receivedItemLabel.setBounds(10, 83, 391, 16);
		newItemPanel.add(receivedItemLabel);
		new_item_label = receivedItemLabel;
		
		JButton receivedItemOKButton = new JButton("OK");
		receivedItemOKButton.setBounds(163, 208, 88, 23);
		receivedItemOKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		newItemPanel.add(receivedItemOKButton);
		
		new_item_panel.setVisible(false);
		stolen_item_panel.setVisible(false);
	}
	
	/**
	 * adds or removes an item from the teams inventory according to the
	 * random event type that is triggered.
	 */
	private void randomEvent() {
		if (game.team.inventory.isEmpty()) {
			event_type = 1;
		}
	    if (event_type == 0) { // Item stolen
			Random random_item = new Random();
	    	int item_index = random_item.nextInt(game.team.inventory.size());
	    	Item item = game.team.inventory.get(item_index);
	    	stolen_item = item;
	    	game.team.inventory.remove(item);
	    } else { // Item received
	    	Item item = game.getRandomItem();
	    	new_item = item;
	    	game.team.inventory.add(item);
		    
		}
		
	}
	
	/**
	 * updates the screen to alert the user that an item has been added/removed
	 */
	private void updateLabels() {
		if (event_type == 0) {
			stolen_item_panel.setVisible(true);
			stolen_item_label.setText(String.format("<html><center>Your team's base has been raided!<br>A %s was stolen from you!</center></html>", stolen_item.name));
		} else {
			new_item_panel.setVisible(true);
			new_item_label.setText(String.format("Your team has been gifted an item: %s!", new_item.name));
		}
	}
}
