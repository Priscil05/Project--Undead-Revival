package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
public class Brown_N_Zombie extends Entity{

    GamePanel gp;
    Player player;

    int trace_parser = 0;
    int _X;
    int _Y;
    Integer trace_direction;
    Rectangle zombieCol = new Rectangle();
    PathFinder path;

    //boolean flag = true;
    List<Integer> trace = new ArrayList<>();

    private static BufferedImage[] zombieMov = null;
    private static BufferedImage[] zombieAttk = null;

    static {
        try {
            zombieMov = new BufferedImage[4];
            zombieAttk = new BufferedImage[4];

            zombieMov[0] = ImageIO.read(Green_N_Zombie.class.getResourceAsStream("/Enemies/brown_N_zombie/brown_N_up.png"));
            zombieMov[1] = ImageIO.read(Green_N_Zombie.class.getResourceAsStream("/Enemies/brown_N_zombie/brown_N_down.png"));
            zombieMov[2] = ImageIO.read(Green_N_Zombie.class.getResourceAsStream("/Enemies/brown_N_zombie/brown_N_left.png"));
            zombieMov[3] = ImageIO.read(Green_N_Zombie.class.getResourceAsStream("/Enemies/brown_N_zombie/brown_N_right.png"));

            zombieAttk[0] = ImageIO.read(Green_N_Zombie.class.getResourceAsStream("/Enemies/brown_N_zombie/brown_N_attack_up.png"));
            zombieAttk[1] = ImageIO.read(Green_N_Zombie.class.getResourceAsStream("/Enemies/brown_N_zombie/brown_N_attack_down.png"));
            zombieAttk[2] = ImageIO.read(Green_N_Zombie.class.getResourceAsStream("/Enemies/brown_N_zombie/brown_N_attack_left.png"));
            zombieAttk[3] = ImageIO.read(Green_N_Zombie.class.getResourceAsStream("/Enemies/brown_N_zombie/brown_N_attack_right.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Brown_N_Zombie(GamePanel gp, Player player, int X, int Y, int speed, int health, int attack_damage)
    {
        this.gp = gp;
        this.player = player;
        screenPos = new Point();
        solidArea = new Rectangle(12, 0, 24, 48);
        damageArea = new Rectangle(12,6,24,42);
        worldPos = new Point(X, Y);
        this.speed = speed;
        this.health = health;
        this.attack_damage = attack_damage;
        setDefault();
    }

    void setDefault()
    {
        path = new PathFinder(gp.tileM.getColMap(), player.getWorldPos(), worldPos);

        attackArea = new Rectangle[4];

        attackArea[0] = new Rectangle(-10+screenPos.x, -10+screenPos.y, 68, 24);
        attackArea[1] = new Rectangle(-10+screenPos.x, 34+screenPos.y, 68, 24);
        attackArea[2] = new Rectangle(-10+screenPos.x, 0+screenPos.y, 24, 48);
        attackArea[3] = new Rectangle(34+screenPos.x, 0+screenPos.y, 24, 48);


    }

    public void update()
    {
        //System.out.println(gp.player.getHealth()+ "\n");
        solidArea.x = 12;
        solidArea.y = 0;
        damageArea.x = 12;
        damageArea.y = 6;
        attackArea[0].x =-10;
        attackArea[0].y =-10;
        attackArea[1].x =-10;
        attackArea[1].y =34;
        attackArea[2].x =-10;
        attackArea[2].y =0;
        attackArea[3].x =34;
        attackArea[3].y =0;


        screenPos.x = worldPos.x - gp.player.getWorldPos().x + gp.player.getScreenPos().x;
        screenPos.y = worldPos.y - gp.player.getWorldPos().y + gp.player.getScreenPos().y;
        solidArea.x += screenPos.x;
        solidArea.y += screenPos.y;
        damageArea.x += screenPos.x;
        damageArea.y += screenPos.y;
        attackArea[0].x += screenPos.x;
        attackArea[0].y += screenPos.y;
        attackArea[1].x += screenPos.x;
        attackArea[1].y += screenPos.y;
        attackArea[2].x += screenPos.x;
        attackArea[2].y += screenPos.y;
        attackArea[3].x += screenPos.x;
        attackArea[3].y += screenPos.y;

        //System.out.println(trace_parser+"\n");
        //System.out.println(attack + "\n");
        if(trace_parser == -1)
        {
            attack = true;
        }

        if(attack)
        {
            atk_counter++;
            if (atk_counter > 15) {
                if (atk_anim == 0) {
                    atk_anim = 1;
                } else if (atk_anim == 1) {
                    atk_anim = 2;
                } else if (atk_anim == 2) {
                    atk_anim = 3;
                } else if (atk_anim == 3) {
                    System.out.println(health + "\n");
                    attack = false;

                    switch (direction) {
                        case "up" -> {
                            //if(gp.zomb != null)
                            //{
                            if(gp.entityCollison.enemyCollision(this.attackArea[0], gp.player.getDamageArea().x, gp.player.getDamageArea().y, gp.player.getDamageArea()))
                            {
                                gp.player.setHealth(gp.player.getHealth()-this.attack_damage);
                                gp.player.setAttack(false);
                                zombieCol = gp.entityCollison.RenemyCollision(this.attackArea[0], gp.player.getDamageArea().x, gp.player.getDamageArea().y, gp.player.getDamageArea());
                            }
                            //}
                        }
                        case "down" -> {
                            //if(gp.zomb != null)
                            //{
                            if(gp.entityCollison.enemyCollision(attackArea[1], gp.player.getDamageArea().x, gp.player.getDamageArea().y, gp.player.getDamageArea()))
                            {
                                gp.player.setHealth(gp.player.getHealth()-this.attack_damage);
                                gp.player.setAttack(false);
                                zombieCol = gp.entityCollison.RenemyCollision(this.attackArea[1], gp.player.getDamageArea().x, gp.player.getDamageArea().y, gp.player.getDamageArea());
                            }
                            // }
                        }
                        case "left" -> {
                            //if(gp.zomb != null)
                            //{
                            if(gp.entityCollison.enemyCollision(attackArea[2], gp.player.getDamageArea().x, gp.player.getDamageArea().y, gp.player.getDamageArea()))
                            {
                                gp.player.setHealth(gp.player.getHealth()-this.attack_damage);
                                gp.player.setAttack(false);
                                zombieCol = gp.entityCollison.RenemyCollision(this.attackArea[2], gp.player.getDamageArea().x, gp.player.getDamageArea().y, gp.player.getDamageArea());
                            }
                            //}
                        }
                        case "right" -> {
                            //if(gp.zomb != null)
                            //{
                            if(gp.entityCollison.enemyCollision(attackArea[3], gp.player.getDamageArea().x, gp.player.getDamageArea().y, gp.player.getDamageArea()))
                            {
                                gp.player.setHealth(gp.player.getHealth()-this.attack_damage);
                                gp.player.setAttack(false);
                                zombieCol = gp.entityCollison.RenemyCollision(this.attackArea[3], gp.player.getDamageArea().x, gp.player.getDamageArea().y, gp.player.getDamageArea());
                            }
                            // }
                        }
                    }

                    atk_anim = 0;
                }
                atk_counter = 0;
            }
        }

        if( distance_player_enemy() > 5 && attack == false)
        {
            /*switch (player.direction) {
                case "up" -> path.set_parameters(player.getWorldPos().x, player.getWorldPos().y + 65, worldPos);
                case "down" -> path.set_parameters(player.getWorldPos().x, player.getWorldPos().y, worldPos);
                case "left" -> path.set_parameters(player.getWorldPos().x + 35, player.getWorldPos().y + 20, worldPos);
                case "right" -> path.set_parameters(player.getWorldPos().x, player.getWorldPos().y + 20, worldPos);
            }*/

            //System.out.printf("%f\n", distance_player_enemy());
            path.set_parameters(player.getWorldPos().x, player.getWorldPos().y, worldPos);
            _X = worldPos.x/gp.tileSize;
            _Y = worldPos.y/gp.tileSize;
            trace = path.findPath();
            trace_parser = 0;
        }

        //direction = "";
        double aux;

        if (!trace.isEmpty() && trace_parser != -1 && attack == false) {

            trace_direction = trace.get(trace_parser);

            switch (trace_direction) {
                case 0 -> {
                    aux = (double) worldPos.y / gp.tileSize;
                    if (aux > _Y - 1.00) {
                        if (!(gp.entityCollison.enemyCollision(player.getDamageArea(), solidArea.x, solidArea.y - speed, solidArea))) {
                            worldPos.y -= speed;
                        } else {
                            trace_parser = -1;
                        }

                    } else {
                        trace_parser++;
                        _X = worldPos.x / gp.tileSize;
                        _Y = worldPos.y / gp.tileSize;
                    }
                    direction = "up";
                }
                case 1 -> {
                    aux = (double) worldPos.y / gp.tileSize;
                    if (aux < _Y + 1.00) {
                        if (!(gp.entityCollison.enemyCollision(player.getDamageArea(), solidArea.x, solidArea.y + speed, solidArea))) {
                            worldPos.y += speed;
                        } else {
                            trace_parser = -1;
                        }

                    } else {
                        trace_parser++;
                        _X = worldPos.x / gp.tileSize;
                        _Y = worldPos.y / gp.tileSize;
                    }
                    direction = "down";
                }
                case 2 -> {
                    aux = (double) worldPos.x / gp.tileSize;
                    if (aux > _X - 1.00) {
                        if (!(gp.entityCollison.enemyCollision(player.getDamageArea(), solidArea.x - speed, solidArea.y, solidArea))) {
                            worldPos.x -= speed;
                        } else {
                            trace_parser = -1;
                        }
                    } else {
                        trace_parser++;
                        _X = worldPos.x / gp.tileSize;
                        _Y = worldPos.y / gp.tileSize;
                    }
                    direction = "left";
                }
                case 3 -> {
                    aux = (double) worldPos.x / gp.tileSize;
                    if (aux < _X + 1.00) {
                        if (!(gp.entityCollison.enemyCollision(player.getDamageArea(), solidArea.x + speed, solidArea.y, solidArea))) {
                            worldPos.x += speed;
                        } else {
                            trace_parser = -1;
                        }

                    } else {
                        trace_parser++;
                        _X = worldPos.x / gp.tileSize;
                        _Y = worldPos.y / gp.tileSize;
                    }
                    direction = "right";
                }
            }
            if(trace_parser == trace.size())
            {
                trace_parser = -1;
            }
        }

        spriteCounter++;
        if (spriteCounter >= 15) {
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

    @Override
    public void draw(Graphics2D g2)
    {
        if(attack)
        {
            switch (direction) {
                case "up" ->
                        g2.drawImage(zombieAttk[0].getSubimage(48 * atk_anim, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "down" ->
                        g2.drawImage(zombieAttk[1].getSubimage(48 * atk_anim, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "left" ->
                        g2.drawImage(zombieAttk[2].getSubimage(48 * atk_anim, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "right" ->
                        g2.drawImage(zombieAttk[3].getSubimage(48 * atk_anim, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
            }
        }
        else {
            switch (direction) {
                case "up" ->
                        g2.drawImage(zombieMov[0].getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "down" ->
                        g2.drawImage(zombieMov[1].getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "left" ->
                        g2.drawImage(zombieMov[2].getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                case "right" ->
                        g2.drawImage(zombieMov[3].getSubimage(48 * spriteNum, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
                default ->
                        g2.drawImage(zombieMov[1].getSubimage(0, 0, 48, 48), screenPos.x, screenPos.y, gp.playerTileSize, gp.playerTileSize, null);
            }
        }

        g2.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
        //g2.setColor(Color.GREEN);
        //g2.drawRect(damageArea.x, damageArea.y, damageArea.width, damageArea.height);

        g2.setColor(Color.BLACK);
        g2.drawRect(zombieCol.x, zombieCol.y, zombieCol.width, zombieCol.height);

        for(int i= 0; i<4; i++)
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
    }

    double distance_player_enemy()
    {
        return Math.sqrt(Math.pow((worldPos.x - player.getWorldPos().x), 2) + Math.pow((worldPos.y - player.getWorldPos().y), 2));
    }
}
