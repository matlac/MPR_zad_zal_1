package domain;

public class RolesPermissions extends Entity implements IHaveId {

    private int id;
    private String permName;
    private int permType; // 1 allow, 0 forbidden

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String perm_name) {
        this.permName = perm_name;
    }

    public int getPermType() {
        return permType;
    }

    public void setPermType(int perm_type) {
        this.permType = perm_type;
    }
}
