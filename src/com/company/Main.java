package com.company;

public class Main {

    public static void addForcesToAllied (User allied){
        for (int i = 1; i <= 8; i++){
            allied.addGroupForce(new GroupPrivate(allied, i, 2 * i - 1, 9)) ;
        }
        for (int i = 1; i <= 3; i++){
            allied.addGroupForce(new GroupTank(allied, i, 3, 2 * i + 15, 9));
        }
        for (int i = 1; i <= 2; i++){
            allied.addGroupForce(new GroupArtillery(allied, i, 2 * i + 21, 9 ));
        }
    }

    public static void addForcesToAxis (User axis){
        for (int i = 1; i <= 7; i++){
            axis.addGroupForce(new GroupPrivate(axis, i, 2 * i - 1, 1));
        }
        for (int i = 1; i <= 6; i++){
            axis.addGroupForce(new GroupTank(axis, i, 4, 2 * i + 13, 1));
        }
    }





    public static void main(String[] args) {


        GameField gameField = new GameField() ;

        User user1 = new User("h") ;
        User user2 = new User("f") ;
        addForcesToAllied(user1) ;
        addForcesToAxis(user2) ;
        gameField.setForces(user1, user2) ;

        boolean finished = false ;
        while (!finished){
            user1.getCommands() ;
            if (finished){
                break ;
            }
            user2.getCommands() ;
        }






        gameField.showCoordinates() ;


    }
}
