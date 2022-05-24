

public class EquipmentSet {

    private int setNumber ;
    private String eCode;
    private String setName;
    private boolean borrowed; // false = avaiable, true = borrowed
    private String borrowerID = "";
    private String status;

    public EquipmentSet(Equipment eq) {
        this.eCode = eq.getECode();
        this.status = "";

        setNumber = eq.getSetNumber() + 1;
        eq.setSetNumber(setNumber);

        this.setName = eCode+"_"+setNumber;
        
        this.borrowed = false;
    }

    public boolean isBorrowed(){

        if(borrowed == true){
            return true;
        }else{
            return false;
        }

    }

    public void setBorrowed(boolean val){
        this.borrowed = val;
    }

    public String getSetName(){
        return this.setName;
    }

    public void setBorrower(String id) {
        this.borrowerID = id;
    }

    public String getSetNameAndBorrower(){
        return this.setName + "(" + this.borrowerID + ")";
    }

    public String getBorrowerId() {
        return this.borrowerID;
    }
    
    public String getEcode(){
        return this.eCode;
    }

    public void addStatus(String status){
        this.status = status;
    }

    public void printStatus(){
        if(this.status.equals("")){
            System.out.println("  "+ setName);
            System.out.println("    Current status: Available");
        }else{
            System.out.println("  "+ setName);
            System.out.println("    Current status: "+status);
        }
    }
}
