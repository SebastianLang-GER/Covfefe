package test;

import covfefe.*;

public class InputParameterTest {
	
	private static final int runs = 5; //Anzahl der Testdurchläufe
	
	public static void main(String[] args) {
		InputParameter ip1 = new InputParameter();
		int errors = 0;
		boolean parameter;

		System.out.println("Testbeginn");
		for(int i = 1; i <= runs; i++) {
			System.out.println("\nTestdurchlauf " + i + ":");
			
			//Test: totalVoltage
			parameter = getRandomBoolean();
			ip1.setTotalVoltage(parameter);
			System.out.println("totalVoltage: \t\t\t" + parameter + "\t" + ip1.getTotalVoltage());
			if(parameter != ip1.getTotalVoltage()) errors++;
			
			//Test: voltage1
			parameter = getRandomBoolean();
			ip1.setVoltage1(parameter);
			System.out.println("voltage1: \t\t\t" + parameter + "\t" + ip1.getVoltage1());
			if(parameter != ip1.getVoltage1()) errors++;
			
			//Test: voltage2
			parameter = getRandomBoolean();
			ip1.setVoltage2(parameter);
			System.out.println("voltage2: \t\t\t" + parameter + "\t" + ip1.getVoltage2());
			if(parameter != ip1.getVoltage2()) errors++;
			
			//Test: totalResistor
			parameter = getRandomBoolean();
			ip1.setTotalResistor(parameter);
			System.out.println("totalResistor: \t\t\t" + parameter + "\t" + ip1.getTotalResistor());
			if(parameter != ip1.getTotalResistor()) errors++;
			
			//Test: resistor1
			parameter = getRandomBoolean();
			ip1.setResistor1(parameter);
			System.out.println("resistor1: \t\t\t" + parameter + "\t" + ip1.getResistor1());
			if(parameter != ip1.getResistor1()) errors++;
			
			//Test: resistor2
			parameter = getRandomBoolean();
			ip1.setResistor2(parameter);
			System.out.println("resistor2: \t\t\t" + parameter + "\t" + ip1.getResistor2());
			if(parameter != ip1.getResistor2()) errors++;
			
			//Test: minResistor1
			parameter = getRandomBoolean();
			ip1.setMinResistor1(parameter);
			System.out.println("minResistor1: \t\t\t" + parameter + "\t" + ip1.getMinResistor1());
			if(parameter != ip1.getMinResistor1()) errors++;
			
			//Test: minResistor2
			parameter = getRandomBoolean();
			ip1.setMinResistor2(parameter);
			System.out.println("minResistor2: \t\t\t" + parameter + "\t" + ip1.getMinResistor2());
			if(parameter != ip1.getMinResistor2()) errors++;
		}
		System.out.println("\nTest mit " + errors + " Fehlern beendet");
	}
	
	public static boolean getRandomBoolean() {
		return (Math.random() >= 0.5);
	}
}