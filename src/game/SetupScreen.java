package game;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetupScreen {

	private JFrame window;
	private JTextField team_name_field;
	public Game game;

	/**
	 * constructor for the setup screen
	 * 
	 * @param new_game 	the Game object
	 */
	public SetupScreen(Game new_game) {
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
		game.closeSetupScreen(this);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setResizable(false);
		window.setTitle("Game Setup");
		window.setBounds(100, 100, 298, 431);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Game options");
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		title.setBounds(0, 0, 292, 40);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		window.getContentPane().add(title);
		
		team_name_field = new JTextField();
		team_name_field.setBounds(138, 68, 122, 20);
		window.getContentPane().add(team_name_field);
		team_name_field.setColumns(10);
		
		JLabel teamNameLabel = new JLabel("Team name:");
		teamNameLabel.setBounds(39, 71, 97, 14);
		window.getContentPane().add(teamNameLabel);
		
		JSlider heroAmountSlider = new JSlider();
		heroAmountSlider.setValue(1);
		heroAmountSlider.setMinimum(1);
		heroAmountSlider.setMaximum(3);
		heroAmountSlider.setBounds(39, 163, 200, 26);
		window.getContentPane().add(heroAmountSlider);
		
		JLabel heroAmountLabel = new JLabel("Number of heroes in team:");
		heroAmountLabel.setBounds(39, 130, 200, 14);
		window.getContentPane().add(heroAmountLabel);
		
		JLabel heroMarker1 = new JLabel("1");
		heroMarker1.setBounds(39, 186, 16, 14);
		window.getContentPane().add(heroMarker1);
		
		JLabel heroMarker2 = new JLabel("2");
		heroMarker2.setBounds(131, 186, 16, 14);
		window.getContentPane().add(heroMarker2);
		
		JLabel heroMarker3 = new JLabel("3");
		heroMarker3.setBounds(223, 186, 16, 14);
		window.getContentPane().add(heroMarker3);
		
		JSlider citiesAmountSlider = new JSlider();
		citiesAmountSlider.setValue(3);
		citiesAmountSlider.setMinimum(3);
		citiesAmountSlider.setMaximum(6);
		citiesAmountSlider.setBounds(39, 255, 200, 26);
		window.getContentPane().add(citiesAmountSlider);
		
		JLabel cityMarker3 = new JLabel("3");
		cityMarker3.setBounds(39, 285, 16, 14);
		window.getContentPane().add(cityMarker3);
		
		JLabel cityMarker4 = new JLabel("4");
		cityMarker4.setBounds(98, 285, 16, 14);
		window.getContentPane().add(cityMarker4);
		
		JLabel cityMarker5 = new JLabel("5");
		cityMarker5.setBounds(162, 285, 16, 14);
		window.getContentPane().add(cityMarker5);
		
		JLabel cityMarker6 = new JLabel("6");
		cityMarker6.setBounds(223, 285, 16, 14);
		window.getContentPane().add(cityMarker6);
		
		JLabel citiesAmountLabel = new JLabel("Number of cities in game:");
		citiesAmountLabel.setBounds(39, 230, 200, 14);
		window.getContentPane().add(citiesAmountLabel);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//set the text entered as the team name
				String teamname = team_name_field.getText();
				if (!teamname.isEmpty()) {
					//set the game variables to the slider values
					game.num_heroes = heroAmountSlider.getValue();
					game.num_cities = citiesAmountSlider.getValue();
					game.team.setTeamName(teamname);
					finishedWindow();
				}
			}
		});
		submit.setBounds(98, 339, 89, 23);
		window.getContentPane().add(submit);
	}

}
