package automat;

public enum Produkt {
	KAFFEE(120), TEE(100), KAKAO(90);

	private String[] optionen = new String[0];
	private final int preis;

	private Produkt(int preis) {
        this.preis = preis;
    }

    public int getPreis() {
        return preis + optionen.length * 10;
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
