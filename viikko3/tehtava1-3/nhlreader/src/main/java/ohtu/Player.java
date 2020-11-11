
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private Integer assists;
    private Integer goals;
    private Integer penalties;
    private String team;
    private Integer games;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void SetNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setAssists(Integer assists) {
        this.assists = assists;
    }
    
    public Integer getAssists() {
        return assists;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }
    
    public Integer getGoals() {
        return goals;
    }
    
    public void setPenalties (Integer penalties) {
        this.penalties = penalties;
    }
    
    public Integer getPenalties() {
        return penalties;
    }
    
    public void SetTeam (String team) {
        this.team = team;
    }
    
    public String getTeam() {
        return team;
    }
    
    public void setGames (Integer games) {
        this.games = games;
    }
    
    public Integer getGames() {
        return games;
    }
    
    @Override
    public String toString() {
        return " name: " + name + " nationality: " + nationality + " assists: " + assists + " goals: " + goals + " penalties: " + penalties + " team: " + team + " games: " + games;
    }
      
}
