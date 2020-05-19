package hu.mudm.icefield.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class MVCView {

    protected MVCModell model;
    protected static Map<String, BufferedImage> images= new HashMap<String, BufferedImage>();

    public MVCView(MVCModell model){
        this.model = model;

        if(images.size()==0)
        {
            try {
                images.put("researcher_blue", ImageIO.read(this.getClass().getResource("/icons/characters/researcher_blue.png")));
                images.put("eskimo_blue", ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_blue.png")));
                images.put("researcher_green", ImageIO.read(this.getClass().getResource("/icons/characters/researcher_green.png")));
                images.put("eskimo_green", ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_green.png")));
                images.put("researcher_orange", ImageIO.read(this.getClass().getResource("/icons/characters/researcher_orange.png")));
                images.put("eskimo_orange", ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_orange.png")));
                images.put("researcher_purple", ImageIO.read(this.getClass().getResource("/icons/characters/researcher_purple.png")));
                images.put("eskimo_purple", ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_purple.png")));
                images.put("researcher_red", ImageIO.read(this.getClass().getResource("/icons/characters/researcher_red.png")));
                images.put("eskimo_red", ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_red.png")));
                images.put("researcher_yellow", ImageIO.read(this.getClass().getResource("/icons/characters/researcher_yellow.png")));
                images.put("eskimo_yellow", ImageIO.read(this.getClass().getResource("/icons/characters/eskimo_yellow.png")));
                images.put("breakableShovel", ImageIO.read(this.getClass().getResource("/icons/items/breakableShovel.png")));
                images.put("diverSuit", ImageIO.read(this.getClass().getResource("/icons/items/diverSuit.png")));
                images.put("food", ImageIO.read(this.getClass().getResource("/icons/items/food.png")));
                images.put("rocketPart_1", ImageIO.read(this.getClass().getResource("/icons/items/rocketPart_1.png")));
                images.put("rocketPart_2", ImageIO.read(this.getClass().getResource("/icons/items/rocketPart_2.png")));
                images.put("rocketPart_3", ImageIO.read(this.getClass().getResource("/icons/items/rocketPart_3.png")));
                images.put("rope", ImageIO.read(this.getClass().getResource("/icons/items/rope.png")));
                images.put("shovel", ImageIO.read(this.getClass().getResource("/icons/items/shovel.png")));
                images.put("tent", ImageIO.read(this.getClass().getResource("/icons/items/tent.png")));

                images.put("igloo",ImageIO.read(this.getClass().getResource("/icons/forIceFloat/igloo.png")));
                images.put("tent",ImageIO.read(this.getClass().getResource("/icons/forIceFloat/tentOnIce.png")));
                images.put("polarBear", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/polarBear.png")));

                images.put("rectangle", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/rectangle.png")));
                images.put("x", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/x.png")));

                images.put("hole", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/hole.png")));
                images.put("iceFloat_1", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/iceFloat_1.png")));
                images.put("iceFloat_2", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/iceFloat_2.png")));
                images.put("iceFloat_3", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/iceFloat_3.png")));
                images.put("iceFloat_4", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/iceFloat_4.png")));


                images.put("action1", ImageIO.read(this.getClass().getResource("/icons/actionNumbers/1.png")));
                images.put("action2", ImageIO.read(this.getClass().getResource("/icons/actionNumbers/2.png")));
                images.put("action3", ImageIO.read(this.getClass().getResource("/icons/actionNumbers/3.png")));
                images.put("action4", ImageIO.read(this.getClass().getResource("/icons/actionNumbers/4.png")));
                images.put("thermometer", ImageIO.read(this.getClass().getResource("/icons/forIceFloat/thermometer.png")));
                images.put("eskimo_display_kek", ImageIO.read(this.getClass().getResource("/icons/characters/display/eskimo_mini_kek.png")));
                images.put("eskimo_display_lila", ImageIO.read(this.getClass().getResource("/icons/characters/display/eskimo_mini_lila.png")));
                images.put("eskimo_display_narancs", ImageIO.read(this.getClass().getResource("/icons/characters/display/eskimo_mini_narancs.png")));
                images.put("eskimo_display_piros", ImageIO.read(this.getClass().getResource("/icons/characters/display/eskimo_mini_piros.png")));
                images.put("eskimo_display_sarga", ImageIO.read(this.getClass().getResource("/icons/characters/display/eskimo_mini_sarga.png")));
                images.put("eskimo_display_zold", ImageIO.read(this.getClass().getResource("/icons/characters/display/eskimo_mini_zold.png")));
                images.put("researcher_display_kek", ImageIO.read(this.getClass().getResource("/icons/characters/display/researcher_mini_kek.png")));
                images.put("researcher_display_lila", ImageIO.read(this.getClass().getResource("/icons/characters/display/researcher_mini_lila.png")));
                images.put("researcher_display_narancs", ImageIO.read(this.getClass().getResource("/icons/characters/display/researcher_mini_narancs.png")));
                images.put("researcher_display_piros", ImageIO.read(this.getClass().getResource("/icons/characters/display/researcher_mini_piros.png")));
                images.put("researcher_display_sarga", ImageIO.read(this.getClass().getResource("/icons/characters/display/researcher_mini_sarga.png")));
                images.put("researcher_display_zold", ImageIO.read(this.getClass().getResource("/icons/characters/display/researcher_mini_zold.png")));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void update();
}
