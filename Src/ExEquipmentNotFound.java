public class ExEquipmentNotFound extends Exception {

    public ExEquipmentNotFound() {
        super("Missing record for Equipment."); 
    }

    public ExEquipmentNotFound(String message) {
        super(message);
    }
    
}
