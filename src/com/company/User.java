package com.company;

import java.util.*;

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

    public void showCards (){
        for (int i = 1; i <= cards.size(); i++){
            System.out.print(i + "." + cards.get(i - 1).getType() + "  ") ;
        }
        System.out.println() ;
    }


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

    public void privateAttack (GroupForce attacker, GroupForce defender, GameField gameField){
        int distance = setDistance(defender.getX(), defender.getY(), attacker.getX(), attacker.getY()) ;
        int allowedNumberOfRolling = 0 ;
        switch (distance){
            case 1 :
                allowedNumberOfRolling = 3 ;
                break ;
            case 2 :
                allowedNumberOfRolling = 2 ;
                break ;
            case 3 :
                allowedNumberOfRolling = 1 ;
                break ;
        }
        switch (gameField.getField()[defender.getY()][defender.getY()].getType()){
            case HILL :
                allowedNumberOfRolling -- ;
                break ;
            case JUNGLE :
                allowedNumberOfRolling -- ;
                break ;
            case CITY :
                allowedNumberOfRolling -- ;
                break ;
            case SHELTER :
                if ((attacker.toString().charAt(0) + "").equals("h")){
                    allowedNumberOfRolling -- ;
                }
                break ;
            default :
                break ;
        }

        if (allowedNumberOfRolling <= 0){
            System.out.println("You are not allowed to roll a dice") ;
        }
        else {
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

    public void tankAttack (GroupForce attacker, GroupForce defender, GameField gameField){
        int distance = setDistance(defender.getX(), defender.getY(), attacker.getX(), attacker.getY()) ;
        int allowedNumberOfRolling = 3 ;
        switch (gameField.getField()[defender.getY()][defender.getY()].getType()){
            case HILL :
                allowedNumberOfRolling -- ;
                break ;
            case JUNGLE :
                allowedNumberOfRolling -= 2 ;
                break ;
            case CITY :
                allowedNumberOfRolling -= 2 ;
                break ;
            case SHELTER :
                if ((attacker.toString().charAt(0) + "").equals("h")){
                    allowedNumberOfRolling -= 2 ;
                }
                break ;
            default :
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

    public void artilleryAttack (GroupForce attacker, GroupForce defender, GameField gameField){
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
                        privateAttack(attacker, defender, gameField) ;
                        break ;
                    }
                }
                if (attacker instanceof GroupTank){
                    if (distance > 3){
                        System.out.println("Your distance from your enemy is more than three. choose another enemy") ;
                    }
                    else {
                        tankAttack(attacker, defender, gameField) ;
                        break ;
                    }
                }
                if (attacker instanceof GroupArtillery){
                    if (distance > 6){
                        System.out.println("Your distance from your enemy is more than six. choose another enemy") ;
                    }
                    else {
                        artilleryAttack(attacker, defender, gameField) ;
                        break ;
                    }
                }
            }
        }
    }




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
