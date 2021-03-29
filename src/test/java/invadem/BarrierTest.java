package invadem;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;


public class BarrierTest {

    PImage one;
    PImage two;
    PImage three;
    PImage four;
    PImage bullet;
    Barriers b;
    List<Projectile> shot;


    @Before
    public void Setup(){
        one = new PImage(8,8);
        two = new PImage(8,8);
        three = new PImage(8,8);
        four = new PImage(8,8);
        bullet = new PImage(1,3);
        shot = new ArrayList<Projectile>();
        b = new Barriers(one, two, three, four, 30, 40, 5, 5, 0);
    }

    @Test
    public void barrierConstruction() {
        assertNotNull(b);
   }

   @Test
   public void testBarrierGetLife() {
       assertEquals(0, b.getLife());
   }

   @Test
   public void testBarrierGetLifeMax() {
       b.setLife();
       assertEquals(1, b.getLife());

       for(int i = 0; i < 3; i++){
           b.setLife();
       }

       assertEquals(3, b.getLife());

   }

   @Test
   public void testBarrierColllision() {
       Projectile p1 = new Projectile(bullet, 30, 40, 1, 3, 0, "down", "normal");
       Projectile p2 = new Projectile(bullet, 100, 50, 2, 5, 0, "down", "super");
       Projectile p3 = new Projectile(bullet, 30, 40, 2, 5, 0, "down", "super");
       List<Projectile> p_list = new ArrayList<Projectile>();

       p_list.add(p1);
       p_list.add(p3);
       shot.add(p1);
       shot.add(p2);
       shot.add(p3);

       Boolean result1 = b.check(shot.get(0));
       assertEquals(true, result1);

       b.barrierchange(shot);
       assertEquals(1, shot.size());
       assertEquals(3, b.getLife());

       Boolean result2 = b.check(shot.get(0));
       assertEquals(false, result2);
       assertEquals(1, shot.size());
       assertEquals(3, b.getLife());

       Barriers b1 = new Barriers(one, two, three, four, 30, 40, 5, 5, 3);

       b1.barrierchange(p_list);
       assertEquals(2, p_list.size());


   }

   @Test
   public void testBarrierGetImage() {
       assertEquals(one, b.getImage());
   }


}
