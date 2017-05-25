package automat;

public interface KaffeeAutomat {

	void waehleOption(String option);

	float fordereWechselgeld();

	float zapfeProdukt();

	float abbruch();

	void waehleProdukt(Produkt bez);

	void bezahleBetrag(float geld);
}
