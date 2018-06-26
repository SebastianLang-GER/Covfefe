package test;

import covfefe.*;

public class ResistorTest {
	public static void main(String[] args) {
		//Test der Methoden Resistor und toString
		Resistor r = new Resistor(new Resistance(), ESeries.E3);
		System.out.println("Wert (Sollte 1.0 Ohm und E3-Reihe sein): " + r.toString());
		
		//Test der Methode getResistance
		System.out.println("Rückagbe des Widerstandswerts (sollte 1.0 Ohm sein): " + r.getResistance());
		
		//Test der Methode setESeries
		r.setESeries(new ESeriesTemplate(6, 20));
		System.out.println("Wert (Sollte 1.0 Ohm und E6-Reihe sein): " + r.toString());
		
		//Test der Methode getESeries
		System.out.println("Wert (Sollte E6-Reihe + Toleranz + Werte sein): " + r.getESeries());
		
		//Test der Methode chooseNextGreaterResistor
		Resistance e = new Resistance();
		e.setValue(1.1);
		r.chooseNextGreaterResistor(e);
		System.out.println("Rückagbe des Widerstandswerts (sollte 1.5 Ohm sein): " + r.getResistance());
		
		//Test der Methode chooseNextSmallerResistor
		e.setValue(1.4);
		r.chooseNextSmallerResistor(e);
		System.out.println("Rückagbe des Widerstandswerts (sollte 1.0 Ohm sein): " + r.getResistance());
				
		//Test der Methode getDecimalFector
		e.setValue(100000);
		System.out.println("Rückagabe des Widerstandswerts (Sollte 100 kOhm sein): " + e.toString());
	}
}