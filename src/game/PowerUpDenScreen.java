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

public class PowerUpDenScreen {

	private JFrame window;
	public Game game;
	public Team team;
	public ArrayList<PowerUp> team_powerups;
	public String[] powerup_names;
	public int selection_index;

	/**
	 * the constructor for the powerup den screen
	 * @param new_game the Game object
	 */
	public PowerUpDenScreen(Game new_game) {
		game = new_game;
		team = game.team;
		//Get all powerups from team inventory
		team_powerups = new ArrayList<PowerUp>();
		for (int i = 0; i < team.inventory.size(); i++) {
			if (team.inventory.get(i) instanceof PowerUp) {
				team_powerups.add((PowerUp) team.inventory.get(i));
			}
		}	
		//add the name of each powerup to an array, to be used in the list model
		powerup_names = new String[team_powerups.size()];
		for (int i = 0; i < team_powerups.size(); i++) {
			powerup_names[i] = team_powerups.get(i).name;
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
		game.closePowerUpDenScreen(this);
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
		
		//The list model that will hold the powerups to choose from
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> powerUpList = new JList<String>(model);
		powerUpList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				selection_index = powerUpList.getSelectedIndex();
			}
		});
		//add powerup names to the list model
		for (int i = 0; i < powerup_names.length; i++) {
			model.addElement(powerup_names[i]);
		}
		
		powerUpList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		powerUpList.setBounds(33, 106, 156, 159);
		window.getContentPane().add(powerUpList);
		
		//Add a button for hero number 1
		JButton hero1Button = new JButton(team.heroes.get(0).hero_name);
		hero1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selection_index != -1) {
					int index = selection_index;
					if (!team_powerups.isEmpty() && team_powerups.get(index) != null) {
						team.heroes.get(0).consumePowerup(team_powerups.get(index), game.team);
						model.remove(index);
						team_powerups.remove(index);
						powerUpList.setSelectedIndex(0);
					}
				}
			}
		});
		hero1Button.setBounds(228, 103, 112, 23);
		window.getContentPane().add(hero1Button);
		
		if (team.heroes.size() >= 2) {
			//Add a button for hero 2 if needed
			JButton hero2Button = new JButton(team.heroes.get(1).hero_name);
			hero2Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selection_index != -1) {
						int index = selection_index;
						if (!team_powerups.isEmpty() && team_powerups.get(index) != null) {
							team.heroes.get(1).consumePowerup(team_powerups.get(index), game.team);
							model.remove(index);
							team_powerups.remove(index);
							powerUpList.setSelectedIndex(0);
						}						
					}
				}
			});
			hero2Button.setBounds(239, 180, 112, 23);
			window.getContentPane().add(hero2Button);
			
			if (team.heroes.size() == 3) {
				//Add a button for hero 3 if needed
				JButton hero3Button = new JButton(team.heroes.get(2).hero_name);
				hero3Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selection_index != -1) {
							int index = selection_index;
							if (!team_powerups.isEmpty() && team_powerups.get(index) != null) {
								team.heroes.get(2).consumePowerup(team_powerups.get(index), game.team);
								model.remove(index);
								team_powerups.remove(index);
								powerUpList.setSelectedIndex(0);
							}
						}
					}
				});
				hero3Button.setBounds(239, 260, 112, 23);
				window.getContentPane().add(hero3Button);
			}
		}
		
		
		
		JLabel availablePowerupsLabel = new JLabel("Available Powerups");
		availablePowerupsLabel.setBounds(33, 81, 156, 14);
		window.getContentPane().add(availablePowerupsLabel);
		
		JLabel heroToConsumeLabel = new JLabel("Hero to consume:");
		heroToConsumeLabel.setBounds(218, 81, 133, 14);
		window.getContentPane().add(heroToConsumeLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(239, 340, 89, 23);
		window.getContentPane().add(backButton);
		
		JLabel Title = new JLabel("Use Powerups");
		Title.setFont(new Font("Tahoma", Font.BOLD, 13));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(0, 25, 368, 14);
		window.getContentPane().add(Title);
	}
}
