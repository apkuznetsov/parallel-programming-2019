public class AppSettings {

    public final int width;
    public final int height;
    public final int bodiesNum;
    public final double bodyMass;
    public final int deltaTime;
    public final double errorDistance;
    public final int durationMillis;
    public final int threadsNum;

    public AppSettings(
            int width,
            int height,
            int bodiesNum,
            double bodyMass,
            int deltaTime,
            double errorDistance,
            int durationMillis,
            int threadsNum
    ) {
        this.width = width;
        this.height = height;
        this.bodiesNum = bodiesNum;
        this.bodyMass = bodyMass;
        this.deltaTime = deltaTime;
        this.errorDistance = errorDistance;
        this.durationMillis = durationMillis;
        this.threadsNum = threadsNum;
    }
}
