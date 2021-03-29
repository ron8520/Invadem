package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class Tank extends All{

    private PImage img;
    int move;

    public Tank(PImage img, int x, int y, int width, int height, int life){
        super(x,y,width,height,life);
        life = 3;
        this.img = img;
        move = 0;
    }


    public void tick(){
        if (move == -1 && x >=179){
            x -=1;
        }
        else if (move  == 1 && x <= 459){
            x +=1;
        }

    }

    public void setMove(String sign){
        if (sign.equals("+")){
            move = 1;
        }
        else if (sign.equals("-")){
            move = -1;
        }
        else if (sign.equals("s")){
            move = 0;
        }
    }

    public void draw(PApplet app){
        app.image(img,x,y,width,height);
        tick();
    }

    public int getLife(){
        return life;
    }

    public void lifeReduce(){
        life--;
    }

    public void setLife(){
        life = 0;
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

    public void Beattacked(List<Projectile> enemy_shots){
        for(int j = 0; j < enemy_shots.size(); j++){
            Projectile bullet = enemy_shots.get(j);
            if(this.check(bullet)){
                if (bullet.CheckType().equals("super")){
                    this.setLife();
                    enemy_shots.remove(j);
                    j--;
                }
                else{
                    this.lifeReduce();
                    enemy_shots.remove(j);
                    j--;
                }
            }
        }
    }


}
