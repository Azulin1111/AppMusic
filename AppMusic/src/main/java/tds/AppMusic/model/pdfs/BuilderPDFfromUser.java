package tds.AppMusic.model.pdfs;

import com.itextpdf.text.DocumentException;
import tds.AppMusic.model.music.Song;

import java.io.FileNotFoundException;

public interface BuilderPDFfromUser {
    void buildPDF(String nameFile) throws DocumentException, FileNotFoundException;
    void buildUser(String nameUser) throws DocumentException;
    void buildPlaylist(String namePlaylist) throws DocumentException;
    void buildSong(Song s) throws DocumentException;
    void getPDF() throws DocumentException;
}
