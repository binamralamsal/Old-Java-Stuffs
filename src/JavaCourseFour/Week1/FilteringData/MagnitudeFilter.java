package JavaCourseFour.Week1.FilteringData;


public class MagnitudeFilter implements Filter {

    private double minMag;
    private double maxMag;

    public MagnitudeFilter(double minMagnitude, double maxMagnitude){
        minMag = minMagnitude;
        maxMag = maxMagnitude;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
    }

    public String getName() {
        return "Magnitude Filter";
    }
}
