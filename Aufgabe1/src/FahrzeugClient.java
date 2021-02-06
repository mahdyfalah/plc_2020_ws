/**
 * @author <your-name-here>
 * Matrikelnummer: 01428941
 */

public class FahrzeugClient {

	public static void main(String[] args) {
		try{
			FahrzeugManagement fahrzeugManagement = new FahrzeugManagement(args[0]);

			switch (args[1]) {
				case "add" :
					if (args[2] == "pkw"){
						fahrzeugManagement.addFahrzeug(
								new Pkw(Integer.valueOf(args[3]), args[4], args[5], Integer.valueOf(args[6]),
										Double.valueOf(args[7]), Integer.valueOf(args[8])));
					}else if (args[2] == "lkw"){
						fahrzeugManagement.addFahrzeug(
								new Lkw(Integer.valueOf(args[3]), args[4], args[5], Integer.valueOf(args[6]),
										Double.valueOf(args[7])));
					}else if (args[2] == "Epkw"){
						fahrzeugManagement.addFahrzeug(
								new Epkw(Integer.valueOf(args[3]), args[4], args[5], Integer.valueOf(args[6]),
										Double.valueOf(args[7]), Integer.valueOf(args[8])));
					}
					else throw new IllegalArgumentException("Error: Parameter ungueltig.");
					break;
				case "show" :
					if (args.length == 2){
						for(Fahrzeug a: fahrzeugManagement.getFahrzeugList())
							System.out.println(a);
					} else if (fahrzeugManagement.getFahrzeugbyId(Integer.valueOf(args[2])) != null){
						System.out.println(fahrzeugManagement.getFahrzeugbyId(Integer.valueOf(args[2])));
					}
					else throw new IllegalArgumentException("Error: Fahrzeug nicht vorhanden. (id="+ Integer.valueOf(args[2]) + ")");
					break;
				case "del" :
					if (args.length == 3) fahrzeugManagement.deleteFahrzeug(Integer.valueOf(args[2]));
					break;
				case "count" :
					if (args.length == 2){
						System.out.println(fahrzeugManagement.numberOfObjects());
					} else if (args[2] == "lkw") {
						System.out.println(fahrzeugManagement.numberOfLKWs());
					} else if (args[2] == "pkw"){
						System.out.println(fahrzeugManagement.numberOfPKWs());
					} else if (args[2] == "bus"){
						System.out.println(fahrzeugManagement.numberOfBus());
					}else if (args[2] == "epkw"){
						System.out.println((int) fahrzeugManagement.getFahrzeugList().stream().filter(f->f instanceof Epkw).count());
					}
					break;
				case "meanprice" :
					if (args.length == 2) System.out.println(Fahrzeug.getDecimalFormat().format(fahrzeugManagement.meanPreis()));
					break;
				case "oldest" :
					if (args.length == 2){
						for (int id : fahrzeugManagement.getOldest())
							System.out.println("Id: " + id);
					}
					break;
				case "pricerange" :
					if (args.length == 4){
						for(Fahrzeug a: fahrzeugManagement.getFahrzeugList()){
							if (a.getPreis() > Integer.valueOf(args[3]) && a.getPreis() < Integer.valueOf(args[4]))
								System.out.println(a);
						}

					}
					break;

				default: throw new IllegalArgumentException("Error: Parameter ungueltig.");
			}

		}catch (NumberFormatException e) {
			System.out.println("Error: Parameter ungueltig.");
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println("Error: Parameter ungueltig.");
		}
	}
}