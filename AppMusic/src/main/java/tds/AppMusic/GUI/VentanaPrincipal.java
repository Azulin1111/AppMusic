package tds.AppMusic.GUI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal {
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
    private JComboBox searchGenreComboBox;
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
    private JComboBox playlistGenreComboBox;
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
    private JList playlistList;
    private JPanel selectedControlsPanel;
    private JButton selectedBackButton;
    private JButton selectedPlayButton;
    private JButton selectedNextButton;
    private JProgressBar selectedSongProgressBar;
    private JTable selectedTable;
    private JScrollPane selectedScrollPane;

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
        searchButton.setText("Button");
        buttonsPanel.add(searchButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newPlaylistButton = new JButton();
        newPlaylistButton.setText("Button");
        buttonsPanel.add(newPlaylistButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recentButton = new JButton();
        recentButton.setText("Button");
        buttonsPanel.add(recentButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        myPlaylistsButton = new JButton();
        myPlaylistsButton.setText("Button");
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
        searchSearchButton.setText("Button");
        searchButtonPanel.add(searchSearchButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchCancelButton = new JButton();
        searchCancelButton.setText("Button");
        searchButtonPanel.add(searchCancelButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        searchButtonPanel.add(spacer2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        searchButtonPanel.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        searchControlsPanel = new JPanel();
        searchControlsPanel.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        searchPanel.add(searchControlsPanel, BorderLayout.SOUTH);
        searchBackButton = new JButton();
        searchBackButton.setText("Button");
        searchControlsPanel.add(searchBackButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchPlayButton = new JButton();
        searchPlayButton.setText("Button");
        searchControlsPanel.add(searchPlayButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchNextButton = new JButton();
        searchNextButton.setText("Button");
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
        recentBackButton.setText("Button");
        recentControlsPanel.add(recentBackButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recentPlayButton = new JButton();
        recentPlayButton.setText("Button");
        recentControlsPanel.add(recentPlayButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recentNextButton = new JButton();
        recentNextButton.setText("Button");
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
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
