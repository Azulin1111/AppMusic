package tds.AppMusic.persistance;

import java.util.HashMap;
import java.util.Map;

enum DAOFactories {
    TDS;

    FactoryDAO getInstance() {
        switch (this) {
            case TDS:
                return new TDSFactoryDAO();
        }
        return null;
    }
}

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

}
