package hu.mudm.icefield.view;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.field.IceFloat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FieldView extends MVCView {

    private final int HORIZONTAL_OFFSET = 6;
    private final int FLOAT_DIMENSION = 128;    //width and height

    private JPanel panel;
    private JLabel icon;
    BufferedImage img;
    Graphics2D backGround;

    public FieldView(Controller controller, MenuView menuView) {
        super(controller);
        panel = menuView.getField();
        icon = new JLabel();

        panel.setBackground(SystemColor.window);
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setMinimumSize(new Dimension(810, 805)); // :(

        try {
            img = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/field_final.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        icon.setIcon(new ImageIcon(img));

       //GroupLayout pFieldLayout = new GroupLayout(panel);
       //panel.setLayout(pFieldLayout);
       //pFieldLayout.setHorizontalGroup(
       //        pFieldLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
       //                .addGroup(pFieldLayout.createSequentialGroup()
       //                        .addGap(22, 22, 22)
       //                        .addComponent(icon)
       //                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       //);
       //pFieldLayout.setVerticalGroup(
       //        pFieldLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
       //                .addGroup(pFieldLayout.createSequentialGroup()
       //                        .addContainerGap()
       //                        .addComponent(icon)
       //                        .addContainerGap(22, Short.MAX_VALUE))
       //);
        //try {
        //    img = ImageIO.read(new File("/icons/forIceFloat/field_final.png"));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        panel.add(icon);
        menuView.packMainFrame();
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                IceFloat i = getIceFloat(x, y);
                if(i == null) {
                    return;
                }

                ArrayList<IceFloat> neighbours = ((Controller)model).getActiveCharacter().getPosition().getNeighbors();
                boolean isNeighbour = false;
                for(IceFloat iceFloat : neighbours){
                    if(iceFloat.getID() == i.getID()) {
                        isNeighbour = true;
                        break;
                    }
                }

                if(isNeighbour){
                    Object lock = model.getMvcController().fieldLock;
                    synchronized (lock){
                        model.getMvcController().setSelectedNeighbour(i.getID());
                        lock.notify();
                    }
                }
            }
        });
    }

    @Override
    public void update() {
        BufferedImage character = null;
        try {
            character = ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_blue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(character == null) return;
        //BufferedImage combined = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        //g.drawImage(img, 0, 0, null);
        g.drawImage(character, 3, 3, null);
        g.dispose();
        ImageIcon image = new ImageIcon(img);
        icon.setIcon(image);
        panel.invalidate();
    }

    private Dimension getIceFloatOffset(IceFloat ice){
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

    private class MyLabel extends JLabel{
        public MyLabel(){
            super();
        }
    }

    private IceFloat getIceFloat(Dimension dimension){
        return getIceFloat(dimension.width,dimension.height);
    }

    private IceFloat getIceFloat(int width, int height){
        int row = height/FLOAT_DIMENSION;
        if (Math.abs(row%2)==1) {
            width -= HORIZONTAL_OFFSET;
            if (width<0) return null;
        }
        else {
            if (width>6*FLOAT_DIMENSION) return null;
        }
        int col = width/FLOAT_DIMENSION;
        int id = 6*row + col;
        return ((Controller)model).getIcefloats().get(id);
    }
}
