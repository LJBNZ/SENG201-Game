package game;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

public class DiceRollScreen {

	private JFrame window;
	public Game game;
	public Team team;
	public Map<Integer, String> hero_powerups = new HashMap<Integer, String>();
	public Villain villain;
	public Hero hero;
	public boolean game_won = false;
	public boolean extra_dice = false;
	public JButton roll_button;
	public JPanel result_panel;
	public JPanel win_panel;
	public JPanel loss_panel;
	public JPanel draw_panel;
	public JLabel hero_roll_text;
	public JLabel villain_roll_text;
	public JLabel loss_text;

	/**
	 * the constructor for the dice roll screen
	 * @param new_game		the Game object
	 * @param selected_hero the Hero chosen to battle with
	 */
	public DiceRollScreen(Game new_game, Hero selected_hero) {
		game = new_game;
		team = game.team;
		villain = game.cities.get(team.current_city_num).villain;
		hero = selected_hero;
		extra_dice = hero.hasPowerUp("Extra Dice");
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
		game.closeDiceRollScreen(this, game_won);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setResizable(false);
		window.setBounds(100, 100, 450, 409);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
				
		JLabel title = new JLabel("Dice Rolls");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 13));
		title.setBounds(10, 11, 414, 27);
		window.getContentPane().add(title);
		
		JButton rollButton = new JButton("Roll dice!");
		rollButton.setBounds(115, 49, 200, 48);
		window.getContentPane().add(rollButton);
		rollButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleGameOutcome();
			}
		});
		roll_button = rollButton;
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBounds(32, 134, 370, 218);
		window.getContentPane().add(resultPanel);
		resultPanel.setLayout(null);
		result_panel = resultPanel;
		resultPanel.setVisible(false);
		
		JPanel lossPanel = new JPanel();
		lossPanel.setLayout(null);
		lossPanel.setBorder(new LineBorder(Color.RED, 2));
		lossPanel.setBounds(13, 96, 347, 111);
		resultPanel.add(lossPanel);
		
		JLabel lossTitle = new JLabel("You Lose!");
		lossTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lossTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lossTitle.setBounds(10, 11, 327, 24);
		lossPanel.add(lossTitle);
		
		JLabel lossText = new JLabel();
		lossText.setHorizontalAlignment(SwingConstants.CENTER);
		lossText.setBounds(20, 46, 317, 14);
		lossPanel.add(lossText);
		loss_text = lossText;
		
		JButton lossOKbutton = new JButton("OK");
		lossOKbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		lossOKbutton.setBounds(129, 77, 89, 23);
		lossPanel.add(lossOKbutton);
		
		loss_panel = lossPanel;
		
		JLabel heroRollTitle = new JLabel("You roll:");
		heroRollTitle.setBounds(64, 11, 77, 14);
		resultPanel.add(heroRollTitle);
		
		JLabel villainRollTitle = new JLabel(String.format("%s rolls:", villain.name));
		villainRollTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		villainRollTitle.setBounds(191, 11, 143, 14);
		resultPanel.add(villainRollTitle);
		
		JLabel vsLabel = new JLabel("vs");
		vsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vsLabel.setBounds(151, 43, 46, 14);
		resultPanel.add(vsLabel);
		
		JLabel villainRoll = new JLabel();
		villainRoll.setHorizontalAlignment(SwingConstants.CENTER);
		villainRoll.setFont(new Font("Tahoma", Font.BOLD, 16));
		villainRoll.setBounds(207, 36, 106, 26);
		resultPanel.add(villainRoll);
		villain_roll_text = villainRoll;
		
		JLabel heroRoll = new JLabel();
		heroRoll.setHorizontalAlignment(SwingConstants.CENTER);
		heroRoll.setFont(new Font("Tahoma", Font.BOLD, 16));
		heroRoll.setBounds(35, 36, 106, 26);
		resultPanel.add(heroRoll);
		hero_roll_text = heroRoll;
		
		JPanel winPanel = new JPanel();
		winPanel.setBorder(new LineBorder(Color.GREEN, 2));
		winPanel.setBounds(13, 96, 347, 111);
		resultPanel.add(winPanel);
		winPanel.setLayout(null);
		
		JLabel winTitle = new JLabel("You Win!");
		winTitle.setHorizontalAlignment(SwingConstants.CENTER);
		winTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		winTitle.setBounds(10, 11, 327, 24);
		winPanel.add(winTitle);
		
		JLabel winText = new JLabel(String.format("You have beaten %s %d out of 3 times!", villain.name, game.score + 1));
		winText.setHorizontalAlignment(SwingConstants.CENTER);
		winText.setBounds(20, 46, 317, 14);
		winPanel.add(winText);
		
		JButton winOKbutton = new JButton("OK");
		winOKbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		winOKbutton.setBounds(129, 77, 89, 23);
		winPanel.add(winOKbutton);
		
		win_panel = winPanel;
		
		JPanel drawPanel = new JPanel();
		drawPanel.setBorder(new LineBorder(Color.BLACK, 2));
		drawPanel.setBounds(13, 96, 347, 111);
		resultPanel.add(drawPanel);
		drawPanel.setLayout(null);
		draw_panel = drawPanel;
		
		JLabel drawTitle = new JLabel("You Draw!");
		drawTitle.setHorizontalAlignment(SwingConstants.CENTER);
		drawTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		drawTitle.setBounds(10, 11, 327, 24);
		drawPanel.add(drawTitle);
		
		JLabel drawText = new JLabel("Roll again!");
		drawText.setHorizontalAlignment(SwingConstants.CENTER);
		drawText.setBounds(20, 46, 317, 14);
		drawPanel.add(drawText);
		
		JLabel increasedLuck = new JLabel();
		increasedLuck.setHorizontalAlignment(SwingConstants.CENTER);
		increasedLuck.setForeground(new Color(0, 128, 0));
		increasedLuck.setBounds(10, 97, 426, 35);
		window.getContentPane().add(increasedLuck);
		if (extra_dice) {
			increasedLuck.setText("<html><center>Your hero has an Extra Dice! <br>Maximum roll is now 18</center></html>");
		}
				
	}

	/**
	 * Gets a random number from the amount of dice used to play
	 * @param dice	the number of dice to roll with
	 * @return		the number rolled by the dice
	 */
	protected int getRoll(int dice) {
		Random diceRoll = new Random();
		return diceRoll.nextInt(dice * 6) + 1;
	}
	
	/**
	 * The logic flow for handling the game and deciding a winner
	 */
	protected void handleGameOutcome() {
		int num_hero_dice = 2;
		//If hero has an extra dice increase dice amount from 2 to 3
		if (extra_dice) {
			num_hero_dice = 3;
		}
		//Calculate villain damage based on hero damage proportion
		double damage = villain.damage;
		damage = damage * hero.damage_proportion;
		//Get the hero and villain rolls
		int hero_roll = getRoll(num_hero_dice);
		int villain_roll = getRoll(2);
		//Set the window elements to show the dice rolls
		villain_roll_text.setText(Integer.toString(villain_roll));
		hero_roll_text.setText(Integer.toString(hero_roll));
		result_panel.setVisible(true);
		//Determine game outcome
		if (!(hero_roll == villain_roll)) {
			//Hero wins
			if (hero_roll > villain_roll) {
				draw_panel.setVisible(false);
				loss_panel.setVisible(false);
				win_panel.setVisible(true);
				game_won = true;
			//Hero loses
			} else {
				hero.current_health -= damage;
				if (hero.checkIfDead()) {
					loss_text.setText(String.format("%s has died!", hero.hero_name));
					team.heroes.remove(hero);
				} else {
					loss_text.setText(String.format("%s loses %.1f%% health!", hero.hero_name, damage));
				}
				game_won = false;
				draw_panel.setVisible(false);
				loss_panel.setVisible(true);
				win_panel.setVisible(false);
			}
			roll_button.setEnabled(false);
		//Draw
		} else {
			draw_panel.setVisible(true);
			loss_panel.setVisible(false);
			win_panel.setVisible(false);
		}
	}

}
