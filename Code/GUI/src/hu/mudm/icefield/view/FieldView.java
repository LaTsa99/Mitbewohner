package hu.mudm.icefield.view;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.field.IceFloat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FieldView extends MVCView {

    private final int HORIZONTAL_OFFSET = 6;
    private final int FLOAT_DIMENSION = 128;    //width and height

    private JPanel panel;
    private JLabel icon;
    BufferedImage img;

    public FieldView(Controller controller, MenuView menuView) {
        super(controller);
        panel = menuView.getField();

        panel.setBackground(SystemColor.window);
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setMinimumSize(new Dimension(810, 805)); // :(

        icon.setIcon(new ImageIcon(img));
        icon.setToolTipText("");

        GroupLayout pFieldLayout = new GroupLayout(panel);
        panel.setLayout(pFieldLayout);
        pFieldLayout.setHorizontalGroup(
                pFieldLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pFieldLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(icon)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pFieldLayout.setVerticalGroup(
                pFieldLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pFieldLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(icon)
                                .addContainerGap(22, Short.MAX_VALUE))
        );
    }

    @Override
    public void update() {
        panel.invalidate();
    }




    private Dimension iceFloatOffset(IceFloat ice){
        boolean doesNeedOffset = false;
        int x, y;
        int index = ice.getID();
        y = index / 6;
        x = index % 6;
        if (x<0) x+=6;
        if (Math.abs(y%2)==1) doesNeedOffset = true;
        x = x*FLOAT_DIMENSION;
        y = y*FLOAT_DIMENSION;
        if (doesNeedOffset) x+=HORIZONTAL_OFFSET;
        return new Dimension(x,y);
    }
}
