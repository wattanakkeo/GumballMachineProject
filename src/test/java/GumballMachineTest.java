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
    }
}