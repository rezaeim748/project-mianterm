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
            System.out.print(i + "." + cards.get(i - 1).getType() + "  ") ;
        }
        System.out.println() ;
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


    public boolean checkInputToMove (Scanner reader, GroupForce groupForce, GameField gameField){
        System.out.print("write your movement description for " + groupForce.toString() + ": ") ;
        String temp ;
        String[] tempArr ;
        boolean canAttack = true ;

        while (true){
            temp = reader.nextLine() ;
            tempArr = temp.split(" ", 6) ;
            int deltaX = 0 ;
            int deltaY = 0 ;

            if (temp.equals("0")){
                break ;
            }

            boolean correctness = true ;
            for (int i = 0; i < tempArr.length; i++){
                switch ("" + tempArr[i].charAt(0)){
                    case "U" :
                    case "D" :
                        if (("" + tempArr[i].charAt(1)).equals("R") || ("" + tempArr[i].charAt(1)).equals("L")){
                            int diagonalMovement = myParseInt(tempArr[i].substring(2)) ;
                            if (!((diagonalMovement >= 1) && (diagonalMovement <= 8))){
                                correctness = false ;
                            }
                            else {
                                switch ("" + tempArr[i].charAt(0)){
                                    case "U" :
                                        switch ("" + tempArr[i].charAt(1)){
                                            case "R" :
                                                deltaX += diagonalMovement ;
                                                deltaY -= diagonalMovement ;
                                                break ;
                                            case "L" :
                                                deltaX -= diagonalMovement ;
                                                deltaY -= diagonalMovement ;
                                                break ;
                                        }
                                        break ;
                                    case "D" :
                                        switch ("" + tempArr[i].charAt(1)){
                                            case "R" :
                                                deltaX += diagonalMovement ;
                                                deltaY += diagonalMovement ;
                                                break ;
                                            case "L" :
                                                deltaX -= diagonalMovement ;
                                                deltaY += diagonalMovement ;
                                                break ;
                                        }
                                        break ;
                                }
                            }
                        }
                        else {
                            correctness = false ;
                        }
                        break ;
                    case "R" :
                    case "L" :
                        int horizontalMovement = myParseInt(tempArr[i].substring(1)) ;
                        if (!((horizontalMovement >= 1) && (horizontalMovement <= 12))){
                            correctness = false ;
                        }
                        else {
                            switch ("" + tempArr[i].charAt(0)){
                                case "R" :
                                    deltaX += horizontalMovement * 2 ;
                                    break ;
                                case "L" :
                                    deltaX -= horizontalMovement * 2 ;
                                    break ;
                            }
                        }
                        break ;

                    default :
                        correctness = false ;
                        break ;
                }
            }
            if (!correctness){
                System.out.println("The way that you declare movement was false. try again:") ;
            }

            else {
                int x = groupForce.getX() ;
                int y = groupForce.getY() ;

                if (!((x + deltaX >= 1) && (x + deltaX <= 25) && (y + deltaY >= 1) && (y + deltaY <= 9))){
                    System.out.println("This coordinate is out of map") ;
                }
                else {
                      if (gameField.getField()[x + deltaX][y + deltaY].getType() == HouseType.RIVER) {
                        System.out.println("The chosen house is river");
                      }
                      else if (!(gameField.getField()[x + deltaX][y + deltaY].isEmpty())) {
                        System.out.println("The chosen house is full");
                      }
                      else if ((gameField.getField()[x + deltaX][y + deltaY].getType() == HouseType.SHELTER) && ((groupForce instanceof GroupTank) || (groupForce instanceof GroupArtillery))) {
                        System.out.println("Tanks and artilleries cant go to shelters");
                      }
                      else {
                          int distance = (Math.abs(deltaX) / 2) + Math.abs(deltaY) ;
                          if (groupForce instanceof GroupPrivate){
                              if (distance <= 2){
                                  if (distance == 2){
                                      canAttack = false ;
                                  }
                                  groupForce.setX(x + deltaX);
                                  groupForce.setY(y + deltaY);
                                  gameField.getField()[x + deltaX][y + deltaY].setEmpty(false);
                                  gameField.getField()[x + deltaX][y + deltaY].setGroupForce(groupForce);
                                  gameField.getField()[x][y].setGroupForce(null);
                                  gameField.getField()[x][y].setEmpty(true);
                                  break;

                              }
                              else {
                                  System.out.println("Private can not move more than two steps") ;
                              }
                          }

                          if (groupForce instanceof GroupTank){
                              if (distance <= 3){
                                  groupForce.setX(x + deltaX);
                                  groupForce.setY(y + deltaY);
                                  gameField.getField()[x + deltaX][y + deltaY].setEmpty(false);
                                  gameField.getField()[x + deltaX][y + deltaY].setGroupForce(groupForce);
                                  gameField.getField()[x][y].setGroupForce(null);
                                  gameField.getField()[x][y].setEmpty(true);
                                  break;
                              }
                              else {
                                  System.out.println("Tank con not move more than three steps") ;
                              }
                          }

                          if (groupForce instanceof GroupArtillery){
                              if (distance <= 1){
                                  if (distance == 1){
                                      canAttack = false ;
                                  }
                                  groupForce.setX(x + deltaX);
                                  groupForce.setY(y + deltaY);
                                  gameField.getField()[x + deltaX][y + deltaY].setEmpty(false);
                                  gameField.getField()[x + deltaX][y + deltaY].setGroupForce(groupForce);
                                  gameField.getField()[x][y].setGroupForce(null);
                                  gameField.getField()[x][y].setEmpty(true);
                                  break;
                              }
                              else {
                                  System.out.println("Artillery can not move more than one step") ;
                              }
                          }

                      }
                }
            }

        }

        return canAttack ;
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


    public int getNumberOfTheGroupsOfAForce (int typeNumber){
        int number = 0 ;
        for (GroupForce groupForce1 : groupForces){
            if (typeNumber == 1){
                if (groupForce1 instanceof GroupPrivate){
                    number ++ ;
                }
            }
            if (typeNumber == 2){
                if (groupForce1 instanceof GroupTank){
                    number ++ ;
                }
            }
            if (typeNumber == 3){
                if (groupForce1 instanceof GroupArtillery){
                    number ++ ;
                }
            }
        }
        return number ;
    }


    public GroupForce isGroupForceValid (String groupName){
        for (GroupForce groupForce : groupForces){
            if (groupForce.toString().equals(groupName)){
                return groupForce ;
            }
        }
        return null ;
    }



    public ArrayList<GroupForce> getWantedGroupForcesOfAType (Scanner reader, Card card){
        ArrayList<GroupForce> wantedGroupForces = new ArrayList<>() ;
        System.out.println("1.Private  2.Tank  3.Artillery") ;
        System.out.print("Choose a number of the force you want to choose the groups from: ") ;
        int typeNumber = checkInput(reader, 1, 3) ;
        int numberOfGroupsOfAType = getNumberOfTheGroupsOfAForce(typeNumber) ;
        int groupsNumberToChoose = 3 ;
        if (numberOfGroupsOfAType == 0){
            System.out.println("You have no groups from this force") ;
            groupsNumberToChoose = 0 ;
        }
        else if (numberOfGroupsOfAType < 3){
            System.out.println("You have only " + numberOfGroupsOfAType + " groups from this force") ;
            groupsNumberToChoose = numberOfGroupsOfAType ;
        }

        for (int i = 0; i < groupsNumberToChoose; i++){
            System.out.print("write a group force name: ") ;
            String groupName ;
            GroupForce groupForce ;
            while (true){
                groupName = reader.nextLine() ;
                groupForce = isGroupForceValid(groupName) ;
                if (groupForce == null){
                    System.out.println("Please write a valid group force name") ;
                }
                else {
                    if (typeNumber == 1){
                        if (!(groupForce instanceof  GroupPrivate)){
                            System.out.println("write a group of private name") ;
                        }
                        else {
                            break ;
                        }
                    }
                    if (typeNumber == 2){
                        if (!(groupForce instanceof GroupTank)){
                            System.out.println("write a group of tank name") ;
                        }
                        else {
                            break ;
                        }
                    }
                    if (typeNumber == 3){
                        if (!(groupForce instanceof GroupArtillery)){
                            System.out.println("write a group of artillery name") ;
                        }
                        else {
                            break ;
                        }
                    }
                }

            }
            wantedGroupForces.add(groupForce) ;
        }

        return wantedGroupForces ;
    }



    public ArrayList<GroupForce> getWantedGroupForcesOfTypes (Scanner reader, Card card){
        ArrayList<GroupForce> wantedGroupForces = new ArrayList<>() ;
        int i = 0 ;
        switch (card.getType()){
            case ORDER1GROUP :
                i = 1 ;
                break ;
            case ORDER2GROUPS :
                i = 2 ;
                break ;
            case ORDER3GROUPS :
                i = 3 ;
                break ;
            case ORDER4GROUPS :
                i = 4 ;
                break ;
        }

        for (int j = 1; j <= i; j++){
            switch (j) {
                case 1 :
                    System.out.print("write the first group force name you want to move: ") ;
                    break ;
                case 2 :
                    System.out.print("write the second group force name you want to move: ") ;
                    break ;
                case 3 :
                    System.out.print("write the third group force name you want to move: ") ;
                    break ;
                case 4 :
                    System.out.print("write the fourth group force name you want to move: ") ;
                    break ;
            }

            String groupName ;
            GroupForce groupForce ;
            while (true){
                groupName = reader.nextLine() ;
                groupForce = isGroupForceValid(groupName) ;
                if (groupForce != null){
                    break ;
                }
                System.out.println("Please write a valid group force name") ;
            }

            wantedGroupForces.add(groupForce) ;

        }

        return wantedGroupForces ;

    }





    public void getCommands (GameField gameField, ArrayList<Card> allCards, ArrayList<Card> usedCards){
        Scanner reader = new Scanner(System.in) ;

        System.out.println("User " + letter + " its your turn") ;
        System.out.println("Choose a card number") ;
        showCards() ;
        int cardNumber = checkInput(reader, 1, cards.size()) ;
        Card card = cards.get(cardNumber - 1) ;
        ArrayList<GroupForce> wantedGroupForcesToMove = new ArrayList<>() ;
        if (card.getType() == CardType.ORDER3UNITS){
            wantedGroupForcesToMove = getWantedGroupForcesOfAType(reader, card) ;
        }
        else {
            wantedGroupForcesToMove = getWantedGroupForcesOfTypes(reader, card) ;
        }

        ArrayList<Boolean> canWantedGroupForcesAttack = new ArrayList<>() ;
        for (int i = 0; i < wantedGroupForcesToMove.size(); i++){
            canWantedGroupForcesAttack.add(checkInputToMove(reader, wantedGroupForcesToMove.get(i), gameField)) ;
        }





    }









}
