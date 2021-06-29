package tds.AppMusic.model.pdfs;

import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;

// Builder's Director class
public class ParserUser {
    BuilderPDFfromUser builder;

    public void parse(User user, String nameFile){
        builder.buildPDF(nameFile);
        builder.buildUser(user.getName());
        for(Playlist p : user.getPlaylists()){
            builder.buildPlaylist(p.getName());

            for(Song s : p.getSongs()){
                builder.buildSong(s);
            }
        }
        builder.getPDF();
    }
}
