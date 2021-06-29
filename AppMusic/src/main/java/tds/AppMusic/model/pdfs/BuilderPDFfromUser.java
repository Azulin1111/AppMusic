package tds.AppMusic.model.pdfs;

public interface BuilderPDFfromUser {
    void buildPDF(String nameFile);
    void buildUser(String nameUser);
    void buildPlaylist();
    void buildSong();
    void getPDF();
}
