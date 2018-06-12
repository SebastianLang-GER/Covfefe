package test;

import covfefe.*;

public class UnitPrefixTest {
	public static void main(String[] args) {
		//Test Standardkonstruktor
		UnitPrefix up1 = new UnitPrefix();
		print(up1);
		
		//Test Konstruktor mit Festlegen der Zehnerpotenz
		UnitPrefix up2 = new UnitPrefix(3);
		print(up2);
		
		//Test aller Einheitenvorsätze
		for(int i = -6; i <= 6; i++) {
			System.out.println(i + ":");
			up2.setPrefix(i);
			print(up2);
		}
	}
	
	private static void print(UnitPrefix up) {
		System.out.println("Zehnerpotenz: " + up.getPower());
		System.out.println("Einheitenvorsatz: " + up.getPrefix());
	}
}