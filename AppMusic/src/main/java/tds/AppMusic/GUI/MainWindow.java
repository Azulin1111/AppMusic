package tds.AppMusic.GUI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import tds.AppMusic.app.Controller;
import tds.AppMusic.model.music.Genre;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends AppWindow {
    private static final String CARD_SEARCH = "searchPanel";
    private static final String CARD_NEWPL = "newPlaylistPanel";
    private static final String CARD_RECENT = "recentPanel";
    private static final String CARD_PLAYLISTS = "selectedPlaylistsPanel";

    private static final String WELCOME_TEXT = "Hola, ";

    private static final Icon ICON_PLAY = new ImageIcon(MainWindow.class.getResource("/Pictures/BotonPlay.png"));
    private static final Icon ICON_PAUSE = new ImageIcon(MainWindow.class.getResource("/Pictures/BotonPausa.png"));

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
    private JComboBox<Genre> searchGenreComboBox;
    private JTextField searchTitleTextField;
    private JTextField searchInterpreteTextField;
    private JPanel searchFiltersPanel;
    private JPanel searchControlsPanel;
    private JButton searchBackButton;
    private JButton searchPlayButton;
    private JButton searchNextButton;
    private JProgressBar searchSongProgressBar;
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
    private JComboBox<Genre> playlistGenreComboBox;
    private JButton playlistAddButton;
    private JButton playlistRemoveButton;
    private JTable playlistAddTable;
    private JTable playlistAddedTable;
    private JPanel playlistEditPanel;
    private JScrollPane playlistToAddScrollPane;
    private JScrollPane playlistAddedScrollPane;
    private JPanel playlistAddControlsPanel;
    private JPanel recentControlsPanel;
    private JButton recentBackButton;
    private JButton recentPlayButton;
    private JButton recentNextButton;
    private JProgressBar recentSongProgressBar;
    private JScrollPane recentScrollPane;
    private JTable recentTable;
    private JList<Playlist> playlistList;
    private JPanel selectedControlsPanel;
    private JButton selectedBackButton;
    private JButton selectedPlayButton;
    private JButton selectedNextButton;
    private JProgressBar selectedSongProgressBar;
    private JTable selectedTable;
    private JScrollPane selectedScrollPane;
    private JScrollPane playlistScrollPane;
    private JButton playlistCancelButton;
    private JButton playlistAcceptButton;
    private JButton playlistSearchButton;

    private final SongTableModel searchModel = new SongTableModel();
    private final SongTableModel playlistAddModel = new SongTableModel();
    private final SongTableModel playlistAddedModel = new SongTableModel();
    private final SongTableModel recentModel = new SongTableModel();
    private final SongTableModel selectedModel = new SongTableModel();

    private final PlaylistListModel playlistModel = new PlaylistListModel();

    public MainWindow(String username) {
        super();
        setContentPane($$$getRootComponent$$$());
        CardLayout cl = (CardLayout) mainCardPanel.getLayout();

        // Set user welcome label
        welcomeLabel.setText(WELCOME_TEXT + username);

        // Playlist list model
        playlistList.setModel(playlistModel);
        playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Main button listeners
        searchButton.addActionListener(e -> {
            switchCards();
            cl.show(mainCardPanel, CARD_SEARCH);
        });
        newPlaylistButton.addActionListener(e -> {
            switchCards();
            cl.show(mainCardPanel, CARD_NEWPL);
        });
        recentButton.addActionListener(e -> {
            switchCards();

            // Additional work: update recent playlist
            recentModel.replaceWith(Controller.INSTANCE.getSongsRecientes());

            cl.show(mainCardPanel, CARD_RECENT);
        });
        myPlaylistsButton.addActionListener(e -> {
            switchCards();

            // Additional work: update playlist list
            playlistModel.replaceWith(Controller.INSTANCE.getPlaylists(username));

            // Additional work: select the first one and display contents
            playlistList.getSelectionModel().setSelectionInterval(0, 0);
            selectedModel.replaceWith(playlistList.getSelectedValue().getSongs());

            cl.show(mainCardPanel, CARD_PLAYLISTS);
        });

        // Specific card listeners
        searchSetup();
        newPlaylistSetup();
        recentSetup();
        myPlaylistsSetup();

        // Upgrade account listener
        upgradeAccButton.addActionListener(e -> {
            // TODO misterio
        });

        // Sign out listener
        logoutButton.addActionListener(e -> {
            dispose();

            LoginWindow l2 = new LoginWindow();
            l2.pack();
            l2.setLocationRelativeTo(null);
            l2.setVisible(true);
        });
    }

    private void switchCards() {
        // Default: stop all music being played
        Controller.INSTANCE.switchTrack(null);

        // Default: return all play buttons back to play state
        searchPlayButton.setIcon(ICON_PLAY);
        recentPlayButton.setIcon(ICON_PLAY);
        selectedPlayButton.setIcon(ICON_PLAY);
    }


    private void searchSetup() {
        // Hide search table on first go
        searchScrollPane.setVisible(false);

        // Set search table model
        searchTable.setModel(searchModel);
        searchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Search listener
        searchSearchButton.addActionListener(e -> {
            // TODO SwingWorker?
            searchSearchButton.setEnabled(false);

            // Stop music
            Controller.INSTANCE.switchTrack(null);
            searchPlayButton.setIcon(ICON_PLAY);

            // Search songs
            String title = searchTitleTextField.getText().trim();
            String interprete = searchInterpreteTextField.getText().trim();
            Genre genre = (Genre) searchGenreComboBox.getSelectedItem();
            java.util.List<Song> songs = Controller.INSTANCE.getSongsFiltered(title, interprete, genre);

            // Display search results
            searchModel.replaceWith(songs);
            searchScrollPane.setVisible(true);
            searchSearchButton.setEnabled(true);
        });

        // Cancel listener
        searchCancelButton.addActionListener(e -> {
            searchScrollPane.setVisible(false);
        });

        // Music selection listener
        searchTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // If double clicked, play
                if (e.getClickCount() == 2) {
                    searchPlayButton.setIcon(ICON_PAUSE);
                    Controller.INSTANCE.switchTrack(searchModel.getSongAt(searchTable.getSelectedRow()));
                }
            }
        });

        // Music button listeners
        searchBackButton.addActionListener(e -> songStep(-1, searchTable, searchModel));
        searchPlayButton.addActionListener(e -> {
            if (searchPlayButton.getIcon().equals(ICON_PLAY)) {
                searchPlayButton.setIcon(ICON_PAUSE);
                Controller.INSTANCE.resumeTrack();
            } else if (searchPlayButton.getIcon().equals(ICON_PAUSE)) {
                searchPlayButton.setIcon(ICON_PLAY);
                Controller.INSTANCE.pauseTrack();
            }
        });
        searchNextButton.addActionListener(e -> songStep(1, searchTable, searchModel));
    }

    private void newPlaylistSetup() {
        // Hide playlist editing on first go
        playlistEditPanel.setVisible(false);

        // Table models
        playlistAddTable.setModel(playlistAddModel);
        playlistAddTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistAddedTable.setModel(playlistAddedModel);
        playlistAddedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Creation listener
        playlistCreateButton.addActionListener(e -> {
            // Check for duplicates
            String name = playlistNameTextField.getText().trim();
            boolean success = Controller.INSTANCE.playlistExists(name);

            // Exists?
            playlistAddModel.clear();
            if (success) playlistAddedModel.clear();
            else {
                say("Error al crear la playlist", "La playlist indicada ya existe.");
                playlistAddedModel.replaceWith(Controller.INSTANCE.getSongsPlaylist(name));
            }
            playlistEditPanel.setVisible(true);
        });

        // Search listener
        playlistSearchButton.addActionListener(e -> {
            // TODO SwingWorker?
            playlistSearchButton.setEnabled(false);

            // Search songs
            String title = playlistTitleTextField.getText().trim();
            String interprete = playlistInterpreteTextField.getText().trim();
            Genre genre = (Genre) playlistGenreComboBox.getSelectedItem();
            java.util.List<Song> songs = Controller.INSTANCE.getSongsFiltered(title, interprete, genre);

            // Display search results
            playlistAddModel.replaceWith(songs);
            playlistSearchButton.setEnabled(true);
        });

        // Add/remove listeners
        playlistAddButton.addActionListener(e -> {
            if (playlistAddTable.getSelectedRow() == -1) return;
            playlistAddedModel.add(playlistAddModel.getSongAt(playlistAddTable.getSelectedRow()));
        });
        playlistRemoveButton.addActionListener(e -> {
            if (playlistAddedTable.getSelectedRow() == -1) return;
            playlistAddedModel.remove(playlistAddedModel.getSongAt(playlistAddedTable.getSelectedRow()));
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
                if (e.getClickCount() == 2) {
                    recentPlayButton.setIcon(ICON_PAUSE);
                    Controller.INSTANCE.switchTrack(recentModel.getSongAt(recentTable.getSelectedRow()));
                }
            }
        });

        // Music button listeners
        recentBackButton.addActionListener(e -> songStep(-1, recentTable, recentModel));
        recentPlayButton.addActionListener(e -> {
            if (recentPlayButton.getIcon().equals(ICON_PLAY)) {
                recentPlayButton.setIcon(ICON_PAUSE);
                Controller.INSTANCE.resumeTrack();
            } else if (recentPlayButton.getIcon().equals(ICON_PAUSE)) {
                recentPlayButton.setIcon(ICON_PLAY);
                Controller.INSTANCE.pauseTrack();
            }
        });
        recentNextButton.addActionListener(e -> songStep(1, recentTable, recentModel));
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
                if (e.getClickCount() == 2) {
                    selectedPlayButton.setIcon(ICON_PAUSE);
                    Controller.INSTANCE.switchTrack(selectedModel.getSongAt(selectedTable.getSelectedRow()));
                }
            }
        });

        // Music button listeners
        selectedBackButton.addActionListener(e -> songStep(-1, selectedTable, selectedModel));
        selectedPlayButton.addActionListener(e -> {
            if (selectedPlayButton.getIcon().equals(ICON_PLAY)) {
                selectedPlayButton.setIcon(ICON_PAUSE);
                Controller.INSTANCE.resumeTrack();
            } else if (selectedPlayButton.getIcon().equals(ICON_PAUSE)) {
                selectedPlayButton.setIcon(ICON_PLAY);
                Controller.INSTANCE.pauseTrack();
            }
        });
        selectedNextButton.addActionListener(e -> songStep(1, selectedTable, selectedModel));
    }


    private void songStep(int step, JTable table, SongTableModel model) {
        // Do nothing if nothing was selected
        int current = table.getSelectedRow();
        if (current == -1) return;

        // Change selection to the next one
        current = (current + step) % model.getRowCount();
        table.getSelectionModel().setSelectionInterval(current, current);

        // Update visibly
        table.scrollRectToVisible(table.getCellRect(current, 0, true));

        // Switch track
        Controller.INSTANCE.switchTrack(model.getSongAt(current));
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        userPanel = new JPanel();
        userPanel.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        mainPanel.add(userPanel, BorderLayout.NORTH);
        logoutButton = new JButton();
        logoutButton.setText("Cerrar sesión");
        userPanel.add(logoutButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upgradeAccButton = new JButton();
        upgradeAccButton.setText("Mejora tu cuenta");
        userPanel.add(upgradeAccButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        welcomeLabel = new JLabel();
        welcomeLabel.setText("Label");
        userPanel.add(welcomeLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        userPanel.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(buttonsPanel, BorderLayout.WEST);
        searchButton = new JButton();
        searchButton.setHorizontalAlignment(2);
        searchButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonBuscar.png")));
        searchButton.setText("Explorar");
        buttonsPanel.add(searchButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newPlaylistButton = new JButton();
        newPlaylistButton.setHorizontalAlignment(2);
        newPlaylistButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonNewPlaylists.png")));
        newPlaylistButton.setText("Nueva lista");
        buttonsPanel.add(newPlaylistButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recentButton = new JButton();
        recentButton.setHorizontalAlignment(2);
        recentButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonRecientes.png")));
        recentButton.setText("Recientes");
        buttonsPanel.add(recentButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        myPlaylistsButton = new JButton();
        myPlaylistsButton.setHorizontalAlignment(2);
        myPlaylistsButton.setHorizontalTextPosition(4);
        myPlaylistsButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonPlaylists.png")));
        myPlaylistsButton.setText("Mis listas");
        buttonsPanel.add(myPlaylistsButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playlistList = new JList();
        buttonsPanel.add(playlistList, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        mainCardPanel = new JPanel();
        mainCardPanel.setLayout(new CardLayout(0, 0));
        mainPanel.add(mainCardPanel, BorderLayout.CENTER);
        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout(0, 0));
        mainCardPanel.add(searchPanel, "searchPanel");
        searchFiltersPanel = new JPanel();
        searchFiltersPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        searchPanel.add(searchFiltersPanel, BorderLayout.NORTH);
        searchGenreComboBox = new JComboBox();
        searchFiltersPanel.add(searchGenreComboBox, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchTitleTextField = new JTextField();
        searchFiltersPanel.add(searchTitleTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchInterpreteTextField = new JTextField();
        searchFiltersPanel.add(searchInterpreteTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButtonPanel = new JPanel();
        searchButtonPanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        searchFiltersPanel.add(searchButtonPanel, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        searchControlsPanel = new JPanel();
        searchControlsPanel.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        searchPanel.add(searchControlsPanel, BorderLayout.SOUTH);
        searchBackButton = new JButton();
        searchBackButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonAnterior.png")));
        searchBackButton.setText("");
        searchControlsPanel.add(searchBackButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchPlayButton = new JButton();
        searchPlayButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonPlay.png")));
        searchPlayButton.setText("");
        searchControlsPanel.add(searchPlayButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchNextButton = new JButton();
        searchNextButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonSiguiente.png")));
        searchNextButton.setText("");
        searchControlsPanel.add(searchNextButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchSongProgressBar = new JProgressBar();
        searchControlsPanel.add(searchSongProgressBar, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        searchControlsPanel.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        searchControlsPanel.add(spacer5, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        searchScrollPane = new JScrollPane();
        searchPanel.add(searchScrollPane, BorderLayout.CENTER);
        searchTable = new JTable();
        searchScrollPane.setViewportView(searchTable);
        newPlaylistPanel = new JPanel();
        newPlaylistPanel.setLayout(new BorderLayout(0, 0));
        mainCardPanel.add(newPlaylistPanel, "newPlaylistPanel");
        playlistCreatePanel = new JPanel();
        playlistCreatePanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        newPlaylistPanel.add(playlistCreatePanel, BorderLayout.NORTH);
        playlistCreateButton = new JButton();
        playlistCreateButton.setText("Crear");
        playlistCreatePanel.add(playlistCreateButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        playlistCreatePanel.add(spacer6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        playlistNameTextField = new JTextField();
        playlistCreatePanel.add(playlistNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer7 = new Spacer();
        playlistCreatePanel.add(spacer7, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        playlistModifyPanel = new JPanel();
        playlistModifyPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        playlistModifyPanel.setVisible(true);
        newPlaylistPanel.add(playlistModifyPanel, BorderLayout.CENTER);
        playlistFiltersPanel = new JPanel();
        playlistFiltersPanel.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        playlistModifyPanel.add(playlistFiltersPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playlistInterpreteTextField = new JTextField();
        playlistFiltersPanel.add(playlistInterpreteTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        playlistTitleTextField = new JTextField();
        playlistFiltersPanel.add(playlistTitleTextField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        playlistGenreComboBox = new JComboBox();
        playlistFiltersPanel.add(playlistGenreComboBox, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        playlistFiltersPanel.add(spacer8, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        playlistFiltersPanel.add(spacer9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        playlistEditPanel = new JPanel();
        playlistEditPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        playlistModifyPanel.add(playlistEditPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        final Spacer spacer10 = new Spacer();
        playlistAddControlsPanel.add(spacer10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        playlistAddControlsPanel.add(spacer11, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        recentPanel = new JPanel();
        recentPanel.setLayout(new BorderLayout(0, 0));
        mainCardPanel.add(recentPanel, "recentPanel");
        recentControlsPanel = new JPanel();
        recentControlsPanel.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        recentPanel.add(recentControlsPanel, BorderLayout.SOUTH);
        recentBackButton = new JButton();
        recentBackButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonAnterior.png")));
        recentBackButton.setText("");
        recentControlsPanel.add(recentBackButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recentPlayButton = new JButton();
        recentPlayButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonPlay.png")));
        recentPlayButton.setText("");
        recentControlsPanel.add(recentPlayButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recentNextButton = new JButton();
        recentNextButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonSiguiente.png")));
        recentNextButton.setText("");
        recentControlsPanel.add(recentNextButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recentSongProgressBar = new JProgressBar();
        recentControlsPanel.add(recentSongProgressBar, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        recentControlsPanel.add(spacer12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        recentControlsPanel.add(spacer13, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        recentScrollPane = new JScrollPane();
        recentPanel.add(recentScrollPane, BorderLayout.CENTER);
        recentTable = new JTable();
        recentScrollPane.setViewportView(recentTable);
        selectedPlaylistsPanel = new JPanel();
        selectedPlaylistsPanel.setLayout(new BorderLayout(0, 0));
        mainCardPanel.add(selectedPlaylistsPanel, "selectedPlaylistPanel");
        selectedControlsPanel = new JPanel();
        selectedControlsPanel.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        selectedPlaylistsPanel.add(selectedControlsPanel, BorderLayout.SOUTH);
        selectedBackButton = new JButton();
        selectedBackButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonAnterior.png")));
        selectedBackButton.setText("");
        selectedControlsPanel.add(selectedBackButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectedPlayButton = new JButton();
        selectedPlayButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonPlay.png")));
        selectedPlayButton.setText("");
        selectedControlsPanel.add(selectedPlayButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectedNextButton = new JButton();
        selectedNextButton.setIcon(new ImageIcon(getClass().getResource("/Pictures/BotonSiguiente.png")));
        selectedNextButton.setText("");
        selectedControlsPanel.add(selectedNextButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectedSongProgressBar = new JProgressBar();
        selectedControlsPanel.add(selectedSongProgressBar, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        selectedControlsPanel.add(spacer14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        selectedControlsPanel.add(spacer15, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        selectedScrollPane = new JScrollPane();
        selectedPlaylistsPanel.add(selectedScrollPane, BorderLayout.CENTER);
        selectedTable = new JTable();
        selectedTable.setVisible(true);
        selectedScrollPane.setViewportView(selectedTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
