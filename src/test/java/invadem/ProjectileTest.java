package invadem;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import processing.core.PImage;

public class ProjectileTest {

    PImage img1;
    PImage img2;
    PImage img3;
    Projectile proj;
    Projectile proj2;
    Projectile proj3;
    Projectile proj4;
    Projectile proj5;
    List<Projectile> shot;

    @Before
    public void Setup(){
        img1 = new PImage(1,3);
        img2 = new PImage(2,5);
        img3 = new PImage(2,5);
        shot = new ArrayList<Projectile>();

        proj = new Projectile(img1, 50, 60, 1, 3, 0, "up", "normal");
        proj2 = new Projectile(img2, 50 ,60, 2, 5, 0, "down", "super");
        proj3 = new Projectile(img2, 50 ,60, 2, 5, 0, "rightdown", "super");
        proj4 = new Projectile(img2, 50 ,60, 2, 5, 0, "leftdown", "super");
        proj5 = new Projectile(img2, 50 ,60, 2, 5, 0, "nothing", "super");
    }

   @Test
   public void testProjectileConstruction() {
       assertNotNull(proj);
   }

   @Test
   public void testProjectileGetLife() {
       assertEquals(0, proj.getLife());
   }

   @Test
   public void testProjectileGetType(){
       assertEquals("normal", proj.CheckType());
   }

   @Test
   public void testProjectileMove() {
       proj.tick();
       proj2.tick();
       proj3.tick();
       proj4.tick();
       proj5.tick();

       assertEquals(59, proj.y);
       assertEquals(61, proj2.y);
       assertEquals(51, proj3.x);
       assertEquals(61, proj3.y);
       assertEquals(49, proj4.x);
       assertEquals(61, proj4.y);
       assertEquals(60, proj5.y);
       assertEquals(50, proj.x);
   }

   @Test
   public void testProjectileRemove(){
       shot.add(new Projectile(img1, -300, 199, 1, 3, 0, "up", "normal"));
       shot.add(new Projectile(img1, -300, -200, 1, 3, 0, "up", "normal"));
       shot.add(new Projectile(img2, 400, -200, 1, 3, 0, "up", "normal"));
       shot.add(new Projectile(img3, 200, 600, 1, 3, 0, "up", "normal"));
       shot.add(new Projectile(img1, 0, 99999999, 1, 3, 0, "up", "normal"));

       Projectile.removeOutBound(shot);
       assertEquals(0, shot.size());
   }

}
