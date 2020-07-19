package JavaCourseFour.Week1.SearchingEarthQuakeData;

import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/JavaCourseFour/Week1/QuakeEntry/data/nov20quakedata.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Total earthquake data: " + list.size());

        ArrayList<QuakeEntry> largest = getLargest(list, 5);
        for (QuakeEntry quake : largest){
            System.out.println(quake);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        int index = 0;
        for (int k = 0; k < quakeData.size(); k++) {
            if (quakeData.get(k).getMagnitude() > quakeData.get(index).getMagnitude())
                index = k;
        }
        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        if (quakeData.size() < howMany)
            howMany = quakeData.size();
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        int index = 0;
        for(int i = 0; i < howMany; i++) {
            index = indexOfLargest(copy);
            ret.add(copy.get(index));
            copy.remove(index);
        }
        return ret;
    }

    public static void main(String[] args) {
        LargestQuakes test = new LargestQuakes();
        test.findLargestQuakes();
    }
}