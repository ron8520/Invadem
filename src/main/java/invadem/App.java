package invadem;

import java.util.List;
import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;
import java.util.Random;


public class App extends PApplet {
    Tank t;
    PFont font;
    Monster m;

    List<Barriers> lefts;
    List<Barriers> centres;
    List<Barriers> rights;
    List<Invader> inva;
    List<Projectile> shots;
    List<Projectile> enemy_shots;
    List<Projectile> monster_shots;

    boolean readyshot;
    boolean win;
    boolean loss;
    boolean all_kill;
    boolean hasCount;

    int count;
    int enemy_amount;
    int current_score;
    int highest_score;
    int current_level;
    int win_level;

    PImage monster;
    PImage fireball;

    public App() {
        current_level = 0;
        highest_score = 10000;
        win_level = 0;
    }

    public void setup() {
        frameRate(60);

        inva = new ArrayList<Invader>();
        shots = new ArrayList<Projectile>();
        enemy_shots = new ArrayList<Projectile>();
        monster_shots = new ArrayList<Projectile>();

        t = new Tank(loadImage("tank1.png"), 310, 464, 22, 16, 3);

        lefts = new ArrayList<Barriers> ();
        centres = new ArrayList<Barriers> ();
        rights = new ArrayList<Barriers> ();

        readyshot = false;
        win = false;
        loss = false;
        all_kill = false;
        enemy_amount = 0;
        count = 0;

        font = createFont("PressStart2P-Regular.ttf",32);
        textFont(font);


        //Set up the location of each Barriers
        lefts.add(new Barriers(loadImage("barrier_left1.png"),loadImage("barrier_left2.png"),loadImage("barrier_left3.png"),loadImage("empty.png"),199,416,8,8,0));
        lefts.add(new Barriers(loadImage("barrier_top1.png"),loadImage("barrier_top2.png"), loadImage("barrier_top3.png"),loadImage("empty.png"),207,416,8,8,0));
        lefts.add(new Barriers(loadImage("barrier_right1.png"),loadImage("barrier_right2.png"),loadImage("barrier_right3.png"),loadImage("empty.png"),215,416,8,8,0));
        lefts.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),199,424,8,8,0));
        lefts.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),199,432,8,8,0));
        lefts.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),215,424,8,8,0));
        lefts.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),215,432,8,8,0));

        centres.add(new Barriers(loadImage("barrier_left1.png"),loadImage("barrier_left2.png"),loadImage("barrier_left3.png"),loadImage("empty.png"),308,416,8,8,0));
        centres.add(new Barriers(loadImage("barrier_top1.png"),loadImage("barrier_top2.png"),loadImage("barrier_top3.png"),loadImage("empty.png"),316,416,8,8,0));
        centres.add(new Barriers(loadImage("barrier_right1.png"),loadImage("barrier_right2.png"),loadImage("barrier_right3.png"),loadImage("empty.png"),324,416,8,8,0));
        centres.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),308,424,8,8,0));
        centres.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),308,432,8,8,0));
        centres.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),324,424,8,8,0));
        centres.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),324,432,8,8,0));

        rights.add(new Barriers(loadImage("barrier_left1.png"),loadImage("barrier_left2.png"),loadImage("barrier_left3.png"),loadImage("empty.png"),415,416,8,8,0));
        rights.add(new Barriers(loadImage("barrier_top1.png"),loadImage("barrier_top2.png"),loadImage("barrier_top3.png"),loadImage("empty.png"),423,416,8,8,0));
        rights.add(new Barriers(loadImage("barrier_right1.png"),loadImage("barrier_right2.png"),loadImage("barrier_right3.png"),loadImage("empty.png"),431,416,8,8,0));
        rights.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),415,424,8,8,0));
        rights.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),415,432,8,8,0));
        rights.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),431,424,8,8,0));
        rights.add(new Barriers(loadImage("barrier_solid1.png"),loadImage("barrier_solid2.png"),loadImage("barrier_solid3.png"),loadImage("empty.png"),431,432,8,8,0));

        // Set up Invader and Monster
        if (win_level == 5) {
            monster = loadImage("monster.png");
            monster.resize(70,70);
            m = new Monster(monster, 270, 100, 70, 70, 20);
        }

        else{
            int k =0;
            for(int i =0; i < 4; i++){
                for(int j =0; j<10; j++){

                    if (i == 0){
                        Invader ememy = new Invader(loadImage("invader1_armoured.png"),loadImage("invader2_armoured.png"),159+k,50,16,16,3,"Tank",false);
                        inva.add(ememy);
                        k+= 33;
                    }

                    else if (i == 1){
                        Invader ememy = new Invader(loadImage("invader1_power.png"),loadImage("invader2_power.png"),159+k,90,16,16,1,"Power",true);
                        inva.add(ememy);
                        k+= 33;
                    }

                    else if (i == 2){
                        Invader ememy = new Invader(loadImage("invader1.png"),loadImage("invader2.png"),159+k,130,16,16,1,"Normal",false);
                        inva.add(ememy);
                        k+= 33;
                    }

                    else if (i == 3){
                        Invader ememy = new Invader(loadImage("invader1.png"),loadImage("invader2.png"),159+k,170,16,16,1,"Normal",false);
                        inva.add(ememy);
                        k+= 33;
                    }
                }
                k = 0;
            }
        }
    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        background(0);
        checkShot();
        checkGameover();
        setTime();

        if (shots.size() > 50) {
            Projectile.removeOutBound(shots);
        }

        if(monster_shots.size() > 50){
            Projectile.removeOutBound(monster_shots);
        }

        if(win || loss){
            background(0);

            if(all_kill){
                image(loadImage("nextlevel.png"), 250, 240, 112, 16);
            }

            else{
                image(loadImage("gameover.png"), 250, 240, 112, 16);
            }

            if(millis() - count >= 2000){
                if(loss){
                    current_level = 0;
                    current_score = 0;
                }

                if(win){
                    current_level++;
                    win_level++;

                    if(current_score > highest_score){
                        Integer new_score = new Integer(current_score);
                        highest_score = new_score;
                    }


                }

                win = false;
                loss = false;
                setup();
            }


        }

        else{

            int level = current_level + 1;

            t.draw(this);

            textSize(10);
            fill(255,255,255);
            text("HighestScore:" + highest_score, 430,20);

            textSize(10);
            fill(255,255,255);
            text("Score:" + current_score, 5,20);

            textSize(10);
            fill(255,255,255);
            text("Level:" + level, 5,470);

            for(int i = 0; i<lefts.size(); i++){

                lefts.get(i).draw(this);

            }

            for(int i = 0; i<centres.size(); i++){
                centres.get(i).draw(this);
            }

            for(int i = 0; i<rights.size(); i++){
                rights.get(i).draw(this);
            }


            if (win_level == 5) {
                if (frameCount % 2 == 0) {
                    m.tick();
                }
                m.draw(this);

                if(frameCount % 30 == 0){
                    enemy_shot();
                }

                for(int i = 0; i < monster_shots.size(); i++){
                    monster_shots.get(i).draw(this);
                }
            }

            else{

                for(Invader in : inva){
                    if(frameCount % 2 == 0){
                        in.tick();

                    }
                    in.draw(this);
                }

                if(frameCount % (300-current_level*60) == 0){
                    enemy_shot();
                }

                for(Projectile attack : enemy_shots){
                    attack.draw(this);
                }
            }

            for(Projectile b : shots){
                b.draw(this);
            }

        }

    }

    public void keyPressed(){

        if (key == ' '){
            readyshot = true;

        }

        if(keyCode == LEFT){

            t.setMove("-");
        }

        if (keyCode == RIGHT){
            t.setMove("+");
        }
    }

    public void keyReleased(){

        if (keyCode == LEFT){
            t.setMove("s");
        }

        if (keyCode == RIGHT){
            t.setMove("s");
        }

        if (key == ' '){
            if(readyshot){
                Projectile shot = new Projectile(loadImage("projectile.png"),t.x+10,t.y-3,1,3,0,"up","normal");
                shots.add(shot);
                readyshot = false;
            }
        }
    }


    // Check each shot form tank hits the invader and barrier or not
    public void checkShot(){
        for(int i = 0; i < 7;i++){
            lefts.get(i).barrierchange(shots);
            centres.get(i).barrierchange(shots);
            rights.get(i).barrierchange(shots);

            if(win_level == 5){
                lefts.get(i).barrierchange(monster_shots);
                centres.get(i).barrierchange(monster_shots);
                rights.get(i).barrierchange(monster_shots);
                t.Beattacked(monster_shots);
            }
            else{
                lefts.get(i).barrierchange(enemy_shots);
                centres.get(i).barrierchange(enemy_shots);
                rights.get(i).barrierchange(enemy_shots);
                t.Beattacked(enemy_shots);
            }
        }

        for(int j = 0; j < shots.size(); j++){
            bang(shots.get(j));
            Invader.RemoveInvader(inva);
        }
    }

    // check single shot
    public void bang(Projectile p){
        if (win_level == 5) {
            if(m.check(p)){
                shots.remove(p);
                m.lifeReduce();
                current_score +=250;
            }
        }

        else{
            for(int i =0; i < inva.size(); i++){

                if(inva.get(i).check(p)){
                    if(!inva.get(i).CheckType().equals("Tank")){


                        if(inva.get(i).CheckType().equals("Power")){
                            current_score +=250;
                            inva.get(i).lifeReduce();
                        }

                        else{
                            current_score +=100;
                            inva.get(i).lifeReduce();
                        }
                        shots.remove(p);
                        enemy_amount++;

                    }

                    else{
                        shots.remove(p);
                        inva.get(i).lifeReduce();
                        if(inva.get(i).getLife() == 0){
                            enemy_amount++;
                            current_score +=250;
                            }

                        }

                }
            }
        }
    }


    public void checkGameover(){

        if(t.getLife() == 0){
            loss = true;
        }

        if(inva.size() == 0 && enemy_amount == 40){
            win = true;
            all_kill = true;
        }

        if(win_level == 5){
            if(m.getLife() == 0){
                win = true;
                all_kill = true;
            }
        }

        for(int i = 0; i < inva.size(); i++){
            if(416 - inva.get(i).y < 26){
                loss = true;
                inva.clear();
                }
            }



    }

    public void setTime(){

        if(win && count == 0 || loss && count == 0){
            count = millis();
        }
    }

    public void enemy_shot(){
        if (win_level == 5) {
            Projectile monster_shot1 = new Projectile(loadImage("fireball.png"), m.x+30, m.y+75, 2, 5, 0, "down", "super");
            Projectile monster_shot2 = new Projectile(loadImage("fireball.png"), m.x+10, m.y+75, 2, 5, 0, "leftdown", "super");
            Projectile monster_shot3 = new Projectile(loadImage("fireball.png"), m.x+50, m.y+75, 2, 5, 0, "rightdown", "super");

            monster_shots.add(monster_shot1);
            monster_shots.add(monster_shot2);
            monster_shots.add(monster_shot3);
        }
        else{
            Random rand = new Random();
            int random_index = rand.nextInt(inva.size());
            Invader random_enemy = inva.get(random_index);

            if(!random_enemy.CheckPower()){
                Projectile random_shot = new Projectile(loadImage("projectile.png"),random_enemy.x+2, random_enemy.y+3, 1, 3, 0, "down","noraml");
                enemy_shots.add(random_shot);
            }

            else{
                Projectile random_shot = new Projectile(loadImage("projectile_lg.png"),random_enemy.x+2, random_enemy.y, 2, 5, 0, "down","super");
                enemy_shots.add(random_shot);
            }
        }
    }


    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
