
public class Gumball {
    private String color;
    private int price;

    /**
    * Constructor for Gumball
    * @color - Color of gumball
    * @price - price (in cents)
    */
    public Gumball(String color, int price) {
        this.color = color;
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }
}
