package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private Labirinto maze;
	private Giocatore player;


	public Partita(){
		this.maze=new Labirinto();
		this.player= new Giocatore(maze.getStanzaIniziale());
	}
	
/**
 * Builder utilizzato per motivi di testing
 * @param maze
 * @param player
 */
//	public Partita(Labirinto maze, Giocatore player) {
//		this.maze=maze;
//	}
	
	
	/**
	 * Metodo che imposta un nuovo labirinto in una istanza di partita
	 * @param newMaze Variabile di tipo Labirinto
	 */
	public void setLabirinto(Labirinto newMaze) {
		this.maze=newMaze;
	}

    /** 
     * Metodo che ritorna un dato di tipo labirinto
     */
	public Labirinto getLabirinto() {
		return maze;
	}
	
	/**
	 * Metodo che imposta il giocatore creato in partita
	 * @param newPlayer Variabile di tipo Giocatore. Questa variabile fa riferimento al nuovo giocatore da cambiare nell'istanza della partita 
	 */
	
	public void setGiocatore(Giocatore newPlayer) {
		this.player=newPlayer;
	}
	/**
	 * Metodo che ritorna il giocatore presente nell'istanza della partita
	 */
	public Giocatore getGiocatore() {
		return player;
	}
}
	
