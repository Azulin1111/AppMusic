package tds.AppMusic.model.pdfs;

import tds.AppMusic.model.music.Song;

public interface BuilderPDFfromUser {
    void buildPDF(String nameFile);
    void buildUser(String nameUser);
    void buildPlaylist(String namePlaylist);
    void buildSong(Song s);
    void getPDF();
}
