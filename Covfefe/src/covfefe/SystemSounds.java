package covfefe;

import java.awt.Toolkit;

/**
 * Klasse zur Ausgabe von Systemsounds
 * @author Sebastian Lang
 * @version 05.07.2018
 */
public final class SystemSounds {

	/**
	 * Auflistung aller zur Verfügung stehenden Sounds
	 */
	public static enum Sound {
		Default,
		Close,
		Maximize,
		Minimize,
		MenuCommand,
		MenuPopup,
		Open,
		RestoreDown,
		RestoreUp,
		Asterisk,
		Exclamation,
		Exit,
		Hand,
		Question,
		Start
	}

	/**
	 * Methode zur Ausgabe eines Systemsounds
	 * @param sound Systemsound aus der Sound-Auflistung
	 */
	public static void play(Sound sound) {
		Runnable runnable = null;
		switch (sound) {
			case Asterisk:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.asterisk");
				break;
			case Close:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.close");
				break;
			case Default:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
				break;
			case Exclamation:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				break;
			case Exit:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exit");
				break;
			case Hand:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.hand");
				break;
			case Maximize:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.maximize");
				break;
			case MenuCommand:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.menuCommand");
				break;
			case MenuPopup:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.menuPopup");
				break;
			case Minimize:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.minimize");
				break;
			case Open:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.open");
				break;
			case Question:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.question");
				break;
			case RestoreDown:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.restoreDown");
				break;
			case RestoreUp:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.restoreUp");
				break;
			case Start:
				runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.start");
				break;
		}
		if (runnable != null) runnable.run();
	}
}