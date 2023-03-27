package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi", "posa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole io) {
		this.partita = new Partita();
		this.io=io;
	}

	public void gioca() {
		String istruzione; 
		io.mostraMessaggio("Come ti chiami: ");
		partita.getGiocatore().setNome(io.leggiRiga());	
		io.mostraMessaggio("Benvenuto "+partita.getGiocatore().getNome()+".\n"+ MESSAGGIO_BENVENUTO);
		do {
			istruzione = io.leggiRiga();
		}while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome()==null)
			io.mostraMessaggio("Scrivi un comando");
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		}else if(comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		}else
			io.mostraMessaggio("Comando sconosciuto");
		if(!(this.partita.getGiocatore().isVivo())) {
			io.mostraMessaggio("Sei morto!");
			return true;
		}else if (this.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		}else 
			return false;
	}   

	private boolean vinta() {
		return this.partita.getGiocatore().getStanzaCorrente()==partita.getLabirinto().getStanzaVincente();
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getGiocatore().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getGiocatore().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		io.mostraMessaggio(partita.getGiocatore().getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Comando "prendi"
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Inserisci il nome dell'attrezzo che vuoi prendere");
		}else {
			Stanza stanzaCorrente = this.partita.getGiocatore().getStanzaCorrente();
			Borsa borsaGiocatore = this.partita.getGiocatore().getBorsa();
			if(stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
				if(borsaGiocatore.addAttrezzo(stanzaCorrente.getAttrezzo(nomeAttrezzo))){
					io.mostraMessaggio("hai preso" + nomeAttrezzo);
					stanzaCorrente.removeAttrezzo(nomeAttrezzo);
				}else {
					io.mostraMessaggio("La borsa è piena");
				}
			}else {
				io.mostraMessaggio("L'attrezzo non è presente nella stanza");
			}
		}
		
	}
	
	/**
	 * Comando "posa"
	 */
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Inserisci il nome dell'attrezzo da lasciare.");
		}else{
			Stanza stanzaCorrente=this.partita.getGiocatore().getStanzaCorrente();
			Borsa borsaGiocatore=this.partita.getGiocatore().getBorsa();
			if(borsaGiocatore.hasAttrezzo(nomeAttrezzo)) {
				if(stanzaCorrente.addAttrezzo(borsaGiocatore.getAttrezzo(nomeAttrezzo))){
					borsaGiocatore.removeAttrezzo(nomeAttrezzo);
					io.mostraMessaggio("L'attrezzo è stato posato nella stanza");
				}else{
					io.mostraMessaggio("L'attrezzo non può essere posato in questa stanza perché è già piena di altri attrezzi.");
					io.mostraMessaggio("Posa l'attrezzo in un'altra stanza!");
				}
			}else{
				io.mostraMessaggio("Questo oggetto non è presente nella borsa");
			}
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}