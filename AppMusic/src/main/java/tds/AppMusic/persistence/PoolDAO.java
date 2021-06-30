/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

/* Esta clase implementa un pool para los adaptadores que lo necesiten */

import java.util.Hashtable;

public enum PoolDAO {
    INSTANCE;
    private final Hashtable<Integer, Object> pool = new Hashtable<>();

    public Object getObject(int id) {
        return pool.get(id);
    } // devuelve null si no encuentra el objeto

    public void addObject(int id, Object objeto) {
        pool.put(id, objeto);
    }

    public boolean contains(int id) {
        return pool.containsKey(id);
    }
}
