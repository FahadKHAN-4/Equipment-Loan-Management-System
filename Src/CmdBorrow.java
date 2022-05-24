public class CmdBorrow extends RecordedCommand {

    Member m;
    Equipment eq;
    EquipmentSet eSet;

    String mStatus;
    String eStatus;

    @Override
    public void execute(String[] cmdPart) {

        try{
            if (cmdPart.length<3)
                throw new ExInsufficientArguments();

            String id = cmdPart[1];
            String eCode = cmdPart[2];
        
            Club cb = Club.getInstance();
            m = cb.getMember(id);

            if(m == null){
                throw new ExMemberNotFound();

            }else{
                AllEquipment equipments = AllEquipment.getInstance();
                eq = equipments.getEquipment(eCode);

                if(eq == null){
                    throw new ExEquipmentNotFound("Equipment record not found.");
                }
        
                SystemDate sd = SystemDate.getInstance();
                String sOld = sd.toString();
                String sNew;
                if(cmdPart.length == 3){
                    sNew = sd.nextDay(7);
                }else{
                    sNew = sd.nextDay(Integer.parseInt(cmdPart[3]));
                }
                

                boolean avail = eq.checkAvailability();
                if(avail == true){
                    throw new ExInsufficientEquipment();
                }
        
                eSet = m.borrowItem(eq);
                System.out.println(m.toStringIDandName() + " borrows " + eSet.getSetName()+ " (" +eq.getName()+") for " + sOld + " to "+ sNew);

                mStatus = "- borrows " + eSet.getSetName()+ " (" +eq.getName()+") for " + sOld + " to "+ sNew;
                m.addStatus(mStatus);

                eStatus = m.toStringIDandName() + " borrows " + " for " + sOld + " to "+ sNew;
                eq.addStatus(eStatus, eSet);
                
                addUndoCommand(this);
                clearRedoList(); 
                System.out.println("Done.");
            }

        }catch (ExInsufficientArguments e) {
                System.out.println(e.getMessage());
        }catch (ExMemberNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExEquipmentNotFound e) {
            System.out.println(e.getMessage()); 
        }catch (ExInsufficientEquipment e) {
            System.out.println(e.getMessage()); 
        } catch (ExMemberBorrowsSameEquipment e) {
            System.out.println(e.getMessage());
        }
   
    }

    @Override
    public void undoMe() {
        this.m.unBorrowItem(eq, eSet);
        m.removeStatus(mStatus);
        eq.removeStatus(eSet);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        try {
            eSet = m.borrowItem(eq);
            m.addStatus(mStatus);
            eq.addStatus(eStatus, eSet);
            
        } catch (ExMemberBorrowsSameEquipment e) {
            e.printStackTrace();
        }
        addUndoCommand(this);
    }
    
}
