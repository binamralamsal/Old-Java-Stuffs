package JavaCourseFour.Week1.FilteringData;

public class DistanceFilter implements Filter{
    private Location location;
    private double maxDistance;

    public DistanceFilter(Location loc, double distance) {
        location = loc;
        maxDistance = distance;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) <= maxDistance;
    }

    public String getName() {
        return "Distance Filter";
    }
}
