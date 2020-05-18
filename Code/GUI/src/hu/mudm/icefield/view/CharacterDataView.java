package hu.mudm.icefield.view;

import javax.swing.*;
import java.awt.*;

public class CharacterDataView extends MVCView {

    private JPanel panel;

    public CharacterDataView(MVCModell modell, MenuView mv) {
        super(modell);
        panel = mv.getDataView();
    }

    @Override
    public void update() {

    }
}
