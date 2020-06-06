package Week3;

import edu.duke.FileResource;

import java.util.ArrayList;


public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    public void readFile() {
        FileResource file = new FileResource();
        for (String line : file.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }
    public void printAll(){
        for (LogEntry le :  records){
            System.out.println(le);
        }
    }
    public void tester(){
        readFile();
        printAll();
    }
    public static void main(String[] args) {
        LogAnalyzer a = new LogAnalyzer();
        a.tester();
    }
}
