package hu.mudm.icefield;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.field.Hole;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.field.UnstableIceFloat;
import hu.mudm.icefield.model.item.DiverSuit;
import hu.mudm.icefield.model.item.Food;
import hu.mudm.icefield.model.item.Rope;
import hu.mudm.icefield.model.item.Shovel;

import java.util.ArrayList;

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

    public Controller GetController() {return controller;}

    public void init()
    {
/*
        ArrayList<IceFloat> iceFloats = new ArrayList<IceFloat>();
        iceFloats.add(new Hole());
        iceFloats.add(new StableIceFloat());
        iceFloats.add(new UnstableIceFloat());
        iceFloats.add(new Hole());
        iceFloats.add(new StableIceFloat(new Food()));
        iceFloats.add(new UnstableIceFloat(new Rope()));
        iceFloats.add(new StableIceFloat(new Shovel()));
        iceFloats.add(new UnstableIceFloat(new DiverSuit()));

        for (int i=1; i<iceFloats.size()-1; i++) {
            iceFloats.get(i).setNeighbor(iceFloats.get(i-1));

            iceFloats.get(i).setNeighbor(iceFloats.get(i+1));
        }*/
    }

    public void start(){}

    public static void lose(){

    }

    public static void win() {

    }
}
