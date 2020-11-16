package com.company;

public class GroupArtillery extends GroupForce {

    public GroupArtillery (User user, int number, int x, int y){
        super(user, number, 2, x, y) ;
    }

    @Override
    public String toString() {
        return "" + getUser().getLetter() + getUnitsNumber() + "A" + getNumber() ;
    }

    @Override
    public boolean isCanAttack() {
        return super.isCanAttack();
    }

    @Override
    public void setCanAttack(boolean canAttack) {
        super.setCanAttack(canAttack);
    }

    @Override
    public User getUser() {
        return super.getUser();
    }

    @Override
    public int getNumber() {
        return super.getNumber();
    }

    @Override
    public int getUnitsNumber() {
        return super.getUnitsNumber();
    }

    @Override
    public void setUnitsNumber(int unitsNumber) {
        super.setUnitsNumber(unitsNumber);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

}
