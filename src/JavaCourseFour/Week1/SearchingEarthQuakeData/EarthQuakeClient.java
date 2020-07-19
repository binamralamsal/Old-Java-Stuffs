package JavaCourseFour.Week1.SearchingEarthQuakeData;

import java.util.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
                                                   double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData){
            if (quake.getMagnitude() > magMin){
                answer.add(quake);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
                                                      double distMax,
                                                      Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quakes : quakeData){
            Location to = quakes.getLocation();
            double dist = from.distanceTo(to);
            double distMaxMeters = distMax * 1000;
            if (dist < distMaxMeters)
                answer.add(quakes);
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude, Longitude, Magnitude, Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/JavaCourseFour/Week1/QuakeEntry/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> bigQuakesData = filterByMagnitude(list, 5.0);
        System.out.println("Total Data read: "+list.size());

        for (QuakeEntry quakes : bigQuakesData){
            System.out.println(quakes);
        }
        System.out.println("Total quakes that meet the given criteria are: " + bigQuakesData.size());
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/JavaCourseFour/Week1/QuakeEntry/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Total Quakes data:  "+ list.size());

        // This location is Durham, NC
//        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> listClose = this.filterByDistanceFrom(list, 1000, city);
        for(QuakeEntry qe : listClose){
            Location to = qe.getLocation();
            double distInMeters = city.distanceTo(to)/1000.0;
            System.out.println( distInMeters + " " + qe.getInfo());
        }
        System.out.println();
        System.out.println("Found " +listClose.size() +" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        FileResource file = new FileResource();
//        String source = file.asString();

        // change this source file location to work
        String source = "src/JavaCourseFour/Week1/QuakeEntry/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println();
        System.out.println("Total Data read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth ){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry quake : quakeData){
            if (quake.getDepth() > minDepth && quake.getDepth() < maxDepth){
                answer.add(quake);
            }
        }

        return answer;
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/JavaCourseFour/Week1/QuakeEntry/data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Total Data read: " + list.size());
        ArrayList<QuakeEntry> quakesDepthData = filterByDepth(list, -10000.0, -8000.0);

        for (QuakeEntry qe : quakesDepthData) {
            System.out.println(qe);
        }
        System.out.println("Found " +quakesDepthData.size() +" quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry quakes : quakeData){
            if (where.equals("start")) {
                if (quakes.getInfo().startsWith(phrase))
                    answer.add(quakes);
            } else if(where.equals("end")) {
                if (quakes.getInfo().endsWith(phrase))
                    answer.add(quakes);
            } else{
                if (quakes.getInfo().contains(phrase))
                    answer.add(quakes);
            }
        }

        return answer;
    }

    public void quakesByPhrase (){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/JavaCourseFour/Week1/QuakeEntry/data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Total Data read: " + list.size());
        ArrayList<QuakeEntry> quakesPhraseData = filterByPhrase(list, "any", "Creek");

        for (QuakeEntry qe : quakesPhraseData) {
            System.out.println(qe);
        }
        System.out.println("Found " +quakesPhraseData.size() +" quakes that match that criteria");
    }

    public static void main(String[] args) {
        EarthQuakeClient test = new EarthQuakeClient();
        test.quakesByPhrase();
    }
}
