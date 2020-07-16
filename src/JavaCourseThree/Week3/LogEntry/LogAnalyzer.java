package JavaCourseThree.Week3.LogEntry;

;

import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.HashMap;


public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    public int countUniqueIps(){
        ArrayList<String> uniqueIps = new ArrayList<String>();
        for (LogEntry le: records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIps.contains(ipAddr)){
                uniqueIps.add(ipAddr);
            }
        }
        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num){
        System.out.println();
        System.out.println("All the status code of log entrys greater than " + num + " are:");
        for (LogEntry le: records){
            if (le.getStatusCode() > num){
                System.out.println(le);
            }
        }
    }

    public void readFile() {
        FileResource file = new FileResource();
        for (String line : file.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
    public void printAll(){
        for (LogEntry le :  records){
            System.out.println(le);
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIpVisits = new ArrayList<String>();
        for (LogEntry le: records){
            String time = le.getAccessTime().toString();
            if (time.contains(someday)){
                if (!uniqueIpVisits.contains(le.getIpAddress())){
                    uniqueIpVisits.add(le.getIpAddress());
                }
            }
        }
        return uniqueIpVisits;
    }

    public ArrayList<String> countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIpVisitsInRange = new ArrayList<String>();
        for (LogEntry le: records){
            int status = le.getStatusCode();
            if (status >= low && status <= high){
                if (!uniqueIpVisitsInRange.contains(le.getIpAddress())){
                    uniqueIpVisitsInRange.add(le.getIpAddress());
                }
            }
        }
        return uniqueIpVisitsInRange;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int highestCount = 0;
        for (int visits: counts.values()){
            if (visits > highestCount){
                highestCount = visits;
            }
        }
        return highestCount;
    }

    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)){
                counts.put(ip, 1);
            }
            else{
                counts.put(ip, counts.get(ip)+ 1);
            }
        }
        return counts;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> IPs){
        int maxValue = mostNumberVisitsByIP(IPs);
        ArrayList<String> ipAddress = new ArrayList<String>();
        for(String key : IPs.keySet()) {
            if(IPs.get(key) == maxValue){
                if(!ipAddress.contains(key)) {
                    ipAddress.add(key);
                }
            }
        }
        return ipAddress;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> maps = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4,10);
            String ipAddress = le.getIpAddress();
            ArrayList<String> ip;
            if(!maps.containsKey(date)) {
                ip = new ArrayList<String>();
                ip.add(ipAddress);
                maps.put(date, ip);
            } else {
                ip = maps.get(date);
                //if(!ip.contains(ipAddress)) { //checks duplicate
                ip.add(ipAddress);
                maps.put(date, ip);
                //}
            }
        }
        return maps;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ip, String day) {
        ArrayList<String> ipWithMostAccess;
        HashMap<String, Integer> ips = new HashMap<String, Integer>();
        for(String key : ip.keySet()) {
            if(key.equals(day)) {
                for(int i = 0; i < ip.get(key).size(); i++) {
                    String ipAd = ip.get(key).get(i);
                    if(!ips.containsKey(ipAd)) {
                        ips.put(ipAd, 1);
                    } else {
                        ips.put(ipAd, ips.get(ipAd) + 1);
                    }
                }
            }
        }
        ipWithMostAccess = iPsMostVisits(ips);
        return ipWithMostAccess;
    }

    public void tester(){
        readFile();
        printAll();
        int uniqueIPs = countUniqueIps();
        System.out.println("There are "+ uniqueIPs + " unique logs");
        printAllHigherThanNum(400);
        ArrayList<String> ipVisits = uniqueIPVisitsOnDay("Sep 27");
        System.out.println();
        System.out.println("All the Ips that accessed on September 27 are:");
        for (String ips : ipVisits){
            System.out.println(ips);
        }
        System.out.println("Size of all Ips: " + ipVisits.size());

        ArrayList<String> countIPsInRange = countUniqueIPsInRange(200,299);
        System.out.println();
        System.out.println("All the Ips that have status code less then 299 and greater then 200 are:");
        for (String ips : countIPsInRange){
            System.out.println(ips);
        }
        System.out.println("Size: " + countIPsInRange.size());

        HashMap<String,Integer> counts = countVisitsPerIP();
        System.out.println();
        System.out.println("All the unique IPs and the counts are: ");
        System.out.println(counts);
        System.out.println("Total unique Ips are: " + counts.size());

        int highestVisitCount = mostNumberVisitsByIP(counts);
        System.out.println();
        System.out.println("The most visit by ip is " + highestVisitCount);

        ArrayList<String> ipsMostVisit = iPsMostVisits(counts);
        System.out.println();
        System.out.println("The Most visit Ips are:");
        for (String IP : ipsMostVisit){
            System.out.println(IP);
        }

        System.out.println();
        System.out.println("Ip address visit in each day are: ");
        HashMap<String, ArrayList<String>> maps = iPsForDays();
        System.out.println(maps);
        int largeCount = 0;
        String largeCountDay = "";
        for (String day : maps.keySet()){
            ArrayList<String> IPS = maps.get(day);
            if (IPS.size() > largeCount){
                largeCount = IPS.size();
                largeCountDay = day;
            }
        }
        System.out.println();
        System.out.println("The highest visit day is "+ largeCountDay + " with " + largeCount + " visits");

        ArrayList<String> mostIP = iPsWithMostVisitsOnDay(maps, "Sep 29");
        System.out.println();
        System.out.println(mostIP);
    }

    public static void main(String[] args) {
        LogAnalyzer a = new LogAnalyzer();
        a.tester();

    }
}
