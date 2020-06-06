package Week3;

import edu.duke.FileResource;

import java.util.ArrayList;


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
            if (le.getStatusCode() >= num){
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

    public void tester(){
        readFile();
        printAll();
        int uniqueIPs = countUniqueIps();
        System.out.println("There are "+ uniqueIPs + " unique logs");
        printAllHigherThanNum(400);
        ArrayList<String> ipVisits = uniqueIPVisitsOnDay("Mar 17");
        System.out.println();
        System.out.println("All the Ips that accessed on March 17 are:");
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
    }
    public static void main(String[] args) {
        LogAnalyzer a = new LogAnalyzer();
        a.tester();
    }
}
