package hu.mudm.icefield.view;

import hu.mudm.icefield.model.player.Character;

import javax.swing.*;

public class CharacterDataView extends MVCView {

    private JPanel panel;


    JLabel lMessage, lCharIcon, lCharTurn, lTempText, lTempIcon, lActionsLeft;
    JPanel pTemp;
    JTextField tfTempNum;
    JComboBox cbActions;
    JButton bFireAction;
    JScrollPane spItems;
    JList itemList;

    Character character;

    public CharacterDataView(MVCModell modell, MenuView mv) {
        super(modell);
        panel = mv.getDataView();
        lCharIcon = new javax.swing.JLabel();
        lCharTurn = new javax.swing.JLabel();
        tfTempNum = new javax.swing.JTextField();

        pTemp = new javax.swing.JPanel();
        cbActions = new javax.swing.JComboBox<>();
        spItems = new javax.swing.JScrollPane();
        itemList = new javax.swing.JList<>();

        bFireAction = new javax.swing.JButton();
        lTempText = new javax.swing.JLabel();
        lTempIcon = new javax.swing.JLabel();
        lActionsLeft = new javax.swing.JLabel();

        lCharIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       // lCharIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/characters/researcher_mini.png"))); // NOI18N
        lCharIcon.setToolTipText("");
        lCharIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lCharTurn.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        //lCharTurn.setText("<html><b>TestBunny1</b>'s turn</html>");

        tfTempNum.setEditable(false);
        tfTempNum.setBackground(new java.awt.Color(240, 240, 240));
        tfTempNum.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfTempNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        //tfTempNum.setText("5");
        tfTempNum.setBorder(null);
        lTempText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTempText.setText("Temperature");

        lTempIcon.setBackground(java.awt.SystemColor.window);
        lTempIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTempIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/forIceFloat/thermometer.png"))); // NOI18N
        lTempIcon.setToolTipText("");

        javax.swing.GroupLayout pTempLayout = new javax.swing.GroupLayout(pTemp);
        pTemp.setLayout(pTempLayout);
        pTempLayout.setHorizontalGroup(
                pTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pTempLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfTempNum, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))
                        .addComponent(lTempText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pTempLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lTempIcon)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pTempLayout.setVerticalGroup(
                pTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pTempLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(lTempText)
                                .addGap(7, 7, 7)
                                .addComponent(tfTempNum, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lTempIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bFireAction.setText("OK");

        spItems.setBorder(null);

        itemList.setBackground(new java.awt.Color(240, 240, 240));
        //TODO: Feltölteni üres ikonokkal (/items/icon_frame.png)
        itemList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        itemList.setFixedCellHeight(50);
        itemList.setFixedCellWidth(50);
        itemList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        itemList.setMaximumSize(new java.awt.Dimension(250, 150));
        itemList.setMinimumSize(new java.awt.Dimension(250, 50));
        itemList.setPreferredSize(new java.awt.Dimension(250, 50));
        itemList.setVisibleRowCount(-1);
        spItems.setViewportView(itemList);

        lActionsLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //lActionsLeft.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/actionNumbers/1.png"))); // NOI18N
        lActionsLeft.setToolTipText("");
    }

    @Override
    public void update() {
        lCharTurn.setText(character.getName()+ "'s turn.");

        if (character.getClass().getSimpleName().equals("Eskimo"))
            lCharIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/characters/eskimo_mini.png")));
        else if (character.getClass().getSimpleName().equals("Researcher"))
            lCharIcon.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/characters/researcher_mini.png")));

        switch(character.getActionsLeft()){
            case 1:
                lActionsLeft.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/actionNumbers/1.png")));
                break;
            case 2:
                lActionsLeft.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/actionNumbers/2.png")));
                break;
            case 3:
                lActionsLeft.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/actionNumbers/3.png")));
                break;
            case 4:
                lActionsLeft.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/icons/actionNumbers/4.png")));
                break;
        }
        tfTempNum.setText(((Integer)(character.getTemp())).toString());

        //TODO: A character.Actions-ből a stringeket kinyerni vhogy..
        cbActions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><i>-Choose your next move-</i></html>", "Shovel", "Move", "Check icefloat capacity", "Build tent" }));

        itemList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });



    }

    public void setCharacter(Character ch){character = ch;}
}
