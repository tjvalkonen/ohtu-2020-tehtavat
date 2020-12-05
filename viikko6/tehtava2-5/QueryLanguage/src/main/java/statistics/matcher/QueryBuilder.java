package statistics.matcher;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class QueryBuilder {
    Matcher query;

    public QueryBuilder() {
        query = new All();
    }

    public Matcher build() {
        return query;
    }

    public QueryBuilder playsIn(String p) {
        this.query = new PlaysIn(p);
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.query = new And(query, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        this.query = new And(query, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        List<Matcher> list = new ArrayList<>();
        list.add(this.query);
        list.addAll(Arrays.asList(matchers));
        this.query = new Or(list.toArray(new Matcher[list.size()]));
        return this;
   }

}