package invadem;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;


public class InvaderTest {
    PImage img1;
    PImage img2;
    Invader inv1;
    Invader inv2;
    Invader inv3;


    @Before
    public void Setup(){
        inv1 = new Invader(img1, img2, 100, 50, 16, 16, 0,"Normal",false);
        inv2 = new Invader(img1, img2, 130, 50, 16, 16, 3,"Tank",false);
        inv3 = new Invader(img1, img2, 150, 50, 16, 16, 0,"Power",true);

    }


   @Test
   public void testInvaderConstruction() {
       assertNotNull(inv1);
       assertNotNull(inv2);
       assertNotNull(inv3);

   }

   @Test
   public void testInvaderCheckType() {
       assertEquals("Normal", inv1.CheckType());
       assertEquals("Tank", inv2.CheckType());
       assertEquals("Power", inv3.CheckType());
   }

   @Test
   public void testInvaderMovement() {
       for(int i = 0; i < 7; i++){
           inv1.tick();
       }
       assertTrue(inv1.right_direction);
       assertTrue(inv1.mov_down);
       assertEquals(7, inv1.move_step);
       assertEquals(1, inv1.current_index);
       assertEquals(img2, inv1.getImage());

       inv1.tick();
       assertEquals(58,inv1.y);
       assertFalse(inv1.mov_down);
       assertEquals(0, inv1.move_step);

       for(int i = 0; i < 29; i++){
           inv1.tick();
       }

       assertEquals(29, inv1.count);
       assertEquals(129, inv1.x);

       inv1.tick();
       assertFalse(inv1.right_direction);
       assertEquals(0, inv1.count);
       assertTrue(inv1.mov_down);

       for(int j = 0; j < 37; j++){
           inv1.tick();
       }

       assertEquals(img1, inv1.getImage());
       assertEquals(29, inv1.count);
       assertEquals(101, inv1.x);

       inv1.tick();
       assertTrue(inv1.right_direction);
       assertTrue(inv1.mov_down);
       assertEquals(0, inv1.move_step);
   }

   @Test
   public void testInvaderCheckPower() {
       assertFalse(inv1.CheckPower());
       assertFalse(inv2.CheckPower());
       assertTrue(inv3.CheckPower());
   }

   @Test
   public void testInvaderIntersectWithPlayerProjectile() {
       PImage bullet = new PImage(1,3);
       Invader inv = new Invader(img1, img2, 55, 120, 16, 16, 0,"Normal",false);
       Projectile proj = new Projectile(bullet, 55, 120, 1, 3, 0, "up","normal");
       Projectile proj1 = new Projectile(bullet, 55, 119, 1, 3, 0, "up","normal");
       Projectile proj2 = new Projectile(bullet, 55, 123, 1, 3, 0, "up","normal");
       Projectile proj3 = new Projectile(bullet, 55, 115, 1, 3, 0, "up","normal");
       Projectile proj4 = new Projectile(bullet, 54, 120, 1, 3, 0, "up","normal");
       Projectile proj5 = new Projectile(bullet, 56, 120, 1, 3, 0, "up","normal");
       Projectile proj6 = new Projectile(bullet, 0, 0, 1, 3, 0, "up","normal");

       assertTrue(inv.check(proj));
       assertTrue(inv.check(proj1));
       assertTrue(inv.check(proj2));
       assertFalse(inv.check(proj3));
       assertFalse(inv.check(proj4));
       assertTrue(inv.check(proj5));
       assertFalse(inv.check(proj6));

   }

   @Test
   public void testInvaderAdjustLife(){
       inv2.lifeReduce();
       assertEquals(2,inv2.getLife());

   }

}
