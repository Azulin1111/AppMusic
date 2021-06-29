package tds.AppMusic.GUI;

import tds.AppMusic.model.music.Song;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SongTableModel extends DefaultTableModel {

    private static final List<String> COLUMN_HEADERS = new LinkedList<>();
    private final List<Song> songs = new LinkedList<>();

    static {
        Collections.addAll(COLUMN_HEADERS, "Título", "Intérprete", "Género");
    }

    public void addAll(Collection<Song> songs) {
        this.songs.addAll(songs);
        fireTableDataChanged();
    }

    public void clear() {
        songs.clear();
        fireTableDataChanged();
    }

    public void add(Song song) {
        this.songs.add(song);
        fireTableDataChanged();
    }

    public void remove(Song song) {
        songs.remove(song);
        fireTableDataChanged();
    }

    public void replaceWith(Collection<Song> songs) {
        clear();
        if (songs != null) addAll(songs);
    }

    public Song getSongAt(int rowIndex) {
        return songs.get(rowIndex);
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    @Override
    public int getRowCount() {
        return songs != null ? songs.size() : 0;
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
}
