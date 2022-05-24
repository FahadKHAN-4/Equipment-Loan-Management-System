

public class CmdStartNewDay extends RecordedCommand {

    private String sOld, sNew;

    @Override
    public void execute(String[] cmdPart) {

        try{
            if (cmdPart.length<2)
                throw new ExInsufficientArguments();
         
            SystemDate sd = SystemDate.getInstance();

            sOld = sd.toString();
            sNew = cmdPart[1];

            sd.set(sNew);

            addUndoCommand(this);
            clearRedoList(); 
            System.out.println("Done.");
        } catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        } catch (ExInvalidDate e){
            System.out.println(e.getMessage());
        }
        
    }

    @Override
    public void undoMe() {
        SystemDate sd = SystemDate.getInstance();
        sd.set(sOld, "undo");
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        SystemDate sd = SystemDate.getInstance();
        try {
            sd.set(sNew);
        } catch (ExInvalidDate e) { 
            e.printStackTrace();
        } 
        addUndoCommand(this);
    }



}
