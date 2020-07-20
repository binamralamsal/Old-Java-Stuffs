package JavaCourseFour.Week1.FilteringData;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> allFilter;
    public MatchAllFilter(){
        allFilter = new ArrayList<Filter>();
    }

    public void addFilter(Filter filterToAdd){
        allFilter.add(filterToAdd);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter each: allFilter){
            if (!each.satisfies(qe)) {
                    return false;
            }
        }
        return true;
    }

    public String getName() {
        String names = "";
        for (Filter f : allFilter){
            names += f.getName() + "\n";
        }
        return names;
    }
}
