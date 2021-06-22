package tds.AppMusic.model.music;

// TODO add documentation
public class Song {

    private final String name;
    private final Genre genre;
    private final String path;
    private final String singer;
    private int playCount = 0;
    private int code;

    public Song(String name, String singer, Genre genre, String path) {
        this.name = name;
        this.singer = singer;
        this.genre = genre;
        this.path = path;
        code = 0;

    }

    public Song(String name, String singer, Genre genre, String path, int playCount) {
        this.name = name;
        this.genre = genre;
        this.singer = singer;
        this.path = path;
        this.playCount = playCount;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getSinger(){
        return singer;
    }

    public String getPath() {
        return path;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void addPlay() {
        playCount++;
    }

    @Override
    public String toString() {
        return name + ": " + singer;
    }
}
