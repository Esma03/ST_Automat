package automat;

import java.util.LinkedList;

public class AutomatenSteuerung implements KaffeeAutomat {
	static AutomatenSteuerung instance;
	Produkt auswahl;
	float geld;

	private AutomatenSteuerung() {
	}

	public static AutomatenSteuerung getInstance() {
		if (instance == null) {
			instance = new AutomatenSteuerung();
		}
		return instance;
	}

	@Override
	public void bezahleBetrag(float geld) {
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
		if (auswahl.preis <= geld) {
			Protokoll p = Protokoll.getInstance();
			p.notiere(auswahl);
			geld = geld - auswahl.preis;
			geld = 0;
			auswahl = null;
		}
		return fordereWechselgeld();
	}

	float getUmsatz() {
		return Protokoll.getInstance().umsatz;
	}

	public String[] listeVerkäufe() {
		LinkedList<Protokoll.VerkaufsEreignis> prot = Protokoll.getInstance().protokollListe;
		String[] s = new String[prot.size()];
		int i = 0;
		for (Protokoll.VerkaufsEreignis vE : prot) {
			s[i++] = vE.produktbezeichnung + " (" + vE.verkaufspreis + "EURO) um: " + vE.datum;
		}
		System.out.println("Liste: " + s.length);
		return s;
	}

	@Override
	public float abbruch() {
		auswahl = null;
		return fordereWechselgeld();
	}

	public void testKonfiguration() {
		LinkedList<Protokoll.VerkaufsEreignis> prot = Protokoll.getInstance().protokollListe;
		while (!prot.isEmpty()) {
			prot.removeFirst();
		}
		fordereWechselgeld();
		Protokoll.getInstance().umsatz = 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutomatenSteuerung other = (AutomatenSteuerung) obj;
		if (auswahl == null) {
			if (other.auswahl != null)
				return false;
		} else if (!auswahl.equals(other.auswahl))
			return false;
		if (Float.floatToIntBits(geld) != Float.floatToIntBits(other.geld))
			return false;
		return true;
	}
}
