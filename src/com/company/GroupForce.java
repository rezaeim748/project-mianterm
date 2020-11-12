package com.company;

public abstract class GroupForce {

    private int number ;
    private int unitsNumber ;
    private User user ;
    private int x ;
    private int y ;


    public GroupForce (User user, int number, int unitsNumber, int x, int y){
        this.user = user ;
        this.number = number ;
        this.unitsNumber = unitsNumber ;
        this.x = x ;
        this.y = y ;
    }

    public int getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    public int getUnitsNumber() {
        return unitsNumber;
    }

    public void setUnitsNumber(int unitsNumber) {
        this.unitsNumber = unitsNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public abstract String toString () ;

    public void move (){}

    public void attack (){}


}
