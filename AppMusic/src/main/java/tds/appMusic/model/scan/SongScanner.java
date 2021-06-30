/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.scan;

import tds.appMusic.model.music.Song;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

/**
 * Escaneador de canciones locales.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class SongScanner {

    private final File root;

    /**
     * @param root La ruta del directorio raíz donde comenzar la búsqueda de canciones.
     * @throws IllegalArgumentException Si {@code root} no es un directorio.
     */
    public SongScanner(File root) throws IllegalArgumentException {
        if (root.isDirectory())
            this.root = root;
        else throw new IllegalArgumentException("Root is not a folder");
    }

    /**
     * Devuelve las canciones encontradas en el directorio raíz.
     * @return Una colección de canciones.
     */
    public Collection<Song> scanRoot() {
        Collection<Song> songs = new HashSet<>();

        // Scan root folder for songs
        File[] folders = root.listFiles();

        // Get songs
        if (folders != null)
            for (File f : folders)
                if (f.isDirectory())
                    songs.addAll(scanFolder(f, f.getName().charAt(0) + f.getName().substring(1).toLowerCase(Locale.ROOT)));

        return songs;
    }

    private Collection<Song> scanFolder(File f, String genre) {
        Collection<Song> songs = new HashSet<>();

        File[] files = f.listFiles();

        if (files != null)
            for (File ff : files)
                if (ff.isFile() && ff.getName().endsWith(".mp3")) {
                    // Song found
                    String filename = ff.getName().replace(".mp3", "");

                    String[] parts = filename.split("-");

                    if (parts.length != 2) {
                        System.err.println("Buscador de canciones> Canción mal formada! Nombre: " + ff.getName());
                        continue;
                    }

                    String interprete = parts[0].trim();
                    String name = parts[1].trim();

                    Song s = new Song(name, interprete, genre, ff.toURI());
                    songs.add(s);
                }

        return songs;
    }
}
