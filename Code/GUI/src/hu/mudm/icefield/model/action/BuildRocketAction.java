package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.player.Character;

public class BuildRocketAction extends Action {
    public BuildRocketAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        Controller.Win();
    }

}
