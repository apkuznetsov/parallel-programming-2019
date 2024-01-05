package kuznetsov.simulation;

import kuznetsov.point.Point;

public interface Simulation {

    int dt();

    int n();

    Point p(int index);

    void compute();

}
