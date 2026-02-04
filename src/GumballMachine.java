
public class GumballMachine {
    final Gumball redGumball;
    final Gumball yellowGumball;

    //Constructor only instantiates a GumballMachine with only 2 gumballs
    public GumballMachine() {
        redGumball = new Gumball("Red", 5);
        yellowGumball = new Gumball("Yellow", 10);
    }
}