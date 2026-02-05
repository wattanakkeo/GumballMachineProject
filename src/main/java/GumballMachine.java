import java.util.Queue;

public class GumballMachine {

    // Types of gumball
    final Gumball redGumball;
    final Gumball yellowGumball;

    // Variables to track the dispensed gumball
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

    // Insert coin, balance added, invalid coins are stored and return
    public int insertCoin(int coinValue) {
        boolean isAccepted = currency.insertCoin(coinValue);
        if (!isAccepted) {
            return coinValue;
        }
        return 0;
    }

    public boolean dispenseGumball(String color) {
        // Checks for the red color gumball and increments redGumDispensed if balance is
        // sufficient
        if (redGumball.getColor().equals(color)) {
            if ((currency.getBalance() - redGumball.getPrice()) < 0) {
                return false;
            }
            currency.deduct(redGumball.getPrice());
            redGumDispensed++;
            return true;
        }

        // Checks for the yellow color gumball and increments redGumDispensed if balance
        // is sufficient
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

    // Presses the Red lever to dispense a red gumball.
    // Returns true if the gumball was dispensed, false if balance is insufficient.
    public boolean pressRedLever() {
        return dispenseGumball("Red");
    }

    // Dispenses a Yellow gumball when the Yellow lever is pressed.
    // Returns true if successful, false otherwise.
    public boolean pressYellowLever() {
        return dispenseGumball("Yellow");
    }

    // Returns the remaining balance, then resets it to zero
    public int returnMyChange() {
        int change = currency.getBalance();
        currency.resetBalance();
        return change;
    }

}
