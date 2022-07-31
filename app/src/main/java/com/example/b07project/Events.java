public class Event{
    int players_num;
    long start_time[12];
    long end_time[12];
    Venue location;
    Activity sports_type;
    public Event(int num, long start, long end, Venue location, Activity activity){
        this.players_num = num;
        this.start_time = start;
        this.end_time = end;
        this.location = loaction;
        this.sports_type = activity;
    }
}