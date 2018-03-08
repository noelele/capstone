package noelanthony.com.lostandfoundfinal.NewsFeed;

/**
 * Created by Noel on 16/02/2018.
 */

public class items {
    private String name;
    private String time;
    private String location;
    private String poster;
    //private boolean status;

    public items(String name, String time, String location, String poster) {
        this.name = name;
        this.time = time;
        this.location = location;
        this.poster = poster;
       // this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getPoster() {
        return poster;
    }
    //public boolean isFound(){
      //  return status;
    //}
}
