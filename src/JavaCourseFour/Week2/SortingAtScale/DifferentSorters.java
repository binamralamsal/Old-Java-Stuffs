package JavaCourseFour.Week2.SortingAtScale;
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    public void sortWithCompareTo() {
        int quakeNumber = 600;
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/JavaCourseFour/Week2/SortingAtScale/data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        System.out.println("Print quake entry in position " + quakeNumber);
        for(QuakeEntry qe: list) {
//            System.out.println(qe);
        }
            System.out.println(list.get(quakeNumber));

    }    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByTitleAndDepth(){
        int quakeNumber = 500;
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/JavaCourseFour/Week2/SortingAtScale/data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        System.out.println("Print quake entry in position " + quakeNumber);
//        for(QuakeEntry qe: list) {
//            System.out.println(qe);
//        }
        System.out.println(list.get(quakeNumber));

    }

    public void sortByLastWordInTitleThenByMagnitude(){
        int quakeNumber = 500;
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/JavaCourseFour/Week2/SortingAtScale/data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        System.out.println("Print quake entry in position " + quakeNumber);
//        for(QuakeEntry qe: list) {
//            System.out.println(qe);
//        }
        System.out.println(list.get(quakeNumber));

    }

    public static void main(String[] args) {
        DifferentSorters test = new DifferentSorters();
        test.sortByLastWordInTitleThenByMagnitude();
    }
}
