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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class GUI extends JFrame {
	Serializacion serializador;
	Player player;
	PanelLobby panelLogin;
	PanelGame principalPanel;
	JLabel title;
	private Escucha listener;

	public GUI(){ 
		initGUI();

		this.setUndecorated(true);
		this.setTitle("Palabras");
		this.setSize(new Dimension (600, 400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initGUI() {
		// TODO Auto-generated method stub
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		listener = new Escucha();

		this.title = new JLabel();
		this.title.setFont(new Font(Font.SERIF, Font.BOLD+Font.ITALIC, 30));
		this.title.setText("Palabrejos");
		this.title.setBackground(Color.pink);
		this.title.setForeground(Color.WHITE);
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setOpaque(true);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		add(title, constraints);

		this.panelLogin = new PanelLobby(listener);
		this.panelLogin.setSize(800, 600);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(panelLogin, constraints);


		this.principalPanel = new PanelGame(listener, listener);
		this.principalPanel.setSize(800, 600);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(principalPanel, constraints);
		this.principalPanel.setVisible(false);

		serializador = new Serializacion();

	}

	private class Escucha implements ActionListener, KeyListener{

		boolean finishSerie = false;
		int word = 0;
		Timer timer1 = new Timer(3000, this::paintWords);

		public void paintWords(ActionEvent e) {
			principalPanel.getWordToShow().setText(principalPanel.getControl().getDictionaryRound().get(word));
			word++;
			verifySerie(word, timer1);
		}

		public void writeNoMore() {
			//button finish serie
			principalPanel.getNoMoreWords().setEnabled(false);
			//chronometer
			principalPanel.getChronometer().setVisible(false);
			//JTextField
			principalPanel.getInputWord().setEditable(false);
			principalPanel.getInputWord().setFocusable(false);
		}

		public void readyForWrite() {
			//JTextField
			principalPanel.getInputWord().setEditable(true);
			principalPanel.getInputWord().setFocusable(true);
			//chronometer
			principalPanel.getChronometer().setVisible(true);
			//button finish serie
			principalPanel.getNoMoreWords().setEnabled(true);	
			principalPanel.getHits().setText(String.valueOf(principalPanel.getControl().getCountHits()));
		}

		
		public void verifySerie(int wordNum, Timer time) {
			if( wordNum == (principalPanel.getControl().getDictionaryRound().size()/2)) {
				principalPanel.initChronometer();
				time.stop();
				this.readyForWrite();

			}if( wordNum == principalPanel.getControl().getDictionaryRound().size()) {
				word=0;
				time.stop();
				this.readyForWrite();
				principalPanel.myChronometer.continueThread();
			}
		}

		public void showJOptionPane() {
			player.endPlayer(principalPanel.getControl().getCountHits(), Integer.parseInt(principalPanel.getLevel().getText())); // <- Le asigno al jugador el nivel o																			
			serializador.serializeObject(player);	
			int result = JOptionPane.showConfirmDialog(null, "Continuas en el nivel "+ principalPanel.getControl().getRound(),"No has pasado de nivel!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(result == JOptionPane.YES_OPTION){
				System.out.print(principalPanel.getControl().getCountHits());
				principalPanel.getLevel().setText(String.valueOf(principalPanel.getControl().getRound()));
				timer1.restart();
			}else if (result == JOptionPane.NO_OPTION){
				player.endPlayer(principalPanel.getControl().getCountHits(),Integer.parseInt(principalPanel.getLevel().getText()));
				serializador.serializeObject(player);
				System.exit(0);
			}
		}

		@Override 
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == panelLogin.getStart() ) {
				File file = new File("src/lastGame/" + panelLogin.getUserName().getText());
				
				if(panelLogin.getUserName().getText().isEmpty()) {
					//Do'nt nothing if the player don't input the name
				}
				else if(file.exists()) {
					player = serializador.deserializeObject(panelLogin.getUserName().getText());
					principalPanel.addPlayer(player);
					principalPanel.getControl().getDictionaryRound().clear();
					principalPanel.getControl().addToArrayRound();
					principalPanel.getControl().getDictionaryRound2().clear();
					principalPanel.getControl().copyArrayRound();
					panelLogin.setVisible(false);
					principalPanel.setVisible(true);
					timer1.start();
					this.writeNoMore();
					
					for(int i =0; i< principalPanel.getControl().getDictionaryRound().size(); i++) {
						System.out.print(principalPanel.getControl().getDictionaryRound().get(i)+ "\n");
					}
					System.out.print("-----------------------");
					
					for(int i =0; i< principalPanel.getControl().getDictionaryRound2().size(); i++) {
						System.out.print(principalPanel.getControl().getDictionaryRound2().get(i)+ "\n");
					}
					

				}else {
					player = new Player(panelLogin.getUserName().getText());
					principalPanel.addPlayer(player);
					panelLogin.setVisible(false);
					principalPanel.setVisible(true); 
					timer1.start();
					this.writeNoMore();
					
					for(int i =0; i< principalPanel.getControl().getDictionaryRound().size(); i++) {
						System.out.print(principalPanel.getControl().getDictionaryRound().get(i)+ "\n");
					}
					System.out.print("-----------------------");
					
					for(int i =0; i< principalPanel.getControl().getDictionaryRound2().size(); i++) {
						System.out.print(principalPanel.getControl().getDictionaryRound2().get(i)+ "\n");
					}

				}
			}if(e.getSource()== principalPanel.getNoMoreWords()) {
				this.finishSerie= true;
				principalPanel.getInputWord().setText(null);
				if(principalPanel.myChronometer.isAlive() &&  this.finishSerie && word !=0 ) {
					principalPanel.myChronometer.suspendThread();
					timer1.restart();
					principalPanel.getWordToShow().setVisible(true);
					this.writeNoMore();
					this.finishSerie= false;
					
				}if(this.finishSerie ) {
					principalPanel.myChronometer.stopThread();
					this.writeNoMore();

					if(principalPanel.getControl().changeRound()) {
						JOptionPane.showConfirmDialog(null, "Pasaste al nivel "+ principalPanel.getControl().getRound(),"Siguiente Ronda!",JOptionPane.YES_NO_OPTION);
						this.writeNoMore();
						principalPanel.getLevel().setText(String.valueOf(principalPanel.getControl().getRound()));
						timer1.restart();

					}else {
						this.showJOptionPane();
					}
				}
				this.showJOptionPane();
			}if(principalPanel.myChronometer != null && !principalPanel.myChronometer.isAlive() && word !=0) {
				this.showJOptionPane();
				
			}if(e.getSource() == principalPanel.getReStart()) {

			}
			if(e.getSource() == principalPanel.getExit()) { //Al salir se deben guardar los datos del jugador
				player.endPlayer(principalPanel.getControl().getCountHits(),Integer.parseInt(principalPanel.getLevel().getText()));
				serializador.serializeObject(player);
				System.exit(0);
			}
		}


		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

			if(e.getSource()== principalPanel.getInputWord()) {

				principalPanel.getWordToShow().setText("Escriba Las palabras");
				if(e.getKeyCode()==KeyEvent.VK_ENTER){

					if(!principalPanel.getInputWord().getText().isEmpty()) {

						String wordInput = principalPanel.getInputWord().getText();
						principalPanel.getControl().setInputWord(wordInput);
						principalPanel.getControl().compareWords();
						principalPanel.getHits().setText(String.valueOf(principalPanel.getControl().getCountHits()));
						principalPanel.getInputWord().setText(null);
					}
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}


}
