import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class AllEquipment {

    private ArrayList<Equipment> allEquipments;	
    private static AllEquipment instance = new AllEquipment();

    private AllEquipment() { allEquipments = new ArrayList<>(); }

	public static AllEquipment getInstance() { return instance; }

	public void addEquipment(Equipment eq) {
		allEquipments.add(eq);
		Collections.sort(allEquipments); 
	}

	public void removeEquipment(Equipment eq) {
		allEquipments.remove(eq);	
	}
	
	public void listAllEquipments() {
		System.out.println(Equipment.getListingHeader()); 

		for (Equipment eq: allEquipments)
			System.out.println(eq.toString()); // m or m.toString()
	}

	public Equipment getEquipment(String eCode){

		for (Equipment eq: allEquipments){
			if(eq.getECode().equals(eCode)){
				return eq;
			}
		}
		return null;
	}

    public void listEquipmentStatus() {
		for (Equipment eq: allEquipments){
			eq.listStatus();
			System.out.println();
		}
    }
    
}
