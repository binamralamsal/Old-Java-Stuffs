package JavaCourseFour.Week3.GeneratingRandomText;

import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public static void testGetFollows(){
        MarkovOne test = new MarkovOne();
        test.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = test.getFollows("es");
        System.out.println("All follows are: " + follows);
        System.out.println("Size of follows are: " + follows.size());
    }

    public static void testGetFollowsWithFile(){
        FileResource file = new FileResource();
        String str = file.asString();
        str = str.replace('\n', ' ');
        MarkovOne test = new MarkovOne();
        test.setTraining(str);
        ArrayList<String> follows = test.getFollows("th");
        System.out.println("All follows are: " + follows);
        System.out.println("Size of follows are: " + follows.size());
    }

    public static void main(String[] args) {
        testGetFollowsWithFile();
    }
}
