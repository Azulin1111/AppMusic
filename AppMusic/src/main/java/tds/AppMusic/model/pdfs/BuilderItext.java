package tds.AppMusic.model.pdfs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import tds.AppMusic.model.music.Song;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BuilderItext implements BuilderPDFfromUser{
    private static final String SEPARATOR = "+--------------------------";
    private FileOutputStream file;
    private Document document;

    @Override
    public void buildPDF(String nameFile) {
        try {
            file = new FileOutputStream(nameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document = new Document();
        try {
            PdfWriter.getInstance(document, file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.open();
    }

    @Override
    public void buildUser(String nameUser) {
        try {
            document.add(new Paragraph(nameUser));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildPlaylist(String namePlaylist) {
        try {
            document.add(new Paragraph(SEPARATOR));
            document.add(new Paragraph("| Playlist : \"" + namePlaylist + "\""));
            document.add(new Paragraph(SEPARATOR));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void buildSong(Song s){
        try{
            document.add(new Paragraph("| Titulo: " + s.getName()));
            document.add(new Paragraph("| Interprete: " + s.getSinger()));
            document.add(new Paragraph("| Genero: " + s.getGenre()));

            document.add(new Paragraph("| "));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void getPDF() {
        try {
            document.add(new Paragraph(SEPARATOR));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }


}
