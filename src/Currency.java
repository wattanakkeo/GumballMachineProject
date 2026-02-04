//Currency can be inserted into the Gumball Machine inserting only
// nickels, dimes, quarters

public class Currency {
    private int total;
    final int nickel = 5;
    final int dime = 10;
    final int quarter = 25;

    public Currency() {
        this.total = 0;
    }

    public void insertNickel() {
        total += nickel;
    }

    public void insertDime() {
        total += dime;
    }

    public void insertQuarter() {
        total += quarter;
    }

    public int getTotal() {
        return total;
    }
}
