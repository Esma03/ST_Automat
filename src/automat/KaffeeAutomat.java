package automat;

public interface KaffeeAutomat {

	void waehleOption(String option);

	int fordereWechselgeld();

	int zapfeProdukt();

	int abbruch();

	void waehleProdukt(Produkt bez);

	void bezahleBetrag(int geld);
}
