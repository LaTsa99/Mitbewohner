package hu.mudm.icefield.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WelcomeFrame extends javax.swing.JFrame {

    /**
     * Creates new form WelcomeFrame
     */
    public WelcomeFrame() {
        /* Create and display the form */
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

        pNorth = new javax.swing.JPanel();
        lTitle = new javax.swing.JLabel();
        lDescr = new javax.swing.JLabel();
        pCenter = new javax.swing.JPanel();
        pPlayers = new javax.swing.JPanel();
        lNickname = new javax.swing.JLabel();
        spNickname = new javax.swing.JScrollPane();
        listNickname = new javax.swing.JList<>();
        spType = new javax.swing.JScrollPane();
        listType = new javax.swing.JList<>();
        tfPlayer = new javax.swing.JTextField();
        lNewPlayer = new javax.swing.JLabel();
        bAdd = new javax.swing.JButton();
        bStartGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Icefield");

        lTitle.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        lTitle.setText("IceField Game");

        lDescr.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        lDescr.setText("   Enter 3-6 players and start the game when you are ready.");

        javax.swing.GroupLayout pNorthLayout = new javax.swing.GroupLayout(pNorth);
        pNorth.setLayout(pNorthLayout);
        pNorthLayout.setHorizontalGroup(
                pNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pNorthLayout.createSequentialGroup()
                                .addGroup(pNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pNorthLayout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(lTitle))
                                        .addGroup(pNorthLayout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(lDescr)))
                                .addContainerGap(115, Short.MAX_VALUE))
        );
        pNorthLayout.setVerticalGroup(
                pNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pNorthLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(lTitle)
                                .addGap(18, 18, 18)
                                .addComponent(lDescr)
                                .addContainerGap(47, Short.MAX_VALUE))
        );

        getContentPane().add(pNorth, java.awt.BorderLayout.NORTH);

        pPlayers.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lNickname.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lNickname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lNickname.setText("Players");

        spNickname.setBorder(null);

        listNickname.setBackground(new java.awt.Color(240, 240, 240));
        listNickname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listNickname.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Ali", "Bambi", "Cili", "Dumbó" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        spNickname.setViewportView(listNickname);

        spType.setBorder(null);

        listType.setBackground(new java.awt.Color(240, 240, 240));
        listType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listType.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Eskimo", "Eskimo", "Researcher", "Eskimo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listType.setToolTipText("");
        spType.setViewportView(listType);

        javax.swing.GroupLayout pPlayersLayout = new javax.swing.GroupLayout(pPlayers);
        pPlayers.setLayout(pPlayersLayout);
        pPlayersLayout.setHorizontalGroup(
                pPlayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pPlayersLayout.createSequentialGroup()
                                .addComponent(spType, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(lNickname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pPlayersLayout.setVerticalGroup(
                pPlayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pPlayersLayout.createSequentialGroup()
                                .addComponent(lNickname)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pPlayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(spNickname, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                        .addComponent(spType)))
        );

        lNewPlayer.setText("New Player:");

        bAdd.setText("Add");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addNewCharacter();
            }
        });

        bStartGame.setText("Start Game");
        bStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startGame();
            }
        });

        javax.swing.GroupLayout pCenterLayout = new javax.swing.GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
                pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pCenterLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(pPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addGroup(pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(bAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lNewPlayer))
                                .addContainerGap(44, Short.MAX_VALUE))
        );
        pCenterLayout.setVerticalGroup(
                pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pCenterLayout.createSequentialGroup()
                                .addGroup(pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addComponent(lNewPlayer)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(bStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(pPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 16, Short.MAX_VALUE))
        );

        getContentPane().add(pCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void addNewCharacter(){
        //TODO generate random char type, add type and nickname to list, ? disable button
    }

    private void startGame(){
        synchronized (lock){
            setVisible(false);
            lock.notify();
        }
    }

//    public List<String> getNickNames(){
//
//    }
//
//    public List<String> getCharacterTypes(){
//
//    }

    public Object lock = new Object();
    // Variables declaration - do not modify
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bStartGame;
    private javax.swing.JList<String> listNickname;
    private javax.swing.JLabel lDescr;
    private javax.swing.JLabel lNewPlayer;
    private javax.swing.JLabel lNickname;
    private javax.swing.JLabel lTitle;
    private javax.swing.JList<String> listType;
    private javax.swing.JPanel pCenter;
    private javax.swing.JPanel pNorth;
    private javax.swing.JPanel pPlayers;
    private javax.swing.JScrollPane spNickname;
    private javax.swing.JScrollPane spType;
    private javax.swing.JTextField tfPlayer;
    // End of variables declaration
}
