package covfefe;

/**
 * Klasse zur Erzeugung von Objekten, die verschiedene E-Reihen repräsentieren
 * @author Marc Gebert, Dominik Thörmer, Tobias Vöth
 * @version 26.06.2018
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
	* Rückgabe der Anzahl der Elemente der E-Reihe
	* @return Anzahl der Elemente der E-Reihe
	*/
	public int getItems() {
		return items;
	}
	
	/**
	* Rückgabe der Toleranz der E-Reihen 
	* @return Toleranz der E-Reihe
	*/
	public double getTolerance() {
		return tolerance;
	}
	
	/**
	* Rückgabe der E-Reihen-Toleranz als Zeichenfolge 
	* @return Toleranz der E-Reihe
	*/
	public String getToleranceAsString() {
		return "±" + String.format(getTolerance() < 1 ? "%.1f" : "%.0f", tolerance) + " %";
	}
	
	/**
	* Rückgabe aller Werte der E-Reihe als Array
	* @return Alle Werte der E-Reihe als Array
	*/
	public double[] getValues() {
		return values;
	}
	
	/**
	* Rückgabe eines bestimmten Werts der E-Reihe
	* @param index Element der E-Reihe
	* @return Angeforderter Wert der E-Reihe
	*/
	public double getValue(int index) {
		return values[index];
	}
	
	/**
	* Rückgabe des Namens der E-Reihe
	* @return Name der E-Reihe
	*/
	public String getName() {
		return "E" + items + "-Reihe";
	}
	
	/**
	* Rückgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette
	*/
	public String toString() {
		String properties = getName() + " (" + getToleranceAsString() + "):";
		for(double value:values) {
			properties += "\n" + String.format(getTolerance() > 5 ? "%.1f" : "%.2f", value);
		}
		return properties;
	}
	
	/**
	 * Berechnung aller Werte der E-Reihe
	 */
	private void calculateValues() {
		values = new double[items];
		for(int i = 0; i < items; i++) {
			values[i] = roundToDecimals(Math.pow(Math.pow(10, i), 1.0 / items), getTolerance() >= 5 ? 1 : 2);
			if (items <= 24) {
				if (values[i] >= 2.6 && values[i] <= 4.7) {
					values[i] += 0.1;
					values[i] = roundToDecimals((values[i]), 1);
				}
				else if (values[i] == 8.3) {
					values[i] -= 0.1;
					values[i] = roundToDecimals((values[i]), 1);
				}
			}
			if (values[i] == 9.19) {
				values[i] += 0.01;
				values[i] = roundToDecimals(values[i], 2);
			}
		}
	}
	
	/**
	 * Gleitkommazahl auf eine bestimmte Anzahl von Nachkommastellen runden
	 * @param number Gleitkommazahl, die gerundet werden soll
	 * @param decimal Anzahl der Nachkommastellen
	 * @return Gerundete Gleitkommazahl
	 */
	private static double roundToDecimals(double number, int decimal) {
		return (int)(number * Math.pow(10, decimal)+0.5) / (double)Math.pow(10, decimal);  
	}
}