package automat;
import java.util.Date;
import java.util.LinkedList;

public class Protokoll {
	int umsatz;
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
		if (prod.getOptionen() != null){
			ve.Optionen = prod.getOptionen();
		}
		ve.verkaufspreis = prod.getPreis();
		ve.datum = new Date();
		protokollListe.addLast(ve);
		umsatz += ve.verkaufspreis;
	}

	public class VerkaufsEreignis {
		String produktbezeichnung;
		String[] Optionen;
		Date datum;
		int verkaufspreis;
	}

	public int getUmsatz() {
		return umsatz;
	}
}
