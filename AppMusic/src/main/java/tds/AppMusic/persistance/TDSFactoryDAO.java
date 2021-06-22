package tds.AppMusic.persistance;

public class TDSFactoryDAO extends FactoryDAO {

    public TDSFactoryDAO() {}

    @Override
    public IAdaptadorUserDAO getClienteDAO() {
        return null;
    }

    @Override
    public IAdaptadorUserDAO getUserDAO() {
        return AdaptadorUserDAO.INSTANCE;
    }

    @Override
    public IAdaptadorSongDAO getSongDAO() {
        return AdaptadorSongDAO.INSTANCE;
    }
}
