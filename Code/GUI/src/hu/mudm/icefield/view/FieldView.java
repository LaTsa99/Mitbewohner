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
        if(true/*((Controller)model).getIsLost()*/) {
            if(icefloat.getClass().getSimpleName().equals("Hole") /*&& icefloat.getCharacters().size()>0*/)
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

        if(showhole)
            background = getImage("hole");
        else {
            int r =0;
            if(((Controller) model).getDuringStorm())
                r = rnd.nextInt(4);
            else
            {
                r = icefloat.getID()%4;
            }
            switch (r + 1) {
                case 1:
                    background = getImage("iceFloat_1");
                    break;
                case 2:
                    background = getImage("iceFloat_2");
                    break;
                case 3:
                    background = getImage("iceFloat_3");
                    break;
                case 4:
                    background = getImage("iceFloat_4");
                    break;
            }
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
            ArrayList<Integer> offsets_y = new ArrayList<Integer>();
            offsets_y.add(128/2-32/2); //kozepvonal
            offsets_y.add(16);
            offsets_y.add(16);

            alreadyDrawn+=drawCharacters(icefloat.getCharacters(), g_background, alreadyDrawn, offsets_y);
            if(icefloat.getSnowLevel()==0 && icefloat.hasItem()) alreadyDrawn+=drawItem(icefloat.getItem(), g_background, alreadyDrawn, offsets_y);
            if(icefloat.hasIglu()) alreadyDrawn+=drawIglu(g_background, alreadyDrawn, offsets_y);
            if(icefloat.hasTent()) alreadyDrawn+=drawTent(g_background, alreadyDrawn, offsets_y);
            if(pb.getPosition().equals(icefloat)) alreadyDrawn+=drawPolarBear(g_background, alreadyDrawn, offsets_y);
        }
        if(rowcount == 2)
        {
            ArrayList<Integer> offsets_y = new ArrayList<Integer>();
            offsets_y.add(128/2-32);
            offsets_y.add(128/2);
            offsets_y.add(16);

            alreadyDrawn+=drawCharacters(icefloat.getCharacters(), g_background, alreadyDrawn, offsets_y);
            if(icefloat.getSnowLevel()==0 && icefloat.hasItem()) alreadyDrawn+=drawItem(icefloat.getItem(), g_background, alreadyDrawn, offsets_y);
            if(icefloat.hasIglu()) alreadyDrawn+=drawIglu(g_background, alreadyDrawn, offsets_y);
            if(icefloat.hasTent()) alreadyDrawn+=drawTent(g_background, alreadyDrawn, offsets_y);
            if(pb.getPosition().equals(icefloat)) alreadyDrawn+=drawPolarBear(g_background, alreadyDrawn, offsets_y);
        }

        if(model.getSelectable() != null && model.getSelectable().contains(icefloat)) {
            BufferedImage b =getImage("x");
            g_background.drawImage(b, 0, 0, null);
        }

        if(((Controller)model).getActiveCharacter().getPosition().equals(icefloat) ||  (icefloat.getItem() != null && icefloat.getItem().getClass().getSimpleName().equals("RocketPart"))) {
            BufferedImage b = getImage("rectangle");
            g_background.drawImage(b, 0, 0, null);
        }


        return background;
    }

    private int drawPolarBear(Graphics g_background, int alreadyDrawn, ArrayList<Integer> offsets_y) {
        BufferedImage imageToBeDrawn = null;
        imageToBeDrawn = getImage("polarBear");
        if (imageToBeDrawn != null)
        {
            g_background.drawImage(imageToBeDrawn, 32 * (alreadyDrawn%4), offsets_y.get(alreadyDrawn/4), null);
            return 1;
        }
        return 0;
    }

    private int drawTent(Graphics g_background, int alreadyDrawn, ArrayList<Integer> offsets_y) {
        BufferedImage imageToBeDrawn = null;
        imageToBeDrawn = getImage("tentOnIce");

        if (imageToBeDrawn != null)
        {
            g_background.drawImage(imageToBeDrawn, 32 * (alreadyDrawn%4), offsets_y.get(alreadyDrawn/4), null);
            return 1;
        }
        return 0;
    }

    private int drawIglu(Graphics g_background, int alreadyDrawn, ArrayList<Integer> offsets_y) {
        BufferedImage imageToBeDrawn = null;
        imageToBeDrawn = getImage("igloo");

        if (imageToBeDrawn != null)
        {
            g_background.drawImage(imageToBeDrawn, 32 * (alreadyDrawn%4), offsets_y.get(alreadyDrawn/4), null);
            return 1;
        }
        return 0;
    }

    private int drawItem(Item item, Graphics g_background, int alreadyDrawn, ArrayList<Integer> offsets_y) {
        BufferedImage imageToBeDrawn = null;
            switch (item.getClass().getSimpleName()) {
                case "BreakableShovel":
                    imageToBeDrawn = getImage("breakableShovel");
                    break;
                case "DiverSuit":
                    imageToBeDrawn = getImage("diverSuit");
                    break;
                case "Food":
                    imageToBeDrawn = getImage("food");
                    break;
                case "RocketPart":
                    switch(((RocketPart)item).getID()+1) {
                        case 1:
                            imageToBeDrawn = getImage("rocketPart_1");
                            break;
                        case 2:
                            imageToBeDrawn = getImage("rocketPart_2");
                            break;
                        case 3:
                            imageToBeDrawn = getImage("rocketPart_3");
                            break;
                    }
                    break;
                case "Rope":
                    imageToBeDrawn = getImage("rope");
                    break;
                case "Shovel":
                    imageToBeDrawn = getImage("shovel");
                    break;
                case "Tent":
                    imageToBeDrawn = getImage("tent");
                    break;
                default:
                    return 0;
            }

        if (imageToBeDrawn != null)
        {
            g_background.drawImage(imageToBeDrawn, 32 * (alreadyDrawn%4), offsets_y.get(alreadyDrawn/4), null);
            return 1;
        }
        return 0;
    }

    private int drawCharacters(ArrayList<Character> characterList, Graphics g_background, int alreadyDrawn, ArrayList<Integer> offsets_y) {
        BufferedImage imageToBeDrawn = null;
        int count = 0;
        for (Character ch : characterList) {
            int id = ch.getId();
            switch (id) {
                case 0:
                    if (ch.getClass().getSimpleName().equals("Researcher"))
                        imageToBeDrawn = getImage("researcher_blue");
                    else
                        imageToBeDrawn = getImage("eskimo_blue");
                    break;
                case 1:
                    if (ch.getClass().getSimpleName().equals("Researcher"))
                        imageToBeDrawn = getImage("researcher_green");
                    else
                        imageToBeDrawn = getImage("eskimo_green");
                    break;
                case 2:
                    if (ch.getClass().getSimpleName().equals("Researcher"))
                        imageToBeDrawn = getImage("researcher_orange");
                    else
                        imageToBeDrawn = getImage("eskimo_orange");
                    break;
                case 3:
                    if (ch.getClass().getSimpleName().equals("Researcher"))
                        imageToBeDrawn = getImage("researcher_purple");
                    else
                        imageToBeDrawn = getImage("eskimo_purple");
                    break;
                case 4:
                    if (ch.getClass().getSimpleName().equals("Researcher"))
                        imageToBeDrawn = getImage("researcher_red");
                    else
                        imageToBeDrawn = getImage("eskimo_red");
                    break;
                case 5:
                    if (ch.getClass().getSimpleName().equals("Researcher"))
                        imageToBeDrawn = getImage("researcher_yellow");
                    else
                        imageToBeDrawn = getImage("eskimo_yellow");
                    break;
                default:
                    return count;
            }

            if (imageToBeDrawn != null)
            {
                g_background.drawImage(imageToBeDrawn, 32 * ((alreadyDrawn+count)%4), offsets_y.get((alreadyDrawn+count)/4), null);
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
