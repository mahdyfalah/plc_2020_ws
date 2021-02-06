/**
 * @author Mohammad Mahdi Fallah
 * Matrikelnummer: 01428941
 */

import java.text.DecimalFormat;


public class Pkw extends Fahrzeug {

	private static final long serialVersionUID = 1L;

	private int letzesService;

	public Pkw(int id, String marke, String modell, int baujahr, double grundpreis, int letzesService) {
		super(id, marke, modell, baujahr, grundpreis);

		if (letzesService < baujahr) throw new IllegalArgumentException("Error: Servicejahr ungueltig.");
		this.letzesService = letzesService;
	}

	public void setLetzesService(int letzesService) {
		if (letzesService < getBaujahr()) throw new IllegalArgumentException("Error: Servicejahr ungueltig.");
		this.letzesService = letzesService;
	}

	public int getLetzesService() {
		return letzesService;
	}

	@Override
	double getRabatt() {
		double temp = (getAlter() * 0.05) + ((getBaujahr() - getLetzesService()) * 0.02);
		return Math.min(temp, 0.15);
	}

	@Override
	public String toString() {
		DecimalFormat df = getDecimalFormat();
		return 	"Typ:         " +  "PKW\n" +
				"Id:          " + getId() +  "\n" +
				"Marke:       " + getMarke() +  "\n" +
				"Modell:      " + getModell() +  "\n" +
				"Baujahr:     " + getBaujahr() +  "\n" +
				"Grundpreis:  " + Fahrzeug.getDecimalFormat().format(getGrundpreis())+  "\n" +
				"Servicejahr: " + getLetzesService() +  "\n" +
				"Preis:       " + Fahrzeug.getDecimalFormat().format((getGrundpreis()- (getGrundpreis() * getRabatt())))+  "\n";

	}
}
