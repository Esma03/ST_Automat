package automat;

public interface KaffeeAutomat {

	void waehleOption(String option);

	float fordereWechselgeld();

	float zapfeProdukt();

	float abbruch();

	void waehleProdukt(Bezeichnung bez);

	void bezahleBetrag(float geld);
}
