import java.util.ArrayList;
import java.util.Collections;

public class Member implements Comparable<Member> {

  private String id;
  private String name;
  private Day joinDate;
  private ArrayList<EquipmentSet> borrowedEq;
  private ArrayList<String> memberStatus;

  public Member(String id, String name)throws ExMemberIdInUse {	

    this.name = name;
    this.id = id;
    this.joinDate = SystemDate.getInstance().clone();
    this.borrowedEq = new ArrayList<>();
    this.memberStatus = new ArrayList<>();

    Member m = Club.getInstance().getMember(this); 

    if(m != null){
        throw new ExMemberIdInUse("Member ID already in use.", m.toStringIDandName()); 
      } else{
        Club.getInstance().addMember(this); 
      }
  }

  public void addStatus(String status){
    this.memberStatus.add(status);
  }

  public void removeStatus(String status){

    memberStatus.remove((memberStatus.size()-1));
    // for(String s: memberStatus){
    //   if(s.equals(status)){
    //     memberStatus.remove(s);
    //   }
    // }
  }

  public EquipmentSet borrowItem(Equipment eq) throws ExMemberBorrowsSameEquipment{

    for(EquipmentSet es : borrowedEq){
      if(es.getEcode().equals(eq.getECode())){
        throw new ExMemberBorrowsSameEquipment();
      }
    }

    EquipmentSet e = eq.borrowAnItem();
    e.setBorrower(this.id);
    this.borrowedEq.add(e);

    return e;
    
  }

  public void unBorrowItem(Equipment eq, EquipmentSet es) {

    eq.unborrowAnItem(es.getSetName());
    this.borrowedEq.remove(es); 
  }

  public String toStringIDandName() {
    return id + " " + name;
  }

  @Override
  public String toString() {
    //Learn: "-" means left-aligned
    return String.format("%-5s%-9s%11s%7d%13d", id, name, joinDate, borrowedEq.size(), 0);
  }

  public static String getListingHeader() {
    return String.format("%-5s%-9s%11s%11s%13s", "ID", "Name", "Join Date ", "#Borrowed", "#Requested");
  }

  @Override
  public int compareTo(Member another) {
    if (this.id.equals(another.id)) return 0;
    else if (this.id.compareTo(another.id)>0) return 1;
    else return -1;
  }

  public String getId() {
    return this.id;
  }

public void listStatus() {

  System.out.println("["+ id +" " + name+ "]");

  if(memberStatus.size()==0){
    System.out.println("No record.");
  }else{

    ArrayList<String> sortedList = new ArrayList<>(memberStatus);

    Collections.sort(sortedList);
    
    for(String s: sortedList){
      System.out.println(s);
      }
  }



  
}


    
}
