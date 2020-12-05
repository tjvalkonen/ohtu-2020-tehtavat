package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        //Matcher m = new And( new HasAtLeast(5, "goals"),
        //                     new HasAtLeast(5, "assists"),
        //                     new PlaysIn("PHI")
        System.out.println("---M1---");
        Matcher m1 = new And( 
                               new HasFewerThan(1, "goals"), 
                               new PlaysIn("NYR")
        );
        
        for (Player player : stats.matches(m1)) {
            System.out.println(player);
        }


        System.out.println(stats.matches(new All()).size());

        System.out.println("---M2---");
        Matcher m2 = new And( 
                               new Not( new HasAtLeast(1, "goals") ), 
                               new PlaysIn("NYR")
        );
        for (Player player : stats.matches(m2)) {
            System.out.println(player);
        }
        System.out.println("---M3---");
        Matcher m3 = new Or(new HasAtLeast(40, "goals"),
                            new HasAtLeast(60, "assists")
        );  

        for (Player player : stats.matches(m3)) {
            System.out.println(player);
        }
        System.out.println("---M4---");
        Matcher m4 = new And(new HasAtLeast(50, "points"),
                     new Or( 
                         new PlaysIn("NYR"),
                         new PlaysIn("NYI"),
                         new PlaysIn("BOS")
                            )
                       );
        for (Player player : stats.matches(m4)) {
            System.out.println(player);
        }

        System.out.println("---Query-1-");

        QueryBuilder query = new QueryBuilder();
        Matcher m5 = query.build();

        for (Player player : stats.matches(m5)) {
            System.out.println( player );
        }

        System.out.println("---Query-2-");

        Matcher m6 = query.playsIn("NYR").build();
 
        for (Player player : stats.matches(m6)) {
            System.out.println( player );
        }

        System.out.println("---Query-3-");

        QueryBuilder query1 = new QueryBuilder();
 
        Matcher m7 = query1.playsIn("NYR")
                         .hasAtLeast(5, "goals")
                         .hasFewerThan(10, "goals").build();
 
        for (Player player : stats.matches(m7)) {
            System.out.println( player );
        }
    }
}
