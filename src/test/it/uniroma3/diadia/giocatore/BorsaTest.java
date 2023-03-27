package test.it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


public class BorsaTest {

    @Test
    public void TestGetPesoVuota(){
    	Borsa borsaTest = new Borsa();
        assertEquals(0, borsaTest.getPeso());
    }

    @Test
    public void TestGetPesoConAttrezzo(){
    	Borsa borsaTest = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        borsaTest.addAttrezzo(attrezzo);
        assertEquals(10,borsaTest.getPeso());
    }

    @Test
    public void TestHasAttrezzoNonInBorsa(){
    	Borsa borsaTest = new Borsa();
        assertFalse(borsaTest.hasAttrezzo("osso"));
    }

    @Test
    public void TestHasAttrezzoInBorsa(){
    	Borsa borsaTest = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        borsaTest.addAttrezzo(attrezzo);
        assertTrue(borsaTest.hasAttrezzo(attrezzo.getNome()));
    }

    public void TestHasAttrezzoNull(){
    	Borsa borsaTest = new Borsa();
        assertFalse(borsaTest.hasAttrezzo(null));
    }

    @Test
    public void TestRemoveAttrezzoNonInBorsa(){
    	Borsa borsaTest = new Borsa();
        assertNull(borsaTest.removeAttrezzo("osso"));
    }

    @Test
    public void TestRemoveAttrezzoInBorsa(){
    	Borsa borsaTest = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        borsaTest.addAttrezzo(attrezzo);
        assertEquals(attrezzo,borsaTest.removeAttrezzo(attrezzo.getNome()));
    }

    public void TestRemoveAttrezzoNull(){
    	Borsa borsaTest = new Borsa();
        assertNull(borsaTest.removeAttrezzo(null));
    }
    
    @Test
    public void TestAddAttrezzoCapienzaDisponibile(){
    	Borsa borsaTest = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        assertTrue(borsaTest.addAttrezzo(attrezzo));
    }

    @Test
    public void TestAddAttrezzoBorsaPiena(){
    	Borsa borsaTest = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        Attrezzo nonInserire = new Attrezzo("Spadina",1);
        borsaTest.addAttrezzo(attrezzo);
        assertFalse(borsaTest.addAttrezzo(nonInserire));
    }
    
    //Controlla il comportamento del metodo addAttrezzo in caso l'array che memorizza gli attrezzi in borsa è pieno
    @Test
    public void testAddAttrezzo() {
    	Attrezzo attrezzo = new Attrezzo ("AttrezzoLeggero",1);
    	Borsa borsaTest = new Borsa(50);
    	Attrezzo attrezzoDaNonAggiungere = new Attrezzo("Attrezzo Di troppo",1);
    	for(int i=0;i<borsaTest.getNumeroMassimoAttrezzi();i++) {
    		borsaTest.addAttrezzo(attrezzo);
    	}
    	assertFalse(borsaTest.addAttrezzo(attrezzoDaNonAggiungere));
    }

    @Test
    public void TestAddAttrezzoNull(){
    	Borsa borsaTest = new Borsa();
        assertFalse(borsaTest.addAttrezzo(null));
    }
}
