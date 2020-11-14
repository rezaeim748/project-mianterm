package com.company;

import java.util.Random ;
import java.util.ArrayList;

public class Main {

    static Random random = new Random() ;

    private static void giveCardsToAllied (User allied, ArrayList<Card> cards){
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(cards.size());
            allied.addCard(cards.get(index));
            cards.remove(index);
        }
    }

    public static void giveCardsToAxis (User Axis, ArrayList<Card> cards){
        for (int i = 0; i < 2; i++){
            int index = random.nextInt(cards.size()) ;
            Axis.addCard(cards.get(index)) ;
            cards.remove(index) ;
        }
    }

    private static void makeCards (ArrayList<Card> cards){
        for (int i = 0; i < 6; i++){
            cards.add(new Card("1g")) ;
        }
        for (int i = 0; i < 13; i++){
            cards.add(new Card("2g")) ;
        }
        for (int i = 0; i < 10; i++){
            cards.add(new Card("3g")) ;
        }
        for (int i = 0; i < 6; i++){
            cards.add(new Card("4g")) ;
        }
        for (int i = 0; i < 5; i++){
            cards.add(new Card("3u")) ;
        }
    }

    private static void addForcesToAllied (User allied){
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

    private static void addForcesToAxis (User axis){
        for (int i = 1; i <= 7; i++){
            axis.addGroupForce(new GroupPrivate(axis, i, 2 * i - 1, 1));
        }
        for (int i = 1; i <= 6; i++){
            axis.addGroupForce(new GroupTank(axis, i, 4, 2 * i + 13, 1));
        }
    }





    public static void main(String[] args) {


        GameField gameField = new GameField() ;
        ArrayList<Card> cards = new ArrayList<>() ;
        ArrayList<Card> usedCards = new ArrayList<>() ;
        makeCards(cards) ;

        User user1 = new User("h") ;
        User user2 = new User("f") ;
        giveCardsToAllied(user1, cards) ;
        giveCardsToAxis(user2, cards) ;
        addForcesToAllied(user1) ;
        addForcesToAxis(user2) ;
        gameField.setForces(user1, user2) ;

        boolean finished = false ;
        while (!finished){
            finished = user1.getCommands(gameField, cards, usedCards, user2) ;
            if (finished){
                break ;
            }
            finished = user2.getCommands(gameField, cards, usedCards, user1) ;
        }




    }
}
