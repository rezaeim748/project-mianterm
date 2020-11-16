package com.company;

import java.util.Random ;

public class GroupArtillery extends GroupForce {
    private Random random = new Random() ;

    /**
     * Perform any initialization that is required
     * @param user The group force's user
     * @param number The group force's number
     * @param x The x coordinate of the group force
     * @param y The y coordinate of the group force
     */
    public GroupArtillery (User user, int number, int x, int y){
        super(user, number, 2, x, y) ;
    }



    // getters and setters

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

    /**
     * Represent a string instead of this group force
     * @return A String
     */
    @Override
    public String toString() {
        return "" + getUser().getLetter() + getUnitsNumber() + "A" + getNumber() ;
    }

    /**
     * Calculate the distance of two house in hexagonal map
     * @param xd x coordinate of the defender
     * @param yd y coordinate of the defender
     * @param xa x coordinate of the attacker
     * @param ya y coordinate of the attacker
     * @return An Int which stores the distance
     */
    @Override
    public int setDistance(int xd, int yd, int xa, int ya) {
        return super.setDistance(xd, yd, xa, ya);
    }

    /**
     * Attack the enemy
     * @param attacker The attacker
     * @param defender The defender
     * @param gameField The game field
     */
    @Override
    public void attack (GroupForce attacker, GroupForce defender, GameField gameField) {

        int distance = setDistance(defender.getX(), defender.getY(), attacker.getX(), attacker.getY()) ;
        int allowedNumberOfRolling = 0 ;
        switch (distance){
            case 1 :
            case 2 :
                allowedNumberOfRolling = 3 ;
                break ;
            case 3 :
            case 4 :
                allowedNumberOfRolling = 4 ;
                break ;
            case 5 :
            case 6 :
                allowedNumberOfRolling = 1 ;
                break ;
        }

        String defenderSpecifier = defender.toString().charAt(2) + "" ;
        boolean canAttack = false ;
        for (int i = 0; i < allowedNumberOfRolling; i++) {
            int diceNumber = random.nextInt(6) + 1;
            System.out.print("dice number: " + diceNumber + ";  ");
            switch (diceNumber) {
                case 1 :
                case 6 :
                    if (defenderSpecifier.equals("P")){
                        canAttack = true ;
                    }
                    break ;
                case 2 :
                    if (defenderSpecifier.equals("T")){
                        canAttack = true ;
                    }
                    break ;
                case 3 :
                case 4 :
                    break ;
                case 5 :
                    canAttack = true ;
                    break ;
            }
            if ((i == allowedNumberOfRolling - 1) && (!canAttack)){
                System.out.println("Unlucky!") ;
            }
            if (canAttack){
                break ;
            }
        }
        if (canAttack){
            System.out.println() ;
            defender.setUnitsNumber(defender.getUnitsNumber() - 1) ;
            if (defender.getUnitsNumber() == 0){
                gameField.getField()[defender.getX()][defender.getY()].setGroupForce(null) ;
                gameField.getField()[defender.getX()][defender.getY()].setEmpty(true) ;
                User user = defender.getUser() ;
                user.removeGroupForce(defender) ;
                attacker.getUser().addMedal() ;
            }
        }


    }


}
