package hu.mudm.icefield.model.action;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.player.Character;

public class BuildRocketAction extends Action {

    private static int pickedUp = 0;
    Game g;

    public BuildRocketAction(Character character) {
        super(character);
    }

    public static int pickedUpParts(){
        return pickedUp;
    }

    @Override
    public void performAction() {
        Controller.Win();
    }

}
