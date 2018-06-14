package covfefe;

/**
 * Vorlage zur Erzeugung von Objekten, die verschiedene E-Reihen repr�sentieren
 * @author Marc
 * @version 13.06.2018
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
	public int getItems(){
		return items;
	}
	
	/**
	* R�ckgabe der Toleranz der E-Reihen 
	* @return Toleranz der E-Reihe
	*/
	public double getTolerance(){
		return tolerance;
	}
	
	/**
	* R�ckgabe der E-Reihen-Toleranz als Zeichenfolge 
	* @return Toleranz der E-Reihe
	*/
	public String getToleranceAsString(){
		return tolerance + " %";
	}
	
	/**
	* R�ckgabe der Werte der E-Reihe als Array
	* @return alle Werte der E-Reihe als Array
	*/
	public double[] getValues(){
		return values;
	}
	
	/**
	* R�ckgabe eines bestimmten Werts der E-Reihe 
	* @param index Element der E-Reihe
	* @return bestimmter Wert der E-Reihe
	*/
	public double getValue(int index){
		return values[index];
	}
	
	private void calculateValues(){
		values = new double[items];
		
	}
	
	/**
	* R�ckgabe des Namens der E-Reihe 
	* @return Name der E-Reihe
	*/
	public String getName(){
		return "";
	}
	
	/**
	* R�ckgabe aller Attribute und Eigenschaften 
	*/
	public String toString(){
		return "";
	}
}