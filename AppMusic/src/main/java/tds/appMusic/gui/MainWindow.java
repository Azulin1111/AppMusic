/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import pulsador.Luz;
import tds.appMusic.app.Controller;
import tds.appMusic.model.discount.Discount;
import tds.appMusic.model.music.Playlist;
import tds.appMusic.model.music.Song;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

/**
 * Ventana principal.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class MainWindow extends AppWindow {

    // Las distintas tarjetas de la ventana principal
    private static final String CARD_SEARCH = "searchPanel";
    private static final String CARD_NEWPL = "newPlaylistPanel";
    private static final String CARD_RECENT = "recentPanel";
    private static final String CARD_PLAYLISTS = "selectedPlaylistPanel";

    private static String CURRENT_CARD = "recentPanel";

    private static final String WELCOME_TEXT = "Hola, ";

    private static final Icon ICON_PLAY = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("/Pictures/BotonPlay.png")));
    private static final Icon ICON_PAUSE = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("/Pictures/BotonPausa.png")));

    private JPanel mainPanel;
    private JButton logoutButton;
    private JButton upgradeAccButton;
    private JLabel welcomeLabel;
    private JButton searchButton;
    private JButton newPlaylistButton;
    private JButton recentButton;
    private JButton myPlaylistsButton;
    private JPanel buttonsPanel;
    private JPanel userPanel;
    private JPanel mainCardPanel;
    private JPanel searchPanel;
    private JPanel newPlaylistPanel;
    private JPanel recentPanel;
    private JPanel selectedPlaylistsPanel;
    private JComboBox<String> searchGenreComboBox;
    private JTextField searchTitleTextField;
    private JTextField searchInterpreteTextField;
    private JPanel searchFiltersPanel;
    private JButton backButton;
    private JButton playButton;
    private JButton nextButton;
    private JButton searchSearchButton;
    private JButton searchCancelButton;
    private JPanel searchButtonPanel;
    private JTable searchTable;
    private JScrollPane searchScrollPane;
    private JPanel playlistCreatePanel;
    private JPanel playlistModifyPanel;
    private JButton playlistCreateButton;
    private JTextField playlistNameTextField;
    private JPanel playlistFiltersPanel;
    private JTextField playlistInterpreteTextField;
    private JTextField playlistTitleTextField;
    private JComboBox<String> playlistGenreComboBox;
    private JButton playlistAddButton;
    private JButton playlistRemoveButton;
    private JTable playlistAddTable;
    private JTable playlistAddedTable;
    private JPanel playlistEditPanel;
    private JScrollPane playlistToAddScrollPane;
    private JScrollPane playlistAddedScrollPane;
    private JPanel playlistAddControlsPanel;
    private JScrollPane recentScrollPane;
    private JTable recentTable;
    private JList<Playlist> playlistList;
    private JTable selectedTable;
    private JScrollPane selectedScrollPane;
    private JScrollPane playlistScrollPane;
    private JButton playlistCancelButton;
    private JButton playlistAcceptButton;
    private JButton playlistSearchButton;
    private JPanel luzPanel;
    private JLabel luzLabel;
    private JPanel musicPanel;
    private JPanel playlistButtonsPanel;
    private JButton makePDFButton;
    private JButton top10Button;
    private JLabel starLabel;

    // Componentes UI personalizados
    private JMenuBar menuBar;
    private Luz luz;
    private JMenu menu;
    private JMenuItem menuItem;

    // Modelos de componentes UI
    private final SongTableModel searchModel = new SongTableModel();
    private final SongTableModel playlistAddModel = new SongTableModel();
    private final SongTableModel playlistAddedModel = new SongTableModel();
    private final SongTableModel recentModel = new SongTableModel();
    private final SongTableModel selectedModel = new SongTableModel();
    private final PlaylistListModel playlistModel = new PlaylistListModel();
    private final GenreComboBoxModel genreCBModel = new GenreComboBoxModel();
    private final GenreComboBoxModel genreCBModel2 = new GenreComboBoxModel();

    private final WindowAdapter windowKiller = new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            System.exit(0);
        }
    };

    public MainWindow(String username) {
        super();
        $$$setupUI$$$();
        setContentPane($$$getRootComponent$$$());

        addWindowListener(windowKiller);

        // Set premium features
        premiumSetup(Controller.INSTANCE.isPremium());

        // Set user welcome label
        welcomeLabel.setText(WELCOME_TEXT + username);

        // Playlist list model
        playlistList.setModel(playlistModel);
        playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Main button listeners
        makePDFButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogType(JFileChooser.SAVE_DIALOG);
            chooser.setFileFilter(new FileNameExtensionFilter("Fichero PDF", "pdf"));
            chooser.showOpenDialog(this);
            File f = chooser.getSelectedFile();
            boolean success = Controller.INSTANCE.generatePDF(f);
            if (!success) say("Generar PDF", "Fallo al generar el PDF! Intenta reiniciar la aplicación.");
            else say("Generar PDF", "Fichero generado con éxito!");
        });
        top10Button.addActionListener(e -> {
            // Implementation goes on in the My Playlists menu, but hidden to the average user.
            switchCard(CARD_PLAYLISTS);
            Controller.updateTop();
            selectedModel.replaceWith(Controller.INSTANCE.getTopSongs());
        });
        searchButton.addActionListener(e -> {
            // Update genres
            genreCBModel.updateGenres();

            switchCard(CARD_SEARCH);
        });
        newPlaylistButton.addActionListener(e -> {
            // Update genres
            genreCBModel2.updateGenres();

            switchCard(CARD_NEWPL);
        });
        recentButton.addActionListener(e -> {
            // Additional work: update recent playlist
            recentModel.replaceWith(Controller.INSTANCE.getRecentPlaylist());

            switchCard(CARD_RECENT);
        });
        myPlaylistsButton.addActionListener(e -> {
            switchCard(CARD_PLAYLISTS);
            // Update playlist list
            playlistModel.replaceWith(Controller.INSTANCE.getPlaylists());

            // Select the first one and display contents
            playlistList.getSelectionModel().setSelectionInterval(0, 0);
            if (playlistList.getSelectedValue() != null)
                selectedModel.replaceWith(playlistList.getSelectedValue());
            else
                selectedModel.replaceWith(new Playlist(""));
        });

        // Specific card listeners
        searchSetup();
        newPlaylistSetup();
        recentSetup();
        myPlaylistsSetup();

        // Luz listener
        luz.addEncendidoListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Archivos XML", "xml"));
            int ret = chooser.showOpenDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                Controller.INSTANCE.loadSongs(chooser.getSelectedFile().toURI());

                // Update filters everywhere
                genreCBModel.updateGenres();
                genreCBModel2.updateGenres();
            }
        });

        // Upgrade account listener
        upgradeAccButton.addActionListener(e -> {
            Discount d = Controller.INSTANCE.getMaximumDiscount();
            if (ask("Comprar premium", "El descuento aplicado es: " + d.asString() + "\nEl precio total es: " + d.finalPrize() + "\nDeseas pagarlo?")) {
                premiumSetup(true);
                Controller.INSTANCE.buyPremium();
            }
        });

        // Sign out listener
        logoutButton.addActionListener(e -> {
            Controller.INSTANCE.switchTrack(null, -1, false);
            removeWindowListener(windowKiller);
            dispose();

            LoginWindow l2 = new LoginWindow();
            l2.pack();
            l2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            l2.setLocationRelativeTo(null);
            l2.setVisible(true);
        });

        // Playlist selection listener
        playlistList.addListSelectionListener(l -> {
            Playlist p = playlistList.getSelectedValue();
            if (p != null) selectedModel.replaceWith(p);
        });

        // Music button listeners
        backButton.addActionListener(e -> {
            Controller.INSTANCE.previousTrack(!CURRENT_CARD.equals(CARD_RECENT));
            if (Controller.INSTANCE.getCurrentPlaylist() != null) playButton.setIcon(ICON_PAUSE);
            updateTableSelection();
            repaint();
        });
        playButton.addActionListener(e -> playSong());
        nextButton.addActionListener(e -> {
            Controller.INSTANCE.nextTrack(!CURRENT_CARD.equals(CARD_RECENT));
            if (Controller.INSTANCE.getCurrentPlaylist() != null) playButton.setIcon(ICON_PAUSE);
            updateTableSelection();
            repaint();
        });

        // Autoselect text field mouse listeners
        MouseAdapter selectAllListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ((JTextField) e.getSource()).selectAll();
            }
        };
        playlistInterpreteTextField.addMouseListener(selectAllListener);
        playlistTitleTextField.addMouseListener(selectAllListener);
        playlistNameTextField.addMouseListener(selectAllListener);
        searchInterpreteTextField.addMouseListener(selectAllListener);
        searchTitleTextField.addMouseListener(selectAllListener);

        // Root directory listener
        menuItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showOpenDialog(this);

            Controller.INSTANCE.scanSongs(chooser.getSelectedFile());
        });

        // Switch to "recent" card
        recentModel.replaceWith(Controller.INSTANCE.getRecentPlaylist());
        switchCard(CARD_RECENT);
    }

    private void switchCard(String newCard) {
        CardLayout cl = (CardLayout) mainCardPanel.getLayout();
        cl.show(mainCardPanel, newCard);
        CURRENT_CARD = newCard;

        // Hide playlist list contents
        playlistModel.clear();

        // Remove all (playable) table selections
        selectedTable.clearSelection();
        recentTable.clearSelection();
        searchTable.clearSelection();

        updateTableSelection();
        pack();
    }

    private void premiumSetup(boolean isPremium) {
        top10Button.setEnabled(isPremium);
        makePDFButton.setEnabled(isPremium);
        starLabel.setVisible(isPremium);
        upgradeAccButton.setEnabled(!isPremium);
    }


    private void searchSetup() {
        // Hide search table on first go
        setComponentVisible(false, searchScrollPane);

        // Set search table model
        searchTable.setModel(searchModel);
        searchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set search combobox model
        searchGenreComboBox.setModel(genreCBModel);

        // Search listener
        searchSearchButton.addActionListener(e -> {
            searchSearchButton.setEnabled(false);
            // Search songs
            String title = searchTitleTextField.getText().trim();
            String interprete = searchInterpreteTextField.getText().trim();
            String genre = (String) searchGenreComboBox.getSelectedItem();
            Playlist songs = Controller.INSTANCE.getSongsFiltered(title, interprete, genre);

            // Display search results
            searchModel.replaceWith(songs);
            setComponentVisible(true, searchScrollPane);
            searchSearchButton.setEnabled(true);
        });

        // Cancel listener
        searchCancelButton.addActionListener(e -> setComponentVisible(false, searchScrollPane));

        // Music selection listener
        searchTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // If double clicked, play
                if (e.getClickCount() == 2) playSong();
            }
        });
    }

    private void newPlaylistSetup() {
        // Hide playlist editing on first go
        setComponentVisible(false, playlistModifyPanel);

        // Table models
        playlistAddTable.setModel(playlistAddModel);
        playlistAddTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistAddedTable.setModel(playlistAddedModel);
        playlistAddedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Combobox model
        playlistGenreComboBox.setModel(genreCBModel2);

        // Creation listener
        playlistCreateButton.addActionListener(e -> {
            // Check for duplicates
            String name = playlistNameTextField.getText().trim();
            boolean success = Controller.INSTANCE.playlistExists(name);

            // Exists?
            playlistAddModel.clear();
            if (!success) playlistAddedModel.clear();
            else {
                say("Error al crear la playlist", "La playlist indicada ya existe.");
                playlistAddedModel.replaceWith(Controller.INSTANCE.getPlaylist(name));
            }
            setComponentVisible(true, playlistModifyPanel);
            pack();
        });

        // Search listener
        playlistSearchButton.addActionListener(e -> {
            playlistSearchButton.setEnabled(false);

            // Search songs
            String title = playlistTitleTextField.getText().trim();
            String interprete = playlistInterpreteTextField.getText().trim();
            String genre = (String) playlistGenreComboBox.getSelectedItem();
            Playlist songs = Controller.INSTANCE.getSongsFiltered(title, interprete, genre);

            // Display search results
            playlistAddModel.replaceWith(songs);
            playlistSearchButton.setEnabled(true);
        });

        // Add/remove listeners
        playlistAddButton.addActionListener(e -> {
            if (playlistAddTable.getSelectedRow() == -1) return;
            Song song = playlistAddModel.getSongAt(playlistAddTable.getSelectedRow());
            playlistAddedModel.add(song);
        });
        playlistRemoveButton.addActionListener(e -> {
            if (playlistAddedTable.getSelectedRow() == -1) return;
            Song song = playlistAddedModel.getSongAt(playlistAddedTable.getSelectedRow());
            playlistAddedModel.remove(song);
        });

        // Accept listener
        playlistAcceptButton.addActionListener(e -> {
            // Create playlist
            String name = playlistNameTextField.getText().trim();
            Controller.INSTANCE.createOrUpdatePlaylist(name, playlistAddedModel.getSongs());
            say("Playlist creada", "Playlist creada con éxito.");
        });
    }

    private void recentSetup() {
        // Table setup
        recentTable.setModel(recentModel);
        recentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Music selection listener
        recentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // If double clicked, play
                if (e.getClickCount() == 2) playSong();
            }
        });
    }

    private void myPlaylistsSetup() {
        // Table setup
        selectedTable.setModel(selectedModel);
        selectedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Music selection listener
        selectedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // If double clicked, play
                if (e.getClickCount() == 2) playSong();
            }
        });
    }


    private void playSong() {
        if (playButton.getIcon().equals(ICON_PAUSE)) {
            playButton.setIcon(ICON_PLAY);
            Controller.INSTANCE.pauseTrack();
        } else if (playButton.getIcon().equals(ICON_PLAY)) {

            if (!itemSelected()) Controller.INSTANCE.resumeTrack();
            else {
                // Start new playlist
                Map.Entry<Playlist, Integer> pair = getSelected();
                Controller.INSTANCE.switchTrack(pair.getKey(), pair.getValue(), !CURRENT_CARD.equals(CARD_RECENT));
            }
            // Only update visually if there's something that can be played
            if (Controller.INSTANCE.getCurrentPlaylist() != null) playButton.setIcon(ICON_PAUSE);
        }
        repaint();
    }

    private boolean itemSelected() {
        switch (CURRENT_CARD) {
            case CARD_SEARCH:
                return searchTable.getSelectedRow() != -1;
            case CARD_RECENT:
                return recentTable.getSelectedRow() != -1;
            case CARD_PLAYLISTS:
                return selectedTable.getSelectedRow() != -1;
            default:
                return false;
        }
    }

    private Map.Entry<Playlist, Integer> getSelected() {
        switch (CURRENT_CARD) {
            case CARD_SEARCH:
                return new AbstractMap.SimpleImmutableEntry<>(searchModel.getCurrentPlaylist(), searchTable.getSelectedRow());
            case CARD_RECENT:
                return new AbstractMap.SimpleImmutableEntry<>(recentModel.getCurrentPlaylist(), recentTable.getSelectedRow());
            case CARD_PLAYLISTS:
                return new AbstractMap.SimpleImmutableEntry<>(selectedModel.getCurrentPlaylist(), selectedTable.getSelectedRow());
            default:
                return new AbstractMap.SimpleEntry<>(null, null);
        }
    }

    private void updateTableSelection() {
        // If there's nothing playing, do nothing
        int cursong = Controller.INSTANCE.getCurrentTrack();
        if (cursong == -1) return;
        if (Controller.INSTANCE.getCurrentPlaylist() == null) return;
        String pname = Controller.INSTANCE.getCurrentPlaylist().getName();

        // Different attitudes with different cards
        switch (CURRENT_CARD) {
            case CARD_SEARCH:
                // Confirm that the music playing comes from the same search
                if (searchModel.getCurrentPlaylist().getName().equals(pname)) {
                    searchTable.getSelectionModel().setSelectionInterval(cursong, cursong);
                    searchTable.scrollRectToVisible(searchTable.getCellRect(cursong, 0, true));
                }
                break;
            case CARD_RECENT:
                // Confirm that the music playing comes from the recent playlist
                if (recentModel.getCurrentPlaylist().getName().equals(pname)) {
                    recentTable.getSelectionModel().setSelectionInterval(cursong, cursong);
                    recentTable.scrollRectToVisible(recentTable.getCellRect(cursong, 0, true));
                }
                break;
            case CARD_PLAYLISTS:
                // Confirm that the music playing comes from the selected playlist
                if (selectedModel.getCurrentPlaylist().getName().equals(pname)) {
                    selectedTable.getSelectionModel().setSelectionInterval(cursong, cursong);
                    selectedTable.scrollRectToVisible(selectedTable.getCellRect(cursong, 0, true));
                }
        }
    }


    // Utility functions

    private void setComponentVisible(boolean visible, Component component) {
        if (component instanceof Container)
            for (Component c : ((Container) component).getComponents())
                setComponentVisibleRecursive(visible, c);
        component.setVisible(visible);
        revalidate();
        repaint();
    }

    private void setComponentVisibleRecursive(boolean visible, Component component) {
        if (component instanceof Container)
            for (Component c : ((Container) component).getComponents())
                setComponentVisibleRecursive(visible, c);
        component.setVisible(visible);
    }


    private void createUIComponents() {
        luzPanel = new JPanel();
        luz = new Luz();
        luzPanel.add(luz);

        menuBar = new JMenuBar();
        menu = new JMenu("Opciones");
        menuItem = new JMenuItem("Cambiar directorio raíz");

        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(menuItem);

        playButton = new JButton();
        playButton.setIcon(ICON_PLAY);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        userPanel = new JPanel();
        userPanel.setLayout(new GridLayoutManager(1, 7, new Insets(5, 5, 5, 5), -1, -1));
        mainPanel.add(userPanel, BorderLayout.NORTH);
        logoutButton = new JButton();
        logoutButton.setText("Cerrar sesión");
        userPanel.add(logoutButton, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upgradeAccButton = new JButton();
        upgradeAccButton.setText("Mejora tu cuenta");
        userPanel.add(upgradeAccButton, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        welcomeLabel = new JLabel();
        welcomeLabel.setText("");
        userPanel.add(welcomeLabel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        userPanel.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        userPanel.add(luzPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        luzLabel = new JLabel();
        luzLabel.setText("Descargar canciones");
        userPanel.add(luzLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        starLabel = new JLabel();
        starLabel.setIcon(new ImageIcon(getClass().getResource("/Pictures/star.png")));
        starLabel.setText("");
        userPanel.add(starLabel, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(buttonsPanel, BorderLayout.WEST);
        searchButton = new JButton();
        searchButton.setHorizontalAlignment(2);
        searchButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonBuscar.png")));
        searchButton.setText("Explorar");
        buttonsPanel.add(searchButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(151, 46), null, 0, false));
        newPlaylistButton = new JButton();
        newPlaylistButton.setHorizontalAlignment(2);
        newPlaylistButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonNewPlaylists.png")));
        newPlaylistButton.setText("Editor de listas");
        buttonsPanel.add(newPlaylistButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(151, 46), null, 0, false));
        recentButton = new JButton();
        recentButton.setHorizontalAlignment(2);
        recentButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonRecientes.png")));
        recentButton.setText("Recientes");
        buttonsPanel.add(recentButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(151, 46), null, 0, false));
        myPlaylistsButton = new JButton();
        myPlaylistsButton.setHorizontalAlignment(2);
        myPlaylistsButton.setHorizontalTextPosition(4);
        myPlaylistsButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonPlaylists.png")));
        myPlaylistsButton.setText("Mis listas");
        buttonsPanel.add(myPlaylistsButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(151, 46), null, 0, false));
        playlistScrollPane = new JScrollPane();
        buttonsPanel.add(playlistScrollPane, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(151, 128), null, 0, false));
        playlistList = new JList();
        playlistScrollPane.setViewportView(playlistList);
        top10Button = new JButton();
        top10Button.setHorizontalAlignment(2);
        top10Button.setIcon(new ImageIcon(getClass().getResource("/Pictures/Boton10.png")));
        top10Button.setText("Top 10");
        buttonsPanel.add(top10Button, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(151, 46), null, 0, false));
        makePDFButton = new JButton();
        makePDFButton.setHorizontalAlignment(2);
        makePDFButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonPDF.png")));
        makePDFButton.setText("Generar PDF");
        buttonsPanel.add(makePDFButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(151, 30), null, 0, false));
        mainCardPanel = new JPanel();
        mainCardPanel.setLayout(new CardLayout(0, 0));
        mainPanel.add(mainCardPanel, BorderLayout.CENTER);
        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout(0, 0));
        mainCardPanel.add(searchPanel, "searchPanel");
        searchFiltersPanel = new JPanel();
        searchFiltersPanel.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        searchPanel.add(searchFiltersPanel, BorderLayout.NORTH);
        searchGenreComboBox = new JComboBox();
        searchFiltersPanel.add(searchGenreComboBox, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchInterpreteTextField = new JTextField();
        searchInterpreteTextField.setText("Intérprete");
        searchFiltersPanel.add(searchInterpreteTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButtonPanel = new JPanel();
        searchButtonPanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        searchFiltersPanel.add(searchButtonPanel, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        searchSearchButton = new JButton();
        searchSearchButton.setText("Buscar");
        searchButtonPanel.add(searchSearchButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchCancelButton = new JButton();
        searchCancelButton.setText("Cancelar");
        searchButtonPanel.add(searchCancelButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        searchButtonPanel.add(spacer2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        searchButtonPanel.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        searchTitleTextField = new JTextField();
        searchTitleTextField.setText("Título");
        searchFiltersPanel.add(searchTitleTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchScrollPane = new JScrollPane();
        searchScrollPane.setPreferredSize(new Dimension(453, -1));
        searchScrollPane.setRequestFocusEnabled(false);
        searchPanel.add(searchScrollPane, BorderLayout.CENTER);
        searchTable = new JTable();
        searchTable.setDoubleBuffered(false);
        searchTable.setDragEnabled(false);
        searchScrollPane.setViewportView(searchTable);
        newPlaylistPanel = new JPanel();
        newPlaylistPanel.setLayout(new BorderLayout(0, 0));
        mainCardPanel.add(newPlaylistPanel, "newPlaylistPanel");
        playlistCreatePanel = new JPanel();
        playlistCreatePanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        newPlaylistPanel.add(playlistCreatePanel, BorderLayout.NORTH);
        playlistCreateButton = new JButton();
        playlistCreateButton.setText("Crear/Modificar");
        playlistCreatePanel.add(playlistCreateButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        playlistCreatePanel.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        playlistNameTextField = new JTextField();
        playlistNameTextField.setText("Inserta el nombre de la playlist");
        playlistCreatePanel.add(playlistNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer5 = new Spacer();
        playlistCreatePanel.add(spacer5, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        playlistModifyPanel = new JPanel();
        playlistModifyPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        playlistModifyPanel.setEnabled(true);
        playlistModifyPanel.setVisible(true);
        newPlaylistPanel.add(playlistModifyPanel, BorderLayout.CENTER);
        playlistFiltersPanel = new JPanel();
        playlistFiltersPanel.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        playlistModifyPanel.add(playlistFiltersPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(587, 34), null, 0, false));
        playlistInterpreteTextField = new JTextField();
        playlistInterpreteTextField.setText("Intérprete");
        playlistFiltersPanel.add(playlistInterpreteTextField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        playlistGenreComboBox = new JComboBox();
        playlistFiltersPanel.add(playlistGenreComboBox, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        playlistFiltersPanel.add(spacer6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        playlistSearchButton = new JButton();
        playlistSearchButton.setText("Buscar");
        playlistFiltersPanel.add(playlistSearchButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        playlistFiltersPanel.add(spacer7, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        playlistTitleTextField = new JTextField();
        playlistTitleTextField.setText("Título");
        playlistFiltersPanel.add(playlistTitleTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        playlistEditPanel = new JPanel();
        playlistEditPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        playlistModifyPanel.add(playlistEditPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(587, 432), null, 0, false));
        playlistToAddScrollPane = new JScrollPane();
        playlistEditPanel.add(playlistToAddScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        playlistAddTable = new JTable();
        playlistToAddScrollPane.setViewportView(playlistAddTable);
        playlistAddedScrollPane = new JScrollPane();
        playlistEditPanel.add(playlistAddedScrollPane, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        playlistAddedTable = new JTable();
        playlistAddedScrollPane.setViewportView(playlistAddedTable);
        playlistAddControlsPanel = new JPanel();
        playlistAddControlsPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        playlistEditPanel.add(playlistAddControlsPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playlistAddButton = new JButton();
        playlistAddButton.setText(">>");
        playlistAddControlsPanel.add(playlistAddButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playlistRemoveButton = new JButton();
        playlistRemoveButton.setText("<<");
        playlistAddControlsPanel.add(playlistRemoveButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        playlistAddControlsPanel.add(spacer8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        playlistAddControlsPanel.add(spacer9, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        playlistButtonsPanel = new JPanel();
        playlistButtonsPanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        playlistModifyPanel.add(playlistButtonsPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(587, 24), null, 0, false));
        final Spacer spacer10 = new Spacer();
        playlistButtonsPanel.add(spacer10, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        playlistCancelButton = new JButton();
        playlistCancelButton.setText("Cancelar");
        playlistButtonsPanel.add(playlistCancelButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playlistAcceptButton = new JButton();
        playlistAcceptButton.setText("Aceptar");
        playlistButtonsPanel.add(playlistAcceptButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        playlistButtonsPanel.add(spacer11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        recentPanel = new JPanel();
        recentPanel.setLayout(new BorderLayout(0, 0));
        mainCardPanel.add(recentPanel, "recentPanel");
        recentScrollPane = new JScrollPane();
        recentPanel.add(recentScrollPane, BorderLayout.CENTER);
        recentTable = new JTable();
        recentScrollPane.setViewportView(recentTable);
        selectedPlaylistsPanel = new JPanel();
        selectedPlaylistsPanel.setLayout(new BorderLayout(0, 0));
        mainCardPanel.add(selectedPlaylistsPanel, "selectedPlaylistPanel");
        selectedScrollPane = new JScrollPane();
        selectedPlaylistsPanel.add(selectedScrollPane, BorderLayout.CENTER);
        selectedTable = new JTable();
        selectedTable.setVisible(true);
        selectedScrollPane.setViewportView(selectedTable);
        musicPanel = new JPanel();
        musicPanel.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(musicPanel, BorderLayout.SOUTH);
        backButton = new JButton();
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setHorizontalAlignment(2);
        backButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonAnterior.png")));
        backButton.setIconTextGap(0);
        backButton.setOpaque(true);
        backButton.setSelected(false);
        backButton.setText("");
        backButton.setVisible(true);
        musicPanel.add(backButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setHideActionText(true);
        playButton.setIconTextGap(0);
        playButton.setText("");
        musicPanel.add(playButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nextButton = new JButton();
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setFocusPainted(false);
        nextButton.setHorizontalAlignment(4);
        nextButton.setHorizontalTextPosition(11);
        nextButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonSiguiente.png")));
        nextButton.setIconTextGap(0);
        nextButton.setText("");
        musicPanel.add(nextButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        musicPanel.add(spacer12, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        musicPanel.add(spacer13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
