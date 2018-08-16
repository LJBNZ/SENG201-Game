package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class GuessTheNumberScreen {

	private JFrame window;
	public Game game;
	public Team team;
	public Map<Integer, String> hero_powerups = new HashMap<Integer, String>();
	public Villain villain;
	public Hero hero;
	public int villain_number = getVillainNumber();
	public int hero_guess;
	public int selected_number;
	public int guesses_left = 2;
	public boolean game_won = false;
	public boolean is_lucky = false;
	public JButton guess_button;
	public JPanel result_panel;
	public JPanel win_panel;
	public JPanel loss_panel;
	public JLabel loss_text;
	public JLabel villain_hint;
	public JLabel guesses_left_text;

	/**
	 * the constructor for the guess the number screen
	 * @param new_game		the Game object
	 * @param selected_hero the Hero chosen to battle with
	 */
	public GuessTheNumberScreen(Game new_game, Hero selected_hero) {
		game = new_game;
		team = game.team;
		villain = game.cities.get(team.current_city_num).villain;
		hero = selected_hero;
		is_lucky = (hero.hasPowerUp("Lucky Charm") || hero.hasPowerUp("Psychic Powers"));
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
		game.closeGuessTheNumberScreen(this, game_won);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes and Villains");
		window.setResizable(false);
		window.setBounds(100, 100, 552, 409);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
				
		JLabel title = new JLabel("Guess the Number");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 13));
		title.setBounds(10, 11, 526, 27);
		window.getContentPane().add(title);
		
		JButton guessButton = new JButton("Guess");
		guessButton.setBounds(39, 296, 77, 57);
		window.getContentPane().add(guessButton);
		guessButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		guessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleGameOutcome();
			}
		});
		guess_button = guessButton;
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBounds(147, 54, 370, 299);
		window.getContentPane().add(resultPanel);
		resultPanel.setLayout(null);
		result_panel = resultPanel;
		resultPanel.setVisible(false);
		
		JLabel guessesLeftText = new JLabel(String.format("Guesses remaining: %d", guesses_left));
		guessesLeftText.setBounds(13, 104, 347, 26);
		resultPanel.add(guessesLeftText);
		guessesLeftText.setHorizontalAlignment(SwingConstants.CENTER);
		guessesLeftText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		guesses_left_text = guessesLeftText;
		
		JPanel lossPanel = new JPanel();
		lossPanel.setLayout(null);
		lossPanel.setBorder(new LineBorder(Color.RED, 2));
		lossPanel.setBounds(13, 175, 347, 111);
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
		
		JPanel winPanel = new JPanel();
		winPanel.setBorder(new LineBorder(Color.GREEN, 2));
		winPanel.setBounds(13, 175, 347, 111);
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
		
		JLabel description = new JLabel(String.format("Guess the number that %s is thinking of", villain.name));
		description.setHorizontalAlignment(SwingConstants.CENTER);
		description.setBounds(13, 11, 347, 26);
		resultPanel.add(description);
		
		JLabel villainHint = new JLabel();
		villainHint.setFont(new Font("Tahoma", Font.BOLD, 13));
		villainHint.setHorizontalAlignment(SwingConstants.CENTER);
		villainHint.setBounds(13, 67, 347, 26);
		resultPanel.add(villainHint);
		villain_hint = villainHint;
		
		JLabel increasedLuck = new JLabel();
		increasedLuck.setHorizontalAlignment(SwingConstants.CENTER);
		increasedLuck.setForeground(new Color(0, 128, 0));
		increasedLuck.setBounds(23, 129, 337, 35);
		resultPanel.add(increasedLuck);
		if (is_lucky) {
			increasedLuck.setText("<html><center>Your hero has increased luck! <br>Your number guessing skills are sharpened</center></html>");
		}
		
		//Create the list element which contains the numbers to guess from (from 1 to 10)
		JList<String> numberList = new JList<String>();
		numberList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (numberList.getSelectedIndex() != -1) {
					selected_number = Integer.parseInt(numberList.getSelectedValue());
				}
			}
		});
		numberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		numberList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numberList.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		numberList.setBounds(39, 54, 77, 218);
		window.getContentPane().add(numberList);
		
		loss_panel.setVisible(false);
		win_panel.setVisible(false);
		result_panel.setVisible(true);
				
	}

	/**
	 * Initiate villain number to random number from 1 to 10
	 * @return 	random int from 1 rto 10
	 */
	protected int getVillainNumber() {
		Random number = new Random();
		return number.nextInt(10) + 1;
	}
	
	/**
	 * The game logic to handle the possible outcomes
	 */
	protected void handleGameOutcome() {
		//Subtract a guess from the allowed amount of guesses
		guesses_left--;
		guesses_left_text.setText(String.format("Guesses remaining: %d", guesses_left));
		result_panel.setVisible(true);
		//Get hero guess from selected list element
		hero_guess = selected_number;
		//Determine villain damage based on hero damage proportion modifier
		double damage = villain.damage;
		damage = damage * hero.damage_proportion;
		//If hero did not successfully guess on this turn
		if (!((hero_guess == villain_number) || (is_lucky && (hero_guess >= villain_number - 1 && hero_guess <= villain_number + 1)))) {
			//if hero guess is larger than villain number
			if (hero_guess > villain_number) {
				//if no guesses left display the loss panel
				if (guesses_left == 0) {
					villain_hint.setText(String.format("Unlucky, %s's number was %d", villain.name, villain_number));
					guesses_left_text.setText("");
					hero.current_health -= damage;
					if (hero.checkIfDead()) {
						loss_text.setText(String.format("%s has died!", hero.hero_name));
						team.heroes.remove(hero);
					} else {
						loss_text.setText(String.format("%s loses %.1f%% health!", hero.hero_name, damage));
					}
					guess_button.setEnabled(false);
					loss_panel.setVisible(true);
					win_panel.setVisible(false);
					game_won = false;
				//else alert player that their guess was too high
				} else {
					villain_hint.setText(String.format("%s's number is LOWER than %d", villain.name, hero_guess));
				}
			// else hero guess is lower than villain number
			} else {
				//if no guesses left display the loss panel
				if (guesses_left == 0) {
					villain_hint.setText(String.format("Unlucky, %s's number was %d", villain.name, villain_number));
					guesses_left_text.setText("");
					hero.current_health -= damage;
					if (hero.checkIfDead()) {
						loss_text.setText(String.format("%s has died!", hero.hero_name));
						team.heroes.remove(hero);
					} else {
						loss_text.setText(String.format("%s loses %.1f%% health!", hero.hero_name, damage));
					}
					guess_button.setEnabled(false);
					loss_panel.setVisible(true);
					win_panel.setVisible(false);
					game_won = false;
				//else alert player that their guess was too low
				} else {
					villain_hint.setText(String.format("%s's number is HIGHER than %d", villain.name, hero_guess));
				}
			}
		//else the hero has successfully guessed the number
		} else {
			villain_hint.setText(String.format("Nice! You correctly guessed the number", villain.name, villain_number));
			guesses_left_text.setText("");
			game_won = true;
			guess_button.setEnabled(false);
			loss_panel.setVisible(false);
			win_panel.setVisible(true);
			
		}
	}
}
