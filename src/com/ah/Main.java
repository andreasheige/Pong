package com.ah;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    // Skapar Frames

    final static int width = 750;
    final static int height = 600;
    private static JFrame frame;
    private static JPanel contentPanel;
    private static Game gamePanel;
    private static Game startGame;
    private static Menu menuPanel;

    static void showMenu() {
        ((CardLayout) getContentPanel().getLayout()).show(getContentPanel(),"Manu");
    }

    public static JPanel getContentPanel() {
        return contentPanel;
    }

    public static Game getGamePanel() {
        return gamePanel;
    }


    public static void main(String[] args) throws IOException {
        frame = new JFrame("D 0 N G - B A L L");
        frame.setSize(width,height);

        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(new Menu(), "Menu");
        gamePanel = new Game(startGame);
        contentPanel.add(gamePanel, "Game");

        frame.add(contentPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);

            }
        });
    }

}
