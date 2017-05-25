package automat;
import java.util.Date;
import java.util.LinkedList;

public class Protokoll {
	float umsatz;
	LinkedList<VerkaufsEreignis> protokollListe;
	static Protokoll instance;

	private Protokoll() {
		protokollListe = new LinkedList<VerkaufsEreignis>();
	}

	static Protokoll getInstance(){
		if(instance == null){
			instance = new Protokoll();
		}
		return instance;
	}

	void notiere(Produkt prod){
		VerkaufsEreignis ve = new VerkaufsEreignis();
		ve.produktbezeichnung = prod.bezeichnung;
		ve.Optionen = prod.optionen.clone();
		ve.verkaufspreis = prod.preis;
		ve.datum = new Date();
		protokollListe.addLast(ve);
		umsatz += ve.verkaufspreis;
	}

	public class VerkaufsEreignis {
		String produktbezeichnung;
		String[] Optionen;
		Date datum;
		float verkaufspreis;
	}
}
