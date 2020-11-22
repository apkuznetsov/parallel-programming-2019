package nbody;

public class NbodySettings {

    public final double bodyMass;
    public final int deltaTime;
    public final double errorDistance;
    public final int threadsNum;

    public NbodySettings(
            double bodyMass,
            int deltaTime,
            double errorDistance,
            int threadsNum
    ) {
        this.bodyMass = bodyMass;
        this.deltaTime = deltaTime;
        this.errorDistance = errorDistance;
        this.threadsNum = threadsNum;
    }
}
