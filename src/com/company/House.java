package com.company;

public class House {

    private HouseType type ;
    private GroupForce groupForce ;
    private int x ;
    private int y ;
    private boolean isEmpty ;



    public House (HouseType type, int x, int y){
        this.type = type ;
        this.x = x ;
        this.y = y ;
        isEmpty = true ;
    }


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
