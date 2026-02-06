/**
 * Core logic of the gumball vending machine
 * Vending machine has 2 gumball colors: 
 *  - Red gumball: 5 cents
 *  - Yellow gumball: 10 cents
 * 
 * User can insert multiple coins, then choose to dispense a gumball at a time
 * The return change will also be calculated and returned to user if there is any
 * 
 * Note: getters for balance and dispensed counts are exposed for unit testing
 */

import java.util.Queue;
public class GumballMachine {
    
    // Types of gumball
    final Gumball redGumball;
    final Gumball yellowGumball;

    // The dispensed gumball amount
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

    /**
     * Insert a coin into machine
     * This will retuirn an int to indicate if the coins are accepted or not:
     * - 0 for acceted coin
     * - 1 for rejected coin
     *
     * @param coinValue - value of inserted coins
     */
    public int insertCoin(int coinValue) {
        boolean isAccepted = currency.insertCoin(coinValue);
        if (!isAccepted) {
            return coinValue;
        }
        return 0;
    }

    /**
     * Dispense a single gumball based on user's selection 
     * If balance is not enough, do nothing.
     * If user choose invalid color, print error and return false
     * 
     * @param color - color of gumball
     * @return true if gumball was dispensed, false otherwise
     */
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

    /**
     * presses the red lever (dispense a red gumball).
     *
     * @return true if dispensed; false otherwise
     */
    public boolean dispenseRed() {
        return dispenseGumball("Red");
    }

    /**
     * presses the yellow lever (dispense a yellow gumball).
     *
     * @return true if dispensed; false otherwise
     */
    public boolean dispenseYellow() {
        return dispenseGumball("Yellow");
    }

    /**
     * Return change + invalid coins to customer, then reset balance to 0
     * 
     * @return total returned money in cents
     */ 
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

