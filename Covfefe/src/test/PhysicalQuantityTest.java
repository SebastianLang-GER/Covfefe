package test;

import covfefe.*;

public class PhysicalQuantityTest extends PhysicalQuantity {
	public PhysicalQuantityTest(String unit) {
		super(unit);
	}
	
	public PhysicalQuantityTest(String unit, boolean positiveOnly) {
		super(unit, positiveOnly);
	}

	public static void main(String[] args) {
		//Test Standardkonstruktor
		PhysicalQuantityTest pq1 = new PhysicalQuantityTest("A");
		
		//Test Konstruktor mit erweiterten Parametern
		PhysicalQuantityTest pq2 = new PhysicalQuantityTest("A", true);
		
		//Test Methoden setPrefix, getPrefix, getPower
		pq2.setPrefix(3);
		System.out.println("Sollwert: \tk \t\t| Istwert: \t" + pq2.getPrefix().getPrefix());
		System.out.println("Sollwert: \t3 \t\t| Istwert: \t" + pq2.getPrefix().getPower());

		//Test Methode getValue
		System.out.println("Sollwert: \t0.0 \t\t| Istwert: \t" + pq2.getValue());
		
		//Test Methode setValue
		pq2.setValue(123); //Test normale Zuweisung
		System.out.println("Sollwert: \t123.0 \t\t| Istwert: \t" + pq2.getValue());
		pq2.setValue(1230); //Test Einheitenvorsatzumformung
		System.out.println("Sollwerte: \t1.23 \t M \t| Istwerte: \t" + pq2.getValue() + " \t " + pq2.getPrefix().getPrefix());
		pq2.setValue(-234); //Test nur Positivzahlen
		System.out.println("Sollwert: \t234.0 \t\t| Istwert: \t" + pq2.getValue());
		pq2.setValue(345, new UnitPrefix(-3));
		System.out.println("Sollwerte: \t345.0 \t m \t| Istwerte: \t" + pq2.getValue() + " \t " + pq2.getPrefix().getPrefix());
		
		//Test Methode getPositiveOnly
		System.out.println("Sollwert: \ttrue \t\t| Istwert: \t" + pq2.getPositiveOnly());

		//Test Methode toString
		System.out.println("Sollwert: \t345.0 mA \t| Istwert: \t" + pq2.toString());
	}
}