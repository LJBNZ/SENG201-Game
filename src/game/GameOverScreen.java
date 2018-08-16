package game;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOverScreen {

	private JFrame window;
	public Game game;

	/**
	 * the constructor for the game over screen
	 * @param new_game	the Game object
	 */
	public GameOverScreen(Game new_game) {
		game = new_game;
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
		game.closeGameOverScreen(this);
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
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		quitButton.setBounds(171, 210, 89, 23);
		window.getContentPane().add(quitButton);
		
		JLabel gameOverLabel = new JLabel("Game Over!");
		gameOverLabel.setForeground(Color.RED);
		gameOverLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setBounds(10, 45, 411, 34);
		window.getContentPane().add(gameOverLabel);
		
		JLabel citiesCompletedLabel = new JLabel(String.format("Your team completed %d out of %d cities", game.current_city, game.cities.size()));
		citiesCompletedLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		citiesCompletedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		citiesCompletedLabel.setBounds(10, 101, 411, 23);
		window.getContentPane().add(citiesCompletedLabel);
	}
}
