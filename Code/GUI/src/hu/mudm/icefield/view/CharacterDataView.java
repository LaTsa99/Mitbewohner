package hu.mudm.icefield.view;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CharacterDataView extends MVCView {

    private JPanel panel;

    JLabel lMessage, lCharIcon, lCharTurn, lTempText, lTempIcon, lActionsLeft;
    JPanel pTemp;
    JTextField tfTempNum;
    JComboBox cbActions;
    JButton bFireAction;
    JScrollPane spItems;
    JList itemList;
    DefaultListModel<String> listModel;
    DefaultComboBoxModel<String> actionsModel;
    Character character;

    public CharacterDataView(MVCModell model, MenuView mv) {
        super(model);
        panel = mv.getDataView();
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
        mv.packMainFrame();
    }

    private void initializePanel(){
        lCharIcon = new JLabel();
        lCharTurn = new JLabel();
        tfTempNum = new JTextField();

        pTemp = new JPanel();
        cbActions = new JComboBox<>();
        spItems = new JScrollPane();
        itemList = new JList<>();

        bFireAction = new JButton();
        lTempText = new JLabel();
        lTempIcon = new JLabel();
        lActionsLeft = new JLabel();

        listModel = new DefaultListModel<String>();
        actionsModel = new DefaultComboBoxModel<>();

        lCharIcon.setHorizontalAlignment(SwingConstants.CENTER);

        lCharIcon.setToolTipText("");
        lCharIcon.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        lCharTurn.setFont(new Font("Century Schoolbook", 0, 14));

        tfTempNum.setEditable(false);
        tfTempNum.setBackground(new Color(240, 240, 240));
        tfTempNum.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        tfTempNum.setHorizontalAlignment(JTextField.CENTER);

        tfTempNum.setBorder(null);
        lTempText.setHorizontalAlignment(SwingConstants.CENTER);
        lTempText.setText("Temperature");

        lTempIcon.setBackground(SystemColor.window);
        lTempIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lTempIcon.setIcon(new ImageIcon(this.getClass().getResource("/icons/forIceFloat/thermometer.png")));
        lTempIcon.setToolTipText("");

        GroupLayout pTempLayout = new GroupLayout(pTemp);
        pTemp.setLayout(pTempLayout);
        pTempLayout.setHorizontalGroup(
                pTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pTempLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfTempNum, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))
                        .addComponent(lTempText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pTempLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lTempIcon)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pTempLayout.setVerticalGroup(
                pTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pTempLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(lTempText)
                                .addGap(7, 7, 7)
                                .addComponent(tfTempNum, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lTempIcon, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
        );

        bFireAction.setText("OK");
        bFireAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onFireButtonClick();
            }
        });

        spItems.setBorder(null);

        itemList.setBackground(new Color(240, 240, 240));

        addEmptyFrames();

        itemList.setModel(listModel);
        itemList.setCellRenderer(new ItemCellRenderer());
        itemList.setFixedCellHeight(50);
        itemList.setFixedCellWidth(50);
        itemList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        itemList.setMaximumSize(new Dimension(250, 150));
        itemList.setMinimumSize(new Dimension(250, 50));
        itemList.setPreferredSize(new Dimension(250, 50));
        itemList.setVisibleRowCount(-1);
        spItems.setViewportView(itemList);

        lActionsLeft.setHorizontalAlignment(SwingConstants.CENTER);
        lActionsLeft.setToolTipText("");

        cbActions.setModel(actionsModel);

        panel.setBorder(BorderFactory.createEtchedBorder());

        GroupLayout pDataViewLayout = new GroupLayout(panel);
        panel.setLayout(pDataViewLayout);
        pDataViewLayout.setHorizontalGroup(
                pDataViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pDataViewLayout.createSequentialGroup()
                                .addGroup(pDataViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pDataViewLayout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addComponent(bFireAction, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pDataViewLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addGroup(pDataViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lCharIcon, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lCharTurn))
                                                .addGap(18, 18, 18)
                                                .addGroup(pDataViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lActionsLeft)
                                                        .addComponent(pTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pDataViewLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(spItems, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, pDataViewLayout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbActions, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        pDataViewLayout.setVerticalGroup(
                pDataViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pDataViewLayout.createSequentialGroup()
                                .addGroup(pDataViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(pDataViewLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lActionsLeft)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, pDataViewLayout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lCharTurn, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                .addGroup(pDataViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lCharIcon, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pTemp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbActions, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bFireAction, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spItems, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );


    }
    @Override
    public void update() {
        character = ((Controller)model).getActiveCharacter();
        if (character == null) return;

        lCharTurn.setText("<html><b>" + character.getName()+"</b>" + "'s turn.</html>");

        if (character.getClass().getSimpleName().equals("Eskimo"))
            lCharIcon.setIcon(new ImageIcon(this.getClass().getResource("/icons/characters/eskimo_mini.png")));
        else if (character.getClass().getSimpleName().equals("Researcher"))
            lCharIcon.setIcon(new ImageIcon(this.getClass().getResource("/icons/characters/researcher_mini.png")));

        switch(character.getActionsLeft()){
            case 1:
                lActionsLeft.setIcon(new ImageIcon(this.getClass().getResource("/icons/actionNumbers/1.png")));
                break;
            case 2:
                lActionsLeft.setIcon(new ImageIcon(this.getClass().getResource("/icons/actionNumbers/2.png")));
                break;
            case 3:
                lActionsLeft.setIcon(new ImageIcon(this.getClass().getResource("/icons/actionNumbers/3.png")));
                break;
            case 4:
                lActionsLeft.setIcon(new ImageIcon(this.getClass().getResource("/icons/actionNumbers/4.png")));
                break;
        }
        tfTempNum.setText(((Integer)(character.getTemp())).toString());

        ArrayList<String> actionNames = new ArrayList<String>();
        for (Class<? extends Action> a: character.getActions()) {
            actionNames.add(a.getSimpleName());
        }
        String[] actionStrings = new String[1+actionNames.size()];

        int i = 0;
        actionStrings[i++] = "<html><i>-Choose your next action-</i></html>";
        for (String s: actionNames) {
            switch(s) {
                case "BuildAction":
                    actionStrings[i++] = "Build an Igloo";
                    break;
                case "BuildRocketAction":
                    actionStrings[i++] = "Assemble the Rocket";
                    break;
                case "BuildTentAction":
                    actionStrings[i++] = "Build a Tent";
                    break;
                case "CheckAction":
                    actionStrings[i++] = "Check the capacity of a neighbouring Icefloat";
                    break;
                case "MoveAction":
                    actionStrings[i++] = "Move to a neighbouring IceFloat";
                    break;
                case "PickupAction":
                    actionStrings[i++] = "Pick up the Item in the IceFloat";
                    break;
                case "ShovelAction":
                    actionStrings[i++] = "Shovel snow from the current IceFloat";
                    break;

            }
        }
        //cbActions.setModel(new DefaultComboBoxModel<>(actionStrings));
        actionsModel.removeAllElements();
        for(i=0;i<actionStrings.length;i++) actionsModel.addElement(actionStrings[i]);

        updateCharacterInventory();

    }

    public void setCharacter(Character ch){character = ch;}

    private void addEmptyFrames() {
        for (int i=0;i<5;i++){
            listModel.addElement("empty");
        }
    }

    private void updateCharacterInventory(){
        listModel.clear();
        int rp = 1;
        ArrayList<Item> items = character.getItems();
        String[] strings;
        if(items.size()<5) strings = new String[5];
        else {
            strings = new String[items.size()];
        }
        for(int i=0; i<items.size();i++)
        {
            strings[i]=new String(items.get(i).getClass().getSimpleName());
            if(strings[i].equals("RocketPart"))
                strings[i] = "rp" + rp++;
        }
        if(items.size()<5) {
          for(int i=items.size(); i<5; i++) {
              strings[i]= "empty";
          }
        }
        for(int i=0;i<strings.length;i++){
            listModel.addElement(strings[i]);
        }
    }

    public class ItemCellRenderer extends DefaultListCellRenderer {

        private Map<String, Icon> icons = new HashMap<String,Icon>();

        public ItemCellRenderer(){
            icons.put("empty", new ImageIcon(this.getClass().getResource("/icons/items/icon_frame.png")));
            icons.put("BreakableShovel",new ImageIcon(this.getClass().getResource("/icons/items/framed_breakableShovel.png")));
            icons.put("DiverSuit",new ImageIcon(this.getClass().getResource("/icons/items/framed_diverSuit.png")));
            icons.put("Rope",new ImageIcon(this.getClass().getResource("/icons/items/framed_rope.png")));
            icons.put("rp1",new ImageIcon(this.getClass().getResource("/icons/items/framed_rp1.png")));
            icons.put("rp2",new ImageIcon(this.getClass().getResource("/icons/items/framed_rp2.png")));
            icons.put("rp3",new ImageIcon(this.getClass().getResource("/icons/items/framed_rp3.png")));
            icons.put("Shovel",new ImageIcon(this.getClass().getResource("/icons/items/framed_shovel.png")));
            icons.put("Tent",new ImageIcon(this.getClass().getResource("/icons/items/framed_tent.png")));
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
            label.setIcon(icons.get((String)value));
            label.setText("");

            return label;
        }
    }

    private void onFireButtonClick(){
        int index = cbActions.getSelectedIndex();
        if(index == 0) return;
        index -= 1;
        Object lock = model.getMvcController().lock;
        synchronized (lock){
            model.getMvcController().setSelectedAction(index);
            lock.notify();
        }
    }
}
