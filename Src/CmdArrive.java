
public class CmdArrive extends RecordedCommand {

    Equipment eq;
    EquipmentSet es;

    @Override
    public void execute(String[] cmdPart) {
        try{

            String eCode = cmdPart[1];

            AllEquipment allEq = AllEquipment.getInstance();
            eq = allEq.getEquipment(eCode);

            if(eq == null){
                throw new ExEquipmentNotFound("Missing record for Equipment "+ eCode+".  Cannot mark this item arrival." );
            }else{
                es = new EquipmentSet(eq);
                eq.addSet(es);

                addUndoCommand(this);
                clearRedoList(); 
                System.out.println("Done.");
            }

        }catch(ExEquipmentNotFound e){
            System.out.println(e.getMessage());

        }
        
    }

    @Override
    public void undoMe() {
        eq.removeSet(es);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        eq.addSet(es);
        addUndoCommand(this);
    }

}
