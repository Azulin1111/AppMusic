package tds.AppMusic.model.pdfs;

import com.itextpdf.text.DocumentException;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;

import java.io.FileNotFoundException;
import java.util.Locale;

// Builder's Director class
public class ParserUser {
    private BuilderPDFfromUser builder;
    private User user;

    public ParserUser(User user){
        this.user = user;
    }

    public void setBuilder(String tipo){
        builder = BuilderPdfFactory.INSTANCE.getBuilderPdf(tipo);
    }

    public void parse(String nameFile) throws DocumentException, FileNotFoundException {
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
