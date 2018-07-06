package test;

import covfefe.*;

public class VoltageDividerTest {

	public static void main(String[] args) {
		
/*		VoltageDivider test = new VoltageDivider();
		InputParameter parameters = new InputParameter();
		int Wert = 0b000000000;
		int WertA = 0b0000000000;
		
		int e = 0;
		int var = 0;
		int varr = 0;
		
		for(int i = 0; i < 512; i++) {
			e = (int) (Wert & WertA);
			if (e == 1) {
				parameters.setRatio(true);
			}
			WertA = WertA << 1;
			e = (int) (Wert & WertA);
			if (e == 2) {
				parameters.setTotalVoltage(true);
			}
			WertA = WertA << 1;
			e = (int) (Wert & WertA);
			if (e == 4) {
				parameters.setVoltage1(true);
			}
			WertA = WertA << 1;
			e = (int) (Wert & WertA);
			if (e == 8) {
				parameters.setVoltage2(true);
			}
			WertA = WertA << 1;
			e = (int) (Wert & WertA);
			if (e == 16) {
				parameters.setTotalResistor(true);
			}
			WertA = WertA << 1;
			e = (int) (Wert & WertA);
			if (e == 32) {
				parameters.setResistor1(true);
			}
			WertA = WertA << 1;
			e = (int) (Wert & WertA);
			if (e == 64) {
				parameters.setResistor2(true);
			}
			WertA = WertA << 1;
			e = (int) (Wert & WertA);
			if (e == 128) {
				parameters.setMinResistor1(true);
			}
			WertA = WertA << 1;
			e = (int) (Wert & WertA);
			if (e == 256) {
				parameters.setMinResistor2(true);
			}
			
			if(test.isValidInput(parameters) == true) {
				System.out.print(Wert + " ist " + test.isValidInput(parameters) + "  ");
				var++;
				binaerDarstellenVonBits(Wert);
			}
				int counterVoltage = 0;
				int counterResistor = 0;
				int counterMinResistor = 0;
				int counterRatio = 0;
				
				if(parameters.getTotalVoltage() == true) counterVoltage++;
				if(parameters.getVoltage1() == true) counterVoltage++;
				if(parameters.getVoltage2() == true) counterVoltage++;
				
				if(parameters.getTotalResistor() == true) counterResistor++;
				if(parameters.getResistor1() == true) counterResistor++;
				if(parameters.getResistor2() == true) counterResistor++;
				
				if(parameters.getMinResistor1() == true) counterMinResistor++;
				if(parameters.getMinResistor2() == true) counterMinResistor++;
				
				if(parameters.getRatio() == true) counterRatio++;

				if ((counterRatio==1 && counterVoltage==1 && counterResistor==0 && counterMinResistor==0)||
					(counterRatio==1 && counterVoltage==1 && counterResistor==1 && counterMinResistor==0)||
					(counterRatio==1 && counterVoltage==1 && counterResistor==0 && (counterMinResistor==1 || counterMinResistor==2))||
					(counterRatio==0 && counterVoltage==2 && counterResistor==0 && counterMinResistor==0)||
					(counterRatio==0 && counterVoltage==2 && counterResistor==1 && counterMinResistor==0)||
					(counterRatio==0 && counterVoltage==2 && counterResistor==0 && (counterMinResistor==1 || counterMinResistor==2))||
					(counterRatio==0 && counterVoltage==1 && counterResistor==2 && counterMinResistor==0)) {
					System.out.println("alles korrekt");
					varr++;
				}
				
				if(Wert == 488) {
					System.out.println(counterRatio + " "  + counterVoltage + " "  + counterResistor + " "  + counterMinResistor);
					if(parameters.getMinResistor1() == true) System.out.println("1");
					if(parameters.getMinResistor2() == true) System.out.println("2");
				}
				
			
				 counterVoltage = 0;
				 counterResistor = 0;
				 counterMinResistor = 0;
				 counterRatio = 0;
			WertA = 0b000000001;
			Wert++;
			parameters.setRatio(false);	
			parameters.setTotalVoltage(false);		
			parameters.setVoltage1(false);		
			parameters.setVoltage2(false);		
			parameters.setTotalResistor(false);			
			parameters.setResistor1(false);			
			parameters.setResistor2(false);			
			parameters.setMinResistor1(false);
			parameters.setMinResistor2(false);	
			
		}
		System.out.println(var);
		System.out.println(varr);*/

		//Test der Methode calculate Values, da mit den InputParameters gesichert ist das nur richtige Eingaben berechnet werden, werden nur diese Ã¼berprÃ¼ft
		
		VoltageDivider VD = new VoltageDivider();
		System.out.println(VD.toString());
		System.out.println("- - - - - - - - - - - - - - - - - - - - -");
		
		
		
		InputParameter IP = new InputParameter();
		Voltage voltage = new Voltage();
		Resistance resistance = new Resistance();
		ESeriesTemplate EST = new ESeriesTemplate(24,5);
		Resistor resistor = new Resistor(resistance, EST);
		
		
		
		
		//1.: Ratio, 1 Voltage und kein Resistor gegeben
		System.out.println("1.1:");
		VD.setRatioResistor1toResistor2(5.0);
		IP.setRatio(true);
		voltage.setValue(10.1);
		VD.setVoltage(0 , voltage);
		IP.setVoltage1(true);
		überprüfen(VD, IP);
		IP.setVoltage1(false);
		
		System.out.println("1.2:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(2.02);
		VD.setVoltage(1, voltage);
		IP.setVoltage2(true);				
		überprüfen(VD, IP);
		IP.setVoltage2(false);
		
		System.out.println("1.3:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(12.12);
		VD.setTotalVoltage(voltage);
		IP.setTotalVoltage(true);				
		überprüfen(VD, IP);
		IP.setTotalVoltage(false);
		
		//2.: Ratio, 1 Voltage und 1 Resistor
		System.out.println("2.1:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(10.1);
		VD.setVoltage(0 , voltage);
		IP.setVoltage1(true);
		resistance.setValue(10);
		resistor.chooseResistor(resistance);
		IP.setResistor1(true);
		VD.setResistor(0, resistor);
		überprüfen(VD, IP);
		IP.setResistor1(false);
		
		System.out.println("2.2:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(10.1);
		VD.setVoltage(0 , voltage);
		IP.setVoltage1(true);
		resistance.setValue(2);
		resistor.chooseResistor(resistance);
		IP.setResistor2(true);
		VD.setResistor(1, resistor);
		überprüfen(VD, IP);
		IP.setResistor2(false);
		
		System.out.println("2.3:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(10.1);
		VD.setVoltage(0 , voltage);
		IP.setVoltage1(true);
		resistance.setValue(12);
		IP.setTotalResistor(true);
		VD.setTotalResistor(resistance);
		überprüfen(VD, IP);
		IP.setTotalResistor(false);
		IP.setVoltage1(false);
		
		System.out.println("2.4:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(2.02);
		VD.setVoltage(1 , voltage);
		IP.setVoltage2(true);
		resistance.setValue(10);
		resistor.chooseResistor(resistance);
		IP.setResistor1(true);
		VD.setResistor(0, resistor);
		überprüfen(VD, IP);
		IP.setResistor1(false);
		
		System.out.println("2.5:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(2.02);
		VD.setVoltage(1 , voltage);
		IP.setVoltage2(true);
		resistance.setValue(2);
		resistor.chooseResistor(resistance);
		IP.setResistor2(true);
		VD.setResistor(1, resistor);
		überprüfen(VD, IP);
		IP.setResistor2(false);
		
		System.out.println("2.6:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(2.02);
		VD.setVoltage(1 , voltage);
		IP.setVoltage2(true);
		resistance.setValue(12);
		IP.setTotalResistor(true);
		VD.setTotalResistor(resistance);
		überprüfen(VD, IP);
		IP.setTotalResistor(false);
		IP.setVoltage2(false);
		
		System.out.println("2.7:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(12.12);
		VD.setTotalVoltage(voltage);
		IP.setTotalVoltage(true);
		resistance.setValue(10);
		resistor.chooseResistor(resistance);
		IP.setResistor1(true);
		VD.setResistor(0, resistor);
		überprüfen(VD, IP);
		IP.setResistor1(false);
		
		System.out.println("2.8:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(12.12);
		VD.setTotalVoltage(voltage);
		IP.setTotalVoltage(true);
		resistance.setValue(2);
		resistor.chooseResistor(resistance);
		IP.setResistor2(true);
		VD.setResistor(1, resistor);
		überprüfen(VD, IP);
		IP.setResistor2(false);
		
		System.out.println("2.9:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(12.12);
		VD.setTotalVoltage(voltage);
		IP.setTotalVoltage(true);
		resistance.setValue(12);
		IP.setTotalResistor(true);
		VD.setTotalResistor(resistance);
		überprüfen(VD, IP);
		IP.setTotalResistor(false);
		IP.setTotalVoltage(false);
		
		//3.: Ratio, 1 Voltage und 1/2 Min Resistor
		System.out.println("3.1:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(10.1);
		VD.setVoltage(0 , voltage);
		IP.setVoltage1(true);
		resistance.setValue(10);
		IP.setMinResistor1(true);
		VD.setMinResistor(0, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor1(false);
		
		System.out.println("3.2:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(10.1);
		VD.setVoltage(0 , voltage);
		IP.setVoltage1(true);
		resistance.setValue(2);
		IP.setMinResistor2(true);
		VD.setMinResistor(1, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor2(false);
		
		System.out.println("3.3:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(10.1);
		VD.setVoltage(0 , voltage);
		IP.setVoltage1(true);
		resistance.setValue(10);
		IP.setMinResistor1(true);
		VD.setMinResistor(0, resistance);
		resistance.setValue(2);
		IP.setMinResistor2(true);
		VD.setMinResistor(1, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor1(false);
		IP.setMinResistor2(false);
		IP.setVoltage1(false);
		
		System.out.println("3.4:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(2.02);
		VD.setVoltage(1 , voltage);
		IP.setVoltage2(true);
		resistance.setValue(10);
		IP.setMinResistor1(true);
		VD.setMinResistor(0, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor1(false);
		
		System.out.println("3.5:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(2.02);
		VD.setVoltage(1 , voltage);
		IP.setVoltage2(true);
		resistance.setValue(2);
		IP.setMinResistor2(true);
		VD.setMinResistor(1, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor2(false);
		
		System.out.println("3.6:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(2.02);
		VD.setVoltage(1 , voltage);
		IP.setVoltage2(true);
		resistance.setValue(10);
		IP.setMinResistor1(true);
		VD.setMinResistor(0, resistance);
		resistance.setValue(2);
		IP.setMinResistor2(true);
		VD.setMinResistor(1, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor1(false);
		IP.setMinResistor2(false);
		IP.setVoltage2(false);
		
		System.out.println("3.7:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(12.12);
		VD.setTotalVoltage(voltage);
		IP.setTotalVoltage(true);
		resistance.setValue(10);
		IP.setMinResistor1(true);
		VD.setMinResistor(0, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor1(false);
		
		System.out.println("3.8:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(12.12);
		VD.setTotalVoltage(voltage);
		IP.setTotalVoltage(true);
		resistance.setValue(2);
		IP.setMinResistor2(true);
		VD.setMinResistor(1, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor2(false);
		
		System.out.println("3.9:");
		VD = new VoltageDivider();
		VD.setRatioResistor1toResistor2(5.0);
		voltage.setValue(12.12);
		VD.setTotalVoltage(voltage);
		IP.setTotalVoltage(true);
		resistance.setValue(10);
		IP.setMinResistor1(true);
		VD.setMinResistor(0, resistance);
		resistance.setValue(2);
		IP.setMinResistor2(true);
		VD.setMinResistor(1, resistance);
		überprüfen(VD, IP);
		IP.setMinResistor1(false);
		IP.setMinResistor2(false);
		IP.setTotalVoltage(false);
	}

	
	
	
	
	
	public static void binaerDarstellenVonBits (int zahl) {
		  
		  int maske = 0b000000001;
		  char[] bitfolge = new char[9];

		  for (int i = 0; i < 9; i++) 
		  {
		   bitfolge[8 - i] = (zahl & maske) == 0 ? '0' : '1';
		   maske = (int) (maske << 1);
		  }

		  System.out.println(bitfolge);
		 }	
	
	public static void überprüfen (VoltageDivider VD, InputParameter IP){	
		VD.isValidInput(IP);
		VD.calculateValues();
		if(VD.getRatioResistor1toResistor2() != 5) System.out.println("Fehler 1");
		if(VD.getResistor(0).getResistance().getValue() != 10) System.out.println("Fehler 2");
		if(VD.getResistor(1).getResistance().getValue() != 2) System.out.println("Fehler 3");
		if(VD.getVoltage(0).getValue() != 10.1) System.out.println("Fehler 4");
		if(VD.getVoltage(1).getValue() != 2.02) System.out.println("Fehler 5");		
	}
}


