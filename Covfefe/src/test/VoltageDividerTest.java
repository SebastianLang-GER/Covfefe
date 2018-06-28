package test;

import covfefe.*;

public class VoltageDividerTest {

	public static void main(String[] args) {
		
		VoltageDivider test = new VoltageDivider();
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
		System.out.println(varr);

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
}


