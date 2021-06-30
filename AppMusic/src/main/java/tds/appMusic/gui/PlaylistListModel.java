/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.gui;

import tds.appMusic.model.music.Playlist;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Modelo de {@link JList} especializado para playlists.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class PlaylistListModel implements ListModel<Playlist>{

    private final List<Playlist> playlists = new LinkedList<>();
    private final List<ListDataListener> listeners = new LinkedList<>();

    /**
     * Añade una playlist a la lista.
     * @param playlist La playlist.
     */
    public void add(Playlist playlist) {
        playlists.add(playlist);
        int index = playlists.indexOf(playlist) - 1;

        ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, playlists.size() - 1);
        for (ListDataListener l : listeners) l.contentsChanged(e);
    }

    /**
     * Añade una colección de playlists a la lista.
     * @param playlists Una colección no nula de playlists.
     */
    public void addAll(Collection<Playlist> playlists) {
        int index = playlists.size() - 1;
        this.playlists.addAll(playlists);

        ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, playlists.size() - 1);
        for (ListDataListener l : listeners) l.contentsChanged(e);
    }

    /**
     * Elimina los contenidos de la lista.
     */
    public void clear() {
        int index = playlists.size() - 1;
        playlists.clear();

        ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, 0, index);
        for (ListDataListener l : listeners) l.contentsChanged(e);
    }

    /**
     * Cambia los contenidos de la lista.
     * @param playlists Una colección no nula con los nuevos contenidos de la lista.
     */
    public void replaceWith(Collection<Playlist> playlists) {
        clear();
        addAll(playlists);
    }

    @Override
    public int getSize() {
        return playlists.size();
    }

    @Override
    public Playlist getElementAt(int index) {
        return playlists.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }
}
