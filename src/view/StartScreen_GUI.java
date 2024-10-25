package view;

import model.*;
import model.Tile.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class StartScreen_GUI extends JFrame implements ActionListener {
    //region >> private attributes
    private JPanel MainPanel;
    private JButton btn_r1;
    private JButton btn_r2;
    private JButton btn_r3;
    private JButton btn_m1;
    private JButton btn_m2;
    private JButton btn_m3;
    private JButton btn_p2;
    private JButton btn_p3;
    private JButton btn_start;
    private JButton btn_exit;
    private JLabel number_of_round;
    private JLabel number_of_player;
    private JLabel type_of_map;
    private JPanel Title;
    //endregion

    private void update_config_view(){
        number_of_player.setText(String.valueOf(Game.number_of_players));
        number_of_round.setText(String.valueOf(Game.number_of_rounds));
        type_of_map.setText(Game.map.getType());
    }

    public StartScreen_GUI(){
        Game.musicPlayer.titleMusicStart();

        //region >> change font size and type of Title (BOMBERMAN!)
        ImageIcon title_icon = new ImageIcon("assets/title.png");
        JLabel title_label = new JLabel(title_icon);
        Title.add(title_label);
        //endregion

        //region >> Form button groups to control input.
        ButtonGroup btn_group_round = new ButtonGroup();
        btn_group_round.add(btn_r1);
        btn_group_round.add(btn_r2);
        btn_group_round.add(btn_r3);

        ButtonGroup btn_group_player = new ButtonGroup();
        btn_group_player.add(btn_p2);
        btn_group_player.add(btn_p3);

        ButtonGroup btn_group_map = new ButtonGroup();
        btn_group_player.add(btn_m1);
        btn_group_player.add(btn_m2);
        btn_group_player.add(btn_m3);
        //endregion

        //region >> Register button action listeners
        btn_p2.addActionListener(this);
        btn_p3.addActionListener(this);
        btn_m1.addActionListener(this);
        btn_m2.addActionListener(this);
        btn_m3.addActionListener(this);
        btn_r1.addActionListener(this);
        btn_r2.addActionListener(this);
        btn_r3.addActionListener(this);

        btn_start.addActionListener(this);
        btn_start.setFont(new Font("Courier New", Font.BOLD, 50));
        btn_start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btn_start.setPreferredSize(new Dimension(400, 100));

        btn_exit.addActionListener(this);
        btn_exit.setFont(new Font("Courier New", Font.BOLD, 50));
        btn_exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btn_exit.setPreferredSize(new Dimension(400, 100));
        //endregion

        //region >> Eliminate the focus function of all buttons. (Not important)
        btn_p2.setFocusable(false);
        btn_p3.setFocusable(false);
        btn_m1.setFocusable(false);
        btn_m2.setFocusable(false);
        btn_m3.setFocusable(false);
        btn_r1.setFocusable(false);
        btn_r2.setFocusable(false);
        btn_r3.setFocusable(false);
        btn_start.setFocusable(false);
        btn_exit.setFocusable(false);
        //endregion


        //region >> Game refresh
        Game.RefreshMode();
        this.update_config_view();
        //endregion

        setTitle("BOMBERMAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //The method to set the behavior when the JFrame window is closed by user


        this.setContentPane(this.MainPanel);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_m1) {
            Game.map = new Map("SmallMap");
        } else if (e.getSource() == btn_m2) {
            Game.map = new Map("MediumMap");
        } else if (e.getSource() == btn_m3) {
            Game.map = new Map("LargeMap");
        }

        if (e.getSource() == btn_p2) {
            Game.number_of_players = 2;
        } else if (e.getSource() == btn_p3) {
            Game.number_of_players = 3;
        }

        if (e.getSource() == btn_r1) {
            Game.number_of_rounds = 1;
        } else if (e.getSource() == btn_r2) {
            Game.number_of_rounds = 2;
        } else if (e.getSource() == btn_r3) {
            Game.number_of_rounds = 3;
        }

        if(e.getSource() == btn_start){
            this.dispose();
            int index = 0;
            //Players object should be generated here since they will be used in the whole game.
            while(index < Game.number_of_players){
                switch (index){
                    case 0:
                        Player player1 = new Player("Player1.png", 1);
                        if(!Game.players.contains(player1)){
                            Game.players.add(player1);
                            break;
                        }
                        break;
                    case 1:
                        Player player2 = new Player("Player2.png", 2);
                        if(!Game.players.contains(player2)){
                            Game.players.add(player2);
                            break;
                        }
                        break;
                    case 2:
                        Player player3 = new Player("Player3.png", 3);
                        if(!Game.players.contains(player3)){
                            Game.players.add(player3);
                            break;
                        }
                }
                index++;
            }
            Monster basicMonster = new Monster("basicMonster.png");
            ConfusedMonster confusedMonster = new ConfusedMonster("confusedMonster.png");
            GhostMonster ghostMonster = new GhostMonster("ghostMonster.png");
            ChaserMonster chaserMonster = new ChaserMonster("chaserMonster.png");

            Game.monsters.add(basicMonster);
            Game.monsters.add(confusedMonster);
            Game.monsters.add(ghostMonster);
            Game.monsters.add(chaserMonster);
            new GameScreen_GUI();
        }

        if (e.getSource() == btn_exit) {
            System.exit(0);
        }

        this.update_config_view();
    }
}
