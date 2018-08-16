package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WinGameScreen {

	private JFrame window;
	public Game game;

	/**
	 * the constructor for the win game screen
	 * @param new_game	the Game object
	 */
	public WinGameScreen(Game new_game) {
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
		game.closeWinGameScreen(this);
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
		
		//button to quit the game
		JButton btnNewButton = new JButton("Finish");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnNewButton.setBounds(171, 210, 89, 23);
		window.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("CONGRATULATIONS!");
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 45, 411, 34);
		window.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(String.format("<html><center>Your team has successfully completed<br>all %d cities and defeated the Joker!</center><html>", game.cities.size()));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 90, 411, 44);
		window.getContentPane().add(lblNewLabel_1);
	}

}
