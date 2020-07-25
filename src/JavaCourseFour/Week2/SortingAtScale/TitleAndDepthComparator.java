package JavaCourseFour.Week2.SortingAtScale;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        int result = o1.getInfo().compareTo(o2.getInfo());
        if (o1.getInfo() == o2.getInfo()){
            return Double.compare(o1.getDepth(), o2.getDepth());
        }
        return result;
    }
}
