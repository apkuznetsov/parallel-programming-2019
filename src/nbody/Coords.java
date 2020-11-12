package nbody;

public class Coords {

    private double x;
    private double y;

    public Coords() {
        x = 0.0;
        y = 0.0;
    }

    public Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
