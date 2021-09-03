/*
 * Programación interactiva
 * Autores: Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
	Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
 * miniProyecto 3 : Palabrejos
 */
package game;

import javax.swing.JLabel;

public class Chronometer extends Thread {
	
	private static int nuMin;//El Contador de minutos
	private static int nuSeg;//El Contador de de segundos
	private static int nuHora;//El Contador de Horas  
	public static boolean suspend; //Suspende un hilo cuando es true
	public static boolean stop;  
	public static boolean run;
	static JLabel chronometer;
	
	public Chronometer ( JLabel label, int seg,int min, int hora) {// Contructor porque la clase es heredada 
		chronometer = label;
		nuMin = min;
		nuSeg = seg;
		nuHora= hora;
		suspend = false; 
		stop= false;
		run = false;
	}
	
	public static void setRun(boolean run) {
		Chronometer.run = run;
	}

	public void run() {
		try {//si ocurre un error al dormir el proceso(sleep(999))
			  while(run){//inicio del for infinito          
				if(nuSeg!=0) {//si no es el ultimo segundo
					nuSeg--;  //decremento el numero de segundos                                  
				}else{
					if(nuMin!=0){//si no es el ultimo minuto
						nuSeg=59;//segundos comienzan en 59
						nuMin--;//decremento el numero de minutos
					}else{
						if (nuHora!=0){ 
							nuHora--;//decremento el numero de horas
							nuMin=59;//minutos comienzan en 59
							nuSeg=59;//segundos comienzan en 59
						}else{                         
							
							break;
						}
					}
				}
				synchronized (this) {
                    while (suspend) {
                        wait(); 
                    }
                    if (stop) {
                    	
                    	break;
                    }
                }               
				chronometer.setText(nuHora+":"+nuMin+":"+nuSeg);//Muestro en pantalla el temporizador
				sleep(999);//Duermo el hilo durante 999 milisegundos(casi un segundo, quintandole el tiempo de proceso)
			}
				
			
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		  }//Fin try               
	}
	 //Pausar el hilo
    synchronized void stopThread(){
    	run = false;
        stop=true;
        //lo siguiente garantiza que un hilo suspendido puede detenerse.
        suspend=false;
        notify();
    }

    //Suspender un hilo
    synchronized void suspendThread(){
        suspend=true;
    }

    //Renaudar un hilo
    synchronized void continueThread(){
    	run= true;
        suspend=false;
        notify();
    }
      
}

