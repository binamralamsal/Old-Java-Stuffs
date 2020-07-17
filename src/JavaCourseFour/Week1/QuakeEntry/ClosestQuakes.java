package JavaCourseFour.Week1.QuakeEntry;

/**
 * Find N-closest quakes
 *
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int j = 0; j < howMany; j++) {
            int minIndex = 0;
            for (int k = 1; k < copy.size(); k++) {
                QuakeEntry quake = copy.get(k);
                Location loc = quake.getLocation();
                if (loc.distanceTo(current) < copy.get(minIndex).getLocation().distanceTo(current)) {
                    minIndex = k;
                }
            }
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/JavaCourseFour/Week1/QuakeEntry/data/nov20quakedatasmall.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Total earthquake data: "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }

    public static void main(String[] args) {
        ClosestQuakes test = new ClosestQuakes();
        test.findClosestQuakes();
    }
}
