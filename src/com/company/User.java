package com.company;

import java.util.Random ;
import java.util.ArrayList ;
import java.util.Scanner;

public class User {

    private String letter ;
    private ArrayList<Card> cards ;
    private ArrayList<GroupForce> groupForces ;
    private int medalOfConquest ;
    private int medalsOfDestruction ;
    private Random random ;

    public User (String letter){
        this.letter = letter ;
        cards = new ArrayList<>() ;
        groupForces = new ArrayList<>() ;
        medalOfConquest = 0 ;
        medalsOfDestruction = 0 ;
        random = new Random() ;
    }

    public String getLetter() {
        return letter ;
    }

    public int getMedalsNumber (){
        return medalOfConquest + medalsOfDestruction ;
    }

    public void addMedal (){
        medalsOfDestruction ++ ;
    }

    public void setMedalOfConquest (int number){
        medalOfConquest = number ;
    }

    public void addGroupForce (GroupForce groupForce){
        groupForces.add(groupForce) ;
    }

    public ArrayList<GroupForce> getGroupForces() {
        return groupForces;
    }

    public void addCard (Card card){
        cards.add(card) ;
    }

    public void removeCard (Card card){
        cards.remove(card) ;
    }

    public void showCards (){
        for (int i = 1; i <= cards.size(); i++){
            System.out.print(i + "." + cards.get(i - 1)) ;
        }
    }



    public int myParseInt (String string){
        try{
            return Integer.parseInt(string) ;
        }
        catch (Exception e){
            return -1 ;
        }

    }


    public boolean isNumberCorrect (int start, int finish, int input){
        for (int i = start; i <= finish; i++){
            if (i == input){
                return true ;
            }
        }
        return false ;
    }


    public int checkInput (Scanner reader, int start, int finish){

        int input ;
        while (true){
            String tempStr = reader.nextLine() ;
            input = myParseInt(tempStr) ;

            if (input == 0){
                break ;
            }
            if (!(isNumberCorrect(start, finish, input))){
                System.out.println("Please choose a number between " + start + " to " + finish) ;
            }
            else {
                break ;
            }
        }
        return input ;
    }





    public void getCommands (GameField gameField, ArrayList<Card> allCards, ArrayList<Card> usedCards){
        Scanner reader = new Scanner(System.in) ;

        System.out.println("User " + letter + " its your turn") ;
        System.out.println("Choose a card number") ;
        showCards() ;
        int cardNumber = checkInput(reader, 1, cards.size()) ;
        Card card = cards.get(cardNumber - 1) ;
        



    }









}
