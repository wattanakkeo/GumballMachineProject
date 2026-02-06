import java.util.Queue;

public class GumballMachine {
    
    // Types of gumball
    final Gumball redGumball;
    final Gumball yellowGumball;

    // The dispensed gumball
    private int redGumDispensed;
    private int yellowGumDispensed;
    
    // Variables to track balance and inserted coins
    final Currency currency;

    // Constructor only instantiates a GumballMachine with only 2 types of gumballs
    public GumballMachine() {
        redGumball = new Gumball("Red", 5);
        yellowGumball = new Gumball("Yellow", 10);
        this.currency = new Currency();
        this.redGumDispensed = 0;
        this.yellowGumDispensed = 0;
    }
    // Getters for testing
    public int getBalance() {
        return currency.getBalance();
    }
    public int getRedDispensed() {
        return redGumDispensed;
    }
    public int getYellowDispensed() {
        return yellowGumDispensed;
    }

    // Insert coin, balance added, invalid coins are stored and return
    public int insertCoin(int coinValue) {
        boolean isAccepted = currency.insertCoin(coinValue);
        if (!isAccepted) {
            return coinValue;
        }
        return 0;
    }

    public boolean dispenseGumball(String color) {
        // Checks for the red color gumball and increments redGumDispensed if balance is sufficient
        if (redGumball.getColor().equals(color)) {
            if ((currency.getBalance() - redGumball.getPrice()) < 0) {
                return false;
            }
            currency.deduct(redGumball.getPrice());
            redGumDispensed++;
            return true;
        }

        // Checks for the yellow color gumball and increments redGumDispensed if balance is sufficient
        if (yellowGumball.getColor().equals(color)) {
            if ((currency.getBalance() - yellowGumball.getPrice()) < 0) {
                return false;
            }
            currency.deduct(yellowGumball.getPrice());
            yellowGumDispensed++;
            return true;
        }

        // Error if some other color is entered
        System.out.println("Error incorrect color options");
        return false;
    }

    // Red lever
    public boolean dispenseRed() {
        return dispenseGumball("Red");
    }

    // Yellow lever
    public boolean dispenseYellow() {
        return dispenseGumball("Yellow");
    }

    // Take money from coin return to tray
    public int pullCoinReturn() {
        int returned = 0;

        // Move any pending invalid coins to the return tray first
        currency.releaseInvalidToTray();

        // Take coins already in the return tray
        returned += currency.pullCoinReturnToTray();

        // Take remaining balance
        returned += currency.getBalance();
        currency.resetBalance();

        return returned;
    }
}

