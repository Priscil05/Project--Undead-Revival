package main;

import entity.Entity;
import java.awt.*;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }


    public boolean checkCollision(Rectangle entitySolidArea, Point entityPos, int entyX, int entyY, Point screenPos) {

        boolean collisionDetected = false;

        for(int layer = 1; layer < 3; layer++)
        {
            int[][] map = gp.tileM.getMap(layer);

            int worldcol = entityPos.x/gp.tileSize;
            int worldrow = entityPos.y/gp.tileSize;
            Rectangle tmp = new Rectangle(0,0,0,0);

            Rectangle _enty;

            while(worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow)
            {
                int worldX = worldcol * gp.tileSize;
                int worldY = worldrow * gp.tileSize;
                int screenX = worldX - entityPos.x + screenPos.x;
                int screenY = worldY - entityPos.y + screenPos.y;


                tmp.x = screenX+gp.tileM.tile[map[worldcol][worldrow]].GetColArea().x;
                tmp.y = screenY+gp.tileM.tile[map[worldcol][worldrow]].GetColArea().y;
                tmp.width = gp.tileM.tile[map[worldcol][worldrow]].GetColArea().width;
                tmp.height = gp.tileM.tile[map[worldcol][worldrow]].GetColArea().height;

                _enty = new Rectangle(entyX, entyY, entitySolidArea.width, entitySolidArea.height);

                collisionDetected = tmp.intersects(_enty);

                if(collisionDetected)
                {
                    break;
                }

                worldcol++;

                if(worldcol == gp.maxWorldCol)
                {
                    worldcol = 0;
                    worldrow++;
                }
            }
            if(collisionDetected)
            {
                break;
            }
        }

        return collisionDetected;
    }
}


