package invadem;

import processing.core.PApplet;

public abstract class All {
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected int life;


    public All(int x, int y, int width, int height, int life){
        this.x =x;
        this.y =y;
        this.width = width;
        this.height = height;
        this.life = life;
}

    public abstract void draw(PApplet app);
    public abstract int getLife();

}
