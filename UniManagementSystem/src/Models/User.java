package Models;

public class User {
    private final int id;
    private final String firstName;
    private final String lastName;

    public User(int id, String firstName, String lastName){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public int getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean limitReached(){
        return false;
    }
}
