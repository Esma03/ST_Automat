package automat;

public enum Bezeichnung {
	KAFFEE(1.2f), TEE(1f), KAKAO(1f);

	private final float preis;

	private Bezeichnung(float preis) {
        this.preis = preis;
    }

    public float getPreis() {
        return preis;
    }
}
