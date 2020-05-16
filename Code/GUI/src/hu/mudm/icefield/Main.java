package hu.mudm.icefield;

import hu.mudm.icefield.view.GUI_Prototype;

class Main {
    public static void main(String[] args){
        Game game = Game.getInstance();
        game.init();
        game.start();
    }
}