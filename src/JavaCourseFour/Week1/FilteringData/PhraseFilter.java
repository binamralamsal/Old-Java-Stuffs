package JavaCourseFour.Week1.FilteringData;

public class PhraseFilter implements Filter {
    private String where;
    private String phrase;

    public PhraseFilter(String whereToFind, String phraseToFind){
        where = whereToFind;
        phrase = phraseToFind;
    }
    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start"))
            return qe.getInfo().startsWith(phrase);
        else if (where.equals("any"))
            return qe.getInfo().contains(phrase);
        else
            return qe.getInfo().endsWith(phrase);
    }

    public String getName() {
        return "Phrase Filter";
    }
}
