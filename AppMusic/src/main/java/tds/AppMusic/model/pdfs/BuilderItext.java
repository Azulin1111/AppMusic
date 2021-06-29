package tds.AppMusic.model.pdfs;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import tds.AppMusic.model.music.Playlist;

import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

public class BuilderItext implements BuilderPDFfromUser{
    private FileOutputStream file;
    private Document document;
    private String nameUser;
    private List<Playlist> playlists;

    @Override
    public void buildPDF(String nameFile) {
        file = new FileOutputStream(nameFile);
        document = new Document();
        playlists = new LinkedList<>();
    }

    @Override
    public void buildUser(String nameUser) {
        this.nameUser = nameUser;
    }

    @Override
    public void buildPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    @Override
    public void buildSong() {

    }

    @Override
    public void getPDF() {
        PdfWriter.getInstance(document, file);
        document.open();
        document.add(new Paragraph(nameUser));
        document.add(new Paragraph("---------------"));
        document.

    }


}
