package invadem;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;


public class MonsterTest {
    Monster mon;
    PImage img;

    @Before
    public void Setup(){
        img = new PImage (70,70);
        mon = new Monster(img, 200, 100, 70, 70, 20);
    }

    @Test
    public void testMonsterMovement(){
        assertTrue(mon.moveDown);
        for(int i =0; i <7; i++){
            mon.tick();
        }

        assertEquals(7, mon.moveStep);
        mon.tick();
        assertFalse(mon.moveDown);
        assertTrue(mon.moveLeft);

        for(int j = 0; j < 40; j++){
            mon.tick();
        }

        assertEquals(160,mon.x);
        mon.tick();
        assertEquals(161,mon.x);
        assertFalse(mon.moveLeft);


        for(int i = 0; i < 240; i++){
            mon.tick();
        }
        assertEquals(401, mon.x);
        mon.tick();
        assertEquals(400, mon.x);
        assertTrue(mon.moveLeft);
    }

    @Test
    public void testMonsterAdjustLife(){
        mon.lifeReduce();
        assertEquals(19,mon.getLife());
    }

    @Test
    public void testMonsterGetImage(){
        PImage img1 = mon.getImage();
        assertEquals(img, img1);
    }

    @Test
    public void testMonsterIntersectWithProjectile(){
        PImage bullet = new PImage(1,3);
        Projectile proj = new Projectile(bullet, 200, 100, 1, 3, 0, "up","normal");
        Projectile proj1 = new Projectile(bullet, 200, 98, 1, 3, 0, "up","normal");
        Projectile proj2 = new Projectile(bullet, 99999, 99999, 1, 3, 0,"up","normal");

        assertTrue(mon.check(proj));
        assertTrue(mon.check(proj1));
        assertFalse(mon.check(proj2));
    }
}
