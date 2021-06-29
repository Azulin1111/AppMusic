package tds.AppMusic.model.pdfs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import tds.AppMusic.model.music.Song;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BuilderItext implements BuilderPDFfromUser{
    private static final String SEPARATOR = "+--------------------------";
    private FileOutputStream file;
    private Document document;

    @Override
    public void buildPDF(File filePDF) throws DocumentException, IOException {
        // Creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist.
        filePDF.createNewFile();
        file = new FileOutputStream(filePDF.getPath());
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
    public void getPDF() throws DocumentException, IOException {
        document.add(new Paragraph(SEPARATOR));
        document.close();
//        file.close();
    }


}
