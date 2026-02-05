import java.util.Queue;

public class GumballMachine {
    
    // Types of gumball
    final Gumball redGumball;
    final Gumball yellowGumball;

    // Constants for valid coins values (in cents)
    public static final int NICKEL = 5;
    public static final int DIME = 10;
    public static final int QUARTER = 25;

    // Variables to track the dispensed gumball
    private int redGumDispensed;
    private int yellowGumDispensed;
    
    // Variables to track balance and inserted coin
    private int currBalance;
    private int invalidCoinsValue; // Number of invalid that will be returned


    //Constructor only instantiates a GumballMachine with only 2 gumballs
    public GumballMachine() {
        redGumball = new Gumball("Red", 5);
        yellowGumball = new Gumball("Yellow", 10);
        this.currBalance = 0;
        this.invalidCoinsValue = 0;
        this.redGumDispensed = 0;
        this.yellowGumDispensed= 0;
    }

    // Insert coin, balance added, invalid coins are stored and return
    public boolean insertCoin(int coinValue) {
        if (isValidCoin(coinValue)) {
            currBalance += coinValue;
            return true;
        } else {
            // Invalid coin is stored and return when lever is pressed
            invalidCoinsValue += coinValue;
            return false;
        }

    }

    // Helper to check for valid coin
    public boolean isValidCoin(int coinValue) {
        return coinValue == NICKEL || coinValue == DIME || coinValue == QUARTER;
    }
}