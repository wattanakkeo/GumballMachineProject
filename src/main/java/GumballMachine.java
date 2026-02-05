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
    private int invalidCoinsValue;


    // Constructor only instantiates a GumballMachine with only 2 types of gumballs
    public GumballMachine() {
        redGumball = new Gumball("Red", 5);
        yellowGumball = new Gumball("Yellow", 10);
        this.currency = new Currency();
        this.invalidCoinsValue = 0;
        this.redGumDispensed = 0;
        this.yellowGumDispensed = 0;
    }

    // Insert coin, balance added, invalid coins are stored and return
    public boolean insertCoin(int coinValue) {
        boolean isAccepted = currency.insertCoin(coinValue);
        if (!isAccepted) {
            invalidCoinsValue += coinValue;
            dispenseInvalidCoin();
        }
        return isAccepted;
    }

    // Helper method to dispense and reset the invalidCoinsValue
    public int dispenseInvalidCoin() {
        int out = invalidCoinsValue;
        invalidCoinsValue = 0;
        return out;
    }

    public boolean dispenseGumball(String color) {
        // Checks for the red color gumball and increments redGumDispensed if balance is sufficient
        if (redGumball.getColor().equals(color)) {
            if ((currency.getBalance() - redGumball.getPrice()) < 0) {
                return false;
            }
            currency.deduct(redGumball.getPrice());
            redGumDispensed++;
            System.out.println(redGumDispensed);
            return true;
        }

        // Checks for the yellow color gumball and increments redGumDispensed if balance is sufficient
        if (yellowGumball.getColor().equals(color)) {
            if ((currency.getBalance() - yellowGumball.getPrice()) < 0) {
                return false;
            }
            currency.deduct(yellowGumball.getPrice());
            yellowGumDispensed++;
            System.out.println(yellowGumDispensed);
            return true;
        }

        // Error if some other color is entered
        System.out.println("Error incorrect color options");
        return false;
    }
}

