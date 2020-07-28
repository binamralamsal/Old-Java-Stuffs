package JavaCourseFour.Week3.WordGram;

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{

    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;

    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
        //printHashMapInfo();
    }

    public String toString() {
        return "EfficientMarkovWord of order " + myOrder;
    }

    private void buildMap() {
        myMap.clear();
        if (myOrder <= 0) return;
        for (int k = 0; k < myText.length-myOrder; k++) {
            WordGram kGram = new WordGram(myText, k, myOrder);
            ArrayList<String> follows = getFollows(kGram);
            follows.add(myText[k+myOrder]);
            //System.out.println(kGram+": "+follows);
            myMap.put(kGram, follows);
        }
        WordGram kGram = new WordGram(myText, myText.length-myOrder, myOrder);
        if (! myMap.containsKey(kGram))
            myMap.put(kGram, new ArrayList<String>());
    }

    private int indexOf(String[] words, WordGram target, int start) {
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        for(int j = 0; j < words.length - myOrder; j++) {
            WordGram wg = new WordGram(words, j, myOrder);
            list.add(wg);
        }
        for(int i = start; i < words.length; i++) {
            if(i >= list.size()) {
                return -1;
            }
            if(target.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        if(myMap.containsKey(kGram)) {
            follows = myMap.get(kGram);
        }
        return follows;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int i = 0; i < numWords - myOrder; i++) {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    public void printHashMapInfo() {
        int maxSize = 0;
        for(WordGram key : myMap.keySet()) {
            int size = myMap.get(key).size();
            if(size > maxSize) {
                maxSize = size;
            }
        }
        System.out.println("Number of keys: " + myMap.size());
        System.out.println("Max num of following a key: " + maxSize);
    }
}