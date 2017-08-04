package com.milselarch;

/**
 * Created by user on 24/7/2017.
 */

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class Board extends JPanel implements Runnable, Commons {
    public int worldx = 0;
    public int worldy = 0;
    public double mousex = 1;
    public double mousey = 1;

    private Dimension d;
    ArrayList<Monster> monsters;
    ArrayList<CleanWater> cleanWaters;
    ArrayList<PShot> pshots;
    ArrayList<EShot> eshots;
    public Player player;

    private GameUI frame;

    //TAdapter eventListener;

    private final int ALIEN_INIT_X = 150;
    private final int ALIEN_INIT_Y = 5;
    private int direction = -1;
    private int deaths = 0;

    private boolean ingame = true;
    private boolean winGame = false;
    private final String explImg = "src/images/explosion.png";
    private String message = "Game Over";

    private Thread animator;

    Board(GameUI frame) {
        this.frame = frame;
    }

    Board(boolean start, GameUI frame) {
        this.frame = frame;

        if (start) {
            initBoard();
        }
    }

    private void initBoard() {
        //this.eventListener = new TAdapter();
        //addKeyListener(new TAdapter());
        setFocusable(true);
        this.frame.setVisible(true);

        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.white);

        gameInit();
        this.setEventListener();
        setDoubleBuffered(true);
    }

    public boolean isIngame() {
        return this.ingame;
    }

    public Player getPlayer() {
        //System.out.println(this.player);
        return this.player;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    public void gameInit() {
        this.monsters = new ArrayList<>();
        this.cleanWaters = new ArrayList<>();
        this.pshots = new ArrayList<>();
        this.eshots = new ArrayList<>();

        RandomRange random = new RandomRange();

        for (int i = 0; i < 200; i++) {
            Monster monster = new Monster(
                this,
                random.rand(0, WORLD_WIDTH),
                random.rand(0, WORLD_HEIGHT)
            );

            monsters.add(monster);
        }

        for (int i = 0; i < 6; i++) {
            CleanWater water = new CleanWater(
                this,
                random.rand(0, WORLD_WIDTH),
                random.rand(0, WORLD_HEIGHT)
            );

            cleanWaters.add(water);
        }

        this.player = new Player(this);

        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public int numOfCleanWaters() {
        return cleanWaters.size();
    }

    public void drawAliens(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Iterator it = monsters.iterator();

        for (Monster monster : monsters) {
            monster.act();

            //System.out.println(player.getX());

            Vector distance = new Vector(
                player.getX() - monster.getX(),
                player.getY() - monster.getY()
            );

            monster.charge(distance);
            monster.draw(g2d);
        }
    }

    public void drawPlayer(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        player.act();
        player.draw(g2d);
    }

    private void updateMousePosition() {
        Point mousePos = this.getMousePosition();

        if (mousePos != null) {
            this.mousex = mousePos.getX();
            this.mousey = mousePos.getY();
        }
    }

    public void drawCleanWaters(Graphics g) {
        for (int index = cleanWaters.size() - 1; index > 0; index--) {
            CleanWater water = cleanWaters.get(index);
            if (player.isColliding(water)) {
                cleanWaters.remove(index);
                player.resetHealth();
                this.frame.updateStatusBar();
                continue;
            }

            water.draw(g);
        }
    }

    public void drawShots(Graphics g) {
        for (int index = pshots.size() - 1; index > 0; index--) {
            PShot pshot = pshots.get(index);
            pshot.act();

            Monster hitMonster = pshot.getHitMonster();
            if (hitMonster != null) {
                hitMonster.stun();
                pshots.remove(index);
                continue;
                //System.out.println("HIT");
            }

            if (pshot.isHitingWall()) {
                pshots.remove(index);
                continue;
            }

            pshot.draw(g);
        }

        for (int index = eshots.size() - 1; index > 0; index--) {
            EShot eshot = eshots.get(index);
            eshot.act();

            if (eshot.isHitingWall()) {
                eshots.remove(index);
                continue;
            }

            if (eshot.isColliding(player)) {
                eshots.remove(index);
                player.loseHealth();
                if (player.health == 0) {
                    this.ingame = false;
                }

                continue;
            }

            if (eshot.getHitStunnedMonster() != null) {
                eshots.remove(index);
                continue;
            }

            eshot.draw(g);
        }
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        this.d = d;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        //System.out.println(this.d);
        g.fillRect(0, 0, d.width, d.height);
        this.frame.updateStatusBar();

        if (this.isIngame()) {
            //super.paintComponent(g);
            //System.out.println("INGAME");
            this.updateMousePosition();

            g.setColor(Color.BLACK);
            g.fillRect(
                -this.worldx,
                -this.worldy,
                WORLD_WIDTH,
                WORLD_HEIGHT
            );

            if (ingame) {
                //System.out.println("WORLDX: " + this.worldx);
                //g.drawLine(0, GROUND, BOARD_WIDTH, GROUND); //draw center line
                drawAliens(g);
                drawCleanWaters(g);
                drawShots(g);
                drawPlayer(g);
                //drawBombing(g);
            }


        } else {
            this.gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void gameOver(Graphics g) {
        this.monsters.clear();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(
            50,
            BOARD_WIDTH / 2 - 30,
            BOARD_WIDTH - 100,
            50
        );

        g.setColor(Color.white);
        g.drawRect(
            50,
            BOARD_WIDTH / 2 - 30,
            BOARD_WIDTH - 100,
            (BOARD_HEIGHT) / 2
        );

        Font font = new Font(
            "Helvetica",
            Font.BOLD,
            30
        );

        FontMetrics metr = this.getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        message = "Game Over";
        g.drawString(
            message,
            (BOARD_WIDTH - metr.stringWidth(message)) / 2,
            BOARD_HEIGHT/2 - metr.getHeight()
        );

        message = "Only 3% of the world's water is fresh";
        g.drawString(
            message,
            (BOARD_WIDTH - metr.stringWidth(message)) / 2,
            BOARD_HEIGHT/2
        );

        message = "Press Enter to try again";
        g.drawString(
            message,
            (BOARD_WIDTH - metr.stringWidth(message)) / 2,
            BOARD_HEIGHT/2 + metr.getHeight()
        );

    }

    public void gameWin() {

    }

    public static double getCurrentTime() {
        return System.currentTimeMillis()/1000.0;
    }

    private void setEventListener() {
        this.frame.setFocusable(true);
        //adapter.setBoard(this.board);
        this.frame.addKeyListener(new TAdapter());
        this.addMouseListener(new MAdapter());
    }

    private class MAdapter extends MouseAdapter {
        //where initialization occurs:
        //Register for mouse events on blankArea and the panel.

        @Override
        public void mousePressed(MouseEvent e) {
            final int button = e.getButton();

            if (button == MouseEvent.BUTTON1) {
                //Dimension center = player.getCenter();
                player.isShooting = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            final int button = e.getButton();

            if (button == MouseEvent.BUTTON1) {
                player.isShooting = false;
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER) {
                //System.out.println("ENTER");

                if (!Board.this.ingame) {
                    //System.out.println("ENTER");
                    Board.this.ingame = true;
                    initBoard();
                }
            }

            //System.out.println(key);
        }
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {
            //System.out.println("POTATOXXX");

            this.repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();

        }
    }
}