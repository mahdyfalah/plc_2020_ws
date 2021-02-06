/**
 * @author Mohammad Mahdi Fallah
 * Matrikelnummer: 01428941
 */

import java.text.DecimalFormat;


public class Lkw extends Fahrzeug {

	private static final long serialVersionUID = 1L;

	public Lkw(int id, String marke, String modell, int baujahr, double grundpreis) {
		super(id, marke, modell, baujahr, grundpreis);
	}


	@Override
	double getRabatt() {
		double temp = getAlter() * 0.05;
		return Math.min(temp, 0.2);
	}

	@Override
	public String toString() {
		DecimalFormat df = getDecimalFormat();
		return 	"Typ:         " +  "LKW\n" +
				"Id:          " + getId() +  "\n" +
				"Marke:       " + getMarke() +  "\n" +
				"Modell:      " + getModell() +  "\n" +
				"Baujahr:     " + getBaujahr() +  "\n" +
				"Grundpreis:  " + Fahrzeug.getDecimalFormat().format(getGrundpreis()) +  "\n" +
				"Preis:       " + Fahrzeug.getDecimalFormat().format((getGrundpreis()- (getGrundpreis() * getRabatt())))+  "\n";

	}

}
