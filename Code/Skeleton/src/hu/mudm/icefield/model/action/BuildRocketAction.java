package hu.mudm.icefield.model.action;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

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
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");
        if (pickedUp==3) {
            g.win();
        }
    }

}
