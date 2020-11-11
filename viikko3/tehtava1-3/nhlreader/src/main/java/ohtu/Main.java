
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import static java.util.Comparator.comparingInt;
import java.util.List;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        // System.out.println("json-muotoinen data:");
        // System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        List<Player> sortedPlayers = new ArrayList<>();
                

        System.out.println("Oliot:");
        for (Player player : players) {
            if(player.getNationality().equals("FIN")){
                sortedPlayers.add(player);
            }
        }
        
        // sortedPlayers.sort(comparingInt(Player::getPoints));
        
        /*
        Collections.sort(sortedPlayers, new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getPoints().compareTo(p2.getPoints());
            }
        });
        */
        
        Collections.sort(sortedPlayers, Collections.reverseOrder());

        
        for (Player player : sortedPlayers) {

            System.out.println(player);

        }

    }
}
