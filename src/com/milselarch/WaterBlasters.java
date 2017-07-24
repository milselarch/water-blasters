package com.milselarch;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class WaterBlasters extends JFrame implements Commons {

    public WaterBlasters() {

        initUI();
    }

    private void initUI() {

        add(new Board());
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            WaterBlasters ex = new WaterBlasters();
            ex.setVisible(true);
        });
    }
}