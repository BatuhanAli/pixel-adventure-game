import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends Entity {
    gamePanel gp;
    keyHandler keyH;
    int spriteMaxCounter = 12; // how many frames it takes to go to the next sprite.

    public final int screenX;
    public final int screenY;

    public Player(gamePanel gamePanel, keyHandler keyH){
        this.gp = gamePanel;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        int collisionSizeY = (int) (0.6 * gp.tileSize);
        int collisionSizeX = (int) (0.2 * gp.tileSize);
        int collisionOffsetX = (gp.tileSize - collisionSizeX) / 2;
        int collisionOffsetY = gp.tileSize - collisionSizeY;
        solidArea = new Rectangle(collisionOffsetX, collisionOffsetY, collisionSizeX, collisionSizeY);

        setDefaultPosition();
        getPlayerImage();
    }

    public void setDefaultPosition(){
        worldX = (int) (gp.tileSize * 9.5);
        worldY = gp.tileSize * 1;
        speed = 6;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResource("player/up_left.png"));
            up2 = ImageIO.read(getClass().getResource("player/up_right.png"));
            down1 = ImageIO.read(getClass().getResource("player/down_left.png"));
            down2 = ImageIO.read(getClass().getResource("player/down_right.png"));
            left1 = ImageIO.read(getClass().getResource("player/right_1.png"));
            left2 = ImageIO.read(getClass().getResource("player/right_2.png"));
            right1 = ImageIO.read(getClass().getResource("player/left_1.png"));
            right2 = ImageIO.read(getClass().getResource("player/left_2.png"));
            stationarySprite = ImageIO.read(getClass().getResource("player/front_still.png"));


        } catch (Exception e) {
            System.out.println("Error loading player images");
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            if (keyH.upPressed){
                direction = "up";
            }
    
            else if (keyH.downPressed){
                direction = "down";
            }
    
            else if (keyH.leftPressed){
                direction = "left";
            }
    
            else if (keyH.rightPressed){
                direction = "right";
            }

            // Checking for collision
            collision = false;
            gp.collisionHandler.checkTile(this);

            // If collision is detected, move back
            if (!collision){
                
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;
                }
            }
    
            spriteCounter++;
    
            if (spriteCounter > spriteMaxCounter){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
    
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
    
                spriteCounter = 0;
            }
        }

        else {
            spriteCounter++;

            if (spriteCounter > spriteMaxCounter){
                spriteCounter = 0;

                direction = "stationary"; // BEFORE DOING NEXT VIDEO IMPLEMENT STATIONARY UP DOWN LEFT AND RIGHT AND FIX UP THE CHARACTER SPRITES A LITTLE BIT!!!
            }
        }
    }

    public void rePaint(Graphics2D g2){
        BufferedImage playerImage = null;

        switch(direction){
            case "up":
                if (spriteNum == 1){
                    playerImage = up1;
                }

                else if (spriteNum == 2){
                    playerImage = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    playerImage = down1;
                }

                else if (spriteNum == 2){
                    playerImage = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    playerImage = left1;
                }

                else if (spriteNum == 2){
                    playerImage = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    playerImage = right1;
                }

                else if (spriteNum == 2){
                    playerImage = right2;
                }
                break;
            
            case "stationary":
                playerImage = stationarySprite;
        }

        g2.drawImage(playerImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}

