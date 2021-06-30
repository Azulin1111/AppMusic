/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

public enum DAOFactories {
    TDS;

    FactoryDAO getInstance() {
        switch (this) {
            case TDS:
                return new TDSFactoryDAO();
        }
        return null;
    }
}
