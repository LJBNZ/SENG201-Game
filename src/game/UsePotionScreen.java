package game;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class UsePotionScreen {

	private JFrame window;
	public Game game;
	public Team team;
	public ArrayList<Potion> team_potions;
	public String[] potion_names;
	public int selection_index;

	/**
	 * the constructor for the use potion screen
	 * @param new_game	the Game object
	 */
	public UsePotionScreen(Game new_game) {
		game = new_game;
		team = game.team;

		//get all potions from team inventory
		team_potions = new ArrayList<Potion>();
		for (int i = 0; i < team.inventory.size(); i++) {
			if (team.inventory.get(i) instanceof Potion) {
				team_potions.add((Potion) team.inventory.get(i));
			}
		}	
		//get all names of potions, to be used in list model
		potion_names = new String[team_potions.size()];
		for (int i = 0; i < team_potions.size(); i++) {
			potion_names[i] = team_potions.get(i).name;
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
		game.closeUsePotionScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setBounds(100, 100, 384, 436);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		//the list model displaying all the available potions to use
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> potionList = new JList<String>(model);
		potionList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (potionList.getSelectedIndex() != -1) {
					selection_index = potionList.getSelectedIndex();
				}
			}
		});
		//add each potion name to the list model
		for (int i = 0; i < potion_names.length; i++) {
			model.addElement(potion_names[i]);
		}
		
		potionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		potionList.setBounds(33, 106, 156, 159);
		window.getContentPane().add(potionList);
		
		//create a button for hero 1 which when clicked will consume the selected potion
		String hero1_button_text = "<html><center>" + team.heroes.get(0).hero_name + "<br> Health: " + team.heroes.get(0).current_health + "%</center></html>";
		JButton hero1Button = new JButton(hero1_button_text);
		hero1Button.setVerticalAlignment(SwingConstants.TOP);
		hero1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selection_index != -1 && !team_potions.isEmpty()) {
					int index = selection_index;
					if (team.heroes.get(0).consumePotion(team_potions.get(index), game.team)) {
						model.remove(index);
						team_potions.remove(index);
						potionList.setSelectedIndex(0);
					}
				}
			}
		});
		hero1Button.setBounds(216, 110, 135, 42);
		window.getContentPane().add(hero1Button);
		//disable hero 1 button if their health is 100
		if (team.heroes.get(0).current_health == 100) {
			hero1Button.setEnabled(false);
		} else {
			hero1Button.setEnabled(true);
		}
		
		if (team.heroes.size() >= 2) {
			//create a button for hero 2 which when clicked will consume the selected potion
			String hero2_button_text = "<html><center>" + team.heroes.get(1).hero_name + "<br> Health: " + team.heroes.get(1).current_health + "%</center></html>";
			JButton hero2Button = new JButton(hero2_button_text);
			hero2Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selection_index != -1 && !team_potions.isEmpty()) {
						int index = selection_index;
						if (team.heroes.get(1).consumePotion(team_potions.get(index), game.team)) {
							model.remove(index);
							team_potions.remove(index);
							potionList.setSelectedIndex(0);
						}
					}
				}
			});
			hero2Button.setBounds(216, 165, 135, 42);
			window.getContentPane().add(hero2Button);
			//disable hero 2 button if their health is 100
			if (team.heroes.get(1).current_health == 100) {
				hero2Button.setEnabled(false);
			} else {
				hero2Button.setEnabled(true);
			}
			
			if (team.heroes.size() == 3) {
				//create a button for hero 3 which when clicked will consume the selected potion
				String hero3_button_text = "<html><center>" + team.heroes.get(2).hero_name + "<br> Health: " + team.heroes.get(2).current_health + "%</center></html>";
				JButton hero3Button = new JButton(hero3_button_text);
				hero3Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selection_index != -1 && !team_potions.isEmpty()) {
							int index = selection_index;
							if (team.heroes.get(2).consumePotion(team_potions.get(index), game.team)) {
								model.remove(index);
								team_potions.remove(index);
								potionList.setSelectedIndex(0);
							}
						}
					}
				});
				hero3Button.setBounds(216, 220, 135, 42);
				window.getContentPane().add(hero3Button);
				//disable hero 3 button if their health is 1-00
				if (team.heroes.get(2).current_health == 100) {
					hero3Button.setEnabled(false);
				} else {
					hero3Button.setEnabled(true);
				}
			}
		}
		
		
		
		JLabel availablePotionsLabel = new JLabel("Available Potions");
		availablePotionsLabel.setBounds(33, 81, 156, 14);
		window.getContentPane().add(availablePotionsLabel);
		
		JLabel heroToConsumeLabel = new JLabel("Hero to consume:");
		heroToConsumeLabel.setBounds(216, 81, 135, 14);
		window.getContentPane().add(heroToConsumeLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(239, 340, 89, 23);
		window.getContentPane().add(backButton);
		
		JLabel title = new JLabel("Use Potions");
		title.setFont(new Font("Tahoma", Font.BOLD, 13));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(0, 25, 368, 14);
		window.getContentPane().add(title);
	}
}
