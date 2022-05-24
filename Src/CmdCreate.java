public class CmdCreate extends RecordedCommand {

    Equipment eq;

    @Override
    public void execute(String[] cmdPart) {
        
        try{
            if (cmdPart.length<3)
                throw new ExInsufficientArguments();

            String ECode = cmdPart[1];
            String name = cmdPart[2];
        
            eq = new Equipment(ECode, name);

            addUndoCommand(this);
            clearRedoList(); 
            System.out.println("Done.");

        }catch(ExInsufficientArguments e){
            System.out.println(e.getMessage());
        }catch(ExEquipmentCodeInUse e){
            System.out.println(e.getMessage());
        }   
        
    }

    @Override
    public void undoMe() {
        AllEquipment equipments = AllEquipment.getInstance();
        equipments.removeEquipment(eq);
        addRedoCommand(this);
        
    }

    @Override
    public void redoMe() {
        AllEquipment equipments = AllEquipment.getInstance();
        equipments.addEquipment(eq);
        addUndoCommand(this);
        
        
    }
    
}
