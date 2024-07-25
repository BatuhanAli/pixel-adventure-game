public class CollisionHandler {

    gamePanel gp;

    public CollisionHandler(gamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftColumn = entityLeftWorldX / gp.tileSize;
        int entityRightColumn = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftColumn][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightColumn][entityTopRow];

                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collision = true;
                }

                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftColumn][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightColumn][entityBottomRow];

                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collision = true;
                }
                break;

            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftColumn][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftColumn][entityBottomRow];

                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collision = true;
                }
                break;

            case "right":  
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightColumn][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightColumn][entityBottomRow];

                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collision = true;
                }
                break;
        }
    }
}