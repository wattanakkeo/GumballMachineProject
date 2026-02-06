import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GumballMachineTest { 
    // Test if GumballMachine create exactly 2 colors: Red and Yellow and if the price is correct
    @Test
    void constructor_createsRedAndYellowGumballs() {
        GumballMachine gm = new GumballMachine();
        
        assertNotNull(gm.redGumball);
        assertNotNull(gm.yellowGumball);
        
        assertEquals("Red", gm.redGumball.getColor());
        assertEquals(5, gm.redGumball.getPrice());

        assertEquals("Yellow", gm.yellowGumball.getColor());
        assertEquals(10, gm.yellowGumball.getPrice());

        assertEquals(0, gm.currency.getBalance());
    }

    // Test if balance add up correctly when valid coins are inserted
    @Test 
    void insert_validCoins_returns0_andIncreaseBalance() {
        GumballMachine gm = new GumballMachine();
        
        assertEquals(0, gm.insertCoin(Currency.NICKEL));
        assertEquals(5, gm.currency.getBalance());

        assertEquals(0, gm.insertCoin(Currency.DIME));
        assertEquals(15, gm.currency.getBalance());

        assertEquals(0, gm.insertCoin(Currency.QUARTER));
        assertEquals(40, gm.currency.getBalance());
    }

    // Test if balance will not change when inserting invalid coin value
    @Test
    void insert_invalidCoins_returnCoinValue_andDoesNotChangeBalance() {
        GumballMachine gm = new GumballMachine();

        // Invalid coin value, balance unchange
        assertEquals(2, gm.insertCoin(2));
        assertEquals(0, gm.currency.getBalance());

        assertEquals(55, gm.insertCoin(55));
        assertEquals(0, gm.currency.getBalance());
    }

    // Test if no gumballs dispensed when user has an insufficient balance
    @Test
    void dispenseGumball_insufficient_balance_returnCurrentGumCount() {
        GumballMachine gm = new GumballMachine();

        // Insufficient balance, gumball dispensed amount remains the same
        assertFalse(gm.dispenseRed());
        assertEquals(0, gm.getRedDispensed());

        assertFalse(gm.dispenseYellow());
        assertEquals(0, gm.getYellowDispensed());
    }

    // Test if correct amount of gumballs is dispensed and balance is deducted
    @Test
    void dispenseGumball_sufficient_balance_incrementGumball_deductBalance_andReturnCurrentGumAmount() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(Currency.QUARTER);

        // Sufficient balance, deduct balance and return gumball
        assertTrue(gm.dispenseRed());
        assertEquals(20, gm.getBalance());

        assertTrue(gm.dispenseYellow());
        assertEquals(10, gm.getBalance());
    }

    // Test the correct amount of change dispensed when returning change
    @Test
    void returnChange() {
        GumballMachine gm = new GumballMachine();

        // Returns users balance
        gm.insertCoin(1);
        assertEquals(1, gm.pullCoinReturn());

        gm.insertCoin(Currency.DIME);
        assertEquals(10, gm.pullCoinReturn());

        gm.insertCoin(Currency.NICKEL);
        assertEquals(5, gm.pullCoinReturn());

    }
}