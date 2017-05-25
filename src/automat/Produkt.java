package automat;

import java.util.Arrays;

public class Produkt {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Produkt)) {
			return false;
		}
		Produkt other = (Produkt) obj;
		if (bezeichnung == null) {
			if (other.bezeichnung != null) {
				return false;
			}
		} else if (!bezeichnung.equals(other.bezeichnung)) {
			return false;
		}
		if (!Arrays.equals(optionen, other.optionen)) {
			return false;
		}
		if (Float.floatToIntBits(preis) != Float.floatToIntBits(other.preis)) {
			return false;
		}
		return true;
	}

	String bezeichnung;
	float preis;
	String[] optionen;

	public Produkt(String bezeichnung, float preis, String[] optionen) {
		this.bezeichnung = bezeichnung;
		this.preis = preis;
		this.optionen = optionen;
	}
}
