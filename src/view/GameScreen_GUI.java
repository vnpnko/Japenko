package view;
import model.*;
import model.EventListener.*;
import model.Tile.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen_GUI extends JFrame implements ActionListener, KeyListener, BombExplodeListener, PlayerDieListener, PlayerStatusChangeListener, PlayerGetTreasureListener, MonsterDieListener {
    private JPanel MainPanel;

    private JLabel CurrentRound;
    private JLabel ElapsedTime;
    private JButton MenuButton;
    private JButton FinishButton;

    private JPanel GameBoard;
    private JPanel Player1StatusPanel;
    private JPanel Player2StatusPanel;
    private JPanel Player3StatusPanel;

    private JLabel Player1Image;
    private JLabel Player2Image;
    private JLabel Player3Image;
    private JPanel Player1Status;
    private JPanel Player2Status;
    private JPanel Player3Status;
    private JLabel Player1Ghost;
    private JLabel Player2Ghost;
    private JLabel Player3Ghost;
    private JLabel Player1RollerSkate;
    private JLabel Player2RollerSkate;
    private JLabel Player3RollerSkate;
    private JLabel Player1Obstacle;
    private JLabel Player2Obstacle;
    private JLabel Player3Obstacle;
    private JLabel Player1Detonator;
    private JLabel Player2Detonator;
    private JLabel Player3Detonator;
    private JLabel Player1Invincibility;
    private JLabel Player2Invincibility;
    private JLabel Player3Invincibility;
    private JTextField player1TextField;
    private JTextField player2TextField;
    private JTextField player3TextField;
    private JLabel Player1Forbidden;

    private final JLayeredPane LayeredPane;
    private final Timer timer = new Timer();
    private int short_time = NormalBomb.time_until_explode;

    //constructor
    public GameScreen_GUI() {
        Game.musicPlayer.battleMusicStart();
        Game.refreshForRound();
        initMonstersPositions();
        LayeredPane = new JLayeredPane();
        initPlayerStatusImage();

        //region >> Register button action listeners and set style fo the buttons
        MenuButton.addActionListener(this);
        MenuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        MenuButton.setFont(new Font("Courier New", Font.PLAIN, 30));

        FinishButton.addActionListener(this);
        FinishButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        FinishButton.setFont(new Font("Courier New", Font.PLAIN, 30));
        //endregion

        Player1StatusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        Player1StatusPanel.setPreferredSize(new Dimension(150, 100));

        Player2StatusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        Player2StatusPanel.setPreferredSize(new Dimension(150, 100));

        Player3StatusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        Player3StatusPanel.setPreferredSize(new Dimension(150, 100));

        //region >> add event listener for keyboard input
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        //endregion

        //region >> add event listener for PlayerDieListener
        for(Player player : Game.players){
            player.setPlayerDieListener(this);
            player.setPlayerStatusChangeListener(this);
            player.setPlayerGetTreasureListener(this);
        }
        for(Monster monster: Game.monsters){
            monster.setMonsterDieListener(this);
        }
        //endregion

        //region >> initiate GUI for game screen
        setTitle("BOMBERMAN");

        //region >> Set game status style
        CurrentRound.setText("Round " + String.valueOf(Game.current_round));
        CurrentRound.setFont(new Font("Courier New", Font.PLAIN, 30));
        CurrentRound.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CurrentRound.setOpaque(true);
        CurrentRound.setBackground(Color.WHITE);

        ElapsedTime.setText("Time:" + String.valueOf(60)); // gameScreenFrame.ElapsedTime is needed instead of 60
        ElapsedTime.setFont(new Font("Courier New", Font.PLAIN, 30));
        ElapsedTime.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ElapsedTime.setOpaque(true);
        ElapsedTime.setBackground(Color.WHITE);
        //endregion

        initPlayersPositions();//Initiate players position and put them on the map for each round
        Game.map.updateMap();//Update map class
        GenerateGameBoard(); //based on the map class, initiate JPanel of the game board
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.MainPanel);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        //endregion

//        Game.map.getLayers().get("Bombs").update();

        this.setVisible(true);
    }

    //region >> public method
    public void PlayerStatusImageAdd(int player_id, TreasureType treasure_type) throws Exception {
        String image_path = switch(treasure_type){
            case BOMB_POWER_UP -> "assets/item_bomb_power_up.png";
            case BOMB_INCREASE -> "assets/item_bomb_increase.png";
            case INVINCIBILITY -> "assets/item_invincibility.png";
            case ROLLERSKATE -> "assets/item_roller_skate.png";
            case OBSTACLE -> "assets/item_obstacle.png";
            case GHOST -> "assets/item_ghost.png";
            case DETONATOR -> "assets/item_detonator.png";
            case FORBIDDEN -> "assets/item_forbidden_bomb.png";
            case BOMB_POWER_DOWN -> "assets/item_power_down.png";
            case SPEED_DOWN -> "assets/item_speed_down.png";
        };

        try{
            JLabel target_label = getTargetStatusLabel(player_id, treasure_type);
            if(target_label != null){
                target_label.setIcon(new ImageIcon(image_path));
            }

        }
        catch (Exception e){
            System.err.println("error: " + e.getMessage());
        }
    }
    public void PlayerStatusImageRemove(int player_id, TreasureType treasure_type){
        try{
            JLabel target_label = getTargetStatusLabel(player_id, treasure_type);
            target_label.setIcon(null);
        }
        catch (Exception e){
            System.err.println("error: " + e.getMessage());
        }
    }
    //endregion
    private void initMonstersPositions() {
        int size = Game.map.getSize();
        int index = 0;

        for(Monster monster : Game.monsters){
            int x = 1;
            int y = 1;
            switch (index){// Use modulus to loop through the four starting positions
                case 0:
                    x = 4;
                    y =3;
                    break;
                case 1:
                    x = size - 5;
                    y = 3;
                    break;
                case 2:
                    x = 4;
                    y = size - 4;
                    break;
                case 3:
                    x = size - 5;
                    y = size - 4;
                    break;
            }
            monster.setX(x);
            monster.setY(y);
            index++;
        }


        for(Monster monster : Game.monsters){
            if(monster instanceof  ChaserMonster){
                setupIndividualMonsterMovement(monster, 500);
            }else{
                setupIndividualMonsterMovement(monster, 1000);
            }
        }
    }

    private void setupIndividualMonsterMovement(Monster monster, int interval) {
        Timer monsterMovementTimer = new Timer();
        TimerTask monsterMoveTask = new TimerTask() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (monster.isAlive() && !Game.is_paused) {
                        monster.updateMovement();
                        monster.move();
                        Game.map.getLayers().get("Characters").update();
                        LayeredPane.revalidate();
                        LayeredPane.repaint();
                    }
                    if(Game.is_finished){
                        monsterMovementTimer.cancel();
                    }
                });
            }
        };
        monsterMovementTimer.scheduleAtFixedRate(monsterMoveTask, 0, interval);
    }

    //region >> private functions
    private void GenerateGameBoard(){
        int size = Game.map.getSize()*30;
        LayeredPane.setPreferredSize(new Dimension(size, size));

        //region >> add layers into LayeredPane
        LayeredPane.add(Game.map.getLayers().get("Background").getLayer(), JLayeredPane.DEFAULT_LAYER, 1);
        LayeredPane.add(Game.map.getLayers().get("Decoration").getLayer(), JLayeredPane.DEFAULT_LAYER, 0);
        LayeredPane.add(Game.map.getLayers().get("Bombs").getLayer(), JLayeredPane.PALETTE_LAYER, 2);
        LayeredPane.add(Game.map.getLayers().get("Objects").getLayer(), JLayeredPane.PALETTE_LAYER, 1);
        LayeredPane.add(Game.map.getLayers().get("Characters").getLayer(), JLayeredPane.PALETTE_LAYER, 0);
        //endregion

        // add LayeredPane in GameBoard JPane
        GameBoard.setLayout(new BorderLayout());
        GameBoard.add(LayeredPane);
    }
    private void initPlayerStatusImage(){
        //region >> display player image
        ImageIcon player1Image = new ImageIcon("assets/player1.png");
        Player1Image.setIcon(player1Image);
        ImageIcon player2Image = new ImageIcon("assets/player2.png");
        Player2Image.setIcon(player2Image);
        if(Game.number_of_players == 3){
            ImageIcon player3Image = new ImageIcon("assets/player3.png");
            Player3Image.setIcon(player3Image);
        }else{
            Player3StatusPanel.setVisible(false);
        }
        //endregion
    }
    private JLabel getTargetStatusLabel(int player_id, TreasureType treasure_type) throws Exception {
        JLabel target_label;
        if(player_id == 1){
            target_label = switch (treasure_type){
                case GHOST -> Player1Ghost;
                case ROLLERSKATE -> Player1RollerSkate;
                case OBSTACLE -> Player1Obstacle;
                case INVINCIBILITY -> Player1Invincibility;
                case DETONATOR -> Player1Detonator;
                case BOMB_POWER_UP -> null;
                case BOMB_INCREASE -> null;
                case BOMB_POWER_DOWN -> null;
                case FORBIDDEN -> null;
                case SPEED_DOWN -> null;
                default -> throw new Exception("Invalid Treasure type : " + treasure_type);
            };
        }else if(player_id == 2){
            target_label = switch (treasure_type){
                case GHOST -> Player2Ghost;
                case ROLLERSKATE -> Player2RollerSkate;
                case OBSTACLE -> Player2Obstacle;
                case INVINCIBILITY -> Player2Invincibility;
                case DETONATOR -> Player2Detonator;
                default -> throw new Exception("Invalid Treasure type : " + treasure_type);
            };
        } else if (player_id == 3) {
            target_label = switch (treasure_type){
                case GHOST -> Player3Ghost;
                case ROLLERSKATE -> Player3RollerSkate;
                case OBSTACLE -> Player3Obstacle;
                case INVINCIBILITY -> Player3Invincibility;
                case DETONATOR -> Player3Detonator;
                default -> throw new Exception("Invalid Treasure type : " + treasure_type);
            };
        }else{
            throw new Exception("Invalid player ID: " + player_id);
        }

        return target_label;
    }
    private void initPlayersPositions(){
        int index = 0;
        int x = 1;
        int y = 1;
        for(Player player : Game.players){
            switch (index){
                case 0:
                    x = 1;
                    y = 1;
                    break;
                case 1:
                    x = 1;
                    y = Game.map.getSize() - 2;
                    break;
                case 2:
                    x = Game.map.getSize() - 2;;
                    y = 1;
                    break;
            }
            player.setX(x);
            player.setY(y);
            index++;
        }
    }
    private void playerMove(int player_id, String direction) throws Exception {
        if(Game.players.get(player_id).move(direction)){
            Game.map.getLayers().get("Characters").update();
            LayeredPane.revalidate();
            LayeredPane.repaint();
        }
    }
    private Bomb playerPutBomb(int player_id){
        Bomb newBomb = Game.players.get(player_id).putBomb();
        Game.map.getLayers().get("Bombs").update();
        LayeredPane.revalidate();
        LayeredPane.repaint();
        return newBomb;
    }
    private void playerPutObstacle(int player_id){
        Game.players.get(player_id).putObstacle();
        Game.map.getLayers().get("Objects").update();
        LayeredPane.revalidate();
        LayeredPane.repaint();
    }
    private void short_timer_start() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (short_time <= 0) {
                    dispose();
                    timer.cancel();
                    Game.is_paused = true;
                    Game.is_round_finished = true;
                    new RoundResultScreen_GUI();
                } else {
                    short_time--;
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    private void updateLayer(String layer_name){
        Game.map.getLayers().get(layer_name).update();
        LayeredPane.revalidate();
        LayeredPane.repaint();
    }

    //actions for each button
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == MenuButton) {
            Game.is_paused = true;
            MenuBar_GUI menuBarGUI =  new MenuBar_GUI(this);
            menuBarGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    requestFocus();
                    Game.is_paused = false;
                }
            });
        }else if(e.getSource() == FinishButton) {
            this.dispose();
            Game.is_paused = true;
            Game.is_round_finished = true;
            new RoundResultScreen_GUI();
        }
    }
    // player controller
    @Override
    public void keyReleased(KeyEvent e) {
        // Processing when the key is released

        Player player1 = Game.players.get(0);
        Player player2 = Game.players.get(1);

        Bomb newBomb;

        switch (e.getKeyCode()) {
            //region >> player1 controller
            case KeyEvent.VK_UP:
                if(player1.isAlive() && !player1.is_cooling_down()){
                    try {
                        playerMove(0, "up");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_DOWN:
                if(player1.isAlive() && !player1.is_cooling_down()){
                    try {
                        playerMove(0, "down");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(player1.isAlive() && !player1.is_cooling_down()){
                    try {
                        playerMove(0, "right");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
                if(player1.isAlive() && !player1.is_cooling_down()){
                    try {
                        playerMove(0, "left");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_SHIFT:
                if(Game.players.get(0).isAlive()){
                    if(Game.players.get(0).hasDetonatorBomb()){
                        Game.players.get(0).explodeDetonatorBomb();
                    }else if (Game.players.get(0).isBombPlaceable()){
                        newBomb = playerPutBomb(0);
                        if(newBomb instanceof NormalBomb){//if generated bomb is NormalBomb, start thread
                            Thread normalBombThread = new Thread((NormalBomb)newBomb);
                            normalBombThread.start();
                        }
                        newBomb.setBombExplodeListener(this);//set GameScreen_GUI to the listener of explosion of newBomb
                        for(Player player: Game.players){
                            newBomb.setBombExplodeListener(player);
                        }
                        for(Monster monster: Game.monsters){
                            newBomb.setBombExplodeListener(monster);
                        }
                    }
                }
                break;
            case KeyEvent.VK_ENTER:
                if(Game.players.get(0).isAlive() && Game.players.get(0).isObstaclePlaceable()){
                    playerPutObstacle(0);
                }
                break;
            //endregion

            //region >> player2 controller
            case KeyEvent.VK_W:
                if(player2.isAlive() && !player2.is_cooling_down()){
                    try {
                        playerMove(1, "up");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_S:
                if(player2.isAlive() && !player2.is_cooling_down()){
                    try {
                        playerMove(1, "down");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_D:
                if(player2.isAlive() && !player2.is_cooling_down()){
                    try {
                        playerMove(1, "right");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_A:
                if(player2.isAlive() && !player2.is_cooling_down()){
                    try {
                        playerMove(1, "left");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_R:
                if(Game.players.get(1).isAlive()){
                    if(Game.players.get(1).hasDetonatorBomb()){
                        Game.players.get(1).explodeDetonatorBomb();
                    }else if (Game.players.get(1).isBombPlaceable()){
                        newBomb = playerPutBomb(1);
                        if(newBomb instanceof NormalBomb){//if generated bomb is NormalBomb, start thread
                            Thread normalBombThread = new Thread((NormalBomb)newBomb);
                            normalBombThread.start();
                        }
                        newBomb.setBombExplodeListener(this);
                        for(Player player: Game.players){
                            newBomb.setBombExplodeListener(player);
                        }
                        for(Monster monster: Game.monsters){
                            newBomb.setBombExplodeListener(monster);
                        }
                    }
                }
                break;
            case KeyEvent.VK_F:
                if(Game.players.get(1).isAlive() && Game.players.get(1).isObstaclePlaceable()){
                    playerPutObstacle(1);
                }
                break;
            //endregion

            //region >> player3 controller
            case KeyEvent.VK_U:
                if(Game.number_of_players == 3 && Game.players.get(2).isAlive() && !Game.players.get(2).is_cooling_down()){
                    try {
                        playerMove(2, "up");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_J:
                if(Game.number_of_players == 3 && Game.players.get(2).isAlive() && !Game.players.get(2).is_cooling_down()){
                    try {
                        playerMove(2, "down");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_K:
                if(Game.number_of_players == 3 && Game.players.get(2).isAlive() && !Game.players.get(2).is_cooling_down()){
                    try {
                        playerMove(2, "right");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_H:
                if(Game.number_of_players == 3 && Game.players.get(2).isAlive() && !Game.players.get(2).is_cooling_down()){
                    try {
                        playerMove(2, "left");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case KeyEvent.VK_O:
                if(Game.players.get(2).isAlive()){
                    if(Game.players.get(2).hasDetonatorBomb()){
                        Game.players.get(2).explodeDetonatorBomb();
                    }else if (Game.players.get(2).isBombPlaceable()){
                        newBomb = playerPutBomb(2);
                        if(newBomb instanceof NormalBomb){//if generated bomb is NormalBomb, start thread
                            Thread normalBombThread = new Thread((NormalBomb)newBomb);
                            normalBombThread.start();
                        }
                        newBomb.setBombExplodeListener(this);
                        for(Player player: Game.players){
                            newBomb.setBombExplodeListener(player);
                        }
                        for(Monster monster: Game.monsters){
                            newBomb.setBombExplodeListener(monster);
                        }
                    }
                }
                break;
            case KeyEvent.VK_P:
                if(Game.players.get(2).isAlive() && Game.players.get(2).isObstaclePlaceable()){
                    playerPutObstacle(2);
                }
                break;
            //endregion
        }
    }
    // Implementation of explosion event listeners
    @Override
    public void bombExploded() {
        Game.map.getLayers().get("Bombs").update();
        LayeredPane.revalidate();
        LayeredPane.repaint();
    }
    //Implementation of finish-explosion event listeners
    @Override
    public void bombFinishExplosion(){
        Game.map.getLayers().get("Bombs").update();
        LayeredPane.revalidate();
        LayeredPane.repaint();
    }
    @Override
    public void bombDestroyedBox() {
        Game.map.getLayers().get("Objects").update();
        LayeredPane.revalidate();
        LayeredPane.repaint();
    }
    @Override
    public void playerDie() {
        updateLayer("Characters");
        if(Game.getNumberOfAlivePlayers() == 1){
            short_timer_start();
        }
    }
    @Override
    public void monsterDie() {
        updateLayer("Characters");
    }
    @Override
    public void PlayerStatusChanged(int player_id, TreasureType treasure_type) throws Exception {
        PlayerStatusImageAdd(player_id, treasure_type);
        updateLayer("Characters");
    }
    @Override
    public void PlayerStatusChangedTimeUp(int player_id, TreasureType treasure_type) throws Exception {
        PlayerStatusImageRemove(player_id, treasure_type);
        updateLayer("Characters");
    }
    @Override
    public void PlayerGetTreasure(){
        updateLayer("Objects");
    }

    //region >> we don't need to implement it
    @Override
    public void keyTyped(KeyEvent e) {
    }
    //we don't need to implement it
    @Override
    public void keyPressed(KeyEvent e) {
    }

    //endregion
}
