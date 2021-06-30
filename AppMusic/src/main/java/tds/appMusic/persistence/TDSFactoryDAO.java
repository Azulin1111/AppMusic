/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.persistence;

/**
 * Generador de adaptadores DAO para las distintas clases almacenadas.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class TDSFactoryDAO extends FactoryDAO {

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
