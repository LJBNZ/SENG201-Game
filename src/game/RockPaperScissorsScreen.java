package game;

import java.util.ArrayList;
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

public class RockPaperScissorsScreen {

	private JFrame window;
	public Game game;
	public Team team;
	public Map<Integer, String> hero_powerups = new HashMap<Integer, String>();
	public Villain villain;
	public Hero hero;
	public int move_selection;
	public boolean game_won = false;
	public boolean is_lucky = false;
	public Map<Integer, String> RPS_names = new HashMap<Integer, String>();
	public JPanel result_panel;
	public JPanel win_panel;
	public JPanel loss_panel;
	public JPanel draw_panel;
	public JLabel hero_choice;
	public JLabel villain_choice;
	public JLabel loss_text;
	ArrayList<JButton> choice_buttons = new ArrayList<JButton>();
	public String hero_move_text = "";
	public String villain_move_text = "";

	/**
	 * Constructor for the rock paper scissors game screen
	 * @param new_game			the Game object
	 * @param selected_hero		the Hero chosen to battle with
	 */
	public RockPaperScissorsScreen(Game new_game, Hero selected_hero) {
		game = new_game;
		team = game.team;
		villain = game.cities.get(team.current_city_num).villain;
		hero = selected_hero;
		is_lucky = (hero.hasPowerUp("Lucky Charm") || hero.hasPowerUp("Psychic Powers"));
		RPS_names.put(1, "Rock");
		RPS_names.put(2, "Paper");
		RPS_names.put(3, "Scissors");
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
		game.closeRockPaperScissorsScreen(this, game_won);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setResizable(false);
		window.setBounds(100, 100, 450, 447);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
				
		JLabel title = new JLabel("Rock Paper Scissors");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 13));
		title.setBounds(10, 11, 414, 27);
		window.getContentPane().add(title);
		
		JPanel choicePanel = new JPanel();
		choicePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		choicePanel.setBounds(31, 71, 370, 71);
		window.getContentPane().add(choicePanel);
		choicePanel.setLayout(null);
		
		JButton rockButton = new JButton("Rock");
		rockButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rockButton.setBounds(10, 11, 89, 50);
		choicePanel.add(rockButton);
		choice_buttons.add(rockButton);
		
		JButton paperButton = new JButton("Paper");
		paperButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		paperButton.setBounds(141, 11, 89, 50);
		choicePanel.add(paperButton);
		choice_buttons.add(paperButton);

		
		JButton scissorsButton = new JButton("Scissors");
		scissorsButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scissorsButton.setBounds(270, 11, 89, 50);
		choicePanel.add(scissorsButton);
		choice_buttons.add(scissorsButton);

		//the event listeners for the Rock/Paper/Scissors buttons
		
		rockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move_selection = 1;
				handleGameOutcome();
			}
		});
		paperButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move_selection = 2;
				handleGameOutcome();
			}
		});
		scissorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move_selection = 3;
				handleGameOutcome();
			}
		});
		
		

		JLabel choiceTitle = new JLabel("Choose a move:");
		choiceTitle.setBounds(34, 49, 150, 14);
		window.getContentPane().add(choiceTitle);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBounds(31, 179, 370, 218);
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
		
		JLabel heroPlaysTitle = new JLabel("You play:");
		heroPlaysTitle.setBounds(64, 11, 77, 14);
		resultPanel.add(heroPlaysTitle);
		
		JLabel villainPlaysTitle = new JLabel(String.format("%s plays:", villain.name));
		villainPlaysTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		villainPlaysTitle.setBounds(191, 11, 143, 14);
		resultPanel.add(villainPlaysTitle);
		
		JLabel vsLabel = new JLabel("vs");
		vsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vsLabel.setBounds(151, 43, 46, 14);
		resultPanel.add(vsLabel);
		
		JLabel villainChoice = new JLabel();
		villainChoice.setHorizontalAlignment(SwingConstants.CENTER);
		villainChoice.setFont(new Font("Tahoma", Font.BOLD, 13));
		villainChoice.setBounds(207, 36, 106, 26);
		resultPanel.add(villainChoice);
		villain_choice = villainChoice;
		
		JLabel heroChoice = new JLabel();
		heroChoice.setHorizontalAlignment(SwingConstants.CENTER);
		heroChoice.setFont(new Font("Tahoma", Font.BOLD, 13));
		heroChoice.setBounds(35, 36, 106, 26);
		resultPanel.add(heroChoice);
		hero_choice = heroChoice;
		
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
		
		JLabel drawText = new JLabel("Choose another move to play");
		drawText.setHorizontalAlignment(SwingConstants.CENTER);
		drawText.setBounds(20, 46, 317, 14);
		drawPanel.add(drawText);
		
		//Alert user if the hero they are using has increased luck 
		JLabel increasedLuck = new JLabel();
		increasedLuck.setHorizontalAlignment(SwingConstants.CENTER);
		increasedLuck.setForeground(new Color(0, 128, 0));
		increasedLuck.setBounds(10, 144, 414, 35);
		window.getContentPane().add(increasedLuck);
		if (is_lucky) {
			increasedLuck.setText("<html><center>Your hero has increased luck! <br>Your Rock Paper Scissors skills are sharpened</center></html>");
		}
				
	}

	/**
	 * Determines the winner of the game based on the move choices
	 * for the hero and villain
	 * @param h_choice	the move chosen by the hero
	 * @param v_choice	the random move chosen for the villain
	 * @return	true if hero wins, false otherwise
	 */
	protected boolean determineResult(int h_choice, int v_choice) {
		boolean hero_wins = false;
		switch(h_choice) {
			//Rock
			case 1:
				switch(v_choice) {
					case 2:
						hero_wins = false;
						break;
					case 3:
						hero_wins = true;
						break;
				}
				break;
			//Paper
			case 2:
				switch(v_choice) {
					case 1:
						hero_wins = true;
						break;
					case 3:
						hero_wins = false;
						break;
			    }
				break;
			//Scissors
			case 3:
				switch(v_choice) {
					case 1:
						hero_wins = false;
						break;
					case 2:
						hero_wins = true;
						break;
			    }
		}
		return hero_wins;
	}

	/**
	 * gets a random move for the villain
	 * @return	int representing the move
	 */
	protected int getVillainMove() {
		Random villain_choice = new Random();
	    int v_choice = villain_choice.nextInt(3) + 1;
		return v_choice;
	}
	
	/**
	 * logic for determining a winner 
	 */
	protected void handleGameOutcome() {
		int villain_move = getVillainMove();
		//If the hero has increased luck, there is a 33% chance the villain will choose a losing move
		//automatically, letting the user win
		if (is_lucky) {
			Random chance = new Random();
			int chance_of_automatic_win = chance.nextInt(100) + 1;
			if (chance_of_automatic_win > 66) {
				if (move_selection == 1) {
					villain_move = 3;
				} else if (move_selection == 2) {
					villain_move = 1;
				} else {
					villain_move = 2;
				}
			}
		} 			
		//Set the moves chosen on the screen to the correct move name
		villain_move_text = RPS_names.get(villain_move);
		hero_move_text = RPS_names.get(move_selection);
		hero_choice.setText(hero_move_text);
		villain_choice.setText(villain_move_text);
		result_panel.setVisible(true);
		//determine the villain damage based on the hero damage proportion modifier
		double damage = villain.damage;
		damage = damage * hero.damage_proportion;
		if (!(move_selection == villain_move)) {
			//Determine the winner based on the moves played
			boolean result = determineResult(move_selection, villain_move);
			if (result) {
				//if hero wins, display win_panel
				draw_panel.setVisible(false);
				loss_panel.setVisible(false);
				win_panel.setVisible(true);
				game_won = true;
			} else {
				//if hero loses then damage the hero and check if they die
				hero.current_health -= damage;
				if (hero.checkIfDead()) {
					//if hero is dead, alert the user and remove the hero from the team
					loss_text.setText(String.format("%s has died!", hero.hero_name));
					team.heroes.remove(hero);
				} else {
					//if the hero is alive still, alert the user to the damage dealt
					loss_text.setText(String.format("%s loses %.1f%% health!", hero.hero_name, damage));
				}
				//display loss_panel
				game_won = false;
				draw_panel.setVisible(false);
				loss_panel.setVisible(true);
				win_panel.setVisible(false);
			}
			//Disable the move buttons so no further moves can be played
			for (int i = 0; i < choice_buttons.size(); i++) {
				choice_buttons.get(i).setEnabled(false);
			}
		} else {
			//else, there is a draw so display the draw_panel
			draw_panel.setVisible(true);
			loss_panel.setVisible(false);
			win_panel.setVisible(false);
		}
	}

}
