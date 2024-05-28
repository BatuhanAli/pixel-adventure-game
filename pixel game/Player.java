import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends Entity {
    gamePanel gp;
    keyHandler keyH;
    int spriteMaxCounter = 12; // how many frames it takes to go to the next sprite.

    public Player(gamePanel gamePanel, keyHandler keyH){
        this.gp = gamePanel;
        this.keyH = keyH;

        setDefaultPosition();
        getPlayerImage();
    }

    public void setDefaultPosition(){
        x = 100;
        y = 100;
        speed = 6;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResource("player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResource("player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResource("player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResource("player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResource("player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResource("player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("player/boy_right_2.png"));


        } catch (Exception e) {
            System.out.println("Error loading player images");
            e.printStackTrace();
        }
    }

    public void update(){

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if (keyH.upPressed){
                direction = "up";
                y -= speed;
            }
    
            else if (keyH.downPressed){
                direction = "down";
                y += speed;
            }
    
            else if (keyH.leftPressed){
                direction = "left";
                x -= speed;
            }
    
            else if (keyH.rightPressed){
                direction = "right";
                x += speed;
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
        }

        g2.drawImage(playerImage, x, y, gp.tileSize, gp.tileSize, null);
    }
}

