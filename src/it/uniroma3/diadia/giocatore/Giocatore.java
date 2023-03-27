package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.ambienti.Stanza;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private String nomeGiocatore;
	private Stanza stanzaCorrente;
	private int cfu;
	private boolean vivo;
	private Borsa borsa;

	public Giocatore(Stanza stanzaIniziale) {
		this.cfu=CFU_INIZIALI;
		this.stanzaCorrente= stanzaIniziale;
		this.vivo=true;
		this.borsa = new Borsa();
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente=stanzaCorrente;
	}
	
	public boolean isVivo() {
		this.vivo=(this.cfu>0);
		return vivo;
	}
	
	public void setCfu(int cfu) {
		this.cfu=cfu;
	}
	
	public int getCfu() {
		return cfu;
	}
	
	public void setNome(String nome) {
		this.nomeGiocatore=nome;
	}
	
	public String getNome() {
		return nomeGiocatore;
	}
	
	public Borsa getBorsa() {
		return borsa;
	}

}
