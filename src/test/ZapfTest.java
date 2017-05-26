package test;

import static org.junit.Assert.*;

import org.junit.Test;

import automat.AutomatenSteuerung;
import automat.Produkt;
import automat.Protokoll;

public class ZapfTest {
	AutomatenSteuerung testAutomat = AutomatenSteuerung.getInstance();
	Protokoll testProtokoll = Protokoll.getInstance();
	private float delta = 0.001f;

	@Test
	public void testGetInstance() {
		AutomatenSteuerung testAutomat = AutomatenSteuerung.getInstance();
		assertEquals(testAutomat,AutomatenSteuerung.getInstance());

		Protokoll testProtokoll = Protokoll.getInstance();
		assertEquals(testProtokoll,Protokoll.getInstance());
	}

	@Test
	public void testBezahleBetrag() {
		testAutomat.testKonfiguration();
		testAutomat.waehleProdukt(Produkt.KAFFEE);
		testAutomat.bezahleBetrag(Produkt.KAFFEE.getPreis());
		testAutomat.zapfeProdukt();
		assertEquals(Produkt.KAFFEE.getPreis(), testProtokoll.getUmsatz(), delta );
		assertEquals("KAFFEE (120EURO)", cutDate(testAutomat.listeVerkäufe()[0]));
		testAutomat.waehleProdukt(Produkt.KAFFEE);
		testAutomat.bezahleBetrag(10);
		testAutomat.bezahleBetrag(20);
		testAutomat.bezahleBetrag(10);
		testAutomat.bezahleBetrag(20);
		testAutomat.bezahleBetrag(40);
		testAutomat.bezahleBetrag(20);
		testAutomat.zapfeProdukt();
		int erwartet = Produkt.KAFFEE.getPreis() * 2;
		assertEquals(erwartet, testProtokoll.getUmsatz(), delta);
		assertEquals("KAFFEE (120EURO)", cutDate(testAutomat.listeVerkäufe()[1]));
	}

	private String cutDate(String date){
		return date.substring(0, date.indexOf(':') - 3);
	}

	@Test
	public void testWaehleProdukt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaehleOption() {
		fail("Not yet implemented");
	}

	@Test
	public void testFordereWechselgeld() {
		testAutomat.testKonfiguration();
		testAutomat.waehleProdukt(Produkt.KAFFEE);
		testAutomat.bezahleBetrag(Produkt.KAFFEE.getPreis() + 1000);
		assertEquals(1000, testAutomat.zapfeProdukt(), delta);
	}

	@Test
	public void testKeineOptionen(){
		testAutomat.waehleProdukt(Produkt.KAKAO);
		testAutomat.waehleOption("heißer");
		testAutomat.bezahleBetrag(Produkt.KAKAO.getPreis() + 1000);
		assertEquals(1000, testAutomat.zapfeProdukt(), delta);

		testAutomat.waehleProdukt(Produkt.KAKAO);
		testAutomat.bezahleBetrag(Produkt.KAKAO.getPreis() + 1000);
		assertEquals(1000 + Produkt.KAKAO.getPreis(), testAutomat.zapfeProdukt(), delta);
	}

	@Test
	public void testGetUmsatz() {
		fail("Not yet implemented");
	}

	@Test
	public void testListeVerkäufe() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbbruch() {
		testBezahleBetrag();
		testAutomat.waehleProdukt(Produkt.KAKAO);
		testAutomat.waehleOption("heißer");
		testAutomat.abbruch();
		testBezahleBetrag();
	}

}
