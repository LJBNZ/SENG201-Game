package game;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TravelScreen {

	private JFrame window;
	public Game game;
	City current_city;
	Map<Integer, String> movement_options;

	/**
	 * the travel screen constructor
	 * @param new_game	the Game object
	 */
	public TravelScreen(Game new_game) {
		game = new_game;
		City city = game.cities.get(game.current_city);
		current_city = city;
		boolean has_map = game.team.hasMap();
		movement_options = city.getMovementOptions(has_map);
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
		game.closeTravelScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setBounds(100, 100, 542, 398);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel travelTitle = new JLabel("Choose a direction to travel in:");
		travelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		travelTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		travelTitle.setBounds(10, 25, 506, 14);
		window.getContentPane().add(travelTitle);
		
		JPanel northPanel = new JPanel();
		northPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		northPanel.setBounds(184, 64, 147, 60);
		window.getContentPane().add(northPanel);
		northPanel.setLayout(null);
		
		JLabel northLocation = new JLabel(movement_options.get(1));
		northLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		northLocation.setHorizontalAlignment(SwingConstants.CENTER);
		northLocation.setBounds(0, 35, 147, 14);
		northPanel.add(northLocation);
		
		JButton northButton = new JButton("North");
		northButton.setBounds(30, 11, 89, 23);
		northPanel.add(northButton);
		northButton.addActionListener(new ActionListener() {
			//if north button clicked move team to north destination
			public void actionPerformed(ActionEvent e) {
				current_city.move(1, game);
				finishedWindow();
			}
		});
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(null);
		eastPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		eastPanel.setBounds(330, 156, 147, 60);
		window.getContentPane().add(eastPanel);
		
		JLabel eastLocation = new JLabel(movement_options.get(2));
		eastLocation.setHorizontalAlignment(SwingConstants.CENTER);
		eastLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		eastLocation.setBounds(0, 35, 147, 14);
		eastPanel.add(eastLocation);
		
		JButton eastButton = new JButton("East");
		eastButton.setBounds(32, 11, 89, 23);
		eastPanel.add(eastButton);
		eastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if east button clicked move team to east destination
				current_city.move(2, game);
				finishedWindow();
			}
		});
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(null);
		southPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		southPanel.setBounds(184, 251, 147, 60);
		window.getContentPane().add(southPanel);
		
		JLabel southLocation = new JLabel(movement_options.get(3));
		southLocation.setHorizontalAlignment(SwingConstants.CENTER);
		southLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		southLocation.setBounds(0, 35, 147, 14);
		southPanel.add(southLocation);
		
		JButton southButton = new JButton("South");
		southButton.setBounds(29, 11, 89, 23);
		southPanel.add(southButton);
		southButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if south button clicked move team to south destination
				current_city.move(3, game);
				finishedWindow();
			}
		});
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(null);
		westPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		westPanel.setBounds(39, 156, 147, 60);
		window.getContentPane().add(westPanel);
		
		JLabel westLocation = new JLabel(movement_options.get(4));
		westLocation.setHorizontalAlignment(SwingConstants.CENTER);
		westLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		westLocation.setBounds(0, 35, 147, 14);
		westPanel.add(westLocation);
		
		JButton westButton = new JButton("West");
		westButton.setBounds(28, 11, 89, 23);
		westPanel.add(westButton);
		westButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if west button clicked move team to west destination
				current_city.move(4, game);
				finishedWindow();
			}
		});
		
	
		
		JButton cancelButton = new JButton("Back");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		cancelButton.setBounds(412, 314, 89, 23);
		window.getContentPane().add(cancelButton);
	}

}
