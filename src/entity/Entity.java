package entity;

import main.CollisionChecker;
import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    Point worldPos;
    Point screenPos;
    int speed;
    int health;
    int attack_damage;

    long start;
    long stop;
    boolean attack = false;

    int atk_anim = 1, atk_counter = 0;

    GamePanel gp;

    BufferedImage up, down, left, right, idle;


    int spriteCounter = 0;

    int spriteNum = 1;

    String direction;

    public Rectangle solidArea;
    public Rectangle[] attackArea;
    public Rectangle damageArea;

    public Point getWorldPos()
    {
        return this.worldPos;
    }

    public Point getScreenPos()
    {
        return this.screenPos;
    }

    public boolean getAttack() {
        return attack;
    }

    public void setAttack(boolean attk)
    {
        this.attack = attk;
    }

    public Rectangle getDamageArea()
    {
        return damageArea;
    }

    public int getHealth()
    {
        return health;
    }
    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getAttack_damage()
    {
        return attack_damage;
    }

    public void update()
    {

    }
    public void draw(Graphics2D g2)
    {

    }
}

