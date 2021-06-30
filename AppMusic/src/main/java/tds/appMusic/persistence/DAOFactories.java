/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.persistence;

/**
 * Colección de tipos de factorías existentes.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum DAOFactories {
    TDS;

    /**
     * Devuelve la instancia de factoría correspondiente al tipo de factoría seleccionado.
     * @return Una instancia de  {@link FactoryDAO}.
     */
    FactoryDAO getInstance() {
        switch (this) {
            case TDS:
                return new TDSFactoryDAO();
        }
        return null;
    }
}
