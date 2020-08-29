import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Combat Arena!!");
        System.out.println("Enter your username: ");
        String username = sc.next();
        System.out.println("Enter '1' to start game or any number to exit");
        String input = sc.next();

        if(input.equals("1")){
            startGame(username);
        } else{
            System.out.println("Game exited...");
        }
    }
    public static void startGame(String username){
        String play = "1";
        Scanner sc = new Scanner(System.in);

        while (play.equals("1")){

            double weapon = 0;

            System.out.println("Game started!");
            System.out.println();
            Computer computer = new Computer(username);
            PlayerOne playerOne = new PlayerOne();

            double sword = 2.95;
            double axe = 2.75;
            double spear = 2.55;
            double guns = 2.98;

            System.out.println(username+" "+"Pick a weapon!!: ");
            System.out.println("Press 1 for Sword, 2 for Axe, 3 for Spear, 4 for guns");
            String num = sc.nextLine();

            if(num.equals("1"))
                weapon = sword;
            else if(num.equals("2"))
                weapon = axe;
            else if(num.equals("3"))
                weapon = spear;
            else if(num.equals("4"))
                weapon = guns;

            System.out.println("Press 'a' to Attack your enemy.");
            String input = sc.nextLine();

            if(input.equals("a")) {
                playerOne.attack(weapon);

                computer.attack(axe);
            }

            while (computer.lifespan > 0 && playerOne.lifespan > 0) {
                System.out.println("Press 'a' to Attack again!.");
                input = sc.nextLine();

                if(input.equals("a")){
                    playerOne.attack(weapon);
                    computer.attack(axe);
                }
            }
            if(computer.lifespan < playerOne.lifespan)
                System.out.println("You have won!");
            else if(computer.lifespan > playerOne.lifespan)
                System.out.println("Beita have lost!");
            else if(computer.lifespan == playerOne.lifespan) {
                System.out.println("Draw!!");
            }

            System.out.println("Press 1 to play again or any number to exit");
            play = sc.nextLine();
        }
    }
}
