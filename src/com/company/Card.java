package com.company;

public class Card {

    private CardType type ;

    public Card (String typeSpecifier){
        switch (typeSpecifier){
            case ("1g") :
                type = CardType.ORDER1GROUP ;
                break ;
            case ("2g") :
                type = CardType.ORDER2GROUPS ;
                break ;
            case ("3g") :
                type = CardType.ORDER3GROUPS ;
                break ;
            case ("4g") :
                type = CardType.ORDER4GROUPS ;
                break ;
            case ("3u") :
                type = CardType.ORDER3UNITS ;
                break ;
            default :
                type = null ;
        }
    }

    public CardType getType (){ return type ; }



}
