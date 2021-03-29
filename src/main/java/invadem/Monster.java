package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class Monster extends All {

    private PImage img;
    boolean moveDown;
    boolean moveLeft;
    boolean moveRight;
    int moveStep;

    public Monster(PImage img, int x, int y, int width, int hegiht, int life){
        super(x,y,width,hegiht,life);
        this.img = img;
        moveDown = true;
    }

    public int getLife(){
        return life;
    }

    public void draw(PApplet app){
        app.image(getImage(),x,y,width,height);
        tick();

    }

    public PImage getImage(){
        return img;
    }

    public void lifeReduce(){
        this.life--;
    }

    public void tick(){

        if (moveDown) {
            this.y+=1;
            moveStep++;

            if (moveStep == 8) {
                moveStep = 0;
                moveDown =false;
                moveLeft = true;
            }
        }

        else{
            if (moveLeft) {
                this.x-=1;
                if (this.x <= 160) {
                    moveLeft = false;
                }
            }

            else{
                this.x+=1;

                if (this.x > 400) {
                    moveLeft = true;
                }

            }

            }

    }

    public boolean check(Projectile p){
        if(this.x < (p.x + p.width) &&
        (this.x+this.width) > p.x &&
        this.y < (p.y + p.height) &&
        (this.height + this.y) > p.y){
            return true;
        }

        return false;
    }


}
