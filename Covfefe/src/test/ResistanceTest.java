package test;

import covfefe.Resistance;


public class ResistanceTest {
	public static void main(String[] args) {
		//Test Standardkonstruktor
		Resistance r = new Resistance();
		if (r.getUnit().equals("?")) {
			System.out.println("Einheit ist korrekt: " + r.getUnit());
		}
		else {
			System.out.println("Fehler: Einheit ist nicht korrekt: " + r.getUnit());
		}
	}
}

