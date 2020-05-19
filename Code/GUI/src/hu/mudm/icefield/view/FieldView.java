package hu.mudm.icefield.view;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.PolarBear;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.item.RocketPart;
import hu.mudm.icefield.model.player.Character;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FieldView extends MVCView {

    private final int HORIZONTAL_OFFSET = 6;
    private final int FLOAT_DIMENSION = 128;    //width and height

    private JPanel panel;
    private JLabel icon;
    BufferedImage img;
    Graphics2D backGround;
    Random rnd;

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
        rnd = new Random();
    }

    @Override
    public void update() {
        BufferedImage combined=new BufferedImage(icon.getWidth(), icon.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();
        g.drawImage(img, 0, 0, null);
        for (IceFloat icefloat: ((Controller)model).getIcefloats()) {
            BufferedImage imageOfIcefloat = createImageOfIceFloat(icefloat);

            if(imageOfIcefloat != null)
            {
                Dimension offset = iceFloatOffset(icefloat);
                g.drawImage(imageOfIcefloat, offset.width, offset.height, null);
            }
        }

        g.dispose();
        ImageIcon image = new ImageIcon(combined);
        icon.setIcon(image);
        panel.invalidate();

    }

    private BufferedImage createImageOfIceFloat(IceFloat icefloat) {
        boolean showhole = false;
        if(((Controller)model).getIsLost()) {
            if(icefloat.getClass().getSimpleName().equals("Hole") && icefloat.getCharacters().size()>0)
                showhole = true;
            if(icefloat.getClass().getSimpleName().equals("UnstableIceFloat") && icefloat.getCharacters().size()> icefloat.getCapacity())
                showhole = true;
        }


        //icefloat
        PolarBear pb = ((Controller)model).getPolarBear();

        int countOfDrawables = countOfDrawables(icefloat);
        if(pb.getPosition().equals(icefloat)) countOfDrawables++;

        //if(countOfDrawables==0) return null;

        BufferedImage background = null;
        try {
            if(showhole)
                background = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/hole.png"));
            else {
                int r = rnd.nextInt(4);
                switch (r + 1) {
                    case 1:
                        background = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/iceFloat_1.png"));
                        break;
                    case 2:
                        background = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/iceFloat_2.png"));
                        break;
                    case 3:
                        background = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/iceFloat_3.png"));
                        break;
                    case 4:
                        background = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/iceFloat_4.png"));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics g_background = background.getGraphics();

        //size: 128x128, size of drawables: 32x32 -> 4 row, 4 column max
        int rowcount = 0;

        if(countOfDrawables<=4) rowcount = 1;
        else if(countOfDrawables<=8) rowcount = 2;
        else if(countOfDrawables<=12) rowcount = 3;
        else if(countOfDrawables<=16) rowcount = 4;
        else rowcount=4;
        //else throw new Exception("Too many drawables on a single icefloat");

        Integer alreadyDrawn = 0;

        if(rowcount == 1)
        {
            int offset_y = 128/2-32/2; //kozepvonal

            alreadyDrawn+=drawCharacters(icefloat.getCharacters(), g_background, alreadyDrawn, offset_y);
            if(icefloat.getSnowLevel()==0 && icefloat.hasItem()) alreadyDrawn+=drawItem(icefloat.getItem(), g_background, alreadyDrawn, offset_y);
            if(icefloat.hasIglu()) alreadyDrawn+=drawIglu(g_background, alreadyDrawn, offset_y);
            if(icefloat.hasTent()) alreadyDrawn+=drawTent(g_background, alreadyDrawn, offset_y);
            if(pb.getPosition().equals(icefloat)) alreadyDrawn+=drawPolarBear(g_background, alreadyDrawn, offset_y);
        }

        if(model.getSelectable() != null && model.getSelectable().contains(this)) {
            try {
                BufferedImage b =ImageIO.read(this.getClass().getResource("/icons/forIceFloat/x.png"));
                g_background.drawImage(b, 0, 0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return background;
    }

    private int drawPolarBear(Graphics g_background, int alreadyDrawn, int offset_y) {
        BufferedImage imageToBeDrawn = null;
        try {
            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/polarBear.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (imageToBeDrawn != null)
        {
            g_background.drawImage(imageToBeDrawn, 32 * alreadyDrawn, offset_y, null);
            return 1;
        }
        return 0;
    }

    private int drawTent(Graphics g_background, int alreadyDrawn, int offset_y) {
        BufferedImage imageToBeDrawn = null;
        try {
            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/tent.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (imageToBeDrawn != null)
        {
            g_background.drawImage(imageToBeDrawn, 32 * alreadyDrawn, offset_y, null);
            return 1;
        }
        return 0;
    }

    private int drawIglu(Graphics g_background, int alreadyDrawn, int offset_y) {
        BufferedImage imageToBeDrawn = null;
        try {
            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/forIceFloat/igloo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (imageToBeDrawn != null)
        {
            g_background.drawImage(imageToBeDrawn, 32 * alreadyDrawn, offset_y, null);
            return 1;
        }
        return 0;
    }

    private int drawItem(Item item, Graphics g_background, int alreadyDrawn, int offset_y) {
        BufferedImage imageToBeDrawn = null;
        try {
            switch (item.getClass().getSimpleName()) {
                case "BreakableShovel":
                    imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/breakableShovel.png"));
                    break;
                case "DiverSuit":
                    imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/diverSuit.png"));
                    break;
                case "Food":
                    imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/food.png"));
                    break;
                case "RocketPart":
                    switch(((RocketPart)item).getID()+1) {
                        case 1:
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/rocketPart_1.png"));
                            break;
                        case 2:
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/rocketPart_2.png"));
                            break;
                        case 3:
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/rocketPart_3.png"));
                            break;
                    }
                    break;
                case "Rope":
                    imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/rope.png"));
                    break;
                case "Shovel":
                    imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/shovel.png"));
                    break;
                case "Tent":
                    imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/items/tent.png"));
                    break;
                default:
                    return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (imageToBeDrawn != null)
        {
            g_background.drawImage(imageToBeDrawn, 32 * alreadyDrawn, offset_y, null);
            return 1;
        }
        return 0;
    }

    private int drawCharacters(ArrayList<Character> characterList, Graphics g_background, int alreadyDrawn, int offset_y) {
        BufferedImage imageToBeDrawn = null;
        int count = 0;
        for (Character ch : characterList) {
            int id = ch.getId();

            try {
                switch (id) {
                    case 0:
                        if (ch.getClass().getSimpleName().equals("Researcher"))
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/researcher_blue.png"));
                        else
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_blue.png"));
                        break;
                    case 1:
                        if (ch.getClass().getSimpleName().equals("Researcher"))
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/researcher_green.png"));
                        else
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_green.png"));
                        break;
                    case 2:
                        if (ch.getClass().getSimpleName().equals("Researcher"))
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/researcher_orange.png"));
                        else
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_orange.png"));
                        break;
                    case 3:
                        if (ch.getClass().getSimpleName().equals("Researcher"))
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/researcher_purple.png"));
                        else
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_purple.png"));
                        break;
                    case 4:
                        if (ch.getClass().getSimpleName().equals("Researcher"))
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/researcher_red.png"));
                        else
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_red.png"));
                        break;
                    case 5:
                        if (ch.getClass().getSimpleName().equals("Researcher"))
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/researcher_yellow.png"));
                        else
                            imageToBeDrawn = ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_yellow.png"));
                        break;
                    default:
                        return count;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (imageToBeDrawn != null)
            {
                g_background.drawImage(imageToBeDrawn, 32 * (alreadyDrawn+count), offset_y, null);
                count++;
            }
        }
        return count;
    }

    private int countOfDrawables(IceFloat icefloat) { //jegesmedvet ezt nem szamolja
        int count = icefloat.getCharacters().size();
        if(icefloat.getSnowLevel()==0 && icefloat.hasItem()) count++;
        if(icefloat.hasIglu()) count++;
        if(icefloat.hasTent()) count++;

        return count;
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
