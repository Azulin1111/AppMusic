package tds.AppMusic.app;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class PersistenceManager {

    private final ServicioPersistencia sp;

    public PersistenceManager() {
        sp = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    public void getSong(String id) {

    }

    public void storeSong(String id) {

    }
}
