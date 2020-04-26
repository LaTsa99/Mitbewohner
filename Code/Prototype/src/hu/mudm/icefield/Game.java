package hu.mudm.icefield;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.PolarBear;
import hu.mudm.icefield.model.field.Hole;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.field.UnstableIceFloat;
import hu.mudm.icefield.model.item.*;
import hu.mudm.icefield.model.player.Character;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Random rand;

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

    private ArrayList<Item> createItems(){

        ArrayList<Item> result = new ArrayList<Item>();
        for (int i=0; i<36; i++){
            int type = rand.nextInt(100);
            if (type < 25)                  result.add(null);
            if (type >= 25 && type < 47)    result.add(new Food());
            if (type >= 47 && type < 60)    result.add(new Tent());
            if (type >= 60 && type < 73)    result.add(new Rope());
            if (type >= 73 && type < 86)    result.add(new BreakableShovel());
            if (type >= 86 && type < 93)    result.add(new Shovel());
            if (type >= 93 )                result.add(new DiverSuit());
        }
        int r1 = rand.nextInt(36);
        int r2 = rand.nextInt(36);
        int r3 = rand.nextInt(36);
        if (r1==r2) r1 = (r1-1)%36;             //a randomok egyenlősége nincs kizárva, vagyis nem látom teljesen át, amúgy szerintem igen (Kriszti)
        if (r1==r3) r1 = (r1-1)%36;
        if (r2==r3) r2 = (r2+1)%36;
        result.set(r1, new RocketPart());
        result.set(r2, new RocketPart());
        result.set(r3, new RocketPart());

        return result;
    }

    private ArrayList<IceFloat> createIcefloat(ArrayList<Item> i){       //első stable, játszható 50%s, 40%u -> kapacitás random, 10%h
        ArrayList<IceFloat> ices = new ArrayList<IceFloat>();

        ices.add(new StableIceFloat(i.get(0)));
        for (int j=1; j<36; j++){
            int type = rand.nextInt(100);
            if (type < 50)                  ices.add(new StableIceFloat(i.get(j)));
            if (type >= 50 && type < 90)    ices.add(new UnstableIceFloat(rand.nextInt(4)+1, i.get(j)));
            if (type >= 90 )                ices.add(new Hole());            // Mi van, ha a RocketPart Holera kerül??
        }
        return ices;
    }

    private void setNeighbors(ArrayList<IceFloat> i){
        ArrayList<IceFloat> neighbors;
        for(int j = 0; j < 36; j++){
            if (j>0)    i.get(j).setNeighbor(i.get(j-1));
            if (j>6)    i.get(j).setNeighbor(i.get(j-6));
            if (j<35)   i.get(j).setNeighbor(i.get(j+1));
            if (j<29)   i.get(j).setNeighbor(i.get(j+6));
        }
    }

    public void init()
    {
        rand = new Random();
        ArrayList<Item> items = createItems();
        ArrayList<IceFloat> iceFloats = createIcefloat(items);
        setNeighbors(iceFloats);
        //TODO: create characters asking GUI for their names
        ArrayList<Character> characters;

        PolarBear maci = new PolarBear(iceFloats.get(rand.nextInt(36)));
        Controller controller = new Controller(characters, iceFloats, maci);



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
}
