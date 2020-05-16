package hu.mudm.icefield.view;

public abstract class MVCView {

    private MVCModell modell;

    public MVCView(MVCModell modell){
        this.modell = modell;
    }

    public abstract void update();
}
