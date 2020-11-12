package com.company;

import java.util.ArrayList;

public class User {

    private String letter ;
    private ArrayList<Card> cards ;
    private ArrayList<GroupForce> groupForces ;
    private int medalOfConquest ;
    private int medalsOfDestruction ;

    public User (String letter){
        this.letter = letter ;
        cards = new ArrayList<>() ;
        groupForces = new ArrayList<>() ;
        medalOfConquest = 0 ;
        medalsOfDestruction = 0 ;
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



}
