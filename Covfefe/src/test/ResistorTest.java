package test;

import covfefe.*;

public class ResistorTest {
	public static void main(String[] args) {
		//Test Konstruktor
		Resistor r = new Resistor(new Resistance(), ESeries.E3);
		System.out.println("Wert: \n" + r.getResistance());
	}
}