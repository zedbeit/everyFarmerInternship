import java.lang.Math;
public class Computer extends Player {
    String username;
    double lifespan = 10;

    public Computer(String username){
        this.username = username;
    }

    @Override
    public void attack(double weapon) {
        System.out.println("Computer is now Attacking!!");
        lifespan -=  weapon;
        System.out.println(username+" "+"life span is now: "+ Math.round(lifespan * 100.0) / 100.0);
    }
}
