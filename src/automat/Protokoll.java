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

	public static Protokoll getInstance(){
		if(instance == null){
			instance = new Protokoll();
		}
		return instance;
	}

	void notiere(Produkt prod){
		VerkaufsEreignis ve = new VerkaufsEreignis();
		ve.produktbezeichnung = prod.name();
		ve.Optionen = prod.getOptionen();
		ve.verkaufspreis = prod.getPreis();
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

	public float getUmsatz() {
		return umsatz;
	}
}
