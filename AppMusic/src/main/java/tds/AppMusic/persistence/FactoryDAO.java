/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

public abstract class FactoryDAO {
    private static FactoryDAO unicaInstancia;

    public static FactoryDAO getInstance(DAOFactories factory) {
        if (unicaInstancia == null) unicaInstancia = factory.getInstance();
        return unicaInstancia;
    }

    protected FactoryDAO(){}

    public abstract IAdaptadorUserDAO getUserDAO();
    public abstract IAdaptadorSongDAO getSongDAO();
    public abstract IAdaptadorPlaylistDAO getPlaylistDAO();

}
