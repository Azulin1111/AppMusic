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

/**
 * Modelo de {@link javax.swing.JTable} especializado para canciones.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class SongTableModel extends DefaultTableModel {

    private static final List<String> COLUMN_HEADERS = new LinkedList<>();
    private Playlist currentPlaylist = new Playlist("");

    static {
        Collections.addAll(COLUMN_HEADERS, "Título", "Intérprete", "Género");
    }

    /**
     * Devuelve la playlist actual.
     * @return La playlist de la cual la tabla obtiene los datos.
     */
    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    /**
     * Elimina los contenidos de la tabla.
     */
    public void clear() {
        currentPlaylist = new Playlist("");
        fireTableDataChanged();
    }

    /**
     * Añade una canción a la tabla, y por ende, a la playlist asociada.
     * @param song La nueva canción.
     */
    public void add(Song song) {
        currentPlaylist.addSong(song);
        fireTableDataChanged();
    }

    /**
     * Elimina una canción de la tabla, y por ende, de la playlist asociada.
     * @param song La canción a eliminar.
     */
    public void remove(Song song) {
        currentPlaylist.removeSong(song);
        fireTableDataChanged();
    }

    /**
     * Reemplaza la playlist asociada.
     * @param playlist La nueva playlist.
     */
    public void replaceWith(Playlist playlist) {
        clear();
        this.currentPlaylist = playlist;
        fireTableDataChanged();
    }

    /**
     * Devuelve la canción correspondiente a la fila en cuestión.
     * @param rowIndex Una fila válida de la tabla.
     * @return La canción asociada a la fila.
     */
    public Song getSongAt(int rowIndex) {
        return currentPlaylist.getSongs().get(rowIndex);
    }

    /**
     * Devuelve las canciones de la tabla.
     * @return Una lista no modificable con las canciones de la tabla.
     */
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
