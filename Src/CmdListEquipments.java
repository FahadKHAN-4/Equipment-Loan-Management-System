public class CmdListEquipments implements Command {

    @Override
    public void execute(String[] cmdPart) {
        AllEquipment.getInstance().listAllEquipments();;
   
        
    }
    
}
