import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class TileManager{

    gamePanel gp;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(gamePanel gp){
        this.gp = gp;
        tiles = new Tile[10];

        mapTileNum = new int[gp.maxWorldColumn][gp.maxWorldRow];
        
        getTileImage();
        loadMap("/Maps/map01.txt");
    }

    public void loadMap(String file){
        try {
            InputStream in = getClass().getResourceAsStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldColumn && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldColumn){
                    String[] tokens = line.split(" ");

                    int num = Integer.parseInt(tokens[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldColumn){
                    col = 0;
                    row++;
                }
            }

            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/wood.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));

            tiles[4] = new Tile();
            tiles[4].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){

        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < gp.maxWorldColumn && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldColumn][worldRow];

            int worldX = worldColumn * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                    g2.drawImage(tiles[tileNum].tileImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }


            worldColumn++;
            
            if (worldColumn == gp.maxWorldColumn){
                worldColumn = 0;
                worldRow++;
            }
        }
    }
}