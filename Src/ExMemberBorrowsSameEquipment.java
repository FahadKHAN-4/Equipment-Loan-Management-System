
public class ExMemberBorrowsSameEquipment extends Exception {

    public ExMemberBorrowsSameEquipment() {
        super("The member is currently borrowing a set of this equipment. He/she cannot borrow one more at the same time.");
    }

    public ExMemberBorrowsSameEquipment(String message) {
        super(message);
    }

}
