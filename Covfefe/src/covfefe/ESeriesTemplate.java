package covfefe;

/**
 * Klasse zur Erzeugung von Objekten, die verschiedene E-Reihen repr�sentieren
 * @author Marc Gebert
 * @version 14.06.2018
 */
public class ESeriesTemplate {

	private final int items;
	private final double tolerance;
	private double[] values;
	
	/**
	* Konstruktor zum Erzeugen von Objekten der Klasse ESeriesTemplate
	* @param items Anzahl der Elemente der E-Reihe
	* @param tolerance Toleranz der E-Reihe
	*/
	public ESeriesTemplate(int items, double tolerance) {
		this.items = items;
		this.tolerance = tolerance;
		calculateValues();
	}
	
	/**
	* R�ckgabe der Anzahl der Elemente der E-Reihe
	* @return Anzahl der Elemente der E-Reihe
	*/
	public int getItems() {
		return items;
	}
	
	/**
	* R�ckgabe der Toleranz der E-Reihen 
	* @return Toleranz der E-Reihe
	*/
	public double getTolerance() {
		return tolerance;
	}
	
	/**
	* R�ckgabe der E-Reihen-Toleranz als Zeichenfolge 
	* @return Toleranz der E-Reihe
	*/
	public String getToleranceAsString() {
		return tolerance + " %";
	}
	
	/**
	* R�ckgabe aller Werte der E-Reihe als Array
	* @return Alle Werte der E-Reihe als Array
	*/
	public double[] getValues() {
		return values;
	}
	
	/**
	* R�ckgabe eines bestimmten Werts der E-Reihe
	* @param index Element der E-Reihe
	* @return Angeforderter Wert der E-Reihe
	*/
	public double getValue(int index) {
		return values[index];
	}
	
	/**
	* R�ckgabe des Namens der E-Reihe
	* @return Name der E-Reihe
	*/
	public String getName() {
		return "E" + items + "-Reihe";
	}
	
	/**
	* R�ckgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette
	*/
	public String toString() {
		String properties = getName() + " (" + getToleranceAsString() + "):";
		for(double value:values) {
			properties += "\n" + value;
		}
		return properties;
	}
	
	private void calculateValues() {
		values = new double[items];
		//### Todo
	}
}