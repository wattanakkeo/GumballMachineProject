/**
 * Manage the balance of the vending machine
 * Valid coin: nickel = 5, dime = 10, quarter = 25
 * Invalid coins that do not match the above values will be stored and returned
 * and the balance will not increase.
 * 
 * This class will handle:
 *  - current balance when inserted valid coins
 *  - pending balance of invalid coins after inserting
 *  - the balance of invalid coins that will be returned to user
 */

public class Currency {
    // Current balance (in cents) from valid inserted coins
    private int balance;

    // Valid value of coins accepted by the vending machines
    public static final int NICKEL = 5;
    public static final int DIME = 10;
    public static final int QUARTER = 25;

    // Invalid coins' balance that will be return
    private int coinReturntray;

    //Invalid coins inserted but not yet returned, will returned when pressing lever
    private int pendingInvalid;

    /**
    * Constructor for Currency object
    * Machine balance will be initilized with 0
    * Return coin tray and pending invalid coin will be initilized with 0
    */
    public Currency() {
        this.balance = 0;
        this.coinReturntray = 0;
        this.pendingInvalid = 0;
    }

    // Getters
    public int getBalance() {
        return balance;
    }

    public int getCoinReturnTrayAmount() {
        return coinReturntray;
    }

    public int getPendingInvalid() {
        return pendingInvalid;
    }

    /**
     * Inserts a coin into machine
     * If the coin is valid, increase the balance
     * If the coin value is invalid, value will be stored in return tray, balance unchanges
     * 
     * @param coinValue - valuye of inserted coins in cent
     * @return true if valid, false if rejected.
     */
    public boolean insertCoin(int coinValue) {
        if (!isValidCoin(coinValue)) {
            // store invalid coin value, then move it to Invalid tray
            pendingInvalid += coinValue;
            releaseInvalidToTray();
            return false;
        }
        balance += coinValue;
        return true;
    }

    /**
     * Deduct amount from balance when dispensing a gumball
     * 
     * @param amount - amount of money to deduct in cent
     * @return true if process is successed, false if insufficient balance
     */
    public boolean deduct(int amount) {
        if (balance < amount) return false;
        balance -= amount;
        return true;
    }

    /**
     * Check if the value of the coin is 5, 10, 25
     * 
     * @param coinValue - value of inserted coin in cent
     * @return true if coin is in nickel/dime./quarter, false otherwise
     */
    private boolean isValidCoin(int coinValue) {
        return coinValue == NICKEL || coinValue == DIME || coinValue == QUARTER;
    }

    /**
     * Move pending invalid coin value to return tray
     * 
     * Called when user press lever
     */
    public void releaseInvalidToTray() {
        if (pendingInvalid > 0) {
            coinReturntray += pendingInvalid;
            pendingInvalid = 0;
        }
    }

    /**
     * Get all money in return tray and empty it
     * @return total amount of returned money in cent
     */
    public int pullCoinReturnToTray() {
        int coinOut = coinReturntray;
        coinReturntray = 0;
        return coinOut;
    }

    // Resets the balance back to 0
    public int resetBalance() {
        return balance = 0;
    }
}
