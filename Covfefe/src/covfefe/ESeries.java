package covfefe;

/**
 * Klasse mit der Funktion einer Bibliothek, in der alle E-Reihen enthalten sind
 * @author Marc Gebert
 * @version 13.06.2018
 */
public final class ESeries {
	
	public static final ESeriesTemplate E3 = new ESeriesTemplate(3, 20);
	public static final ESeriesTemplate E6 = new ESeriesTemplate(6, 20);
	public static final ESeriesTemplate E12 = new ESeriesTemplate(12, 10);
	public static final ESeriesTemplate E24 = new ESeriesTemplate(24, 5);
	public static final ESeriesTemplate E48 = new ESeriesTemplate(48, 2);
	public static final ESeriesTemplate E96 = new ESeriesTemplate(96, 1);
	public static final ESeriesTemplate E192 = new ESeriesTemplate(192, 0.5);
}