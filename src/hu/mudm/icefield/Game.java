package hu.mudm.icefield;

import hu.mudm.icefield.model.Controller;

public class Game {

    private static Game singleInstance = null;

    public static Game getInstance(){
        if(singleInstance == null)
            singleInstance = new Game();

        return singleInstance;
    }

    // Ez a konstruktor
    private Game(){}

    private Controller controller;

    public void init(){}

    public void start(){}

    public static void lose(){}

    public static void win(){}
}
