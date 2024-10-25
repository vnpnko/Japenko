package view;

import model.Game;
import model.Tile.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoundResultScreen_GUI extends JFrame implements ActionListener {
    private JPanel MainPanel;
    private JButton nextRoundButton;
    private JPanel ResultTable;
    private JLabel WinnerLabel;
    private JLabel crownLabel;
    private JLabel RoundResult;
    private JPanel P1WinCountPanel;
    private JPanel P2WinCountPanel;
    private JPanel P3WinCountPanel;
    private JLabel p1WinCount;
    private JLabel p2WinCount;
    private JLabel p3WinCount;
//    private JLabel WinnerLabel;

    public RoundResultScreen_GUI() {
        Player player1 = Game.players.get(0);
        Player player2 = Game.players.get(1);

        //region >> set text into WinnerLabel
        Player winner = null;
        for (Player player : Game.players) {
            if(player.isAlive()){
                winner = player;
                break;
            }
        }
        if(winner != null){
            WinnerLabel.setText(winner.getDisplayName());
            winner.increaseScore();
        }else{
            crownLabel.setVisible(false);
            WinnerLabel.setText("DRAW");
        }
        WinnerLabel.setBackground(new Color(235, 217, 0));
        WinnerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        WinnerLabel.setPreferredSize(new Dimension(120, 60));
        //endregion

        this.setTitle("Round Result");
        RoundResult.setText("Round " + String.valueOf(Game.current_round));

        ResultTable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ResultTable.setPreferredSize(new Dimension(400, 300));

        ImageIcon crownImage = new ImageIcon("assets/crown.png");
        Image scaledImage = crownImage.getImage().getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH);
        crownLabel.setIcon(new ImageIcon(scaledImage));

        p1WinCount.setText(String.valueOf(player1.getScore()));
        P1WinCountPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        P1WinCountPanel.setPreferredSize(new Dimension(120, 100));

        p2WinCount.setText(String.valueOf(player2.getScore()));
        P2WinCountPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        P2WinCountPanel.setPreferredSize(new Dimension(120, 100));

        if(Game.number_of_players == 3){
            Player player3 = Game.players.get(2);
            p3WinCount.setText(String.valueOf(player3.getScore()));
        }else{
            P3WinCountPanel.setVisible(false);
        }

        nextRoundButton.addActionListener(this);
        nextRoundButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        nextRoundButton.setFont(new Font("Courier New", Font.BOLD, 50));
        nextRoundButton.setPreferredSize(new Dimension(400, 100));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.MainPanel);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        Game.current_round++;
        if(Game.current_round <= Game.number_of_rounds){
            new GameScreen_GUI();
        }
        else{
            Game.is_finished = true;
            new ResultScreen_GUI();
        }
    }
}
