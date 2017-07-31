package com.milselarch;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;


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

        JScrollPane scrollxy = new JScrollPane(
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        this.insertGame(scrollxy);

        //configure scrollxy JScrollPane
        scrollxy.setSize(new Dimension(400, 400));
        scrollxy.getViewport().setBackground(Color.GREEN);
        add(scrollxy, BorderLayout.CENTER);

        //make statusbar
        JLabel statusbar = new JLabel(" Statusbar");
        add(statusbar, BorderLayout.SOUTH);

        //configure JFrame
        this.setSize(700, 500);
        this.setTitle("BorderLayout");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        this.setResizable(false);
    }

    private void insertGame(JScrollPane scrollxy) {
        scrollxy.setVisible(true);
        this.board = new Board(true,this);
        this.board.gameInit();

        this.board.setVisible(true);
        scrollxy.add(this.board);
        this.board.setVisible(true);
        this.board.setSize(BOARD_WIDTH, BOARD_HEIGHT);
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