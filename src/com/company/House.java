package com.company;

public class House {

    private HouseType type ;
    private GroupForce groupForce ;
    private int x ;
    private int y ;
    private boolean isEmpty ;


    /**
     * Perform any initialization that is required
     * @param type Type of the house
     * @param x x coordinate of the house
     * @param y y coordinate of the house
     */
    public House (HouseType type, int x, int y){
        this.type = type ;
        this.x = x ;
        this.y = y ;
        isEmpty = true ;
    }

    // getters and setters

    public HouseType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GroupForce getGroupForce() {
        return groupForce;
    }

    public void setGroupForce(GroupForce groupForce) {
        this.groupForce = groupForce;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isEmpty (){return isEmpty ;}



}
