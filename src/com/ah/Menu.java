package com.ah;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Menu extends JPanel{


    public  Menu(){ // Skapar meny

        setBorder(new EmptyBorder(300,350,350,300));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("D 0 N G - B A L L\n"), gbc);    // Label på frame
        ISound.playSound("bg_sound.wav");          // Spelar upp BG ljud

        SetAnchorAndFill(gbc);
        AddButtons(gbc);
    }


    private void SetAnchorAndFill(GridBagConstraints gbc) // Centrerar +  knappar vertikal
    {
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.VERTICAL;

    }

    private void AddButtons(GridBagConstraints gbc)  // Skapar knappar
    {
        JPanel b = new JPanel(new GridBagLayout());
        // b = buttons
        JButton playButton = new JButton("Play Game"); // Play knapp
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) Main.getContentPanel().getLayout()).show(Main.getContentPanel(),"Game");
                Main.getGamePanel().requestFocusInWindow();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Main.getGamePanel().startGame();
                    }
                }).start();
            }
        });
        b.add(playButton, gbc);

        JButton exitButton = new JButton("EXIT"); // Exit program
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        b.add(exitButton,gbc);

        gbc.weighty = 100;
        add(b, gbc);
    }
}