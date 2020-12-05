package statistics.matcher;

public class QueryBuilder {
    Matcher query;

    public QueryBuilder() {
        this.query = new All();
    }

    public Matcher build() {
        return this.query;
    }

    public QueryBuilder playsIn(String p) {
        this.query = new And(query, new PlaysIn(p));
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
}