
public class ExInsufficientEquipment extends Exception {

    public ExInsufficientEquipment() {
        super("There is no available set of this equipment for the command.");
    }

    public ExInsufficientEquipment(String message) {
        super(message);
    }


}
