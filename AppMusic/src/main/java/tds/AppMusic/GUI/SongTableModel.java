/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.GUI;

import tds.AppMusic.model.music.Playlist;
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
    private Playlist currentPlaylist = new Playlist("");

    static {
        Collections.addAll(COLUMN_HEADERS, "Título", "Intérprete", "Género");
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void clear() {
        currentPlaylist = new Playlist("");
        fireTableDataChanged();
    }

    public void add(Song song) {
        currentPlaylist.addSong(song);
        fireTableDataChanged();
    }

    public void remove(Song song) {
        currentPlaylist.removeSong(song);
        fireTableDataChanged();
    }

    public void replaceWith(Playlist playlist) {
        clear();
        this.currentPlaylist = playlist;
        fireTableDataChanged();
    }

    public Song getSongAt(int rowIndex) {
        return currentPlaylist.getSongs().get(rowIndex);
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(currentPlaylist.getSongs());
    }

    @Override
    public int getRowCount() {
        return currentPlaylist != null ? currentPlaylist.getSongs().size() : 0;
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
        Song song = currentPlaylist.getSongs().get(rowIndex);
        if (columnIndex == COLUMN_HEADERS.indexOf("Título")) return song.getName();
        if (columnIndex == COLUMN_HEADERS.indexOf("Intérprete")) return song.getSinger();
        if (columnIndex == COLUMN_HEADERS.indexOf("Género")) return song.getGenre();
        return null;
    }
}
