package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.player.Character;

public class InstableIceFloat extends IceFloat {


    @Override
    public Boolean stepOn(Character ch, IceFloat prev) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void stepOn(Character ch) {
        throw new UnsupportedOperationException();
    }
}
