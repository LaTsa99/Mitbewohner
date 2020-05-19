package hu.mudm.icefield.view;

import hu.mudm.icefield.model.Controller;

import javax.swing.*;

public class FieldView extends MVCView {

    private JPanel panel;

    public FieldView(Controller controller, MenuView menuView) {
        super(controller);
        panel = menuView.getField();

        panel.setBackground(java.awt.SystemColor.window);
        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.setMinimumSize(new java.awt.Dimension(810, 805)); // :(
    }

    @Override
    public void update() {

    }
}
