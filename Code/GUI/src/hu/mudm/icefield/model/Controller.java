package hu.mudm.icefield.model;

import hu.mudm.icefield.model.action.*;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

public class Controller extends MVCModell {
    private PolarBear p;
    private ArrayList<IceFloat> icefloats;
    private ArrayList<Character> characters;
    private Character activeCharacter;
    private Random r;
    private static boolean isWon;
    private static boolean isLost;
    private static int rocketPartsCnt = 0;

    public ArrayList<IceFloat> getIcefloats() {
        return icefloats;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public PolarBear getPolarBear() {
        return p;
    }

    public Controller(GUI gui) {
        super(gui);
        r = new Random();
        isWon = false;
        isLost = false;
    }

    public void gameLoop() {
        while (!isWon && !isLost) {
            for (Character ch : characters) {
                activeCharacter = ch;
                gui.startTurn();
                gui.showMessage("<html>The turn of <b>" + ch.getName() + "</b> has started.</html>");
                for (int i = 0; i < 4; i++) {
                    //gui.startTurn(); ??
                    validateActions(ch);
                    ch.setActionsLeft(4-i);
                    updateViews();
                    Action action = null;
                    //gui.see(ch);
                    //gui.showMessage((4-i)+" actions left");
                    try {
                        action = createAction(ch);
                    } catch (NoActionException e) {
                        //e.printStackTrace();
                        i = 4; //ending the loop
                        gui.showMessage("Player " + ch.getName() + " has no possible Actions left, ending turn");
                    }
                    action.performAction();
                    updateViews();
                    if(isLost || isWon) return;
                }
            }
            gui.showMessage("The polar bear is moving");
            p.Wake();
            gui.showMessage("A snowstorm is happening");
            snowstorm();
            for (IceFloat icefloat : icefloats) {
                icefloat.endRound();
            }
            gui.endRound();
        }
        updateViews();
    }

    public void snowstorm() {
        if (icefloats != null) {
            for (IceFloat ice : icefloats) {
                if (r.nextFloat() < 0.5f) {
                    ice.addSnow();
                }
            }
        }
    }

    public void showMessage(String message) {
        gui.showMessage(message);
    }

    public void setIcefloats(ArrayList<IceFloat> newFloats) {
        icefloats = newFloats;
    }

    public void setCharacters(ArrayList<Character> newCharacters) {
        characters = newCharacters;
    }

    public void setPolarBear(PolarBear bear) {
        p = bear;
    }

    private void validateActions(Character ch) {
        if (ch.getIceFloat().getSnowLevel() > 0) //if the current position has snow on it
            ch.addAction(ShovelAction.class);
        else ch.removeAction(ShovelAction.class);
        if (ch.getIceFloat().getNeighbors().size() > 0)//if the current position has neighbors
            ch.addAction(MoveAction.class);
        else ch.removeAction(MoveAction.class);
        if (canRocketBeBuilt())//if all parts have been picked up and all players are on the same position
            ch.addAction(BuildRocketAction.class);
        else ch.removeAction(BuildRocketAction.class);
        if (ch.getIceFloat().getSnowLevel() <= 0 && ch.getIceFloat().hasItem()) //if the current position has an item in it and there is no snow
            ch.addAction(PickupAction.class);
        else ch.removeAction(PickupAction.class);
    }

    private Action createAction(Character ch) throws NoActionException {
        Action action=null;

        //get index of action class
        int actionIndex = gui.getAction(ch);
        Class<? extends Action> classofaction = ch.getActions().get(actionIndex);

        //get constructor of the chosen action class
        Constructor<? extends Action>[] constructors = (Constructor<? extends Action>[]) classofaction.getConstructors();
        Constructor<? extends Action> constructor = constructors[0];

        try {
            //if the constructor requires 3 parameters (character, icefloat and a function)
            if (constructors[0].getParameterCount() > 2) {
                int neighborID = gui.getChosenNeighborID(ch.getIceFloat());
                IceFloat icefloat = null;
                int i = 0;
                while (icefloat == null && i < icefloats.size()) {
                    if (neighborID == icefloats.get(i).getID())
                        icefloat = icefloats.get(i);
                    i++;
                }
                if (i == icefloats.size()) throw new IDNotFoundException();

                action = constructor.newInstance(ch, icefloat, this);
            }
            //if the constructor requires needs a character and a target
            else if (constructors[0].getParameterCount() > 1) {
                int neighborID = gui.getChosenNeighborID(ch.getIceFloat());
                IceFloat icefloat = null;
                int i = 0;
                while (icefloat == null && i < icefloats.size()) {
                    if (neighborID == icefloats.get(i).getID())
                        icefloat = icefloats.get(i);
                    i++;
                }
                if (i == icefloats.size()) throw new IDNotFoundException();

                action = constructor.newInstance(ch, icefloat);
            }
            //if the constructor requires only needs a character
            else {
                action = constructor.newInstance(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return action;
    }

    public boolean canRocketBeBuilt(){
        //the same with the "canWin" method in documentation
        if(rocketPartsCnt < 3) return false;

        Character ch = characters.get(0);
        IceFloat iceFloat = ch.getIceFloat();
        return iceFloat.playersHere() == characters.size();
    }

    public static void rocketPartPickedUp() {
        Controller.rocketPartsCnt++;
    }

    public boolean getisWon() {
        return isWon;
    }

    public boolean getisLost() {
        return isLost;
    }

    public static void Win() {
        //gui.showMessage("You won! :D");
        isWon = true;
        isLost = false;
    }

    public static void Lose(String cause) {
        //GUI_Prototype.printMessage("You lost :(\n" + cause);
        isLost = true;
        isWon = false;
    }

    @Override
    public void createViews(MenuView mv) {
        views.add(new CharacterDataView(this, mv));
        views.add(new IceFloatView(this, mv));
    }

    public Character getActiveCharacter(){
        return activeCharacter;
    }
}
