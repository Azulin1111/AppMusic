package tds.AppMusic.GUI;

import tds.AppMusic.app.Controller;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.LinkedList;
import java.util.List;

public class GenreComboBoxModel implements ComboBoxModel<String> {

    public static final String ALL_GENRES = "<Cualquier género>";

    private final List<ListDataListener> listeners = new LinkedList<>();
    private final List<String> genres = new LinkedList<>();
    private int selected = 0;

    {
        genres.add(ALL_GENRES);
    }

    public void updateGenres() {
        genres.clear();
        genres.add(ALL_GENRES);
        genres.addAll(Controller.INSTANCE.getGenres());

        if (selected >= genres.size()) selected = -1;

        ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, genres.size() - 1);
        for (ListDataListener l : listeners) l.contentsChanged(e);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = genres.indexOf(anItem.toString());
    }

    @Override
    public String getSelectedItem() {
        return selected == -1 ? null : genres.get(selected);
    }

    @Override
    public int getSize() {
        return genres.size();
    }

    @Override
    public String getElementAt(int index) {
        return genres.get(index);
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