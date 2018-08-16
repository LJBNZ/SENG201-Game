package game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckHealingScreen {

	private JFrame window;
	public Game game;
	public Team team;

	/**
	 * constructor for the check healing screen
	 * @param new_game	the Game object
	 */
	public CheckHealingScreen(Game new_game) {
		game = new_game;
		team = game.team;
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
		game.closeCheckHealingScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setBounds(100, 100, 649, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Team Health Statuses");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 13));
		title.setBounds(10, 11, 613, 14);
		window.getContentPane().add(title);
		
		ArrayList<JLabel> statusLabels = new ArrayList<JLabel>();
		//For each hero generate a line on the window listing their name and healing status
		for (int i = 0; i < team.heroes.size(); i++) {
			int y_offset = i * 40;
			JLabel heroName = new JLabel(team.heroes.get(i).hero_name);
			heroName.setFont(new Font("Tahoma", Font.BOLD, 13));
			heroName.setBounds(43, 70 + y_offset, 75, 16);
			window.getContentPane().add(heroName);
			
			JLabel heroStatus = new JLabel("STATUS");
			heroStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
			heroStatus.setBounds(135, 70 + y_offset, 430, 16);
			window.getContentPane().add(heroStatus);
			
			statusLabels.add(heroStatus);
		}
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(518, 227, 89, 23);
		window.getContentPane().add(backButton);
		
		
		//Create an executor which will poll for healing updates every second and update the window text
		ScheduledExecutorService e = Executors.newSingleThreadScheduledExecutor();
		e.scheduleAtFixedRate(new Runnable() {
			public void run() {
				LocalDateTime date = LocalDateTime.now();
				team.checkHealingStatus();
				int current_time = date.toLocalTime().toSecondOfDay();
				for (int i = 0; i < team.heroes.size(); i++) {
					JLabel current_label = statusLabels.get(i);
					Hero hero = team.heroes.get(i);
					if (hero.currently_healing) {
						int time_left = (hero.healing_checkpoints.get(hero.healing_checkpoints.size() - 1)) - current_time;
						current_label.setText(String.format("Health = %d%%, healing using a %s (%d seconds left)", hero.current_health, hero.healing_potion.name, time_left));
					} else {
						current_label.setText(String.format("Health = %d%%, not healing", hero.current_health));
					}
				}
			};
		}, 0, 1, TimeUnit.SECONDS);
	}
}
