public class PlayerOne extends Player {
    double lifespan = 10;

    @Override
    public void attack(double weapon) {
        lifespan -=  weapon;
        System.out.println("Computer life span is now: "+ Math.round(lifespan * 100.0) / 100.0);
    }
}
