/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.pdfs;

import com.itextpdf.text.DocumentException;
import tds.appMusic.model.music.Playlist;
import tds.appMusic.model.music.Song;
import tds.appMusic.model.users.User;

import java.io.File;
import java.io.IOException;

/**
 * La clase directora del builder.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class PdfGenerator {

    private BuilderPDFfromUser builder;
    private final User user;

    public PdfGenerator(User user){
        this.user = user;
    }

    /**
     * Establece el builder a utilizar.
     * @param tipo El builder.
     */
    public void setBuilder(Builders tipo){
        builder = BuilderPdfFactory.INSTANCE.getBuilderPdf(tipo);
    }

    /**
     * Inicia construcción del fichero PDF.
     * @param filePDF La ruta al fichero de salida.
     */
    public void parse(File filePDF) throws DocumentException, IOException {
        builder.buildPDF(filePDF);
        builder.buildUser(user.getName());
        for(Playlist p : user.getPlaylists()) {
            builder.buildPlaylist(p.getName());
            for(Song s : p.getSongs()) builder.buildSong(s);
        }
        builder.getPDF();
    }
}
