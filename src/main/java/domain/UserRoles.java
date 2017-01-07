package domain;

public class UserRoles extends Entity implements IHaveId {

    private int id;
    private int userId;

    @Override
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user){ this.id = user.getId(); }
    public void setUserId(int id){ this.userId = id; }
    public int getUserId(){ return userId; }

}
