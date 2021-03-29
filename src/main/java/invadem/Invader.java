package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class Invader extends All{

    int current_index;
    boolean right_direction;
    boolean mov_down;
    int count;
    int move_step;
    private PImage[] sprites;
    private PImage one;
    private PImage two;
    private String type;
    private boolean power;

    public Invader(PImage one, PImage two, int x, int y, int width, int height, int life, String type, boolean power){
        super(x,y,width,height,life);
        sprites = new PImage []{this.one = one, this.two = two};
        this.type = type;
        this.power = power;
        current_index =0;
        count = 0;
        move_step =0;
        right_direction = true;
        mov_down = true;

        if(this.type.equals("Tank")){
            life = 3;
        }
    }

    public String CheckType(){
        return this.type;
    }

    public PImage getImage(){
        return sprites[current_index];
    }

    public boolean CheckPower(){
        return this.power;
    }

    public void lifeReduce(){
        life--;
    }

    public int getLife(){
        return life;
    }

    public void tick(){
        if(mov_down){
            this.y +=1;
            move_step++;
            current_index = 1;

            if(move_step == 8){
                mov_down = false;
                move_step =0;
                current_index =0;
            }

        }

        else{

            if(right_direction){

                this.x +=1;
                count+=1;

                if(count ==30){
                    count =0;
                    right_direction = false;
                    mov_down = true;
                }
            }

            else{
                this.x -=1;
                count+=1;

                if(count ==30){
                    count =0;
                    right_direction = true;
                    mov_down = true;
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

    public static void RemoveInvader(List<Invader> inva){
        for(int i = 0; i< inva.size();i++){
            if(inva.get(i).getLife() == 0){
                inva.remove(i);
                i--;
            }
        }
    }

    public void draw(PApplet app){
        app.image(getImage(),x,y,width,height);
    }
}
