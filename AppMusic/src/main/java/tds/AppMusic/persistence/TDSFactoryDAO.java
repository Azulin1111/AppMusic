/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

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
