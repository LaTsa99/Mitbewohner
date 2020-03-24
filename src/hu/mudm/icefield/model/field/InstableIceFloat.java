package hu.mudm.icefield.model.field;

public class InstableIceFloat extends IceFloat {

    @Override
    public Boolean stepOn(Character ch, IceFloat prev) {
        throw new UnsupportedOperationException();
    }
    
}
