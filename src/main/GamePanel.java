/*
package main;

import javax.swing.*;
import java.awt.*;

import Tiles.Tile;
import Tiles.TileManager;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    //screen settings

    public final int playerTileSize = 48;
    public final int tileSize = 48;
    public final int maxScreenCol = 34; //40
    public final int maxScreenRow = 17; //22
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //world map parameters

    public final int maxWorldCol = 135;
    public final int maxWorldRow = 85;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public final int layer = 0;

    //FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public CollisionChecker Colchk = new CollisionChecker(this);

   public Player player = new Player(this, keyH);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() // uses run() method
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        double timer = 0;
        double drawCount = 0;

        while(gameThread != null){



            update();
            repaint();
            drawCount++;

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                timer += remainingTime;
                remainingTime = remainingTime /1000000;
                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime +=drawInterval;
                if(timer >= 1000000000)
                {
                    //System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw_layer(g2, 0);
        tileM.draw_layer(g2, 1);

        player.draw(g2);

        tileM.draw_layer(g2, 2);

        g2.dispose();
    }
}


*/
package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;


import Tiles.TileManager;
import UI.HotBar;
import entity.*;

public class GamePanel extends Canvas implements Runnable {
    //screen settings

    public final int playerTileSize = 48;
    public final int tileSize = 48;
    public final int maxScreenCol = 34;
    public final int maxScreenRow = 17;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //world map parameters

    public final int maxWorldCol = 135;
    public final int maxWorldRow = 85;

    //FPS
    int FPS = 60;

    DataBase DB = new DataBase(this);

    public TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    HotBar hotbar = new HotBar(this, keyH);

    public CollisionChecker tileCollision = new CollisionChecker(this);
    public EntityCollisionChecker entityCollison = new EntityCollisionChecker(this);

    public Player player = new Player(this, keyH);
    //public Green_N_Zombie zomb = new Green_N_Zombie(this, player);

    //public Green_VN_Zombie(GamePanel gp, Player player, int X, int Y, int speed, int health, int attack_damage)

    public Green_N_Zombie GN1_1, GN2_1;
    public Brown_N_Zombie BN1_1, BN2_1;
    public Green_B_Zombie GB_1;
    public Green_VN_Zombie GVN1_2, GVN2_2;
    public Brown_VN_Zombie BVN1_2, BVN2_2;
    public Green_B_Zombie GB1_2, GB2_2;

    public Green_N_Zombie GN1_3, GN2_3;
    public Brown_N_Zombie BN1_3, BN2_3;
    public Green_VN_Zombie GVN1_3, GVN2_3;
    public Brown_VN_Zombie BVN1_3, BVN2_3;
    public Green_B_Zombie GB1_3, GB2_3;
    public Brown_B_Zombie BB_3;
    public Entity[] SetUp_level1 = new Entity[5];
    public Entity[] SetUp_level2 = new Entity[6];
    public Entity[] SetUp_level3 = new Entity[11];




   public boolean flag_lvl_1;
    public boolean flag_lvl_2;
    public boolean flag_lvl_3;
    public boolean flag_inamici;

    boolean gamePaused = true;





    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        SetUp_lvl1();
        SetUp_lvl2();
        SetUp_lvl3();
    }

    public void startGameThread() // uses run() method
    {
        gameThread = new Thread(this);
        gameThread.start();
        flag_lvl_1 = true;
    }

    @Override
    public void run() {

        createBufferStrategy(3);
        BufferStrategy bufferStrategy = getBufferStrategy();

        if (bufferStrategy == null) {
            createBufferStrategy(3);
            bufferStrategy = getBufferStrategy();
        }

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        double timer = 0;
        double drawCount = 0;

        while(gameThread != null){
            System.out.println();
            if(keyH.isKeyActive(KeyEvent.VK_P))
            {
                gamePaused = false;
                DB.write(player.getHealth());
                System.out.println("Sa scris in baza de date");
            }

            if(gamePaused)
            {
                Graphics2D g2 = (Graphics2D) bufferStrategy.getDrawGraphics();

                update();
                //System.out.println("S-a mai facut un update");
                g2.clearRect(0, 0, getWidth(), getHeight());
                paintComponent(g2);
                bufferStrategy.show();
                g2.dispose();


            }

            if(keyH.isKeyActive(KeyEvent.VK_ESCAPE))
            {
                gamePaused = true;
                System.out.println("S-a executat ESC");
            }

            drawCount++;

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                timer += remainingTime;
                remainingTime = remainingTime /1000000;
                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime +=drawInterval;
                if(timer >= 1000000000)
                {
                    //System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }





    }

    public void update()
    {
        if(player.getHealth() > 0)
        {
            player.update();
        }

        if(flag_lvl_1)
        {
            flag_inamici = true;
            //System.out.println("se executa ceva pe aici?");
            for (int i = 0; i < SetUp_level1.length; i++) {

                if(SetUp_level1[i] != null)
                {
                    SetUp_level1[i].update();
                    flag_inamici = false;
                }
            }
            if(flag_inamici == true)
            {
                flag_lvl_1 = false;
                flag_lvl_2 = true;
                open_gate_1();
            }

        }
        else if(flag_lvl_2)
        {
            flag_inamici = true;
            for (int i = 0; i < SetUp_level2.length; i++) {

                if(SetUp_level2[i] != null)
                {
                    SetUp_level2[i].update();
                    flag_inamici = false;
                }
            }
            if(flag_inamici == true)
            {
                flag_lvl_2 = false;
                flag_lvl_3 = true;
                open_gate_2();
            }
        }
        else if(flag_lvl_3)
        {
            flag_inamici = true;
            for (int i = 0; i < SetUp_level3.length; i++) {

                if(SetUp_level3[i] != null)
                {
                    SetUp_level3[i].update();
                    flag_inamici = false;
                }
            }
            if(flag_inamici == true)
            {
                flag_lvl_3 = false;
                //gameThread = null;
            }
        }
        hotbar.update();
    }

    public void paintComponent(Graphics2D g2)
    {
        super.paint(g2);
        g2.setColor(Color.RED);
        tileM.draw_layer(g2, 0);
        tileM.draw_layer(g2, 1);

        if(flag_lvl_1)
        {
            //System.out.println("Se deseneaza ceva pe aici?");
            for (int i = 0; i < SetUp_level1.length; i++) {

                if(SetUp_level1[i] != null)
                {
                    SetUp_level1[i].draw(g2);

                }
            }
        }
        else if(flag_lvl_2)
        {
            for (int i = 0; i < SetUp_level2.length; i++) {

                if(SetUp_level2[i] != null)
                {
                    SetUp_level2[i].draw(g2);
                }
            }
        }
        else if(flag_lvl_3)
        {

            for (int i = 0; i < SetUp_level3.length; i++) {

                if(SetUp_level3[i] != null)
                {
                    SetUp_level3[i].draw(g2);
                }
            }
        }

        player.draw(g2);

        tileM.draw_layer(g2, 2);

        hotbar.draw(g2);

        g2.dispose();
    }

    public void SetUp_lvl1()
    {
         GN1_1 = new Green_N_Zombie(this, player, 32*tileSize, 11*tileSize, 2, 100, 5);
         GN2_1 = new Green_N_Zombie(this, player, 41*tileSize, 12*tileSize, 2, 100, 5);
         BN1_1 = new Brown_N_Zombie(this, player, 16*tileSize, 7*tileSize, 2, 100, 5);
         BN2_1 = new Brown_N_Zombie(this, player, 12*tileSize, 15*tileSize, 2, 100, 5);
         GB_1 = new Green_B_Zombie(this, player, 45*tileSize, 22*tileSize, 3, 100, 10);
         SetUp_level1[0] = GN1_1;
        SetUp_level1[1] =GN2_1;
        SetUp_level1[2] =BN1_1;
        SetUp_level1[3] =BN2_1;
        SetUp_level1[4] =GB_1;
    }
    public void SetUp_lvl2()
    {
        GVN1_2 = new Green_VN_Zombie(this, player, 31*tileSize, 73*tileSize, 2, 100, 10);
        GVN2_2 = new Green_VN_Zombie(this, player, 31*tileSize, 74*tileSize, 2, 100, 10);
        BVN1_2 = new Brown_VN_Zombie(this, player, 115*tileSize, 73*tileSize, 2, 100, 10);
        BVN2_2 = new Brown_VN_Zombie(this, player, 118*tileSize, 76*tileSize, 2, 100, 10);
        GB1_2 = new Green_B_Zombie(this, player, 123*tileSize, 77*tileSize, 3, 100, 15);
        GB2_2= new Green_B_Zombie(this, player, 125*tileSize, 77*tileSize, 3, 100, 15);
        SetUp_level2[0] = GVN1_2;
        SetUp_level2[1]=GVN2_2;
        SetUp_level2[2]=BVN1_2;
        SetUp_level2[3]=BVN2_2;
        SetUp_level2[4]=GB1_2;
        SetUp_level2[5] = GB2_2;
    }
    public void SetUp_lvl3()
    {
         GN1_3 = new Green_N_Zombie(this, player, 122*tileSize, 15*tileSize, 2, 100, 15);
         GN2_3 = new Green_N_Zombie(this, player, 122*tileSize, 16*tileSize, 2, 100, 15);
         BN1_3 = new Brown_N_Zombie(this, player, 123*tileSize, 16*tileSize, 2, 100, 15);
         BN2_3 = new Brown_N_Zombie(this, player, 123*tileSize, 15*tileSize, 2, 100, 15);
         GVN1_3 = new Green_VN_Zombie(this, player, 94*tileSize, 13*tileSize, 2, 100, 20);
         GVN2_3 = new Green_VN_Zombie(this, player, 94*tileSize, 15*tileSize, 2, 100, 20);
         BVN1_3 = new Brown_VN_Zombie(this, player, 96*tileSize, 15*tileSize, 2, 100, 20);
         BVN2_3 = new Brown_VN_Zombie(this, player, 96*tileSize, 12*tileSize, 2, 100, 20);
         GB1_3 = new Green_B_Zombie(this, player, 109*tileSize, 21*tileSize, 3, 100, 30);
         GB2_3 = new Green_B_Zombie(this, player, 109*tileSize, 23*tileSize, 3, 100, 30);
         BB_3 = new Brown_B_Zombie(this, player, 116*tileSize, 18*tileSize, 3, 100, 30);
        SetUp_level3[0]=GN1_3;
        SetUp_level3[1]=GN2_3;
        SetUp_level3[2]=BN1_3;
        SetUp_level3[3]=BN2_3;
        SetUp_level3[4]=GVN1_3;
        SetUp_level3[5]=GVN2_3;
        SetUp_level3[6]=BVN1_3;
        SetUp_level3[7]=BVN2_3;
        SetUp_level3[8]=GB1_3;
        SetUp_level3[9]=GB2_3;
        SetUp_level3[10]=BB_3;
    }

    public void open_gate_1()
    {
        tileM.set_map0(1, 68, 21);
    }

    public void open_gate_2()
    {
        tileM.set_map0(1, 95, 62);
    }
}


