package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class BattleScreen {

	private JFrame window;
	public Game game;
	public Team team;
	public Map<Integer, String> hero_powerups = new HashMap<Integer, String>();
	public Villain villain;
	public int hero_selection;

	/**
	 * The screen class constructor- initiate the variables used in the screen rendering
	 * 
	 * @param new_game the current Game object
	 */
	public BattleScreen(Game new_game) {
		game = new_game;
		team = game.team;
		villain = game.cities.get(team.current_city_num).villain;
		
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
		game.closeBattleScreen(this, hero_selection);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setBounds(100, 100, 686, 383);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel heroTypeHeader = new JLabel("Hero type");
		heroTypeHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		heroTypeHeader.setBounds(155, 200, 71, 14);
		window.getContentPane().add(heroTypeHeader);
		
		JLabel heroHealthHeader = new JLabel("Health");
		heroHealthHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		heroHealthHeader.setBounds(270, 200, 71, 14);
		window.getContentPane().add(heroHealthHeader);
		
		JLabel heroPowerupsHeader = new JLabel("Powerups");
		heroPowerupsHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		heroPowerupsHeader.setBounds(373, 200, 71, 14);
		window.getContentPane().add(heroPowerupsHeader);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 166, 650, 170);
		window.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Generate button and status for each hero
		for (int i = 0; i < team.heroes.size(); i++) {
			int y_offset = i * 30;
			JButton heroNameButton = new JButton(team.heroes.get(i).hero_name);
			heroNameButton.setName(Integer.toString(i));
			heroNameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hero_selection = Integer.parseInt(heroNameButton.getName());
					finishedWindow();
				}
			});
			heroNameButton.setFont(new Font("Tahoma", Font.BOLD, 13));
			heroNameButton.setBounds(18, 55 + y_offset, 98, 28);
			panel.add(heroNameButton);
						
			JLabel heroType = new JLabel(game.types.get(team.heroes.get(i).hero_type));
			heroType.setHorizontalAlignment(SwingConstants.CENTER);
			heroType.setBounds(126, 63 + y_offset, 93, 14);
			panel.add(heroType);
			
			JLabel heroHealth = new JLabel(Integer.toString(team.heroes.get(i).current_health) + "%");
			heroHealth.setHorizontalAlignment(SwingConstants.CENTER);
			heroHealth.setBounds(230, 63 + y_offset, 93, 14);
			panel.add(heroHealth);
			
			JLabel heroPowerups = new JLabel(hero_powerups.get(i));
			heroPowerups.setHorizontalAlignment(SwingConstants.LEFT);
			heroPowerups.setBounds(360, 63 + y_offset, 239, 14);
			panel.add(heroPowerups);
			
		}
		
		
		System.out.println(hero_selection);
		JLabel teamStatusTitle = new JLabel("Heroes");
		teamStatusTitle.setBounds(284, 6, 81, 16);
		panel.add(teamStatusTitle);
		teamStatusTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		teamStatusTitle.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		JLabel lblYourTeamHas = new JLabel(String.format("Your Team has been challenged to %s by %s!", game.game_names.get(villain.game), villain.name));
		lblYourTeamHas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblYourTeamHas.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourTeamHas.setBounds(10, 11, 650, 25);
		window.getContentPane().add(lblYourTeamHas);
		
		JLabel lblNewLabel = new JLabel(String.format("%s says: \"%s\"", villain.name, villain.taunt));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 35, 650, 25);
		window.getContentPane().add(lblNewLabel);
		
		JLabel lblChooseAHero = new JLabel(String.format("Choose a Hero to battle %s with:", villain.name));
		lblChooseAHero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChooseAHero.setBounds(35, 130, 409, 25);
		window.getContentPane().add(lblChooseAHero);
		
		JLabel scoreText = new JLabel(String.format("(Your team has beaten %s %d out of 3 times)", villain.name, game.score));
		scoreText.setHorizontalAlignment(SwingConstants.CENTER);
		scoreText.setBounds(101, 85, 462, 14);
		window.getContentPane().add(scoreText);
				
	}
}
