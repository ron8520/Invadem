package invadem;


import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;

public class AppTest extends App{
    App app;


    @Before
    public void Setup(){
        app = new App();
        String [] args = new String[] {"args"};
        PApplet.runSketch(args, app);
        delay(1000);
        noLoop();
    }

    @Test
    public void testAppConstruction(){
        assertNotNull(app);
    }

    @Test
    public void testAppSetup(){
        assertEquals(7, app.lefts.size());
        assertEquals(7, app.centres.size());
        assertEquals(7, app.rights.size());
        assertNotNull(app.t);
        assertEquals(40, app.inva.size());
        assertNotNull(app.font);
        assertEquals(10000, app.highest_score);
        assertEquals(0, app.current_score);
    }

    @Test
    public void testAppMonsterLevel(){
        App mon_game = new App();
        mon_game.win_level = 5;
        String [] args1 = new String[] {""};
        PApplet.runSketch(args1, mon_game);
        delay(1000);
        noLoop();
        assertNotNull(mon_game.m);
        assertEquals(0, mon_game.inva.size());
    }

    @Test
    public void testAppKeyEvent(){
        app.keyCode = LEFT;
        app.keyPressed();
        assertEquals(-1 , app.t.move);

        app.keyCode = LEFT;
        app.keyReleased();
        assertEquals(0 , app.t.move);

        app.keyCode = RIGHT;
        app.keyPressed();
        assertEquals(1 , app.t.move);

        app.keyCode = RIGHT;
        app.keyReleased();
        assertEquals(0, app.t.move);

        app.keyCode = UP;
        app.keyPressed();
        assertEquals(0, app.t.move);

        app.key = ' ';
        app.keyPressed();
        assertTrue(app.readyshot);

        app.key = ' ';
        app.keyReleased();
        assertEquals(1, app.shots.size());
        assertFalse(app.readyshot);

        app.key = ' ';
        app.readyshot = false;
        app.keyReleased();
        assertEquals(1, app.shots.size());
    }

    @Test
    public void testAppBang1(){
        app.key = ' ';
        app.keyPressed();
        app.keyReleased();

        app.shots.get(0).x = 190;
        app.shots.get(0).y = 200;
        delay(2000);
        assertEquals(39, app.inva.size());
        assertEquals(0, app.shots.size());
        assertEquals(1, app.enemy_amount);
        assertEquals(100, app.current_score);
    }

    @Test
    public void testAppBang2(){
        app.key = ' ';
        app.keyPressed();
        app.keyReleased();

        app.shots.get(0).x = 190;
        app.shots.get(0).y = 80;
        delay(2000);
        assertEquals(40, app.inva.size());
        assertEquals(0, app.shots.size());
        assertEquals(0, app.enemy_amount);
        assertEquals(0, app.current_score);
        assertEquals(2, app.inva.get(0).getLife());
    }

    @Test
    public void testAppBang3(){
        app.key = ' ';
        app.keyPressed();
        app.keyReleased();

        app.shots.get(0).x = 190;
        app.shots.get(0).y = 100;
        delay(2000);
        assertEquals(39, app.inva.size());
        assertEquals(0, app.shots.size());
        assertEquals(1, app.enemy_amount);
        assertEquals(250, app.current_score);
    }

    @Test
    public void testAppGameLoss1(){
        app.inva.get(39).y = 400;
        delay(1000);
        assertTrue(app.loss);
    }

    @Test
    public void testAppGameLoss2(){
        app.t.setLife();
        delay(1000);
        assertTrue(app.loss);
    }

    @Test
    public void testAppGameWin1(){
        app.inva.clear();
        app.enemy_amount = 40;
        delay(1000);
        assertTrue(app.win);
        assertTrue(app.all_kill);
        assertEquals(10000, app.highest_score);
        assertEquals(0, app.current_score);
    }

    @Test
    public void testAppGameWin2(){
        app.current_score = 10001;
        app.inva.clear();
        app.enemy_amount = 40;
        delay(500);
        assertTrue(app.all_kill);
        assertTrue(app.win);
        delay(2000);
        assertEquals(10001, app.highest_score);

    }
}
