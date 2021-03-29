package invadem;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;

public class TankTest {
    PImage img;
    PImage bullet;
    Tank tank;
    List<Projectile> shot;

    @Before
    public void Setup(){
        img = new PImage(22, 16);
        bullet = new PImage(1,3);
        tank = new Tank(img, 310, 464, 22, 16, 3);

        shot = new ArrayList<Projectile>();
    }

   @Test
   public void testTankConstruction() {
       assertNotNull(tank);
   }

   @Test
   public void testTankAdjustmentLife() {
       tank.setLife();
       assertEquals(0, tank.getLife());

       tank.lifeReduce();
       assertEquals(-1, tank.getLife());
   }

   @Test
   public void testTankMovement() {
       String sign = new String("+");
       Tank tank1 = new Tank(img, 160, 464, 22, 16, 3);
       Tank tank2 = new Tank(img, 500, 464, 22, 16, 3);

       tank.setMove(sign);
       assertEquals(1, tank.move);
       tank.tick();
       tank1.move = 0;
       tank1.tick();
       tank2.tick();
       assertEquals(311, tank.x);
       assertEquals(160, tank1.x);
       assertEquals(500, tank2.x);

       sign = "-";
       tank.setMove(sign);
       assertEquals(-1, tank.move);
       for(int i =0; i< 10; i++){
           tank.tick();
           tank1.tick();
           tank2.tick();
       }
       assertEquals(301, tank.x);
       assertEquals(160, tank1.x);
       assertEquals(500, tank2.x);

       sign = "s";
       tank.setMove(sign);
       assertEquals(0, tank.move);
       tank.tick();
       assertEquals(301, tank.x);

       sign = "w";
       tank.setMove(sign);
       assertEquals(0, tank.move);
       tank.tick();
       assertEquals(301, tank.x);


   }

   @Test
   public void testTankCollision(){
       Projectile p1 = new Projectile(bullet, 300, 464, 1,3,0,"down","normal");
       Projectile p3 = new Projectile(bullet, 310, 464, 1,3,0,"down","normal");
       shot.add(p1);
       shot.add(p3);

       boolean result = tank.check(p3);
       assertTrue(result);
       boolean result1 = tank.check(p1);
       assertFalse(result1);

       tank.Beattacked(shot);
       assertEquals(1, shot.size());
       assertEquals(2, tank.getLife());
   }

   @Test
   public void testTankCollisionWithSuper(){
        Projectile p2 = new Projectile(bullet, 310, 464, 1,3,0,"down","super");
        shot.add(p2);

        tank.Beattacked(shot);
        assertEquals(0, shot.size());
        assertEquals(0, tank.getLife());
   }
}
