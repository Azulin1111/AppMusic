package tds.AppMusic.GUI;

import tds.AppMusic.model.music.Playlist;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PlaylistListModel implements ListModel<Playlist>{

    private final List<Playlist> playlists = new LinkedList<>();
    private final List<ListDataListener> listeners = new LinkedList<>();


    public void add(Playlist playlist) {
        playlists.add(playlist);
        int index = playlists.indexOf(playlist) - 1;

        ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, playlists.size() - 1);
        for (ListDataListener l : listeners) l.contentsChanged(e);
    }

    public void addAll(Collection<Playlist> playlists) {
        int index = playlists.size() - 1;
        this.playlists.addAll(playlists);

        ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, playlists.size() - 1);
        for (ListDataListener l : listeners) l.contentsChanged(e);
    }

    public void clear() {
        int index = playlists.size() - 1;
        playlists.clear();

        ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, 0, index);
        for (ListDataListener l : listeners) l.contentsChanged(e);
    }

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
