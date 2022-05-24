import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Equipment implements Comparable<Equipment>{

    private int setNumber = 0;
    String ECode;
    String name;
    private ArrayList<EquipmentSet> ESets;

    public Equipment(String eCode, String name)throws ExEquipmentCodeInUse {

        ECode = eCode;
        this.name = name;
        ESets = new ArrayList<>();

        Equipment eq = AllEquipment.getInstance().getEquipment(this.ECode); 

        if(eq != null){
            throw new ExEquipmentCodeInUse("Equipment code already in use: "+ ECode + " " + eq.getName()); 
        } else{
            AllEquipment.getInstance().addEquipment(this); 
        }
        
    }

    public String getName() {
        return this.name;
    }

    public EquipmentSet borrowAnItem(){

        for(EquipmentSet e: ESets){
            if(e.isBorrowed()){
                continue;
            }else{
                e.setBorrowed(true);
                return e;
            }
        }
        return null;
    }

    public void unborrowAnItem(String es) {
        
		for(EquipmentSet e: ESets){
            if(e.getSetName().equals(es)){
                e.setBorrowed(false);
                e.setBorrower("");
            }
        }
	}

    public void addSet(EquipmentSet es){
        ESets.add(es);
    }

    public void removeSet(EquipmentSet es){
        ESets.remove(es);
    }

    @Override
    public String toString() {
        //Learn: "-" means left-aligned

        if(ESets.size() == 0 || ESets.get(0).isBorrowed() == false){
            return String.format("%-5s%-17s%4d", ECode, name, ESets.size());

        }else{
            return String.format("%-5s%-17s%4d %s", ECode, name, ESets.size(), "(Borrowed set(s): " + toStringEquipmentSet() + ")"); 
        // equipment code, name, number of sets, borrowed sets"(Borrowed set(s): " + ____ + ")"
    }

    }

    private String toStringEquipmentSet() {
        
        String ans = "";
        boolean first = true;

        for(int i=0; i<ESets.size(); i++){
            if(ESets.get(i).getBorrowerId().equals("")){
                continue;
            }else{

                if(first == true){
                    ans += ESets.get(i).getSetNameAndBorrower();
                    first = false;
                }else{
                    ans += ", ";
                    ans += ESets.get(i).getSetNameAndBorrower();
                }
                
                
                // if(i == ESets.size()-1){
                //     continue;
                // }else{
                //     ans += ", ";
                // }
            }   
        }
        return ans;
    }

    public static String getListingHeader() {
        return String.format("%-5s%-17s%6s", "Code", "Name", "#sets");
    }

    @Override
    public int compareTo(Equipment another) {

        if (this.ECode.equals(another.ECode)) return 0;
        else if (this.ECode.compareTo(another.ECode)>0) return 1;
        else return -1;
    }

   
    public String getECode(){
        return this.ECode;
    }

	public int getSetNumber() {
		return this.setNumber;
	}

	public void setSetNumber(int setNumber2) {
        this.setNumber = setNumber2;
	}

    public Boolean checkAvailability() {

        boolean avail= true;
        for(EquipmentSet e: ESets){
            if(e.isBorrowed()){
                continue;
            }else{
                avail = false;
            }
        }
        return avail;
        
    }

    public void addStatus(String string, EquipmentSet eSet) {
        for(EquipmentSet es: ESets){
            if(es.getSetName().equals(eSet.getSetName())){
                es.addStatus(string);
            }  
        }
    }

    public void removeStatus(EquipmentSet eSet) {
        for(EquipmentSet es: ESets){
            if(es.getSetName().equals(eSet.getSetName())){
                es.addStatus("");
            }  
        }
	}

	public void listStatus() {
        System.out.println("["+ ECode +" " + name+ "]");

        if(ESets.size() == 0){
            System.out.println("  We do not have any sets for this equipment.");

        }else{
            for(EquipmentSet es : ESets){
                es.printStatus();
            }
        }
        
	}

	

	

    
}
