import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GumballMachineTest { 
    /** 
    * - Test if GumballMachine object creates exactly 2 colors of gumball: Red and yellow
    * - Test if Red gumball is exactly 5 cents and Yellow gumball is exactly 10 cents
    * - Test if balance is initilized with 0.
    */
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

    /**
     * - Verify if invalid coins return 0 when accepted
     * - Verify if balance is added up correctly: 5 + 10 + 25
     */
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

    /**
     * - Test if inserting invalid coins value is detected
     * - Verify that invalid coins will not change the balance
     */
    @Test
    void insert_invalidCoins_returnCoinValue_andDoesNotChangeBalance() {
        GumballMachine gm = new GumballMachine();

        // Invalid coin value, balance unchange
        assertEquals(2, gm.insertCoin(2));
        assertEquals(0, gm.currency.getBalance());

        assertEquals(55, gm.insertCoin(55));
        assertEquals(0, gm.currency.getBalance());
    }

    /** 
    * - Test that dispensing does not occur when user does not have enough balanmce.
    * - Verify that the dispensing count of machine unchanges when user has insufficient balance. 
    */
    @Test
    void dispenseGumball_insufficient_balance_returnCurrentGumCount() {
        GumballMachine gm = new GumballMachine();

        // Insufficient balance, gumball dispensed amount remains the same
        assertFalse(gm.dispenseRed());
        assertEquals(0, gm.getRedDispensed());

        assertFalse(gm.dispenseYellow());
        assertEquals(0, gm.getYellowDispensed());
    }

    /**
     * - Test if balance is deducted correctly with sufficent balance
     * - Test if gumball is dispensed correctly with sufficient balance
     */
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

    /**
     * - test if pullCoinReturn() return all current balance + returned/invalid coin 
     * - verify that the balance is reset back to 0 afterward.
     */
    @Test
    void returnChange() {
        GumballMachine gm = new GumballMachine();

        // Returns users balance
        gm.insertCoin(1);
        assertEquals(1, gm.pullCoinReturn());
        assertEquals(0, gm.getBalance());

        gm.insertCoin(Currency.DIME);
        assertEquals(10, gm.pullCoinReturn());
        assertEquals(0, gm.getBalance());

        gm.insertCoin(Currency.NICKEL);
        assertEquals(5, gm.pullCoinReturn());
        assertEquals(0, gm.getBalance());
    }

    /**
     * - Test if pullCoinReturn() returns correct balance after spending
     * - Verify the balance is set to 0 after
     */

    @Test
    void returnChange_after_spending() {
        GumballMachine gm = new GumballMachine();

        //Insert quarter and spends 10 cents on a yellow gumball
        gm.insertCoin(Currency.QUARTER);
        gm.dispenseYellow();
        assertEquals(15, gm.pullCoinReturn());
        assertEquals(0, gm.getBalance());

    }
}