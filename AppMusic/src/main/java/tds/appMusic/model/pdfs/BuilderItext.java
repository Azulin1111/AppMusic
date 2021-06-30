/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.pdfs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import tds.appMusic.model.music.Song;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Implementación de constructor PDF.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class BuilderItext implements BuilderPDFfromUser {

    private static final String SEPARATOR = "+--------------------------";
    private Document document;

    @Override
    public void buildPDF(File filePDF) throws DocumentException, IOException {
        // Creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist.
        if (!filePDF.createNewFile()) throw new IOException("El fichero ya existe.");

        FileOutputStream file = new FileOutputStream(filePDF.getPath());
        document = new Document();
        PdfWriter.getInstance(document, file);
        document.open();
    }

    @Override
    public void buildUser(String nameUser) throws DocumentException {
        document.add(new Paragraph(nameUser));
    }

    @Override
    public void buildPlaylist(String namePlaylist) throws DocumentException {
        document.add(new Paragraph(SEPARATOR));
        document.add(new Paragraph("| Playlist : \"" + namePlaylist + "\""));
        document.add(new Paragraph(SEPARATOR));
    }

    @Override
    public void buildSong(Song s) throws DocumentException {
        document.add(new Paragraph("| Titulo: " + s.getName()));
        document.add(new Paragraph("| Interprete: " + s.getSinger()));
        document.add(new Paragraph("| Genero: " + s.getGenre()));

        document.add(new Paragraph("| "));
    }

    @Override
    public void getPDF() throws DocumentException {
        document.add(new Paragraph(SEPARATOR));
        document.close();
    }
}
