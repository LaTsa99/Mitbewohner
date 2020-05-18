package hu.mudm.icefield.view;

//----------------------------------------------------------//
//           Jó tanácsok aki hozzányúl a lenti kódhoz:      //
//                                                          //
// A jlabel9 kuka, helyetta az ő paneljére rajzolunk majd   //
// Azért van ott, hogy amíg a rajzolás nincs megírva, lássuk//
// A játékmezőt.                                            //
//                                                          //
// A változókat érdemes refactorolni, mert így használha-   //
// tatlanok. //TODO Domi                                    //
//----------------------------------------------------------//


import javax.swing.*;

public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        pField = new javax.swing.JPanel();
        lDemoField = new javax.swing.JLabel();
        lMessage = new javax.swing.JLabel();
        pDataView = new javax.swing.JPanel();
        lCharIcon = new javax.swing.JLabel();
        lCharTurn = new javax.swing.JLabel();
        pTemp = new javax.swing.JPanel();
        tfTempNum = new javax.swing.JTextField();
        lTempText = new javax.swing.JLabel();
        lTempIcon = new javax.swing.JLabel();
        cbActions = new javax.swing.JComboBox<>();
        bFireAction = new javax.swing.JButton();
        spItems = new javax.swing.JScrollPane();
        itemList = new javax.swing.JList<>();
        lActionsLeft = new javax.swing.JLabel();
        pIceFloat = new javax.swing.JPanel();
        lSnowInfo = new javax.swing.JLabel();
        lSnowIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Icefield");

        pField.setBackground(java.awt.SystemColor.window);
        pField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pField.setMinimumSize(new java.awt.Dimension(810, 805)); // :(


        lDemoField.setIcon(new javax.swing.ImageIcon("D:\\BME\\Semester 4\\Projlab\\Icons\\field_sample.png")); // NOI18N
        lDemoField.setToolTipText("");

        //Ez a sok sor csak az lDemoFieldet helyezi el, ki lehet szedni később
        /////////////////////////////////////////////////////////////////////////////////
        javax.swing.GroupLayout pFieldLayout = new javax.swing.GroupLayout(pField);
        pField.setLayout(pFieldLayout);
        pFieldLayout.setHorizontalGroup(
                pFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pFieldLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lDemoField)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pFieldLayout.setVerticalGroup(
                pFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pFieldLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lDemoField)
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        /////////////////////////////////////////////////////////////////////////////////

        lMessage.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        lMessage.setText("<html><b>TestBunny</b>'s turn has started.</html>");
        lMessage.setToolTipText("");

        pDataView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lCharIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lCharIcon.setIcon(new javax.swing.ImageIcon("D:\\BME\\Semester 4\\Projlab\\Icons\\researcher_mini.png")); // NOI18N
        lCharIcon.setToolTipText("");
        lCharIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lCharTurn.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        lCharTurn.setText("<html><b>TestBunny1</b>'s turn</html>");

        tfTempNum.setEditable(false);
        tfTempNum.setBackground(new java.awt.Color(240, 240, 240));
        tfTempNum.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfTempNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTempNum.setText("5");
        tfTempNum.setBorder(null);
        tfTempNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTempNumActionPerformed(evt);
            }
        });

        lTempText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTempText.setText("Temperature");

        lTempIcon.setBackground(java.awt.SystemColor.window);
        lTempIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTempIcon.setIcon(new javax.swing.ImageIcon("D:\\BME\\Semester 4\\Projlab\\Icons\\homero_tr.png")); // NOI18N
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

        cbActions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><i>-Choose your next move-</i></html>", "Shovel", "Move", "Check icefloat capacity", "Build tent" }));

        bFireAction.setText("OK");
        bFireAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFireActionActionPerformed(evt);
            }
        });

        spItems.setBorder(null);

        itemList.setBackground(new java.awt.Color(240, 240, 240));
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
        lActionsLeft.setIcon(new javax.swing.ImageIcon("D:\\BME\\Semester 4\\Projlab\\Icons\\akciok\\1.png")); // NOI18N
        lActionsLeft.setToolTipText("");

        javax.swing.GroupLayout pDataViewLayout = new javax.swing.GroupLayout(pDataView);
        pDataView.setLayout(pDataViewLayout);
        pDataViewLayout.setHorizontalGroup(
                pDataViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pDataViewLayout.createSequentialGroup()
                                .addGroup(pDataViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pDataViewLayout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addComponent(bFireAction, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pDataViewLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addGroup(pDataViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lCharIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lCharTurn))
                                                .addGap(18, 18, 18)
                                                .addGroup(pDataViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lActionsLeft)
                                                        .addComponent(pTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pDataViewLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(spItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDataViewLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbActions, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        pDataViewLayout.setVerticalGroup(
                pDataViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pDataViewLayout.createSequentialGroup()
                                .addGroup(pDataViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pDataViewLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lActionsLeft)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDataViewLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lCharTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                .addGroup(pDataViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lCharIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbActions, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bFireAction, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spItems, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pIceFloat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lSnowInfo.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        lSnowInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lSnowInfo.setText("<html>The icefloat you are standing on <br>\nhas 2 layers of snow.</html>");

        lSnowIcon.setIcon(new javax.swing.ImageIcon("D:\\BME\\Semester 4\\Projlab\\Icons\\layer2.png")); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(pField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(pIceFloat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(pDataView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(lMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(pDataView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(pIceFloat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(pField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void bFireActionActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tfTempNumActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }



//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainFrame().setVisible(true);
//            }
//        });
//    }



    // Variables declaration - do not modify
    private javax.swing.JButton bFireAction;
    private javax.swing.JComboBox<String> cbActions;
    private javax.swing.JList<String> itemList;
    private javax.swing.JLabel lActionsLeft;
    private javax.swing.JLabel lCharIcon;
    private javax.swing.JLabel lCharTurn;
    private javax.swing.JLabel lDemoField;
    private javax.swing.JLabel lMessage;
    private javax.swing.JLabel lSnowIcon;
    private javax.swing.JLabel lSnowInfo;
    private javax.swing.JLabel lTempIcon;
    private javax.swing.JLabel lTempText;
    private javax.swing.JPanel pDataView;
    private javax.swing.JPanel pField;
    private javax.swing.JPanel pIceFloat;
    private javax.swing.JPanel pTemp;
    private javax.swing.JScrollPane spItems;
    private javax.swing.JTextField tfTempNum;
    // End of variables declaration
}


