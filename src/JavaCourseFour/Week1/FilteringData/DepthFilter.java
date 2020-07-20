package JavaCourseFour.Week1.FilteringData;

public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;

    public DepthFilter(double minMagnitude, double maxMagnitude){
        minDepth = minMagnitude;
        maxDepth = maxMagnitude;
    }

    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth);
    }

    public String getName() {
        return "Depth Filter";
    }
}
