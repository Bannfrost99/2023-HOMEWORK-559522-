package test.it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
    private Giocatore giocatore;
    private Stanza stanzaIniziale;

    @Before
    public void creaGiocatoreTest(){
    	this.stanzaIniziale = new Stanza ("StanzaTest");
        this.giocatore = new Giocatore(stanzaIniziale);
    }
    
    @Test
    public void testGetStanzaCorrente() {
    	assertEquals(this.stanzaIniziale,this.giocatore.getStanzaCorrente());
    }
    
    @Test
    public void testSetStanzaCorrente() {
    	Stanza stanzaGenerica = new Stanza ("Stanza Generica");
    	giocatore.setStanzaCorrente(stanzaGenerica);
    	assertEquals(stanzaGenerica,this.giocatore.getStanzaCorrente());
    }
    
    @Test
    public void testCambiaStanzaCorrente() {
    	Stanza stanzaGenerica1 = new Stanza ("Stanza Generica");
    	giocatore.setStanzaCorrente(stanzaGenerica1);
    	Stanza stanzaGenerica2 = new Stanza ("Stanza Generica");
    	giocatore.setStanzaCorrente(stanzaGenerica2);
    	assertEquals(stanzaGenerica2,this.giocatore.getStanzaCorrente());
    }
    
    @Test
    public void testSetStanzaCorrenteNull() {
    	Stanza stanzaNull= null;
    	giocatore.setStanzaCorrente(stanzaNull);
    	assertNull(this.giocatore.getStanzaCorrente());
    }
    
    @Test
    public void testGetCfu(){
        this.giocatore.setCfu(30);
        assertEquals(30, this.giocatore.getCfu());
    }

    @Test
    public void testSetCfu(){
        this.giocatore.setCfu(0);
        assertEquals(0,this.giocatore.getCfu());
    }
    
    @Test
    public void testIsVivoGiocatoreVivo() {
    	this.giocatore.setCfu(1);
    	assertTrue(this.giocatore.isVivo());
    }
    
    @Test
    public void testIsVivoGiocatoreMorto() {
    	this.giocatore.setCfu(0);
    	assertFalse(this.giocatore.isVivo());
    }
    
    @Test
    public void testSetNome() {
    	String nome = "Paolo";
    	this.giocatore.setNome(nome);
    	assertEquals(nome,giocatore.getNome());
    }
    @Test
    public void testSetNomeNull() {
    	String nome = null;
    	this.giocatore.setNome(nome);
    	assertNull(this.giocatore.getNome());
    }
}