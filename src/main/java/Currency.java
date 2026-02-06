public class Currency {
    private int balance;

    public static final int NICKEL = 5;
    public static final int DIME = 10;
    public static final int QUARTER = 25;

    // Money stored in "coin return"
    private int coinReturntray;

    //Invalid coins inserted but not yet returned, will returned when pressing lever
    private int pendingInvalid;

    public Currency() {
        this.balance = 0;
        this.coinReturntray = 0;
        this.pendingInvalid = 0;
    }

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
     *
     * @param coinValue -
     * @return
     */
    public boolean insertCoin(int coinValue) {
        if (!isValidCoin(coinValue)) {
            pendingInvalid += coinValue;
            releaseInvalidToTray();
            return false;
        }
        balance += coinValue;
        return true;
    }

    public boolean deduct(int amount) {
        if (balance < amount) return false;
        balance -= amount;
        return true;
    }

    private boolean isValidCoin(int coinValue) {
        return coinValue == NICKEL || coinValue == DIME || coinValue == QUARTER;
    }

    // Call when user hit the lever, move pending invalid coins to return tray
    public void releaseInvalidToTray() {
        if (pendingInvalid > 0) {
            coinReturntray += pendingInvalid;
            pendingInvalid = 0;
        }
    }

    // get all money from return tray and empty return tray
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
