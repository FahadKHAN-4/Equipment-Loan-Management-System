import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Club {

    private ArrayList<Member> allMembers;	
    private static Club instance = new Club();

    private Club() { allMembers = new ArrayList<>(); }

	public static Club getInstance() { return instance ; }

	public void addMember(Member m) {// See how it is called in Member constructor (Step 3)
		allMembers.add(m);
		Collections.sort(allMembers); // allMembers
	}

	public void removeMember(Member m) {
		allMembers.remove(m);	
	}
	
	public void listClubMembers() {
		System.out.println(Member.getListingHeader()); // Member

		for (Member m: allMembers)
			System.out.println(m.toString()); // m or m.toString()
	}

	public Member getMember(Member member) {
		for (Member m: allMembers){
			if(m.compareTo(member) == 0){
				return m;
			}
		}
		return null;	
	}

	public Member getMember(String id){
		for (Member m: allMembers){
			if(m.getId().equals(id)){
				return m;
			}
		}
		return null;

	}

	public void listMemberStatus() {
		for (Member m: allMembers){
			m.listStatus();
			System.out.println();
		}
	}
    
}
