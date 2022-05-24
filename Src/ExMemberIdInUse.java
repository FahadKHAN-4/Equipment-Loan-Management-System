public class ExMemberIdInUse extends Exception {

    private String memberAndID; 

    public ExMemberIdInUse(){
        super("Member ID already in use."); 
    }

    public String getNewMessage(){return "Member ID already in use: " + memberAndID;}

    public ExMemberIdInUse(String msg, String memberAndID){
        super(msg);
        this.memberAndID = memberAndID;
    }

}
