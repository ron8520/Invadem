package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class Barriers extends All{
    private List<PImage> img;
    private PImage one;
    private PImage two;
    private PImage three;
    private PImage four;


    public Barriers(PImage one, PImage two, PImage three, PImage four, int x, int y, int width, int height, int life){
        super(x,y,width,height,life);
        img = new ArrayList<PImage>();
        img.add(this.one = one);
        img.add(this.two = two);
        img.add(this.three = three);
        img.add(this.four = four);
    }

    public void draw(PApplet app){
        app.image(getImage(),x,y,width,height);
    }

    public PImage getImage(){
        return img.get(life);
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

    public void setLife(){
        if(life < 3){
            life+=1;
        }
        else{
            life = 3;
        }
    }

    public int getLife(){
        return life;
    }

    public void barrierchange(List<Projectile> p){
        for(int i =0; i < p.size(); i++){
            if(this.check(p.get(i))){

                if(this.getLife() < 3){
                    if(p.get(i).CheckType().equals("super")){
                        life = 3;
                        p.remove(i);
                        i--;
                    }

                    else{
                        this.setLife();
                        p.remove(i);
                        i--;
                    }
                }
            }
        }
    }
}
