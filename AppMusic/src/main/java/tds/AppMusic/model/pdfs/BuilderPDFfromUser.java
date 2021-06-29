package tds.AppMusic.model.pdfs;

import com.itextpdf.text.DocumentException;
import tds.AppMusic.model.music.Song;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface BuilderPDFfromUser {
    void buildPDF(File filePDF) throws DocumentException, FileNotFoundException, IOException;
    void buildUser(String nameUser) throws DocumentException;
    void buildPlaylist(String namePlaylist) throws DocumentException;
    void buildSong(Song s) throws DocumentException;
    void getPDF() throws DocumentException, IOException;
}
