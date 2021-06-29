package tds.AppMusic.persistence;

public class TDSFactoryDAO extends FactoryDAO {

    public TDSFactoryDAO() {}

    @Override
    public IAdaptadorUserDAO getUserDAO() {
        return AdaptadorUserDAO.INSTANCE;
    }

    @Override
    public IAdaptadorSongDAO getSongDAO() {
        return AdaptadorSongDAO.INSTANCE;
    }

    @Override
    public IAdaptadorPlaylistDAO getPlaylistDAO() {
        return AdaptadorPlaylistDAO.INSTANCE;
    }
}
