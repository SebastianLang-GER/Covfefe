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
		
		
	}
}