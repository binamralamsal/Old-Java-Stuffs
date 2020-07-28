
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package JavaCourseFour.Week3.WordGram;
import JavaCourseFour.Week3.WordNGram.MarkovWordOne;
import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(5);
        runModel(markovWord, st, 50, 844);
    }

    public void testHashMap() {
//        String st = "this is a test yes this is really a test";
//        String st = "this is a test yes this is really a test yes a test this is wow";
        int order = 2;
        int seed = 65;
        int length = 50;
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovWord markovWord = new EfficientMarkovWord(order);
        runModel(markovWord, st, length, seed);
        markovWord.printHashMapInfo();
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }

    public static void main(String[] args) {
        MarkovRunner test = new MarkovRunner();
        test.testHashMap();
    }
}
