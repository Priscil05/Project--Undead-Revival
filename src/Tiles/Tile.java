package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    boolean collision;
    Rectangle solidArea;

    public Tile(BufferedImage img, boolean collision, Rectangle solidArea)
    {
        this.image = img;
        this.collision = collision;
        this.solidArea = solidArea;
    }

    public boolean GetCollision()
    {
        return this.collision;
    }

    public void SetCollision(boolean collsion)
    {
        this.collision = collision;
    }

    public Rectangle GetColArea()
    {
        return this.solidArea;
    }

}
