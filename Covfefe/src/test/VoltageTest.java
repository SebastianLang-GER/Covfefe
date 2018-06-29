package test;

import covfefe.*;

public class VoltageTest {
	public static void main(String[] args) {
		//Test Standardkonstruktor
		Voltage v = new Voltage();
		if (v.getUnit().equals("V")) {
			System.out.println("Einheit ist korrekt: " + v.getUnit());
		}
		else {
			System.out.println("Fehler: Einheit ist nicht korrekt: " + v.getUnit());
		}
	}
}