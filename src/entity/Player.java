package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.KeyEvent;

public class Player extends Entity{


    ////PENTRU ABILITATI FACI UN VECTOR DE LA 0 LA 3 de tip boolean unde retii daca ai dat pick la arme sau nu
    // adica sa stii daca poti sa le folosesti sau nu

    KeyHandler keyH;
    BufferedImage[] sw_attack;




    public Rectangle zombieCol;

     boolean[] owned_wep;
    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        screenPos = new Point(gp.screenWidth/2 - gp.tileSize/2, gp.screenHeight/2 - gp.tileSize/2);

        solidArea = new Rectangle(20, 40, 10, 8);
        attackArea = new Rectangle[4];
        damageArea = new Rectangle(12,6,24,42);
        zombieCol = new Rectangle();

        worldPos = new Point(5*gp.tileSize, 34*gp.tileSize);
        //worldPos = new Point(1580+3*gp.tileSize, 786);
        speed = 3;
        direction = "down";
        health = 100;

        solidArea.x += screenPos.x;
        solidArea.y += screenPos.y;
        damageArea.x += screenPos.x;
        damageArea.y += screenPos.y;

        attackArea[0] = new Rectangle(-10+screenPos.x, -10+screenPos.y, 68, 24);
        attackArea[1] = new Rectangle(-10+screenPos.x, 34+screenPos.y, 68, 24);
        attackArea[2] = new Rectangle(-10+screenPos.x, 0+screenPos.y, 24, 48);
        attackArea[3] = new Rectangle(34+screenPos.x, 0+screenPos.y, 24, 48);

        owned_wep = new boolean[5];
        sw_attack = new BufferedImage[4];
        owned_wep[0] = true;
        attack_damage = 10;

    }

    public void getPlayerImage()
    {
        try
        {
             up = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_walk_up.png"));
             down = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_walk_down.png"));
             left = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_walk_left.png"));
             right = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_walk_right.png"));
             idle = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_idle.png"));
             sw_attack[0] = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_attack_up.png"));
            sw_attack[1] = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_attack_down.png"));
            sw_attack[2] = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_attack_left.png"));
            sw_attack[3] = ImageIO.read(getClass().getResourceAsStream("/Player/sword_mov/sword_attack_right.png"));

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean[] getWep()
    {
        return owned_wep;
    }
    public void update()
    {
        if(keyH.isKeyActive(KeyEvent.VK_SPACE))
        {
            stop = System.currentTimeMillis();
            if(stop - start > 500)
            {
                attack = true;
            }
        }
        if(attack == true) {
            atk_counter++;
            if (atk_counter > 5) {
                if (atk_anim == 0) {
                    atk_anim = 1;
                } else if (atk_anim == 1) {
                    atk_anim = 2;
                } else if (atk_anim == 2) {
                    atk_anim = 3;
                } else if (atk_anim == 3) {
                    attack = false;
                    if(gp.flag_lvl_1)
                    {
                        for(int i= 0; i<gp.SetUp_level1.length; i++)
                        {
                            if(gp.SetUp_level1[i] != null)
                            {
                                switch (direction) {
                                    case "up" -> {
                                        if(gp.SetUp_level1[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[0], gp.SetUp_level1[i].getDamageArea().x, gp.SetUp_level1[i].getDamageArea().y, gp.SetUp_level1[i].getDamageArea()))
                                            {
                                                gp.SetUp_level1[i].setHealth(gp.SetUp_level1[i].getHealth()-this.attack_damage);
                                                gp.SetUp_level1[i].setAttack(false);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[0], gp.SetUp_level1[i].getDamageArea().x, gp.SetUp_level1[i].getDamageArea().y, gp.SetUp_level1[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "down" -> {
                                        if(gp.SetUp_level1[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[1], gp.SetUp_level1[i].getDamageArea().x, gp.SetUp_level1[i].getDamageArea().y, gp.SetUp_level1[i].getDamageArea()))
                                            {
                                                gp.SetUp_level1[i].setHealth(gp.SetUp_level1[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[1], gp.SetUp_level1[i].getDamageArea().x, gp.SetUp_level1[i].getDamageArea().y, gp.SetUp_level1[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "left" -> {
                                        if(gp.SetUp_level1[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[2], gp.SetUp_level1[i].getDamageArea().x, gp.SetUp_level1[i].getDamageArea().y, gp.SetUp_level1[i].getDamageArea()))
                                            {
                                                gp.SetUp_level1[i].setHealth(gp.SetUp_level1[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[2], gp.SetUp_level1[i].getDamageArea().x, gp.SetUp_level1[i].getDamageArea().y, gp.SetUp_level1[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "right" -> {
                                        if(gp.SetUp_level1[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[3], gp.SetUp_level1[i].getDamageArea().x, gp.SetUp_level1[i].getDamageArea().y, gp.SetUp_level1[i].getDamageArea()))
                                            {
                                                gp.SetUp_level1[i].setHealth(gp.SetUp_level1[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[3], gp.SetUp_level1[i].getDamageArea().x, gp.SetUp_level1[i].getDamageArea().y, gp.SetUp_level1[i].getDamageArea());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if(gp.flag_lvl_2){
                        for(int i= 0; i<gp.SetUp_level2.length; i++)
                        {
                            if(gp.SetUp_level2[i] != null)
                            {
                                switch (direction) {
                                    case "up" -> {
                                        if(gp.SetUp_level2[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[0], gp.SetUp_level2[i].getDamageArea().x, gp.SetUp_level2[i].getDamageArea().y, gp.SetUp_level2[i].getDamageArea()))
                                            {
                                                gp.SetUp_level2[i].setHealth(gp.SetUp_level2[i].getHealth()-this.attack_damage);
                                                gp.SetUp_level2[i].setAttack(false);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[0], gp.SetUp_level2[i].getDamageArea().x, gp.SetUp_level2[i].getDamageArea().y, gp.SetUp_level2[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "down" -> {
                                        if(gp.SetUp_level2[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[1], gp.SetUp_level2[i].getDamageArea().x, gp.SetUp_level2[i].getDamageArea().y, gp.SetUp_level2[i].getDamageArea()))
                                            {
                                                gp.SetUp_level2[i].setHealth(gp.SetUp_level2[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[1], gp.SetUp_level2[i].getDamageArea().x, gp.SetUp_level2[i].getDamageArea().y, gp.SetUp_level2[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "left" -> {
                                        if(gp.SetUp_level2[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[2], gp.SetUp_level2[i].getDamageArea().x, gp.SetUp_level2[i].getDamageArea().y, gp.SetUp_level2[i].getDamageArea()))
                                            {
                                                gp.SetUp_level2[i].setHealth(gp.SetUp_level2[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[2], gp.SetUp_level2[i].getDamageArea().x, gp.SetUp_level2[i].getDamageArea().y, gp.SetUp_level2[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "right" -> {
                                        if(gp.SetUp_level2[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[3], gp.SetUp_level2[i].getDamageArea().x, gp.SetUp_level2[i].getDamageArea().y, gp.SetUp_level2[i].getDamageArea()))
                                            {
                                                gp.SetUp_level2[i].setHealth(gp.SetUp_level2[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[3], gp.SetUp_level2[i].getDamageArea().x, gp.SetUp_level2[i].getDamageArea().y, gp.SetUp_level2[i].getDamageArea());
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                    else if(gp.flag_lvl_3)
                    {
                        for(int i= 0; i<gp.SetUp_level3.length; i++)
                        {
                            if(gp.SetUp_level3[i] != null)
                            {
                                switch (direction) {
                                    case "up" -> {
                                        if(gp.SetUp_level3[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[0], gp.SetUp_level3[i].getDamageArea().x, gp.SetUp_level3[i].getDamageArea().y, gp.SetUp_level3[i].getDamageArea()))
                                            {
                                                gp.SetUp_level3[i].setHealth(gp.SetUp_level3[i].getHealth()-this.attack_damage);
                                                gp.SetUp_level3[i].setAttack(false);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[0], gp.SetUp_level3[i].getDamageArea().x, gp.SetUp_level3[i].getDamageArea().y, gp.SetUp_level3[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "down" -> {
                                        if(gp.SetUp_level3[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[1], gp.SetUp_level3[i].getDamageArea().x, gp.SetUp_level3[i].getDamageArea().y, gp.SetUp_level3[i].getDamageArea()))
                                            {
                                                gp.SetUp_level3[i].setHealth(gp.SetUp_level3[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[1], gp.SetUp_level3[i].getDamageArea().x, gp.SetUp_level3[i].getDamageArea().y, gp.SetUp_level3[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "left" -> {
                                        if(gp.SetUp_level3[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[2], gp.SetUp_level3[i].getDamageArea().x, gp.SetUp_level3[i].getDamageArea().y, gp.SetUp_level3[i].getDamageArea()))
                                            {
                                                gp.SetUp_level3[i].setHealth(gp.SetUp_level3[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[2], gp.SetUp_level3[i].getDamageArea().x, gp.SetUp_level3[i].getDamageArea().y, gp.SetUp_level3[i].getDamageArea());
                                            }
                                        }
                                    }
                                    case "right" -> {
                                        if(gp.SetUp_level3[i] != null)
                                        {
                                            if(gp.entityCollison.enemyCollision(attackArea[3], gp.SetUp_level3[i].getDamageArea().x, gp.SetUp_level3[i].getDamageArea().y, gp.SetUp_level3[i].getDamageArea()))
                                            {
                                                gp.SetUp_level3[i].setHealth(gp.SetUp_level3[i].getHealth()-this.attack_damage);
                                                zombieCol = gp.entityCollison.RenemyCollision(attackArea[3], gp.SetUp_level3[i].getDamageArea().x, gp.SetUp_level3[i].getDamageArea().y, gp.SetUp_level3[i].getDamageArea());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    start = System.currentTimeMillis();

                    atk_anim = 0;
                }
                atk_counter = 0;
            }
        }

        if((keyH.isKeyActive(KeyEvent.VK_W) ||
            keyH.isKeyActive(KeyEvent.VK_S) ||
            keyH.isKeyActive(KeyEvent.VK_A) ||
            keyH.isKeyActive(KeyEvent.VK_D) )&&
            !(attack))
        {
            if (keyH.isKeyActive(KeyEvent.VK_W)) {
                if(!(gp.tileCollision.checkCollision(solidArea, worldPos, solidArea.x, solidArea.y-speed, screenPos)))
                    worldPos.y -= speed;
                direction = "up";
            }
            if (keyH.isKeyActive(KeyEvent.VK_S)) {
                if(!(gp.tileCollision.checkCollision(solidArea, worldPos, solidArea.x, solidArea.y+speed, screenPos)))
                    worldPos.y += speed;
                direction = "down";
            }
            if (keyH.isKeyActive(KeyEvent.VK_A)) {
                if(!(gp.tileCollision.checkCollision(solidArea, worldPos, solidArea.x-speed, solidArea.y, screenPos)))
                    worldPos.x -= speed;
                direction = "left";
            }
            if (keyH.isKeyActive(KeyEvent.VK_D)) {
                if(!(gp.tileCollision.checkCollision(solidArea, worldPos, solidArea.x+speed, solidArea.y, screenPos)))
                    worldPos.x += speed;
                direction = "right";
            }


            spriteCounter++;
            if (spriteCounter >= 6) {
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2)
    {
        if(attack)
        {
            switch (direction) {
                case "up" ->
                        g2.drawImage(sw_attack[0].getSubimage(48 * atk_anim, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "down" ->
                        g2.drawImage(sw_attack[1].getSubimage(48 * atk_anim, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "left" ->
                        g2.drawImage(sw_attack[2].getSubimage(48 * atk_anim, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "right" ->
                        g2.drawImage(sw_attack[3].getSubimage(48 * atk_anim, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
            }
        }
        else if((keyH.isKeyActive(KeyEvent.VK_W) ||
            keyH.isKeyActive(KeyEvent.VK_S) ||
            keyH.isKeyActive(KeyEvent.VK_A) ||
            keyH.isKeyActive(KeyEvent.VK_D) )&&
            (attack == false))
        {
            if (keyH.isKeyActive(KeyEvent.VK_W)) {

                g2.drawImage(up.getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);

            } else if (keyH.isKeyActive(KeyEvent.VK_S)) {

                g2.drawImage(down.getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);

            } else if (keyH.isKeyActive(KeyEvent.VK_A)) {

                g2.drawImage(left.getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);

            } else if (keyH.isKeyActive(KeyEvent.VK_D)) {

                g2.drawImage(right.getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);

            } else if (keyH.isKeyActive(KeyEvent.VK_W) && keyH.isKeyActive(KeyEvent.VK_A)) {

                g2.drawImage(up.getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);

            } else if (keyH.isKeyActive(KeyEvent.VK_S) && keyH.isKeyActive(KeyEvent.VK_A)) {

                g2.drawImage(down.getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);

            } else if (keyH.isKeyActive(KeyEvent.VK_W) && keyH.isKeyActive(KeyEvent.VK_D)) {

                g2.drawImage(up.getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);

            } else if (keyH.isKeyActive(KeyEvent.VK_S) && keyH.isKeyActive(KeyEvent.VK_D)) {

                g2.drawImage(down.getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);

            }
        }
        else
        {
            switch (direction) {
                case "down" ->
                        g2.drawImage(idle.getSubimage(0, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "up" ->
                        g2.drawImage(idle.getSubimage(48, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "right" ->
                        g2.drawImage(idle.getSubimage(96, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "left" ->
                        g2.drawImage(idle.getSubimage(144, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
            }
        }
        //g2.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
        g2.drawRect(damageArea.x, damageArea.y, damageArea.width, damageArea.height);
        /*for(int i= 0; i<4; i++)
        {
            switch (i)
            {
                case 0:
                    g2.setColor(Color.RED);
                    g2.drawRect(attackArea[i].x, attackArea[i].y, attackArea[i].width, attackArea[i].height);
                    break;
                case 1:
                    g2.setColor(Color.GREEN);
                    g2.drawRect(attackArea[i].x, attackArea[i].y, attackArea[i].width, attackArea[i].height);
                    break;
                case 2:
                    g2.setColor(Color.BLUE);
                    g2.drawRect(attackArea[i].x, attackArea[i].y, attackArea[i].width, attackArea[i].height);
                    break;
                case 3:
                    g2.setColor(Color.YELLOW);
                    g2.drawRect(attackArea[i].x, attackArea[i].y, attackArea[i].width, attackArea[i].height);
                    break;
            }
        }
        g2.setColor(Color.BLACK);
        g2.drawRect(zombieCol.x, zombieCol.y, zombieCol.width, zombieCol.height);*/
    }
}
