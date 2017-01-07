package domain;

public class EnumerationValue extends Entity implements IHaveId {

    private int id;
    private int intKey;
    private String stringKey;
    private String value;
    private String name;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntKey() {
        return intKey;
    }

    public void setIntKey(int intKey) {
        this.intKey = intKey;
    }

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
