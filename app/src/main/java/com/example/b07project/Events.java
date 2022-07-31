public class Event{
    int players_num;
    long start_time[12];
    long end_time[12];
    Venue location;
    public Event(int num, long start, long end, Venue location){
        this.players_num = num;
        this.start_time = start;
        this.end_time = end;
        this.location = loaction;
    }
}