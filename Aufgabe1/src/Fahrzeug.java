/**
 * @author Mohammad Mahdi Fallah
 * Matrikelnummer: 01428941
 */

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Year;


public abstract class Fahrzeug implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String marke;
	private String modell;
	private int baujahr;
	private double grundpreis;

	public Fahrzeug(int id, String marke, String modell, int baujahr, double grundpreis) {
		if(marke.isEmpty() || modell.isEmpty() || grundpreis <= 0) throw new IllegalArgumentException("Error: Parameter ungueltig.");
		if(baujahr > getCurrentYear()) throw new IllegalArgumentException("Error: Baujahr ungueltig.");

		this.marke = marke;
		this.modell = modell;
		this.baujahr = baujahr;
		this.grundpreis = grundpreis;
		this.id = id;
	}

	public void setMarke(String marke) {
		if(marke.isEmpty()) throw new IllegalArgumentException("Error: Parameter ungueltig.");
		this.marke = marke;
	}

	public void setModell(String modell) {
		if(modell.isEmpty()) throw new IllegalArgumentException("Error: Parameter ungueltig.");
		this.modell = modell;
	}

	public void setBaujahr(int baujahr) {
		if(baujahr > getCurrentYear()) throw new IllegalArgumentException("Error: Baujahr ungueltig.");
		this.baujahr = baujahr;
	}

	public void setGrundpreis(double grundpreis) {
		if(grundpreis <= 0) throw new IllegalArgumentException("Error: Parameter ungueltig.");
		this.grundpreis = grundpreis;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarke() {
		return marke;
	}

	public String getModell() {
		return modell;
	}

	public int getBaujahr() {
		return baujahr;
	}

	public double getGrundpreis() {
		return grundpreis;
	}

	public int getId() {
		return id;
	}

	public int getAlter(){
		return getCurrentYear() - baujahr;
	}

	public double getPreis(){
		return (getGrundpreis() - (getGrundpreis() * getRabatt()));
	}


	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}

	private int getCurrentYear() {
		Year y = Year.now();
		return y.getValue();
	}

	abstract double getRabatt();
}
