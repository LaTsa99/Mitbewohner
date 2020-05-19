package hu.mudm.icefield;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.PolarBear;
import hu.mudm.icefield.model.field.Hole;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.field.UnstableIceFloat;
import hu.mudm.icefield.model.item.*;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.*;

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

    private Controller controller;

    // Ez a konstruktor
    private Game(){}

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

        return result;
    }

    private ArrayList<IceFloat> createIcefloats(ArrayList<Item> i){       //első stable, játszható 50%s, 40%u -> kapacitás random, 10%h
        ArrayList<IceFloat> ices = new ArrayList<IceFloat>();
        int type;

        ices.add(new StableIceFloat(i.get(0)));
        for (int j=1; j<36; j++){
            type = rand.nextInt(100);
            if (type < 50)                  ices.add(new StableIceFloat(i.get(j)));
            if (type >= 50 && type < 90)    ices.add(new UnstableIceFloat(rand.nextInt(1)+1, i.get(j)));
            if (type >= 90 )                ices.add(new Hole());
        }

        int[] rd = new int[3];
        rd[0] = rand.nextInt(35) + 1;
        rd[1] = -1;
        rd[2] = -1;
        while (rd[1]<0||rd[0]==rd[1]) rd[1] = rand.nextInt(35) + 1;
        while (rd[2]<0||rd[0]==rd[2] || rd[1]==rd[2]) rd[2] = rand.nextInt(35) + 1;

        for(int j= 0; j<3; j++){
        type = rand.nextInt(90);
        if (type < 50 )            {        // or rd[j]==0
            int id = ices.get(rd[j]).getID();
            ices.set(rd[j], new StableIceFloat(new RocketPart()));
            ices.get(rd[j]).setID(id);
        }
        else if (type >= 50 && type < 90)    {
            int id = ices.get(rd[j]).getID();
            ices.set(rd[j], new UnstableIceFloat(rand.nextInt(1)+1, new RocketPart()));
            ices.get(rd[j]).setID(id);
        }
        }

        return ices;
    }

    private void setNeighbors(ArrayList<IceFloat> i){
        ArrayList<IceFloat> neighbors;

        for(int j = 0; j < 36; j++){
            if((j + 1) % 6 > (j % 6)) i.get(j).setNeighbor(i.get(j+1));
            if((j + 6) < 36) i.get(j).setNeighbor(i.get(j + 6));
        }
    }

    public void init()
    {

        rand = new Random();
        ArrayList<Item> items = createItems();
        ArrayList<IceFloat> iceFloats = createIcefloats(items);
        setNeighbors(iceFloats);
        GUI gui = new MVCController();
        controller = new Controller(gui);

        PolarBear maci = new PolarBear(iceFloats.get(35));

        controller.setIcefloats(iceFloats);
        //initial snow level
        for (int i = 0; i < 5; i++)
            controller.snowstorm();
        controller.setPolarBear(maci);
        ArrayList<Character> characters = gui.getCharacters((StableIceFloat) iceFloats.get(0));   //getCharacters() uses iceFloats! (startPosition)
        if (characters == null) System.exit(0);
        controller.setCharacters(characters);

        //TODO: Létrehozni az összes MVCViewt, a modellhez, kontrollerhez (menüfieldes message).. + set
        MenuView menuView = new MenuView(controller);
        MessageView messageView = new MessageView(controller, menuView);
        FieldView fieldView = new FieldView(controller, menuView);
        ((MVCController)gui).setViews(menuView, fieldView, messageView);
        controller.addView(messageView);
        controller.addView(fieldView);
        controller.addView(menuView);
        controller.createViews(menuView);
        controller.setMvcController((MVCController) gui);
    }

    public void start(){ this.controller.gameLoop(); }
}
