package view;

import model.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultScreen_GUI extends JFrame implements ActionListener {
    private JPanel MainPanel;
    private JButton btn_startsc;
    private JPanel Players;

    public ResultScreen_GUI() {
        this.setTitle("RESULT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        for (int i = 1; i < Game.number_of_players+1; i++) {
//            JLabel player = new JLabel("Player " + i + ": " + Game.players.get(i - 1).getScore());
//            Player.add(player);
//        }
        Players.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 150));

        for (int i = 1; i <= Game.number_of_players; i++) {
            JLabel playerLabel = new JLabel();
            playerLabel.setText("Player " + i + ": " + Game.players.get(i-1).getScore());
            playerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
            playerLabel.setPreferredSize(new Dimension(400, 200));
            playerLabel.setOpaque(true);
            Color color = switch (i) {
                case 1 -> new Color(246,198,173,255);
                case 2 -> new Color(229,158,221,255);
                default -> new Color(180,229,162,255);
            };
            playerLabel.setBackground(color);
            playerLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel playerPanel = new JPanel();
            playerPanel.add(playerLabel);
            playerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            playerPanel.setBackground(new Color(0, 0, 0));
            Players.add(playerPanel);

            JSeparator horizontalseparator = new JSeparator(SwingConstants.HORIZONTAL);
            horizontalseparator.setPreferredSize(new Dimension(10, 0));
            Players.add(horizontalseparator);
        }

        btn_startsc.addActionListener(this);
        btn_startsc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btn_startsc.setFont(new Font("Courier New", Font.BOLD, 50));
        btn_startsc.setPreferredSize(new Dimension(400, 100));

        this.setContentPane(this.MainPanel);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)   {
        if (e.getSource() == btn_startsc) {
            this.dispose();
            Game.RefreshMode();
            new StartScreen_GUI();
        }
    }
}
