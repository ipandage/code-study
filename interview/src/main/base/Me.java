package base;

public class Me {

    public Me(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    private String name;
    private Integer age;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
