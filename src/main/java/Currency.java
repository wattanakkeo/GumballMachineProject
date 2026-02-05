public class Currency {
    private int balance;

    public static final int NICKEL = 5;
    public static final int DIME = 10;
    public static final int QUARTER = 25;

    public Currency() {
        this.balance = 0;
    }

    public boolean insertCoin(int coinValue) {
        if (!isValidCoin(coinValue))
            return false;
        balance += coinValue;
        return true;
    }

    public boolean deduct(int amount) {
        if (balance < amount)
            return false;
        balance -= amount;
        return true;
    }

    public int getBalance() {
        return balance;
    }

    private boolean isValidCoin(int coinValue) {
        return coinValue == NICKEL || coinValue == DIME || coinValue == QUARTER;
    }

    public void resetBalance() {
        balance = 0;
    }
}
