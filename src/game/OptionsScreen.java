package game;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class OptionsScreen {

	private JFrame window;
	public Game game;
	public Destination current_dest;
	public City current_city;
	public Map<Integer, String> dest_options;

	/**
	 * the constructor for the options screen
	 * @param new_game	the Game object
	 */
	public OptionsScreen(Game new_game) {
		game = new_game;
		current_city = game.cities.get(game.current_city);
		Destination current_destination = current_city.locations.get(current_city.current_destination);
		Map<Integer, String> destination_options = current_destination.getOptions();
		current_dest = current_destination;
		dest_options = destination_options;
		//If team is at the home base options screen, give a chance for random event to trigger
		if (current_destination instanceof HomeBase && game.eligible_for_random_event) {
			if (game.randomEventTriggered()) {
				game.launchRandomEventScreen();
			}
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
		game.closeOptionsScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setResizable(false);
		window.setTitle("Heroes and Villains");
		window.setBounds(100, 100, 499, 249);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel locationTitle = new JLabel("Your team is at the");
		locationTitle.setBounds(112, 11, 156, 14);
		window.getContentPane().add(locationTitle);
		
		JLabel locationNameTitle = new JLabel(current_dest.place_name);
		locationNameTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		locationNameTitle.setBounds(264, 5, 116, 23);
		window.getContentPane().add(locationNameTitle);
		
		JLabel optionsLabel = new JLabel("Options:");
		optionsLabel.setBounds(57, 59, 80, 14);
		window.getContentPane().add(optionsLabel);
				
		JButton option1Button = new JButton(dest_options.get(1));
		option1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current_dest.parseChoice(1, game);
				finishedWindow();
			}
		});
		option1Button.setBounds(57, 84, 370, 23);
		window.getContentPane().add(option1Button);
		
		JButton option2Button = new JButton(dest_options.get(2));
		option2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current_dest.parseChoice(2, game);
				finishedWindow();
			}
		});
		option2Button.setBounds(57, 124, 370, 23);
		window.getContentPane().add(option2Button);
		
		//If team is at the Lair options screen and they possess clairvoyance powerup then display villain game
		if (current_dest instanceof Lair && game.team.hasClairvoyance()) {
			JLabel lblNewLabel = new JLabel(String.format("Your clairvoyance tells you that %s will play %s", current_city.villain.name, game.game_names.get(current_city.villain.game)));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(new Color(0, 128, 0));
			lblNewLabel.setBounds(10, 34, 473, 14);
			window.getContentPane().add(lblNewLabel);	
		}
		
		//If the destination has a third option then create an extra button
		if (dest_options.get(3) != null) {
			JButton option3Button = new JButton(dest_options.get(3));
			option3Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					current_dest.parseChoice(3, game);
					finishedWindow();
				}
			});
			option3Button.setBounds(57, 164, 370, 23);
			window.getContentPane().add(option3Button);
		}
		
		
	}

}
