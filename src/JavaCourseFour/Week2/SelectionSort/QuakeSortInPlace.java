
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package JavaCourseFour.Week2.SelectionSort;
import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIndex = from;
        for (int i = from + 1; i < quakeData.size(); i++)
            if (quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth())
                maxIndex = i;
        return maxIndex;
    }
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for (int i = 0; i < 50; i++){
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIndex);
            in.set(i, qmax);
            in.set(maxIndex, qi);
        }
    }
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i = 0; i + 1 < quakeData.size() - numSorted; i++){
            if (quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()){
                QuakeEntry qe = quakeData.get(i);
                QuakeEntry qe1 = quakeData.get(i+1);
                quakeData.set(i, qe1);
                quakeData.set(i+1, qe);
            }
        }
    }
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for(int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
        }
    }
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i = 0; i + 1 < quakes.size(); i++){
            if (quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int count = 0;
        for(int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            count++;
            if (checkInSortedOrder(in))
                break;
        }
        System.out.println("Passes  need to bubble sort: " + count);
    }
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int count = 0;
        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            count++;
            if (checkInSortedOrder(in))
                break;
        }
        System.out.println("Passes  need to bubble sort: " + count);
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/JavaCourseFour/Week2/SelectionSort/data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
//       for (QuakeEntry qe : list){
//           System.out.println(qe);
//       }
        System.out.println("Total Earthquake Data: "+list.size());
//        sortByMagnitude(list);
//        sortByLargestDepth(list);
//        sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
//        sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    public static void main(String[] args) {
        QuakeSortInPlace test = new QuakeSortInPlace();
        test.testSort();
    }
}
