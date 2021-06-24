package tds.AppMusic.persistance;

/* Esta clase implementa un pool para los adaptadores que lo necesiten */

import java.util.Hashtable;

public enum PoolDAO {
    INSTANCE;
    private Hashtable<Integer, Object> pool;

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