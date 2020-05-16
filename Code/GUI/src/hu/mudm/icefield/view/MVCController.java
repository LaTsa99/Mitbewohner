package hu.mudm.icefield.view;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.player.Character;

import java.util.ArrayList;

public class MVCController implements GUI {

    private MenuView menuView;
    private FieldView fieldView;
    private MessageView messageView;

    private WelcomeFrame welcomeFrame;

    public MVCController(){
    }

    @Override
    public void showMessage(String s) {
        messageView.setText(s);
    }

    @Override
    public int getAction(Character character) throws NoActionException {
        return 0;
    }

    @Override
    public int getChosenNeighborID(IceFloat icefloat) throws NoNeighborException {
        return 0;
    }

    @Override
    public ArrayList<Character> getCharacters(StableIceFloat startingIceFloat) {
        return null;
        //TODO welcomeFrame pops up
    }

    @Override
    public void see(Character ch) {

    }

    @Override
    public void endRound() {

    }

    @Override
    public void startTurn() {

    }
}
