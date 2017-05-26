package automat;

import java.util.LinkedList;

public class AutomatenSteuerung implements KaffeeAutomat {
	static AutomatenSteuerung instance;
	Produkt auswahl;
	int geld;

	private AutomatenSteuerung() {
	}

	public static AutomatenSteuerung getInstance() {
		if (instance == null) {
			instance = new AutomatenSteuerung();
		}
		Status.start();
		return instance;
	}

	@Override
	public void bezahleBetrag(int geld) {
			this.geld += geld;
	}

	@Override
	public void waehleProdukt(Produkt bez) {
		if (Status.setState("Auswahl")) {
			auswahl = bez;
			Status.nextState();
		}
	}

	@Override
	public void waehleOption(String option) {
		if (Status.setState("Optionen")) {
			auswahl.addOption(option);
			Status.nextState();
		}
	}

	@Override
	public int fordereWechselgeld() {
		if (Status.setState("Abbruch")) {
			int temp = geld;
			geld = 0;
			Status.nextState();
			return temp;
		}
		return 0;
	}

	@Override
	public int zapfeProdukt() {
		if (Status.setState("Ausgabe")) {
			if (auswahl == Produkt.KAKAO) {
				if (auswahl.getOptionen().length == 0) {
					System.out.println("keine Option ausgewählt. Aktion wird abgebrochen.");
					return abbruch();
				}
			}
			if (auswahl.getPreis() <= geld) {
				Protokoll p = Protokoll.getInstance();
				p.notiere(auswahl);
				geld = geld - auswahl.getPreis();
				System.out.print("AUSGABE: ");
				for (String opt : auswahl.getOptionen()) {
					System.out.print(opt + " ");
				}
				System.out.println(auswahl);
				auswahl = null;
			}
			Status.nextState();
		}
		return fordereWechselgeld();
	}

	int getUmsatz() {
		return Protokoll.getInstance().umsatz;
	}

	public String[] listeVerkäufe() {
		LinkedList<Protokoll.VerkaufsEreignis> prot = Protokoll.getInstance().protokollListe;
		String[] s = new String[prot.size()];
		int i = 0;
		for (Protokoll.VerkaufsEreignis vE : prot) {
			s[i++] = vE.produktbezeichnung + " (" + vE.verkaufspreis + "EURO) um: " + vE.datum;
		}
		return s;
	}

	@Override
	public int abbruch() {
		Status.abbruch();
		auswahl = null;
		Status.start();
		return fordereWechselgeld();
	}

	public void testKonfiguration() {
		LinkedList<Protokoll.VerkaufsEreignis> prot = Protokoll.getInstance().protokollListe;
		while (!prot.isEmpty()) {
			prot.removeFirst();
		}
		fordereWechselgeld();
		Protokoll.getInstance().umsatz = 0;
		Status.start();
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
