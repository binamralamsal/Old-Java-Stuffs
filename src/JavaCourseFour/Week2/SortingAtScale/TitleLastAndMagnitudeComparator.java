package JavaCourseFour.Week2.SortingAtScale;

import java.util.Comparator;
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        String q1_title = o1.getInfo();
        String q2_title = o2.getInfo();
        String q1_lastWord = q1_title.substring(q1_title.lastIndexOf(" ") + 1);
        String q2_lastWord = q2_title.substring(q2_title.lastIndexOf(" ") + 1);
        int val = q1_lastWord.compareTo(q2_lastWord);
        if(val == 0) {
            val = Double.compare(o1.getMagnitude(), o2.getMagnitude());
        }
        return val;
    }
}
