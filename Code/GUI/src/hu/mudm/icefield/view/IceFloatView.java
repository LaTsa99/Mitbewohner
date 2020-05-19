package hu.mudm.icefield.view;
import hu.mudm.icefield.model.Controller;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class IceFloatView extends MVCView {

    //private String iglooPath;
    //private String tentPath;
    private JPanel pIceFloat;
    private JLabel lSnowIcon;
    private JLabel lSnowInfo;

    public IceFloatView(MVCModell modell, MenuView mv) {
        super(modell);
        pIceFloat = mv.getIceFloat();
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    initializePanel();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void initializePanel(){
        lSnowInfo = new JLabel();
        lSnowIcon = new JLabel();
        pIceFloat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lSnowInfo.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        lSnowInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lSnowInfo.setText("<html>The icefloat you are standing on <br>\nhas 2 layers of snow.</html>");

        lSnowIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/layers/layer2.png"))); // NOI18N
        lSnowIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pIceFloatLayout = new javax.swing.GroupLayout(pIceFloat);
        pIceFloat.setLayout(pIceFloatLayout);
        pIceFloatLayout.setHorizontalGroup(
                pIceFloatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pIceFloatLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lSnowIcon)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pIceFloatLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lSnowInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
        );
        pIceFloatLayout.setVerticalGroup(
                pIceFloatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pIceFloatLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lSnowInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lSnowIcon)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    @Override
    public void update() {
        int snowLevel = ((Controller) model).getActiveCharacter().getIceFloat().getSnowLevel();

        if(snowLevel == 0){
            lSnowInfo.setText("<html>The icefloat you are standing on <br>\nhas 0 layers of snow.</html>");
            lSnowIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/layers/layer0.png"))); // NOI18N
        }
        else if(snowLevel == 1) {
            lSnowInfo.setText("<html>The icefloat you are standing on <br>\nhas 1 layers of snow.</html>");
            lSnowIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/layers/layer1.png"))); // NOI18N
        }
        else if(snowLevel == 2) {
            lSnowInfo.setText("<html>The icefloat you are standing on <br>\nhas 2 layers of snow.</html>");
            lSnowIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/layers/layer2.png"))); // NOI18N
        }
        else if(snowLevel == 3) {
            lSnowInfo.setText("<html>The icefloat you are standing on <br>\nhas 3 layers of snow.</html>");
            lSnowIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/layers/layer3.png"))); // NOI18N
        }
        else {
            lSnowInfo.setText("<html>The icefloat you are standing on <br>\nhas "+ snowLevel +"layers of snow.</html>");
            lSnowIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/layers/layer4.png"))); // NOI18N
        }
    }
}
