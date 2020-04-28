package hu.mudm.icefield.view;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

import java.util.ArrayList;

public interface GUI {

    void showMessage(String s);

    int getAction(Character character) throws NoActionException;

    int getChosenNeighborID(IceFloat icefloat) throws NoNeighborException;

    ArrayList<Character> getCharacters();

    void see(Character ch);

    void endRound();

    void startTurn();
}
