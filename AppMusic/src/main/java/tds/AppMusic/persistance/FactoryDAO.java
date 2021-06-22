package tds.AppMusic.persistance;

public abstract class FactoryDAO {
    private static FactoryDAO unicaInstancia;

    public static final String DAO_TDS = "tds.AppMusic.persistance.TDSFactoryDAO";

    public static FactoryDAO getInstance(String tipo) throws ClassNotFoundException {
        if (unicaInstancia == null){
            return Class.forName(tipo).newInstance();
        }
        return unicaInstancia;
    }

    public static FactoryDAO getInstance() throws ClassNotFoundException{
        if (unicaInstancia == null){
            return getInstance(FactoryDAO.DAO_TDS);
        } else
            return unicaInstancia;
    }

    protected FactoryDAO(){}

    public abstract IAdaptadorUserDAO getUserDAO();
    public abstract IAdaptadorSongDAO getSongDAO();

}
