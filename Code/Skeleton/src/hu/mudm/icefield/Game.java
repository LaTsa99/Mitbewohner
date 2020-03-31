package hu.mudm.icefield;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.view.GUI_skeleton;

public class Game {

    private static Game singleInstance = null;

    public static Game getInstance(){
        if(singleInstance == null)
            singleInstance = new Game();

        return singleInstance;
    }

    // Ez a konstruktor
    Game(){}

    private Controller controller;

    public void init(){}

    public void start(){}

    public static void lose(){
        GUI_skeleton.printlnWithTabs(Game.class,"lose()");
    }

    public static void win(){}
}
