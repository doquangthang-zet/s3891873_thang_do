package vn.edu.rmit.group_project;

public abstract class TaxType {
    //Attributes
    private String name;
    private int amount;

    //Setter and getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
