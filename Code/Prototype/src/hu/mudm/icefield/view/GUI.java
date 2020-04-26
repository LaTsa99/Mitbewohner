package hu.mudm.icefield.view;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

import java.util.ArrayList;

public interface GUI {

    int getAction(Character character) throws NoActionException;

    int getChosenNeighborID(IceFloat icefloat) throws Exception;

    ArrayList<Character> getCharacters();
}
