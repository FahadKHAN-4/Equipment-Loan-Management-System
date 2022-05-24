public class CmdRegister extends RecordedCommand {

    private Member m; 
    
    @Override
    public void execute(String[] cmdPart) {

       try{
            if (cmdPart.length<3)
                throw new ExInsufficientArguments();

            String id = cmdPart[1];
            String name = cmdPart[2];

            m = new Member(id, name);

            addUndoCommand(this);
            clearRedoList(); 
            System.out.println("Done.");
        }catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        }catch (ExMemberIdInUse e){
            System.out.println(e.getNewMessage());
        }
        
    }

    @Override
    public void undoMe() {
        Club club = Club.getInstance();
        club.removeMember(m);
        addRedoCommand(this);
        
    }

    @Override
    public void redoMe() {
        Club club = Club.getInstance();
        club.addMember(m);
        addUndoCommand(this);    
    }



}
