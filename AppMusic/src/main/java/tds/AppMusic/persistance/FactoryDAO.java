package tds.AppMusic.persistance;

public abstract class FactoryDAO {
    private static FactoryDAO unicaInstancia;

    public static FactoryDAO getInstance(DAOFactories factory) {
        if (unicaInstancia == null) unicaInstancia = factory.getInstance();
        return unicaInstancia;
    }

    protected FactoryDAO(){}

    public abstract IAdaptadorUserDAO getClienteDAO();
    public abstract IAdaptadorUserDAO getUserDAO();
    public abstract IAdaptadorSongDAO getSongDAO();
    public abstract IAdaptadorPlaylistDAO getPlaylistDAO();

}
