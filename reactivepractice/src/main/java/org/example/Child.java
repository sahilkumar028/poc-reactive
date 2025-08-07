package org.example;

abstract class Parent{
    private int money=1000;
    abstract public void money();
    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money=money;
    }
}


public class Child extends Parent{
    @Override
    public void money() {
            setMoney(getMoney()*10);
    }

    public static void main(String[] args) {
        Parent parent=new Child();
        parent.money();
        System.out.println(parent.getMoney());
    }
}
