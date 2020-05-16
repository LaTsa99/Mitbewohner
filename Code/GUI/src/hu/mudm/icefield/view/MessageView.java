package hu.mudm.icefield.view;

import javax.swing.*;

public class MessageView extends MVCView {

    private JLabel label;

    public MessageView(MVCModell modell) {
        super(modell);
    }

    @Override
    public void update() {

    }

    public void setText(String s){
        label.setText(s);
    }
}
