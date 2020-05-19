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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void update();
}
