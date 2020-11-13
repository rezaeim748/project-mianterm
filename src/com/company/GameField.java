package com.company;

public class GameField {

    private House[][] field ;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";





    public GameField (){
        field = new House[26][10] ;
        setField() ;
    }

    private void setField (){

        field[1][1] = new House(HouseType.HILL, 1, 1) ;
        field[3][1] = new House(HouseType.HILL, 3, 1) ;
        field[5][1] = new House(HouseType.ORDINARY, 5, 1) ;
        field[7][1] = new House(HouseType.SHELTER, 7, 1) ;
        field[9][1] = new House(HouseType.BRIDGE, 9, 1) ;
        field[11][1] = new House(HouseType.ORDINARY, 11, 1) ;
        field[13][1] = new House(HouseType.ORDINARY, 13, 1) ;
        field[15][1] = new House(HouseType.ORDINARY, 15, 1) ;
        field[17][1] = new House(HouseType.ORDINARY, 17, 1) ;
        field[19][1] = new House(HouseType.ORDINARY, 19, 1) ;
        field[21][1] = new House(HouseType.ORDINARY, 21, 1) ;
        field[23][1] = new House(HouseType.ORDINARY, 23, 1) ;
        field[25][1] = new House(HouseType.ORDINARY, 25, 1) ;

        field[2][2] = new House(HouseType.ORDINARY, 2, 2) ;
        field[4][2] = new House(HouseType.RIVER, 4, 2) ;
        field[6][2] = new House(HouseType.RIVER, 6, 2) ;
        field[8][2] = new House(HouseType.RIVER, 8, 2) ;
        field[10][2] = new House(HouseType.SHELTER, 10, 2) ;
        field[12][2] = new House(HouseType.ORDINARY, 12, 2) ;
        field[14][2] = new House(HouseType.HILL, 14, 2) ;
        field[16][2] = new House(HouseType.ORDINARY, 16, 2) ;
        field[18][2] = new House(HouseType.ORDINARY, 18, 2) ;
        field[20][2] = new House(HouseType.ORDINARY, 20, 2) ;
        field[22][2] = new House(HouseType.ORDINARY, 22, 2) ;
        field[24][2] = new House(HouseType.ORDINARY, 24, 2) ;

        field[1][3] = new House(HouseType.CITY, 1, 3) ;
        field[3][3] = new House(HouseType.RIVER, 3, 3) ;
        field[5][3] = new House(HouseType.ORDINARY, 5, 3) ;
        field[7][3] = new House(HouseType.ORDINARY, 7, 3) ;
        field[9][3] = new House(HouseType.ORDINARY, 9, 3) ;
        field[11][3] = new House(HouseType.ORDINARY, 11, 3) ;
        field[13][3] = new House(HouseType.HILL, 13, 3) ;
        field[15][3] = new House(HouseType.ORDINARY, 15, 3) ;
        field[17][3] = new House(HouseType.ORDINARY, 17, 3) ;
        field[19][3] = new House(HouseType.JUNGLE, 19, 3) ;
        field[21][3] = new House(HouseType.ORDINARY, 21, 3) ;
        field[23][3] = new House(HouseType.ORDINARY, 23, 3) ;
        field[25][3] = new House(HouseType.JUNGLE, 25, 3) ;

        field[2][4] = new House(HouseType.BRIDGE, 2, 4) ;
        field[4][4] = new House(HouseType.JUNGLE, 4, 4) ;
        field[6][4] = new House(HouseType.ORDINARY, 6, 4) ;
        field[8][4] = new House(HouseType.JUNGLE, 8, 4) ;
        field[10][4] = new House(HouseType.ORDINARY, 10, 4) ;
        field[12][4] = new House(HouseType.ORDINARY, 12, 4) ;
        field[14][4] = new House(HouseType.ORDINARY, 14, 4) ;
        field[16][4] = new House(HouseType.ORDINARY, 16, 4) ;
        field[18][4] = new House(HouseType.ORDINARY, 18, 4) ;
        field[20][4] = new House(HouseType.ORDINARY, 20, 4) ;
        field[22][4] = new House(HouseType.ORDINARY, 22, 4) ;
        field[24][4] = new House(HouseType.JUNGLE, 24, 4) ;

        field[1][5] = new House(HouseType.RIVER, 1, 5) ;
        field[3][5] = new House(HouseType.JUNGLE, 3, 5) ;
        field[5][5] = new House(HouseType.ORDINARY, 5, 5) ;
        field[7][5] = new House(HouseType.ORDINARY, 7, 5) ;
        field[9][5] = new House(HouseType.ORDINARY, 9, 5) ;
        field[11][5] = new House(HouseType.HILL, 11, 5) ;
        field[13][5] = new House(HouseType.CITY, 13, 5) ;
        field[15][5] = new House(HouseType.ORDINARY, 15, 5) ;
        field[17][5] = new House(HouseType.JUNGLE, 17, 5) ;
        field[19][5] = new House(HouseType.ORDINARY, 19, 5) ;
        field[21][5] = new House(HouseType.CITY, 21, 5) ;
        field[23][5] = new House(HouseType.HILL, 23, 5) ;
        field[25][5] = new House(HouseType.JUNGLE, 25, 5) ;

        field[2][6] = new House(HouseType.ORDINARY, 2, 6) ;
        field[4][6] = new House(HouseType.ORDINARY, 4, 6) ;
        field[6][6] = new House(HouseType.ORDINARY, 6, 6) ;
        field[8][6] = new House(HouseType.JUNGLE, 8, 6) ;
        field[10][6] = new House(HouseType.HILL, 10, 6) ;
        field[12][6] = new House(HouseType.ORDINARY, 12, 6) ;
        field[14][6] = new House(HouseType.ORDINARY, 14, 6) ;
        field[16][6] = new House(HouseType.ORDINARY, 16, 6) ;
        field[18][6] = new House(HouseType.ORDINARY, 18, 6) ;
        field[20][6] = new House(HouseType.ORDINARY, 20, 6) ;
        field[22][6] = new House(HouseType.HILL, 22, 6) ;
        field[24][6] = new House(HouseType.JUNGLE, 24, 6) ;

        field[1][7] = new House(HouseType.ORDINARY, 1, 7) ;
        field[3][7] = new House(HouseType.ORDINARY, 3, 7) ;
        field[5][7] = new House(HouseType.CITY, 5, 7) ;
        field[7][7] = new House(HouseType.ORDINARY, 7, 7) ;
        field[9][7] = new House(HouseType.ORDINARY, 9, 7) ;
        field[11][7] = new House(HouseType.ORDINARY, 11, 7) ;
        field[13][7] = new House(HouseType.ORDINARY, 13, 7) ;
        field[15][7] = new House(HouseType.JUNGLE, 15, 7) ;
        field[17][7] = new House(HouseType.JUNGLE, 17, 7) ;
        field[19][7] = new House(HouseType.ORDINARY, 19, 7) ;
        field[21][7] = new House(HouseType.ORDINARY, 21, 7) ;
        field[23][7] = new House(HouseType.ORDINARY, 23, 7) ;
        field[25][7] = new House(HouseType.ORDINARY, 25, 7) ;

        field[2][8] = new House(HouseType.ORDINARY, 2, 8) ;
        field[4][8] = new House(HouseType.ORDINARY, 4, 8) ;
        field[6][8] = new House(HouseType.ORDINARY, 6, 8) ;
        field[8][8] = new House(HouseType.JUNGLE, 8, 8) ;
        field[10][8] = new House(HouseType.JUNGLE, 10, 8) ;
        field[12][8] = new House(HouseType.ORDINARY, 12, 8) ;
        field[14][8] = new House(HouseType.ORDINARY, 14, 8) ;
        field[16][8] = new House(HouseType.ORDINARY, 16, 8) ;
        field[18][8] = new House(HouseType.JUNGLE, 18, 8) ;
        field[20][8] = new House(HouseType.ORDINARY, 20, 8) ;
        field[22][8] = new House(HouseType.ORDINARY, 22, 8) ;
        field[24][8] = new House(HouseType.ORDINARY, 24, 8) ;

        field[1][9] = new House(HouseType.ORDINARY, 1, 9) ;
        field[3][9] = new House(HouseType.ORDINARY, 3, 9) ;
        field[5][9] = new House(HouseType.ORDINARY, 5, 9) ;
        field[7][9] = new House(HouseType.ORDINARY, 7, 9) ;
        field[9][9] = new House(HouseType.ORDINARY, 9, 9) ;
        field[11][9] = new House(HouseType.ORDINARY, 11, 9) ;
        field[13][9] = new House(HouseType.ORDINARY, 13, 9) ;
        field[15][9] = new House(HouseType.ORDINARY, 15, 9) ;
        field[17][9] = new House(HouseType.ORDINARY, 17, 9) ;
        field[19][9] = new House(HouseType.ORDINARY, 19, 9) ;
        field[21][9] = new House(HouseType.ORDINARY, 21, 9) ;
        field[23][9] = new House(HouseType.CITY, 23, 9) ;
        field[25][9] = new House(HouseType.ORDINARY, 25, 9) ;




    }




    public String[][] setFieldToCoordinates (){
        String[][] coordinates = new String[79][37] ;
        for (int x = 1; x <= 25; x++){
            for (House house : field[x]){
                if (house != null) {
                    String mark = null;
                    switch (house.getType()) {
                        case ORDINARY:
                            mark = "*" ;
                            break ;
                        case HILL:
                            mark = "^" ;
                            break ;
                        case JUNGLE:
                            mark = "#" ;
                            break ;
                        case SHELTER:
                            mark = ";" ;
                            break ;
                        case CITY :
                            mark = "+" ;
                            break ;
                        case RIVER :
                            mark = "'" ;
                            break ;
                        case BRIDGE :
                            mark = ":" ;
                            break ;
                    }

                    int xCoordinate = 3 * house.getX() - 2 ;
                    int yCoordinate = 4 * house.getY() - 3 ;
                    for (int i = 0; i < 6; i++){
                        coordinates[xCoordinate + i][yCoordinate] = mark ;
                    }
                    yCoordinate ++ ;
                    coordinates[xCoordinate][yCoordinate] = mark ;
                    if (house.getGroupForce() != null) {
                        String forceGroupDescription = house.getGroupForce().toString();
                        coordinates[xCoordinate + 1][yCoordinate] = forceGroupDescription.charAt(0) + "";
                        coordinates[xCoordinate + 2][yCoordinate] = forceGroupDescription.charAt(1) + "";
                        coordinates[xCoordinate + 3][yCoordinate] = forceGroupDescription.charAt(2) + "";
                        coordinates[xCoordinate + 4][yCoordinate] = forceGroupDescription.charAt(3) + "";
                    }

                    coordinates[xCoordinate + 5][yCoordinate] = mark ;
                    yCoordinate ++ ;
                    coordinates[xCoordinate][yCoordinate] = mark ;
                    coordinates[xCoordinate + 5][yCoordinate] = mark ;
                    yCoordinate ++ ;
                    for (int i = 0; i < 6; i++){
                        coordinates[xCoordinate + i][yCoordinate] = mark ;
                    }

                }
            }
        }

        return coordinates ;


    }

    public void showCoordinates (){

        String[][] coordinates = setFieldToCoordinates() ;
        for (int y = 1; y <= 36; y++){
            for (int x = 1; x <= 78; x++){
                if ((x % 6 == 1) & ((y % 8 >= 1) & (y % 8 <= 4))){
                    System.out.print(" ") ;
                }
                if ((x % 6 == 4) & ((y % 8 >= 5) | (y % 8 == 0))){
                    System.out.print(" ") ;
                }
                if (coordinates[x][y] == null){
                    System.out.print(" ") ;
                }
                else {
                    switch (coordinates[x][y]){
                        case "*" :
                            System.out.print(ANSI_WHITE) ;
                            break ;
                        case "^" :
                            System.out.print(ANSI_PURPLE) ;
                            break ;
                        case "#" :
                            System.out.print(ANSI_GREEN) ;
                            break ;
                        case ";" :
                            System.out.print(ANSI_RED) ;
                            break ;
                        case "+" :
                            System.out.print(ANSI_YELLOW) ;
                            break ;
                        case "'" :
                            System.out.print(ANSI_BLUE) ;
                            break ;
                        case ":" :
                            System.out.print(ANSI_CYAN) ;
                            break ;
                    }
                    System.out.print(coordinates[x][y]) ;
                }
            }
            System.out.println() ;
        }
        System.out.println() ;

    }



    public void setForces (User user1, User user2){
        for (int x = 1; x <= 25; x++){
            for (int y = 1; y <= 9; y++){
                if (field[x][y] != null){
                    field[x][y].setGroupForce(null) ;
                }
            }
        }


        for (GroupForce groupForce : user1.getGroupForces()){
            int x = groupForce.getX() ;
            int y = groupForce.getY() ;
            field[x][y].setGroupForce(groupForce) ;
        }
        for (GroupForce groupForce : user2.getGroupForces()){
            int x = groupForce.getX() ;
            int y = groupForce.getY() ;
            field[x][y].setGroupForce(groupForce) ;
        }







    }












}
