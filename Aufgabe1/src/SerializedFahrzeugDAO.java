/**
 * @author Mohammad Mahdi Fallah
 * Matrikelnummer: 01428941
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SerializedFahrzeugDAO implements FahrzeugDAO {

    private static SerializedFahrzeugDAO instance;
    private static List<Fahrzeug> fahrzeugen = new ArrayList<Fahrzeug>();
    private static File file;

    private SerializedFahrzeugDAO(){}

    public static SerializedFahrzeugDAO getInstance(String data) {
        if (instance != null) { return instance; }
        file = new File(data);
        try {
            if (!file.exists() || file.length() == 0) return new SerializedFahrzeugDAO();
            ObjectInputStream reader;
            reader = new ObjectInputStream(new FileInputStream(data));
            fahrzeugen = (List<Fahrzeug>)reader.readObject();
            reader.close();
        }
        catch (Exception e) {
            System.err.println("Fehler bei Deserialisierung: " + e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }
        return instance = new SerializedFahrzeugDAO();
    }

    private void serializeFahrzeugen() {
        try {
            if (file.exists()) file.delete();
            else file.createNewFile();
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file, true));
            writer.writeObject(fahrzeugen);
            writer.close();
        }
        catch (Exception e) {
            System.err.println("Fehler bei Serialisierung: " + e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }
    }

    @Override
    public List<Fahrzeug> getFahrzeugList() {
        return fahrzeugen;
    }

    @Override
    public Fahrzeug getFahrzeugbyId(int id) {
        for (Fahrzeug fz : fahrzeugen) {
            if (fz.getId() == id) return fz;
        }
        return null;
    }

    @Override
    public void speichereFahrzeug(Fahrzeug fz) {
        if (getFahrzeugbyId(fz.getId()) != null) throw new IllegalArgumentException("Error: Fahrzeug bereits vorhanden. (id=" + fz.getId() + ")");
        fahrzeugen.add(fz);
        serializeFahrzeugen();
    }

    @Override
    public void loescheFahrzeug(int id) {
        if (getFahrzeugbyId(id) == null) throw new IllegalArgumentException("Error: Fahrzeug nicht vorhanden. (id="+ id + ")");
        fahrzeugen.remove(getFahrzeugbyId(id));
        serializeFahrzeugen();
    }
}
