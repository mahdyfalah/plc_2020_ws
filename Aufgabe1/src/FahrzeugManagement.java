/**
 * @author Mohammad Mahdi Fallah
 * Matrikelnummer: 01428941
 */

import java.util.ArrayList;
import java.util.List;

public class FahrzeugManagement {
	FahrzeugDAO fahrzeugDAO;

	public FahrzeugManagement(String fileName){this.fahrzeugDAO = SerializedFahrzeugDAO.getInstance(fileName);}

	public List<Fahrzeug> getFahrzeugList(){return fahrzeugDAO.getFahrzeugList();}

	public Fahrzeug getFahrzeugbyId(int id){return fahrzeugDAO.getFahrzeugbyId(id);}

	public void addFahrzeug(Fahrzeug fahrzeug){fahrzeugDAO.speichereFahrzeug(fahrzeug);}

	public void deleteFahrzeug(int id){fahrzeugDAO.loescheFahrzeug(id);}

	public int numberOfObjects(){return (int) getFahrzeugList().size();}

	public int numberOfPKWs(){
	    int count = 0;
	    for (Fahrzeug fz : getFahrzeugList()){
	        if (fz instanceof Pkw) count++;
        }
	    return count;
    }

    public int numberOfLKWs(){
        int count = 0;
        for (Fahrzeug fz : getFahrzeugList()){
            if (fz instanceof Lkw) count++;
        }
        return count;
    }

	public int numberOfBus(){
		int count = 0;
		for (Fahrzeug fz : getFahrzeugList()){
			if (fz instanceof Bus) count++;
		}
		return count;
	}

    public double meanPreis(){
	    double sum = 0.0;
	    for (Fahrzeug fz: getFahrzeugList()) sum += fz.getPreis();
	    return getFahrzeugList().isEmpty()? sum : sum/getFahrzeugList().size();
    }

    public List<Integer> getOldest(){
	    int max = 0;
	    List<Integer> oldestList = new ArrayList<Integer>();

	    for (Fahrzeug fz: getFahrzeugList()){
	        if (fz.getAlter() > max) max = fz.getAlter();
        }

        for (Fahrzeug fz: getFahrzeugList()){
            if (fz.getAlter() == max) oldestList.add(fz.getId());
        }

	    return oldestList;
    }
	
}
