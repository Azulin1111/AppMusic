/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.pdfs;

import com.itextpdf.text.DocumentException;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

// Builder's Director class
public class ParserUser {
    private BuilderPDFfromUser builder;
    private final User user;

    public ParserUser(User user){
        this.user = user;
    }
    public void setBuilder(Builders tipo){
        builder = BuilderPdfFactory.INSTANCE.getBuilderPdf(tipo);
    }

    public void parse(File filePDF) throws DocumentException, IOException {
        builder.buildPDF(filePDF);
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
