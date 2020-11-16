package com.company;

import java.util.*;

public class User {

    private String letter ;
    private ArrayList<Card> cards ;
    private ArrayList<GroupForce> groupForces ;
    private int medalOfConquest ;
    private int medalsOfDestruction ;
    private Random random ;

    /**
     * Perform any initialization that is required
     * @param letter The letter which represents each user
     */
    public User (String letter){
        this.letter = letter ;
        cards = new ArrayList<>() ;
        groupForces = new ArrayList<>() ;
        medalOfConquest = 0 ;
        medalsOfDestruction = 0 ;
        random = new Random() ;
    }

    // getters and setters

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

    public void removeGroupForce (GroupForce groupForce){ groupForces.remove(groupForce) ; }

    public ArrayList<GroupForce> getGroupForces() {
        return groupForces;
    }

    public void addCard (Card card){
        cards.add(card) ;
    }

    public void removeCard (Card card){
        cards.remove(card) ;
    }

    /**
     * Show cards
     */
    public void showCards (){
        for (int i = 1; i <= cards.size(); i++){
            System.out.print(i + "." + cards.get(i - 1).getType() + "  ") ;
        }
        System.out.println() ;
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
     * Turn the given string to an integer
     * @param string The given string
     * @return An Integer
     */
    public int myParseInt (String string){
        try{
            return Integer.parseInt(string) ;
        }
        catch (Exception e){
            return -1 ;
        }

    }


    /**
     * check if the input is in a particular period or not
     * @param start The start point of the period
     * @param finish The finish point of the period
     * @param input The inout
     * @return A boolean
     */
    public boolean isNumberCorrect (int start, int finish, int input){
        for (int i = start; i <= finish; i++){
            if (i == input){
                return true ;
            }
        }
        return false ;
    }


    /**
     * Perform the attack process
     * @param reader The scanner which get input from user
     * @param attacker The attacker group force
     * @param gameField The game field
     * @param competitor The user competitor
     */
    public void checkInputToAttack (Scanner reader, GroupForce attacker, GameField gameField, User competitor){
        if (!attacker.isCanAttack()){
            System.out.println(attacker.toString() + " can not attack this round") ;
            return ;
        }
        System.out.print("write the group force name " + attacker.toString() + " want to attack: ") ;
        String attackerLetter = attacker.toString().charAt(0) + "" ;
        String defenderLetter ;
        if (attackerLetter . equals("h")){
            defenderLetter = "f" ;
        }
        else{
            defenderLetter = "h" ;
        }
        String defenderName ;
        while (true){
            defenderName = reader.nextLine() ;
            if (defenderName.equals("0")){
                break ;
            }
            GroupForce defender = competitor.isGroupForceValid(defenderName) ;
            if (defender == null){
                System.out.println("Please choose a valid group force") ;
            }
            else if (attackerLetter.equals(defenderName.charAt(0) + "")){
                System.out.println("You can not attack your own group force") ;
            }
            else {
                int distance = setDistance(defender.getX(), defender.getY(), attacker.getX(), attacker.getY()) ;
                if (attacker instanceof GroupPrivate){
                    if (distance > 3){
                        System.out.println("Your distance from your enemy is more than three. choose another enemy") ;
                    }
                    else {
                        attacker.attack(attacker, defender, gameField) ;
                        break ;
                    }
                }
                if (attacker instanceof GroupTank){
                    if (distance > 3){
                        System.out.println("Your distance from your enemy is more than three. choose another enemy") ;
                    }
                    else {
                        attacker.attack(attacker, defender, gameField) ;
                        break ;
                    }
                }
                if (attacker instanceof GroupArtillery){
                    if (distance > 6){
                        System.out.println("Your distance from your enemy is more than six. choose another enemy") ;
                    }
                    else {
                        attacker.attack(attacker, defender, gameField);
                        break ;
                    }
                }
            }
        }
    }


    /**
     * Perform the move process
     * @param reader The scanner which get inputs from user
     * @param groupForce the group force that user want to move
     * @param gameField The game field
     */
    public void checkInputToMove (Scanner reader, GroupForce groupForce, GameField gameField){
        System.out.print("write your movement description for " + groupForce.toString() + ": ") ;
        String temp ;
        String[] tempArr ;
        boolean canAttack = true ;

        while (true){
            while (true) {
                temp = reader.nextLine();
                tempArr = temp.split(" ", 6);
                if (!(temp.equals(""))){
                    break ;
                }
            }
            int deltaX = 0 ;
            int deltaY = 0 ;

            if (temp.equals("0")){
                groupForce.setCanAttack(true) ;
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
                          int distance = setDistance(x + deltaX, y + deltaY, x, y) ;
                          if (groupForce instanceof GroupPrivate){
                              if (distance <= 2){
                                  if ((distance == 2) || (gameField.getField()[x + deltaX][y + deltaY].getType() == HouseType.CITY) || (gameField.getField()[x + deltaX][y + deltaY].getType() == HouseType.JUNGLE)){
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
                                  if ((gameField.getField()[x + deltaX][y + deltaY].getType() == HouseType.CITY) || (gameField.getField()[x + deltaX][y + deltaY].getType() == HouseType.JUNGLE)){
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
                                  System.out.println("Tank con not move more than three steps") ;
                              }
                          }

                          if (groupForce instanceof GroupArtillery){
                              if (distance <= 1){
                                  if ((distance == 1) || (gameField.getField()[x + deltaX][y + deltaY].getType() == HouseType.CITY) || (gameField.getField()[x + deltaX][y + deltaY].getType() == HouseType.JUNGLE)){
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

        if (!canAttack){
            groupForce.setCanAttack(false) ;
        }
    }


    /**
     * Check if the given input is right or not
     * @param reader The scanner which get inputs from user
     * @param start The start point of the period
     * @param finish The finish point of the period
     * @return An Integer which stores the correct input
     */
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


    /**
     * Get the number of the groups of a particular type of the forces
     * @param typeNumber The number which refers to a type
     * @return An Integer
     */
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


    /**
     * Check if the given group force is valid or not
     * @param groupName The name of the group force
     * @return A GroupForce
     */
    public GroupForce isGroupForceValid (String groupName){
        for (GroupForce groupForce : groupForces){
            if (groupForce.toString().equals(groupName)){
                return groupForce ;
            }
        }
        return null ;
    }


    /**
     * Get the groups of a particular type of the forces
     * @param reader The scanner which gets inputs from user
     * @param card The Card
     * @return An ArrayList of the groups of the force
     */
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


    /**
     * Get the groups from all types of the forces
     * @param reader The scanner which gets inputs from user
     * @param card The card
     * @return An ArrayList of the groups of the forces
     */
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


    /**
     * Get the commands of the user
     * @param gameField The game field
     * @param allCards The cards
     * @param usedCards The used cards
     * @param competitor The competitor of the user
     * @return A boolean which shows the game is finished or not
     */
    public boolean getCommands (GameField gameField, ArrayList<Card> allCards, ArrayList<Card> usedCards, User competitor){
        Scanner reader = new Scanner(System.in) ;

        if (letter.equals("h")) {
            gameField.setForces(this, competitor) ;
        }
        else {
            gameField.setForces(competitor, this) ;
        }
        gameField.showCoordinates() ;
        System.out.println("User " + letter + " its your turn") ;
        System.out.println("Choose a card number") ;
        showCards() ;
        int cardNumber = checkInput(reader, 1, cards.size()) ;
        if (cardNumber == 0){
            return true ;
        }
        Card card = cards.get(cardNumber - 1) ;
        cards.remove(card) ;
        usedCards.add(card) ;
        Card alternativeCard = allCards.get(random.nextInt(allCards.size())) ;
        cards.add(alternativeCard) ;
        allCards.remove(alternativeCard) ;
        if (allCards.size() == 0){
            for (int i = 0; i < usedCards.size(); i++){
                Card x = usedCards.get(i) ;
                allCards.add(x) ;
                usedCards.remove(x) ;
            }
        }

        ArrayList<GroupForce> wantedGroupForcesToMove = new ArrayList<>() ;
        if (card.getType() == CardType.ORDER3UNITS){
            wantedGroupForcesToMove = getWantedGroupForcesOfAType(reader, card) ;
        }
        else {
            wantedGroupForcesToMove = getWantedGroupForcesOfTypes(reader, card) ;
        }
        for (GroupForce groupForce : wantedGroupForcesToMove){
            checkInputToMove(reader, groupForce, gameField) ;
        }

        if (letter.equals("h")) {
            gameField.setForces(this, competitor) ;
        }
        else {
            gameField.setForces(competitor, this) ;
        }
        gameField.showCoordinates() ;

        ArrayList<GroupForce> groupForcesToAttack = wantedGroupForcesToMove ;
        for (GroupForce groupForce : groupForcesToAttack){
            checkInputToAttack(reader, groupForce, gameField, competitor) ;
        }

        if (getMedalsNumber() == 6){
            if (letter.equals("h")){
                System.out.println("Allied won") ;
                System.out.println("scores: Allied = " + getMedalsNumber() + "; Axis = " + competitor.getMedalsNumber()) ;
                return true ;
            }
            else {
                System.out.println("Axis won") ;
                System.out.println("scores: Axis = " + getMedalsNumber() + "; Allied = " + competitor.getMedalsNumber()) ;
                return true ;
            }
        }

        return false ;







    }









}
