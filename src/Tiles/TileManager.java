package Tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    static int k;
    int[][][] map;
    boolean[][] collision_map = new boolean[85][135];


    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[1060];
        map = new int[3][gp.maxWorldCol][gp.maxWorldRow];
        k = 1;
        getTileImage();
        loadMap();
        createColMap();
    }

    void getTileImage()
    {
        tile[0] = new Tile(new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB), false, new Rectangle(0,0,0,0));

        GetTileInfo("/Tiles/Buildings.png", 1008, 1440, true, 0, 0, 48, 48);
        GetTileInfo("/Tiles/Road.png", 240, 432, false, 0, 0, 0, 0);
        GetTileInfo("/Tiles/B_roofs.png", 432, 672, true, 0, 24, 48, 24);
        GetTileInfo("/Tiles/OneB_col_obj.png", 288, 336, true,0, 0, 48, 48);
        GetTileInfo("/Tiles/Complx_col_obj.png", 336, 48, false, 18, 0, 12, 23);
        GetTileInfo("/Tiles/Big_colission.png", 96, 48, true,  8, 0, 32, 38);
        GetTileInfo("/Tiles/Village_Buildings.png", 528, 432, true,  0, 0, 48, 48);
        GetTileInfo("/Tiles/Village_Buildings_roofs.png", 480, 144, true,  0, 24, 48, 24);
        GetTileInfo("/Tiles/village_road.png", 240, 144, false,  0, 0, 0, 0);
    }

    void loadMap()
    {
        GetMapInfo("/Map/Road_1.txt", 0);
        GetMapInfo("/Map/Buildings_2.txt", 1);
        GetMapInfo("/Map/B_roofs_3.txt", 2);
    }

    public void draw_layer(Graphics2D g2, int layer)
    {
        int worldcol = 0;
        int worldrow = 0;


        while(worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow)
        {
            int worldX = worldcol * gp.tileSize;
            int worldY = worldrow * gp.tileSize;
            int screenX = worldX - gp.player.getWorldPos().x + gp.player.getScreenPos().x;
            int screenY = worldY - gp.player.getWorldPos().y + gp.player.getScreenPos().y;

            if(worldX + gp.tileSize > gp.player.getWorldPos().x- gp.player.getScreenPos().x &&
                worldX - gp.tileSize< gp.player.getWorldPos().x + gp.player.getScreenPos().x &&
                worldY + gp.tileSize > gp.player.getWorldPos().y - gp.player.getScreenPos().y &&
                worldY - gp.tileSize < gp.player.getWorldPos().y + gp.player.getScreenPos().y)
            {
                g2.drawImage(tile[map[layer][worldcol][worldrow]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                g2.drawRect(screenX+tile[map[layer][worldcol][worldrow]].solidArea.x,
                            screenY+tile[map[layer][worldcol][worldrow]].solidArea.y,
                                tile[map[layer][worldcol][worldrow]].solidArea.width,
                                tile[map[layer][worldcol][worldrow]].solidArea.height);
            }


            worldcol++;

            if(worldcol == gp.maxWorldCol)
            {
                worldcol = 0;
                worldrow++;
            }
        }
    }

    void GetTileInfo(String path, int width, int height, boolean collision, int col_x, int col_y, int col_w, int col_h)
    {
        BufferedImage image;
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            for(int i = 0; i < height; i+=48) {
                for(int j = 0; j < width; j+= 48) {
                    tile[k++] = new Tile(image.getSubimage(j, i, 48, 48), collision, new Rectangle(col_x, col_y, col_w, col_h));
                }
            }
        }catch(IOException e)
        {
        e.printStackTrace();
        }

    }

    void GetMapInfo(String path, int layer) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;


            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(",");
                    int number = Integer.parseInt(numbers[col]);

                    map[layer][col][row] = number;
                    col++;
                }
                col = 0;
                row++;

            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println("Nu sa putut deschide fisierul de leyere");
        }


    }

    void createColMap()
    {
        boolean[][] temp = new boolean[135][85];


        for(int col = 0; col < gp.maxWorldCol; col++)
        {
            for(int row = 0; row < gp.maxWorldRow; row++)
            {
                    temp[col][row] = tile[map[1][col][row]].collision;

            }
        }


        for(int col = 0; col < gp.maxWorldCol; col++)
        {
            for(int row = 0; row < gp.maxWorldRow; row++)
            {
                collision_map[row][col] = temp[col][row];
            }
        }

        for(int row = 0; row < gp.maxWorldRow; row++)
        {
            for(int col = 0; col < gp.maxWorldCol; col++)
            {
                collision_map[row][col] = !(collision_map[row][col]);
            }
        }

        try {
            OutputStream outputStream = new FileOutputStream("C:\\Users\\AC-CTI\\Desktop\\joc PAOO\\1211B\\Project - Undead Revival\\res\\Map\\colisionmap.txt");
            String val;

            for (int row =0; row<gp.maxWorldRow; row++) {
                for (int col=0; col<gp.maxWorldCol; col++) {
                    boolean valoare = collision_map[row][col];
                    if(valoare == false )
                    {
                        val = "0";
                    }
                    else {
                        val = "1";
                    }
                    outputStream.write(val.getBytes());
                }
                outputStream.write("\n".getBytes()); // Treci la următorul rând
            }

            outputStream.close();
            System.out.println("Matricea a fost scrisă în fișier.");
        } catch (IOException e) {
            System.out.println("A intervenit o eroare la scrierea în fișier: " + e.getMessage());
        }
    }

    public int[][] getMap(int layer)
    {
        return map[layer];
    }
    public boolean[][] getColMap()
    {
        return collision_map;
    }
    public void set_map0(int layer, int x, int y)
    {
        map[layer][x][y] = 0;
    }


}




