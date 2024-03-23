package UI;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HotBar {

    GamePanel gp;
    BufferedImage hotbar, hotbar_sel, sword, life_support, life_cell;

    KeyHandler keys;
    int lastIndex = 0;
    int currentIndex = 0;

    boolean[] hotBarIndex;
    public HotBar(GamePanel gp, KeyHandler keys)
    {
        this.gp = gp;
        getImage();
        hotBarIndex = new boolean[5];
        this.keys = keys;
    }

    void getImage()
    {
        try{
            hotbar = ImageIO.read(getClass().getResourceAsStream("/UserInterface/Hotbar.png"));
            hotbar_sel = ImageIO.read(getClass().getResourceAsStream("/UserInterface/Hotbar_sel.png"));
            sword = ImageIO.read(getClass().getResourceAsStream("/UserInterface/Sword.png"));
            life_support = ImageIO.read(getClass().getResourceAsStream("/UserInterface/Player_life_support.png"));
            life_cell = ImageIO.read(getClass().getResourceAsStream("/UserInterface/Player_life_cell.png"));
        }catch(IOException e)
        {
            System.out.println("Nu s-a putut deschide hotbar.png");
        }
    }

    public void draw(Graphics2D g2)
    {
        g2.drawImage(hotbar, gp.tileSize, gp.screenHeight-3*gp.tileSize, 2*gp.playerTileSize*5, 2*gp.playerTileSize, null);
        g2.drawImage(sword, gp.tileSize, gp.screenHeight-3*gp.tileSize, 2*gp.playerTileSize, 2*gp.playerTileSize, null);
        g2.drawImage(hotbar_sel, (1+2*currentIndex)*gp.tileSize, gp.screenHeight-3*gp.tileSize, 2*gp.tileSize/*playerTileSize*/, 2*gp.tileSize/*playerTileSize*/, null);
        g2.drawImage(life_support, 48, 48, 300, 48, null);
        for(int i=0; i<gp.player.getHealth(); i++)
        {
            g2.drawImage(life_cell, 48 + 3*i, 48, 3, 48, null);
        }
    }

    public void update()
    {
        if(keys.isKeyActive(KeyEvent.VK_1) && gp.player.getWep()[0])
        {
            currentIndex = 0;
            hotBarIndex[lastIndex] = false;
            hotBarIndex[currentIndex] = true;
            lastIndex = currentIndex;
        }
        else if(keys.isKeyActive(KeyEvent.VK_2) && gp.player.getWep()[1])
        {
            currentIndex = 1;
            hotBarIndex[lastIndex] = false;
            hotBarIndex[currentIndex] = true;
            lastIndex = currentIndex;
        }
        else if(keys.isKeyActive(KeyEvent.VK_3) && gp.player.getWep()[2])
        {
            currentIndex = 2;
            hotBarIndex[lastIndex] = false;
            hotBarIndex[currentIndex] = true;
            lastIndex = currentIndex;
        }
        else if(keys.isKeyActive(KeyEvent.VK_4) && gp.player.getWep()[3])
        {
            currentIndex = 3;
            hotBarIndex[lastIndex] = false;
            hotBarIndex[currentIndex] = true;
            lastIndex = currentIndex;
        }else if(keys.isKeyActive(KeyEvent.VK_5) && gp.player.getWep()[4])
        {
            currentIndex = 4;
            hotBarIndex[lastIndex] = false;
            hotBarIndex[currentIndex] = true;
            lastIndex = currentIndex;
        }
    }
}
