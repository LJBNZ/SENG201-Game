package game;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class HeroSelectScreen {

	private JFrame window;
	public Game game;
	private JTextField heroNameInput;
	private int selected_type = 0;	
	
	/**
	 * the constructor for the hero select screen
	 * @param new_game	the Game object
	 */
	public HeroSelectScreen(Game new_game) {
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
		game.closeHeroSelectScreen(this);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		window = new JFrame();
		window.setTitle("Hero Select");
		window.setResizable(false);
		window.setBounds(100, 100, 560, 375);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel HeroSelectTitle = new JLabel("Hero Select");
		HeroSelectTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		HeroSelectTitle.setBounds(235, 11, 93, 14);
		window.getContentPane().add(HeroSelectTitle);
		
		heroNameInput = new JTextField();
		heroNameInput.setBounds(152, 297, 193, 20);
		window.getContentPane().add(heroNameInput);
		heroNameInput.setColumns(10);
		
		JLabel heroNameLabel = new JLabel("Hero name:");
		heroNameLabel.setBounds(37, 300, 97, 14);
		window.getContentPane().add(heroNameLabel);
		
		JButton acceptButton = new JButton("Accept");
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hero_name = heroNameInput.getText();
				if (!hero_name.isEmpty() && selected_type > 0) {
					game.createHero(hero_name, selected_type);
					finishedWindow();
				}
			}
		});
		acceptButton.setBounds(399, 296, 89, 23);
		window.getContentPane().add(acceptButton);
		
		JPanel attributesPanel = new JPanel();
		attributesPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		attributesPanel.setBounds(37, 197, 451, 69);
		window.getContentPane().add(attributesPanel);
		attributesPanel.setLayout(null);
		
		JLabel attributeText = new JLabel("Select a type");
		attributeText.setFont(new Font("Tahoma", Font.ITALIC, 11));
		attributeText.setVerticalAlignment(SwingConstants.TOP);
		attributeText.setHorizontalAlignment(SwingConstants.CENTER);
		attributeText.setBounds(10, 33, 431, 25);
		attributesPanel.add(attributeText);
		
		JLabel attributeTitle = new JLabel("");
		attributeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		attributeTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		attributeTitle.setBounds(10, 8, 431, 14);
		attributesPanel.add(attributeTitle);
		
		JButton type1Button = new JButton(game.types.get(1));
		type1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_type = 1;
				attributeText.setText(game.type_attributes.get(1));
				attributeTitle.setText(game.types.get(1));
			}
		});
		type1Button.setBounds(97, 78, 147, 23);
		window.getContentPane().add(type1Button);
		
		JButton type2Button = new JButton(game.types.get(2));
		type2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_type = 2;
				attributeText.setText(game.type_attributes.get(2));
				attributeTitle.setText(game.types.get(2));
			}
		});
		type2Button.setBounds(291, 78, 147, 23);
		window.getContentPane().add(type2Button);
		
		JButton type3Button = new JButton(game.types.get(3));
		type3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_type = 3;
				attributeText.setText(game.type_attributes.get(3));
				attributeTitle.setText(game.types.get(3));
			}
		});
		type3Button.setBounds(97, 112, 147, 23);
		window.getContentPane().add(type3Button);
		
		JButton type4Button = new JButton(game.types.get(4));
		type4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_type = 4;
				attributeText.setText(game.type_attributes.get(4));
				attributeTitle.setText(game.types.get(4));
			}
		});
		type4Button.setBounds(291, 112, 147, 23);
		window.getContentPane().add(type4Button);
		
		JButton type5Button = new JButton(game.types.get(5));
		type5Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_type = 5;
				attributeText.setText(game.type_attributes.get(5));
				attributeTitle.setText(game.types.get(5));
			}
		});
		type5Button.setBounds(97, 146, 147, 23);
		window.getContentPane().add(type5Button);
		
		JButton type6Button = new JButton(game.types.get(6));
		type6Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_type = 6;
				attributeText.setText(game.type_attributes.get(6));
				attributeTitle.setText(game.types.get(6));
			}
		});
		type6Button.setBounds(291, 146, 147, 23);
		window.getContentPane().add(type6Button);
		
		JLabel heroTypeLabel = new JLabel("Hero type:");
		heroTypeLabel.setBounds(98, 53, 106, 14);
		window.getContentPane().add(heroTypeLabel);
		
		//Disable buttons for hero types already taken by heroes in team
		if (game.existing_types.contains(1)) {
			type1Button.setEnabled(false);
		} 
		if (game.existing_types.contains(2)) {
			type2Button.setEnabled(false);
		} 
		if (game.existing_types.contains(3)) {
			type3Button.setEnabled(false);
		} 
		if (game.existing_types.contains(4)) {
			type4Button.setEnabled(false);
		} 
		if (game.existing_types.contains(5)) {
			type5Button.setEnabled(false);
		} 
		if (game.existing_types.contains(6)) {
			type6Button.setEnabled(false);
		}		
		
	}
}
