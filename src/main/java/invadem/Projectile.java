package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class Projectile extends All{

    private PImage img;
    private String direction;
    private String type;

    public Projectile(PImage img, int x, int y, int width, int height, int life, String direction, String type){
        super(x,y,width,height,life);
        this.img = img;
        this.direction = direction;
        this.type = type;
    }

    public void tick(){
        if(this.direction.equals("up")){
            y -=1;
        }

        else if (this.direction.equals("down")){
            y+=1;
        }

        else if (this.direction.equals("leftdown")){
            y+=1;
            x-=1;
        }

        else if (this.direction.equals("rightdown")){
            y+=1;
            x+=1;
        }
    }

    public String CheckType(){
        return this.type;
    }

    public static void removeOutBound(List<Projectile> p){
        for(int i = 0; i < p.size(); i++){
            if(p.get(i).x < 0 || p.get(i).x > 640 || p.get(i).y < 0 || p.get(i).y > 480){
                p.remove(i);
                i--;
            }
        }
    }

    public int getLife(){
        return 0;
    }

    public void draw(PApplet app){
        app.image(img,x,y,width,height);
        tick();
    }
}
