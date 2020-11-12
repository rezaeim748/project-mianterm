package com.company;

public class Card {

    private CardType type ;

    public Card (String typeSpecifier){
        switch (typeSpecifier){
            case ("1g") :
                type = CardType.ORDER1GROUP ;
            case ("2g") :
                type = CardType.ORDER2GROUPS ;
            case ("3g") :
                type = CardType.ORDER3GROUPS ;
            case ("4g") :
                type = CardType.ORDER4GROUPS ;
            case ("3u") :
                type = CardType.ORDER3UNITS ;
            default :
                type = null ;
        }
    }



}
