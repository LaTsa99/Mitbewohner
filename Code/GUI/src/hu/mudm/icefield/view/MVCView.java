package hu.mudm.icefield.view;

public abstract class MVCView {

    protected MVCModell model;

    public MVCView(MVCModell model){
        this.model = model;
    }

    public abstract void update();
}
