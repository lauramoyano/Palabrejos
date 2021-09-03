/*
 * Programación interactiva
 * Autores: Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
	Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
 * miniProyecto 3 : Palabrejos
 */
package game;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Serializacion {

	private FileInputStream fileInput;
	private ObjectInputStream input;
	private FileOutputStream fileOutput;
	private ObjectOutputStream output;
	
	/*
	 * public Serializacion() {
	 * 
	 * }
	 */
	
	public void serializeObject(Player player) {
		try {
			fileOutput = new FileOutputStream("src/lastGame/" + player.getName());
			output = new ObjectOutputStream(fileOutput);
			output.writeObject(player);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public Player deserializeObject(String player) {
		Player player1 = null;
		try {
			fileInput = new FileInputStream("src/lastGame/" + player);
			input = new ObjectInputStream(fileInput);
			player1 = (Player)input.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player1;
	}
}
