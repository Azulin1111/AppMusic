package tds.AppMusic.model.pdfs;

import tds.AppMusic.model.users.User;

// Builder's Director class
public class ParserUser {
    BuilderPDFfromUser builder;

    public void parse(User user){
        builder.buildPDF("C:\\newPDF.pdf");
        builder.buildUser(user.getName());
        user.getPlaylists().forEach(p -> builder.buildPlaylist(p));

    }

}
