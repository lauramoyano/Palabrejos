/*
 * Programación interactiva
 * Autores: Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
	Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
 * miniProyecto 3 : Palabrejos
 */
package game;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelLobby extends JPanel {
	
	private JLabel inputName;
	private JButton start;
	private JTextField userName;

	public PanelLobby(ActionListener escucha) {
		
		this.inputName = new JLabel("Ingrese su nombre para iniciar: ");
		this.add(inputName);
		
		this.userName =new JTextField(10);
		this.add(userName); 
		
		this.start = new JButton("Jugar");
		this.start.addActionListener(escucha);
		this.add(start);
	}
	public JTextField getUserName() {
		return userName;
	}
	
	public JButton getStart() {
		return start;
	}
}
