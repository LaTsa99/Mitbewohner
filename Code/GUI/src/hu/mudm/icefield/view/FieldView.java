package hu.mudm.icefield.view;

import hu.mudm.icefield.model.Controller;

import javax.swing.*;

public class FieldView extends MVCView {

    private JPanel panel;

    public FieldView(Controller controller, MenuView menuView) {
        super(controller);
        panel = menuView.getField();
    }

    @Override
    public void update() {

    }
}
