package automat;

public class Status {
	private static String[] state = { "Abbruch", "Start", "Auswahl", "Optionen", "Ausgabe" };
	private static String currentState = "Start";

	public static void nextState() {
		currentState = state[(getIndex(currentState) + 1) % state.length];
	}

	public static boolean isLegalState(String state) {
		int i = getIndex(currentState);
		int j = getIndex(state);

		if ((i <= j && i > 2) || j == 0) {
			return true;
		} else {
			if(j - i == 1){
				return true;
			}
			return false;
		}
	}

	public static void abbruch() {
		currentState = state[0];
	}

	public static void start() {
		currentState = state[1];
	}

	private static int getIndex(String currentState) {
		for (int i = 0; i < state.length; i++) {
			if (state[i] == currentState) {
				return i;
			}
		}
		return -1;
	}

	public static boolean setState(String state) {
		if (isLegalState(state)) {
			currentState = state;
			return true;
		} else {
			return false;
		}
	}
}
