package hu.mudm.icefield.view;

import javax.swing.*;

public class IceFloatView extends MVCView {

    private String iglooPath;
    private String tentPath;
    private JPanel panel;

    public IceFloatView(MVCModell modell, MenuView mv) {
        super(modell);
        panel = mv.getIceFloat();
    }

    @Override
    public void update() {

    }
}
