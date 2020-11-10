package model;

public class SimpleType extends Type{
    private String property;

    public SimpleType(){}
    public SimpleType(String property){
        this.property = property;
    }
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "SimpleType{" +
                "property='" + property + '\'' +
                '}';
    }
}
