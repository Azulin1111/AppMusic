package tds.AppMusic.GUI;

import tds.AppMusic.model.music.Song;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SongTableModel implements TableModel {

    private static final List<String> COLUMN_HEADERS = new LinkedList<>();

    private final List<TableModelListener> listeners = new LinkedList<>();

    private final List<Song> songs = new LinkedList<>();

    static {
        Collections.addAll(COLUMN_HEADERS, "Título", "Intérprete", "Género");
    }

    public void addAll(Collection<Song> songs) {
        int begin = this.songs.size() - 1;
        this.songs.addAll(songs);
        TableModelEvent e = new TableModelEvent(this, begin, this.songs.size() - 1);
        for (TableModelListener l : listeners) l.tableChanged(e);
    }

    public void clear() {
        int end = songs.size() - 1;
        songs.clear();
        TableModelEvent e = new TableModelEvent(this, 0, end);
        for (TableModelListener l : listeners) l.tableChanged(e);
    }

    public void add(Song song) {
        this.songs.add(song);
        int index = songs.indexOf(song);

        TableModelEvent e = new TableModelEvent(this, index, songs.size() - 1);
        for (TableModelListener l : listeners) l.tableChanged(e);
    }

    public void remove(Song song) {
        int begin = songs.indexOf(song);
        songs.remove(song);

        if (begin != -1) {
            TableModelEvent e = new TableModelEvent(this, begin, songs.size() - 1);
            for (TableModelListener l : listeners) l.tableChanged(e);
        }

    }

    public void replaceWith(Collection<Song> songs) {
        clear();
        addAll(songs);
    }

    public Song getSongAt(int rowIndex) {
        return songs.get(rowIndex);
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    @Override
    public int getRowCount() {
        return songs.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_HEADERS.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return (columnIndex < COLUMN_HEADERS.size() && columnIndex >= 0) ? COLUMN_HEADERS.get(columnIndex) : null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Song song = songs.get(rowIndex);
        if (columnIndex == COLUMN_HEADERS.indexOf("Título")) return song.getName();
        if (columnIndex == COLUMN_HEADERS.indexOf("Intérprete")) return song.getSinger();
        if (columnIndex == COLUMN_HEADERS.indexOf("Género")) return song.getGenre();
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
