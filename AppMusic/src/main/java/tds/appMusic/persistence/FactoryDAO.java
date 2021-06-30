/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.persistence;

/**
 * Factoría de adaptadores DAO para la persistencia.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public abstract class FactoryDAO {
    /**
     * La única instancia de la factoría.
     */
    private static FactoryDAO unicaInstancia;

    /**
     * Recupera la instancia única del tipo especificado. En caso de haber llamado a este método previamente, devuelve
     * la instancia generada.
     * @param factory El tipo de factoría.
     * @return La instancia de la factoría.
     */
    public static FactoryDAO getInstance(DAOFactories factory) {
        if (unicaInstancia == null) unicaInstancia = factory.getInstance();
        return unicaInstancia;
    }

    protected FactoryDAO(){}

    /**
     * Devuelve el adaptador de usuarios correspondiente
     * @return Una instancia {@link IAdaptadorUserDAO}.
     */
    public abstract IAdaptadorUserDAO getUserDAO();
    /**
     * Devuelve el adaptador de canciones correspondiente
     * @return Una instancia {@link IAdaptadorSongDAO}.
     */
    public abstract IAdaptadorSongDAO getSongDAO();
    /**
     * Devuelve el adaptador de playlists correspondiente
     * @return Una instancia {@link IAdaptadorPlaylistDAO}.
     */
    public abstract IAdaptadorPlaylistDAO getPlaylistDAO();

}
