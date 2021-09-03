/*
 * Programación interactiva
 * Autores: Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
	Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
 * miniProyecto 3 : Palabrejos
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class PanelGame extends JPanel {
	
	private JLabel wordToShow;
	private JLabel image;
	private JLabel textLevel;
	private JLabel textHits;
	private JLabel level;
	private JLabel hits;
	private JTextField inputWord;
	private JLabel chronometer;
	private JButton reStart;
	private JButton exit;
	private JButton noMoreWords;
	private Logic control;
	private ImageIcon icon;
	static Chronometer myChronometer;
	private Player player;
	
	public PanelGame(ActionListener escucha, KeyListener escuchaTeclado) {
		
	
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		this.icon = new ImageIcon("src/Images/Teclado1.png"); 
		this.control = new Logic();
		
		this.textLevel= new JLabel("Round");
		this.textLevel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		this.add(textLevel, constraints);
		
		this.level= new JLabel();
		this.level.setBorder(BorderFactory.createLineBorder(Color.lightGray, 4));
		this.level.setText(String.valueOf(control.getRound()));
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		this.add(level, constraints);
		
		this.textHits= new JLabel("Hits");
		this.textHits.setBorder(BorderFactory.createLineBorder(Color.black, 4));
		constraints.gridx=3;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		this.add(textHits, constraints);
		
		this.hits= new JLabel("0");
		this.hits.setBorder(BorderFactory.createLineBorder(Color.lightGray, 4));
		constraints.gridx=4;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		this.add(hits, constraints);
		
		this.wordToShow= new JLabel();
		this.wordToShow.setFont(new Font(Font.SERIF, Font.BOLD+Font.ITALIC, 30));
		this.wordToShow.setBackground(Color.darkGray);
		this.wordToShow.setForeground(Color.PINK);
		this.wordToShow.setHorizontalAlignment(JLabel.CENTER);
		this.wordToShow.setOpaque(true);
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=5;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		this.add(wordToShow, constraints);
		
		
		this.inputWord =new JTextField();
		this.inputWord.setEditable(false);
		this.inputWord.addKeyListener(escuchaTeclado);
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=3;
		constraints.gridheight=2;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		this.add(inputWord, constraints);
		
		this.chronometer = new JLabel();
		this.chronometer.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
		constraints.gridx=3;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=2;
		constraints.fill=GridBagConstraints.CENTER;
		this.add(chronometer, constraints);
		
		this.noMoreWords = new JButton("Terminar serie");
		this.noMoreWords.addActionListener(escucha);
		this.noMoreWords.setFont(new Font("Traditional Arabic", Font.PLAIN, 15));
		constraints.gridx=4; 
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=2;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;

		this.add(noMoreWords, constraints);
		 
		this.image = new JLabel();
		this.image.setSize(new Dimension(516, 200));
		this.image.setIcon(icon);
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=5;
		constraints.gridheight=2;
		constraints.fill=GridBagConstraints.VERTICAL;
		this.add(image, constraints);
		
		this.reStart = new JButton("Reiniciar");
		this.reStart.addActionListener(escucha);
		this.reStart.setFont(new Font("Traditional Arabic", Font.PLAIN, 15));
		constraints.gridx=0;
		constraints.gridy=7;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.FIRST_LINE_END;
		this.add(reStart, constraints);
		
		this.exit = new JButton("Salir");
		this.exit.addActionListener(escucha);
		this.exit.setFont(new Font("Traditional Arabic", Font.PLAIN, 15));
		constraints.gridx=4;
		constraints.gridy=7;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		//constraints.fill=GridBagConstraints.EAST;
		
		this.add(exit, constraints);
	}
	
	public void addPlayer(Player player) {  //Creada 29/08/2021
		this.player = player;
		this.level.setText(String.valueOf(player.getLevel()));
		control.setRound(player.getLevel());

	}
	
	public Player getPlayer() {
		return player;
	}


	public JButton getReStart() {
		return reStart;
	}

	public JButton getExit() {
		return exit;
	}

	public JButton getNoMoreWords() {
		return noMoreWords;
	}

	public void initChronometer() {
		
			myChronometer = new Chronometer(this.chronometer, 40, 1, 0 );
			myChronometer.setRun(true);
			myChronometer.start();		
			
	}
	
	public JLabel getLevel() {
		return level;
	}
	
//	public void reStartGame() {
//		control = new Logic();
//		view.getPanel().paintButtons();
//		this.getView().setHitsValue(getPoints());
//	}

	public JLabel getChronometer() {
		return chronometer;
	}


	public JLabel getHits() {
		return hits;
	}


	public JTextField getInputWord() {
		return inputWord;
	}


	public Logic getControl() {
		return control;
	}

	public JLabel getWordToShow() {
		return wordToShow;
	}

}
