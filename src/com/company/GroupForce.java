package com.company;

public abstract class GroupForce {

    private int number ;
    private int unitsNumber ;
    private User user ;
    private int x ;
    private int y ;
    private boolean canAttack ;


    /**
     * Perform any initialization that is required
     * @param user The group force's user
     * @param number The group force's number
     * @param unitsNumber The group force's units number
     * @param x The x coordinate of the group force
     * @param y The y coordinate of the group force
     */
    public GroupForce (User user, int number, int unitsNumber, int x, int y){
        this.user = user ;
        this.number = number ;
        this.unitsNumber = unitsNumber ;
        this.x = x ;
        this.y = y ;
        canAttack = true ;
    }

    // getters and setters

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
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

    /**
     * Calculate the distance of two house in hexagonal map
     * @param xd x coordinate of the defender
     * @param yd y coordinate of the defender
     * @param xa x coordinate of the attacker
     * @param ya y coordinate of the attacker
     * @return An Int which stores the distance
     */
    public int setDistance (int xd, int yd, int xa, int ya){
        int deltaX = Math.abs(xd - xa) ;
        int deltaY = Math.abs(yd - ya) ;
        int distance = 0 ;
        if (deltaX == deltaY){
            distance = deltaX ;
        }
        if (deltaX > deltaY){
            distance = deltaY + (deltaX - deltaY) / 2 ;
        }
        if (deltaY > deltaX){
            distance = deltaY ;
        }

        return distance ;

    }

    /**
     * Represent a string instead of this group force
     * @return A String
     */
    public abstract String toString () ;

    /**
     * Attack the enemy
     * @param attacker The attacker
     * @param defender The defender
     * @param gameField The game field
     */
    public abstract void attack (GroupForce attacker, GroupForce defender, GameField gameField) ;


}
