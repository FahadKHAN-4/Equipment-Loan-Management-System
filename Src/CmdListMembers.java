

public class CmdListMembers implements Command {

    @Override
    public void execute(String[] cmdPart) {
        Club club = Club.getInstance();
        club.listClubMembers();
        
    }



}
