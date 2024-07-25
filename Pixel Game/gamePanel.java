import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class gamePanel extends JPanel implements Runnable{
    
    // variables

    final int originalTileSize = 16; //16 x 16 tiles
    final int scale = 5; // factor to scale by

    final public int tileSize = originalTileSize * scale; // scaled tileSize
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = maxScreenColumn * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    keyHandler keyH = new keyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);
    CollisionHandler collisionHandler = new CollisionHandler(this);

    // WORLD SETTINGS
    public final int maxWorldColumn = 20; // MAKE SURE BOTH MAXWORLDCOLUMN AND MAXWORLDROW ARE THE CORRECT NUMBER OF COLUMNS AND ROWS IN THE MAP!!!
    public final int maxWorldRow = 20;    // (extra rows just creates extra grass at bottom, less just deletes lines, extra columns breaks the game, less columns deletes columns at the end)
    public final int worldWidth = maxWorldColumn * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // FPS
    int FPS = 60;

    public gamePanel(){
        this.addKeyListener(keyH);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){

        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.rePaint(g2);

        g2.dispose();
    }
}