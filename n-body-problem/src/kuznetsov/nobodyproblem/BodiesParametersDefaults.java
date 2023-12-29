package kuznetsov.nobodyproblem;

public class BodiesParametersDefaults {

    // n
    public static final int MIN_BODIES_NUM = 1;
    public static final int MAX_BODIES_NUM = 1024;

    // m
    public static final double MIN_BODY_MASS = 1e4;
    public static final double MAX_BODY_MASS = 9e15;

    // dt
    public static final int MIN_DELTA_TIME = 16;
    public static final int MAX_DELTA_TIME = 1_000;

    // eps
    public static final double MIN_ERROR_DISTANCE = 1;
    public static final double MAX_ERROR_DISTANCE = 1_000;

    // width and height
    public static final int MIN_WIDTH = 200;
    public static final int MAX_WIDTH = 2_000;
    public static final int MIN_HEIGHT = 200;
    public static final int MAX_HEIGHT = 2_000;

    // duration millis
    public static final int MIN_DURATION_MILLIS = 10_000;
    public static final int MAX_DURATION_MILLIS = 3_600_000;

    // threads
    public static final int MIN_THREADS_NUM = 1;
    public static final int MAX_THREADS_NUM = 64;

}
