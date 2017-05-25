package automat;

public enum Produkt {
	KAFFEE(1.2f), TEE(1f), KAKAO(1f);

	private String[] optionen;
	private final float preis;

	private Produkt(float preis) {
        this.preis = preis;
    }

    public float getPreis() {
        return preis;
    }

    public void addOption(String newOpt){
    	String[] opt = new String[optionen.length + 1];
    	for (int i = 0; i < optionen.length; i++) {
			opt[i] = optionen[i];
		}
    	opt[optionen.length] = newOpt;
    }

    public String[] getOptionen(){
    	return optionen.clone();
    }
}
