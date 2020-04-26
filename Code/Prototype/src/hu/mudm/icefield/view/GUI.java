package hu.mudm.icefield.view;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

import java.util.ArrayList;

public interface GUI {

    public int getAction(Character character);

    public int getStep(IceFloat iceFloat);

    public ArrayList<Character> getCharacters();
}
