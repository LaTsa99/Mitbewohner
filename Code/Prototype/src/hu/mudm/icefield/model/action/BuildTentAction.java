package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.player.Character;

public class BuildTentAction extends Action {
    public BuildTentAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        ch.BuildTent();
        ch.getPosition().buildTent();
    }
}
