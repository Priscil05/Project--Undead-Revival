package main;

import java.awt.*;

public class EntityCollisionChecker {

    GamePanel gp;
    public EntityCollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public boolean enemyCollision(Rectangle player_solidArea, int _zombieX, int _zombieY, Rectangle zombie_solidArea)
    {
        boolean collisionDetected = false;

        Rectangle player_tmp = new Rectangle(player_solidArea.x, player_solidArea.y, player_solidArea.width, player_solidArea.height);
        Rectangle zombie_tmp = new Rectangle(_zombieX, _zombieY, zombie_solidArea.width, zombie_solidArea.height);

        collisionDetected = player_tmp.intersects(zombie_tmp);

        return collisionDetected;
    }

    public Rectangle RenemyCollision(Rectangle player_solidArea, int _zombieX, int _zombieY, Rectangle zombie_solidArea)
    {


        Rectangle player_tmp = new Rectangle(player_solidArea.x, player_solidArea.y, player_solidArea.width, player_solidArea.height);
        Rectangle zombie_tmp = new Rectangle(_zombieX, _zombieY, zombie_solidArea.width, zombie_solidArea.height);

        Rectangle intersection = new Rectangle();
        intersection = player_tmp.intersection(zombie_tmp);

        return intersection;
    }
}
