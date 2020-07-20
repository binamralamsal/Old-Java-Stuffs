package JavaCourseFour.Week1.FilteringData;
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/JavaCourseFour/Week1/FilteringData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("Total earthquake data: " + list.size());

//        Filter f = new MagnitudeFilter(4.0, 5.0);
//        ArrayList<QuakeEntry> m7  = filter(list, f);
//        Filter g = new DepthFilter(-35000.0, -12000.0);
//        m7 = filter(m7, g);
//        Location loc = new Location(39.7392, -104.9903);
        Filter f = new MagnitudeFilter(3.5, 4.5);
        ArrayList<QuakeEntry> m7 = filter(list, f);
        Filter g = new DepthFilter(-55000.0, -20000.0 );
        m7 = filter(m7, g);
        for (QuakeEntry qe: m7) {
            System.out.println(qe);
        }
        System.out.println("Total earthquake that match the criteria: " + m7.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/JavaCourseFour/Week1/FilteringData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Total earthquake data: " + list.size());

        Filter a = new MagnitudeFilter(1.0, 4.0);
        Filter b = new DepthFilter(-180000.0, -30000.0 );
        Filter c = new PhraseFilter("any", "o");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(a);
        maf.addFilter(b);
        maf.addFilter(c);

        ArrayList<QuakeEntry> output = filter(list, maf);

        for (QuakeEntry qe: output) {
            System.out.println(qe);
        }

        System.out.println();
        System.out.println("Found " + output.size() + " earthquake data that match following criteria:");
        System.out.println(maf.getName());
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/JavaCourseFour/Week1/FilteringData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Total earthquake data: " + list.size());

        Filter a = new MagnitudeFilter(0.0, 5.0);
        Location loc = new Location(55.7308, 9.1153);
        Filter b = new DistanceFilter(loc, 3000000  );
        Filter c = new PhraseFilter("any", "e");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(a);
        maf.addFilter(b);
        maf.addFilter(c);

        ArrayList<QuakeEntry> output = filter(list, maf);

        for (QuakeEntry qe: output) {
            System.out.println(qe);
        }
        System.out.println();
        System.out.println("Found " + output.size() + " earthquake data that match following criteria:");
        System.out.println(maf.getName());
    }

    public static void main(String[] args) {
        EarthQuakeClient2 test = new EarthQuakeClient2();
        test.testMatchAllFilter2();
    }
}
