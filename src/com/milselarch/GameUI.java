package com.milselarch;

import java.awt.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameUI extends JFrame implements Commons {
    private static Board board;

    public GameUI() {
        initUI();
    }

    private void initUI() {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");

        menubar.add(file);
        setJMenuBar(menubar);

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        ImageIcon exit = new ImageIcon("exit.png");
        JButton bexit = new JButton(exit);
        bexit.setBorder(new EmptyBorder(0, 0, 0, 0));
        toolbar.add(bexit);

        add(toolbar, BorderLayout.NORTH);

        //JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
        //vertical.setFloatable(false);
        //vertical.setMargin(new Insets(10, 5, 5, 5));

        ImageIcon driveIcon = new ImageIcon("drive.png");
        ImageIcon compIcon = new ImageIcon("computer.png");
        ImageIcon printIcon = new ImageIcon("printer.png");

        JButton driveBtn = new JButton(driveIcon);
        driveBtn.setBorder(new EmptyBorder(3, 0, 3, 0));

        JButton compBtn = new JButton(compIcon);
        compBtn.setBorder(new EmptyBorder(3, 0, 3, 0));
        JButton printBtn = new JButton(printIcon);
        printBtn.setBorder(new EmptyBorder(3, 0, 3, 0));

        //vertical.add(driveBtn);
        //vertical.add(compBtn);
        //vertical.add(printBtn);
        //add(vertical, BorderLayout.WEST);

        //make statusbar
        JLabel statusbar = new JLabel(" Statusbar");
        add(statusbar, BorderLayout.SOUTH);

        this.insertGame();

        //configure JFrame
        this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        this.setTitle("BorderLayout");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        this.setResizable(false);

        //this.board.setSize(this.board.getSize());
        //System.out.println(this.getScrollxy().getSize());
        this.board.setSize(
            new Dimension(
                this.getWidth(),
                this.board.getSize().height
            )
        );

        this.board.gameInit();
    }

    private void insertGame() {
        //scrollxy.setViewportBorder(null);

        this.board = new Board(true,this);
        //this.board.gameInit();

        //this.board.setVisible(true);
        add(this.board, BorderLayout.CENTER);
        this.board.setVisible(true);
        //this.board.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        //this.board.setSize(scrollxy.getSize());
    }

    public static void main(String[] args) {
        /*
        SwingUtilities.invokeLater(() -> {
            GameUI ex = new GameUI();
            ex.setVisible(true);
        });
        */

        EventQueue.invokeLater(() -> {
            GameUI ex = new GameUI();
            ex.setVisible(true);
        });
    }
}