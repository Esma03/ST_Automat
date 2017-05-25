package automat;

import java.util.LinkedList;

public class AutomatenSteuerung implements KaffeeAutomat {
	static AutomatenSteuerung instance;
	Produkt auswahl;
	int geld;

	private AutomatenSteuerung() {
	}

	static AutomatenSteuerung getInstance() {
		if (instance == null) {
			instance = new AutomatenSteuerung();
		}
		return instance;
	}

	@Override
	public void bezahleBetrag(int geld) {
		this.geld += geld;
	}

	@Override
	public void waehleProdukt(Bezeichnung bez) {
		switch (bez) {
		case KAKAO:
			auswahl = new Kakao();
			break;
		case KAFFEE:
			auswahl = new Kaffee();
			break;
		case TEE:
			auswahl = new Tee();
			break;
		}
	}

	@Override
	public void waehleOption(String option) {
		auswahl.optionen[0] = option;
	}

	@Override
	public float fordereWechselgeld() {
		float temp = geld;
		geld = 0;
		return temp;
	}

	@Override
	public float zapfeProdukt() {
		Protokoll p = Protokoll.getInstance();
		p.notiere(auswahl);
		float temp = geld;
		geld = 0;
		return temp;
	}

	float getUmsatz(){
		return Protokoll.getInstance().umsatz;
	}

	String[] listeVerkäufe(){
		LinkedList<Protokoll.VerkaufsEreignis> prot = Protokoll.getInstance().protokollListe;
		String[] s = new String[prot.size()];
		for (Protokoll.VerkaufsEreignis vE : prot) {
			int i = s.length;
			s[i] = vE.produktbezeichnung + " (" + vE.verkaufspreis + "EURO) um: " + vE.datum;
		}
		return s;
	}

	@Override
	public float abbruch() {
		auswahl = null;
		float temp = geld;
		geld = 0;
		return temp;
	}
}
