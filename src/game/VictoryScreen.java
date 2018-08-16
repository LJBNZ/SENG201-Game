package game;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VictoryScreen {

	private JFrame window;
	public Game game;

	/**
	 * the constructor for the victory screen
	 * @param new_game	the Game object
	 */
	public VictoryScreen(Game new_game) {
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
		game.closeVictoryScreen(this);
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
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnNewButton.setBounds(171, 210, 89, 23);
		window.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Victory!");
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 45, 411, 34);
		window.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(String.format("Your team has defeated %s!", game.cities.get(game.current_city - 1).villain.name));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 93, 411, 23);
		window.getContentPane().add(lblNewLabel_1);
		
		JLabel lblYouAdvanceTo = new JLabel(String.format("You are awarded $50 and advance to city number %d of %d", game.current_city + 1, game.cities.size()));
		lblYouAdvanceTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouAdvanceTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYouAdvanceTo.setBounds(10, 123, 411, 23);
		window.getContentPane().add(lblYouAdvanceTo);
	}
}
