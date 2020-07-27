package JavaCourseFour.Week3.InterfaceAbstractClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int n;
    private HashMap<String, ArrayList<String>> map;
    public EfficientMarkovModel(int N){

        n = N;
        map = new HashMap<String, ArrayList<String>>();
        myRandom = new Random();
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    @Override
    public String toString() {
        return "Markov model of order: " + n;
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);
        for(int k=0; k < numChars - n; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }
    private void buildMap() {
        for (int pos = 0; pos <= myText.length() - n; pos++) {
            int subEnd = pos + n;
            String sub = myText.substring(pos, subEnd);
            if (!map.containsKey(sub)) {
                map.put(sub, new ArrayList<String>());
            }
            if (subEnd < myText.length()) {
                String follower = myText.substring(subEnd, subEnd + 1);
                //System.out.println(sub + ": " + follower);
                ArrayList<String> followers = map.get(sub);
                followers.add(follower);
                map.put(sub, followers);
            }
        }
    }

    @Override
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }

    public void printHashMapInfo() {
        System.out.println("Number of keys: " + map.size());

        int largestSize = 0;
        for (String key : map.keySet()) {
            int keySize = map.get(key).size();
            if (keySize > largestSize) {
                largestSize = keySize;
            }
        }
        System.out.println("The size of the largest ArrayList of characters: " + largestSize);

        System.out.println("The keys that have the maximum size value:");
        for (String key : map.keySet()) {
            if (map.get(key).size() == largestSize) {
                System.out.println(key);
            }
        }

        System.out.println("\n");
    }
}
