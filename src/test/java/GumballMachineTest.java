import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GumballMachineTest {
    // Test if GumballMachine create exactly 2 colors: Red and Yellow and if the
    // price is correct
    @Test
    void constructor_createsRedAndYellowGumballs() {
        GumballMachine gm = new GumballMachine();

        assertNotNull(gm.redGumball);
        assertNotNull(gm.yellowGumball);

        assertEquals("Red", gm.redGumball.getColor());
        assertEquals(5, gm.redGumball.getPrice());

        assertEquals("Yellow", gm.yellowGumball.getColor());
        assertEquals(10, gm.yellowGumball.getPrice());
    }

    // Test valid coin insertion
    @Test
    void validCoinsAreAccepted() {
        GumballMachine gm = new GumballMachine();

        // Inserts a valid coin: nickel (5), dime (10), or quarter (25)
        // Returns 0 if the coin is accepted
        assertEquals(0, gm.insertCoin(5));
        assertEquals(0, gm.insertCoin(10));
        assertEquals(0, gm.insertCoin(25));
    }

    // Test invalid coin insertion
    @Test
    void invalidCoinIsReturned() {
        GumballMachine gm = new GumballMachine();

        // Attempts to insert invalid coins (any value other than 5, 10, or 25)
        // The machine returns the coin
        assertEquals(3, gm.insertCoin(3));
        assertEquals(7, gm.insertCoin(7));
    }
}