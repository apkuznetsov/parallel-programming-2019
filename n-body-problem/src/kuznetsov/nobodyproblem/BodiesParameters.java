package kuznetsov.nobodyproblem;

import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MAX_BODIES_NUM;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MAX_BODY_MASS;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MAX_DELTA_TIME;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MAX_DURATION_MILLIS;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MAX_ERROR_DISTANCE;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MAX_HEIGHT;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MAX_THREADS_NUM;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MAX_WIDTH;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MIN_BODIES_NUM;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MIN_BODY_MASS;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MIN_DELTA_TIME;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MIN_DURATION_MILLIS;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MIN_ERROR_DISTANCE;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MIN_HEIGHT;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MIN_THREADS_NUM;
import static kuznetsov.nobodyproblem.BodiesParametersDefaults.MIN_WIDTH;

public record BodiesParameters(
        int n, double m, int dt, double eps, int width, int height, int durationMillis, int threadsNum) {

    public BodiesParameters {
        if (n < MIN_BODIES_NUM || n > MAX_BODIES_NUM) {
            throw new IllegalArgumentException("n = " + n);
        } else if (m < MIN_BODY_MASS || m > MAX_BODY_MASS) {
            throw new IllegalArgumentException("m = " + m);
        } else if (dt < MIN_DELTA_TIME || dt > MAX_DELTA_TIME) {
            throw new IllegalArgumentException("dt = " + dt);
        } else if (eps < MIN_ERROR_DISTANCE || eps > MAX_ERROR_DISTANCE) {
            throw new IllegalArgumentException("eps = " + eps);
        } else if (width < MIN_WIDTH || width > MAX_WIDTH) {
            throw new IllegalArgumentException("width = " + width);
        } else if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new IllegalArgumentException("height = " + height);
        } else if (durationMillis < MIN_DURATION_MILLIS || durationMillis > MAX_DURATION_MILLIS) {
            throw new IllegalArgumentException("duration millis = " + durationMillis);
        } else if (threadsNum < MIN_THREADS_NUM || threadsNum > MAX_THREADS_NUM) {
            throw new IllegalArgumentException("threads num = " + threadsNum);
        }
    }

}
