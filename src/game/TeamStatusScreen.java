package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeamStatusScreen {

	private JFrame window;
	public Game game;
	public Team team;
	public Map<Integer, String> hero_powerups = new HashMap<Integer, String>();
	public ArrayList<Item> inventory;

	/**
	 * the constructor for the team status screen
	 * @param new_game	the Game object
	 */
	public TeamStatusScreen(Game new_game) {
		game = new_game;
		game.eligible_for_random_event = false;
		team = game.team;
		inventory = team.inventory;
		
		/**
		 * get all powerups from team inventory to display
		 */
		for (int i = 0; i < team.heroes.size(); i++) {
			Hero hero = team.heroes.get(i);
			String powerups = "";
			for (int j = 0; j < hero.current_powerups.size(); j++) {
				powerups += hero.current_powerups.get(j).name + "   ";
			}
			hero_powerups.put(i, powerups);
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
		game.closeTeamStatusScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setBounds(100, 100, 686, 509);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel heroTypeHeader = new JLabel("Hero type");
		heroTypeHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		heroTypeHeader.setBounds(155, 45, 71, 14);
		window.getContentPane().add(heroTypeHeader);
		
		JLabel heroHealthHeader = new JLabel("Health");
		heroHealthHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		heroHealthHeader.setBounds(270, 45, 71, 14);
		window.getContentPane().add(heroHealthHeader);
		
		JLabel heroPowerupsHeader = new JLabel("Powerups");
		heroPowerupsHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		heroPowerupsHeader.setBounds(373, 45, 71, 14);
		window.getContentPane().add(heroPowerupsHeader);
		
		//Render a line displaying each team member's status
		for (int i = 0; i < team.heroes.size(); i++) {
			int y_offset = i * 40;
			JLabel heroName = new JLabel(team.heroes.get(i).hero_name);
			heroName.setFont(new Font("Tahoma", Font.BOLD, 13));
			heroName.setBounds(38, 63 + y_offset, 98, 28);
			window.getContentPane().add(heroName);
			
			JLabel heroType = new JLabel(game.types.get(team.heroes.get(i).hero_type));
			heroType.setHorizontalAlignment(SwingConstants.CENTER);
			heroType.setBounds(133, 71 + y_offset, 93, 14);
			window.getContentPane().add(heroType);
			
			JLabel heroHealth = new JLabel(Integer.toString(team.heroes.get(i).current_health) + "%");
			heroHealth.setHorizontalAlignment(SwingConstants.CENTER);
			heroHealth.setBounds(248, 71 + y_offset, 93, 14);
			window.getContentPane().add(heroHealth);
			
			JLabel heroPowerups = new JLabel(hero_powerups.get(i));
			heroPowerups.setHorizontalAlignment(SwingConstants.LEFT);
			heroPowerups.setBounds(370, 71 + y_offset, 239, 14);
			window.getContentPane().add(heroPowerups);
		}
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		statusPanel.setBounds(10, 11, 650, 170);
		window.getContentPane().add(statusPanel);
		statusPanel.setLayout(null);
		
		JLabel teamStatusTitle = new JLabel("Team Status");
		teamStatusTitle.setBounds(284, 6, 81, 16);
		statusPanel.add(teamStatusTitle);
		teamStatusTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		teamStatusTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		inventoryPanel.setBounds(10, 196, 650, 223);
		window.getContentPane().add(inventoryPanel);
		inventoryPanel.setLayout(null);
		
		JLabel teamInventoryTitle = new JLabel("Team Inventory");
		teamInventoryTitle.setBounds(273, 6, 103, 16);
		inventoryPanel.add(teamInventoryTitle);
		teamInventoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		teamInventoryTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel itemNameLabel = new JLabel("Item name");
		itemNameLabel.setBounds(132, 33, 71, 14);
		inventoryPanel.add(itemNameLabel);
		itemNameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel itemDescriptionLabel = new JLabel("Item description");
		itemDescriptionLabel.setBounds(281, 33, 110, 14);
		inventoryPanel.add(itemDescriptionLabel);
		itemDescriptionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(552, 436, 89, 23);
		window.getContentPane().add(backButton);
		
		//for each item in team inventory, display the name and description
		for (int i = 0; i < team.inventory.size(); i++) {
			int y_offset = i * 25;
			Item item = team.inventory.get(i);

			JLabel lblNewLabel = new JLabel(item.name);
			lblNewLabel.setBounds(142, 58 + y_offset, 115, 14);
			inventoryPanel.add(lblNewLabel);
			
			JLabel lblNewLabel_2 = new JLabel(item.description);
			lblNewLabel_2.setBounds(291, 58 + y_offset, 269, 14);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
			inventoryPanel.add(lblNewLabel_2);
		}
	}
}
