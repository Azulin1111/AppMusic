package tds.AppMusic.GUI;

import tds.AppMusic.internal.Controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PrincipalView extends JFrame {
    private Controller controller;

    private JPanel mainPanel;
    private JPanel northPanel;
    private JButton buttonPremium;
    private JButton buttonLogout;
    private JLabel textName;
    private JPanel buttonPanel;
    private JPanel centralPanel;
    private JButton newListButton;
    private JButton recentButton;
    private JButton myPlaylistsButton;
    private JButton explorerButton;
    private JPanel explorerWindow;
    private JPanel newPlaylistWindow;
    private JPanel startWindow;
    private JPanel recentWindow;
    private JPanel myPlaylistWindow;
    private JLabel labelWelcome;
    private JTextField interpreterField;
    private JTextField titleField;
    private JTextField genreField;
    private JLabel interpreterLabel;
    private JLabel titleLabel;
    private JLabel GenreLabel;
    private JPanel explorerNorthPanel;
    private JPanel explorerCenterPanel;
    private JButton searchButton;
    private JButton cancelButton;
    private JPanel explorerOriginalListPanel;
    private JPanel explorerNoListPanel;
    private JPanel explorerListPanel;
    private JList explorerList;


    public PrincipalView() {
        super();
        $$$setupUI$$$();
        configureView();

        explorerButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (centralPanel.getLayout());
            cl.show(centralPanel, "explorer");
        });
        newListButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (centralPanel.getLayout());
            cl.show(centralPanel, "newPlaylist");
        });
        recentButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (centralPanel.getLayout());
            cl.show(centralPanel, "recent");
        });
        myPlaylistsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (centralPanel.getLayout());
            cl.show(centralPanel, "myPlaylists");
        });
        searchButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (explorerOriginalListPanel.getLayout());
            cl.show(explorerOriginalListPanel, "table");
        });
        cancelButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (explorerOriginalListPanel.getLayout());
            cl.show(explorerOriginalListPanel, "noTable");
        });
    }

    private void configureView() {
        controller = new Controller();


        this.setTitle("PrincipalView");
        this.setSize(700, 750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(mainPanel);
        textName.setText("Hello, " + controller.getCurrentUser()); //TODO esto no sé si irá aquí (?)
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
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        northPanel.setBackground(new Color(-8747097));
        northPanel.setEnabled(false);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, -1, northPanel.getFont()), new Color(-9336645)));
        textName = new JLabel();
        textName.setText("");
        northPanel.add(textName);
        buttonPremium = new JButton();
        buttonPremium.setText("Premium");
        northPanel.add(buttonPremium);
        buttonLogout = new JButton();
        buttonLogout.setText("Logout");
        northPanel.add(buttonLogout);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(100, 0, 100, 0), -1, -1, false, true));
        buttonPanel.setBackground(new Color(-13223359));
        buttonPanel.setEnabled(true);
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        newListButton = new JButton();
        newListButton.setText("New Playlist");
        buttonPanel.add(newListButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(140, 50), null, 0, false));
        recentButton = new JButton();
        recentButton.setText("Recent Songs");
        buttonPanel.add(recentButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(140, 50), null, 0, false));
        myPlaylistsButton = new JButton();
        myPlaylistsButton.setText("My Playlists");
        buttonPanel.add(myPlaylistsButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(140, 50), null, 0, false));
        explorerButton = new JButton();
        explorerButton.setText("Explorer");
        buttonPanel.add(explorerButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(140, 50), null, 0, false));
        centralPanel = new JPanel();
        centralPanel.setLayout(new CardLayout(0, 0));
        mainPanel.add(centralPanel, BorderLayout.CENTER);
        startWindow = new JPanel();
        startWindow.setLayout(new BorderLayout(0, 0));
        centralPanel.add(startWindow, "start");
        labelWelcome = new JLabel();
        Font labelWelcomeFont = this.$$$getFont$$$(null, -1, 28, labelWelcome.getFont());
        if (labelWelcomeFont != null) labelWelcome.setFont(labelWelcomeFont);
        labelWelcome.setHorizontalAlignment(0);
        labelWelcome.setHorizontalTextPosition(0);
        labelWelcome.setText("WELCOME TO APPMUSIC");
        startWindow.add(labelWelcome, BorderLayout.CENTER);
        explorerWindow = new JPanel();
        explorerWindow.setLayout(new BorderLayout(0, 0));
        centralPanel.add(explorerWindow, "explorer");
        explorerWindow.setBorder(BorderFactory.createTitledBorder(null, "EXPLORER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, -1, explorerWindow.getFont()), new Color(-16777216)));
        explorerNorthPanel = new JPanel();
        explorerNorthPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        explorerWindow.add(explorerNorthPanel, BorderLayout.NORTH);
        interpreterLabel = new JLabel();
        interpreterLabel.setText("Interpreter");
        explorerNorthPanel.add(interpreterLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleLabel = new JLabel();
        titleLabel.setText("Title");
        explorerNorthPanel.add(titleLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GenreLabel = new JLabel();
        GenreLabel.setText("Genre");
        explorerNorthPanel.add(GenreLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        interpreterField = new JTextField();
        explorerNorthPanel.add(interpreterField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        titleField = new JTextField();
        explorerNorthPanel.add(titleField, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        genreField = new JTextField();
        explorerNorthPanel.add(genreField, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        explorerCenterPanel = new JPanel();
        explorerCenterPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        explorerWindow.add(explorerCenterPanel, BorderLayout.CENTER);
        searchButton = new JButton();
        searchButton.setText("Search");
        explorerCenterPanel.add(searchButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        explorerCenterPanel.add(cancelButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        explorerCenterPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        explorerCenterPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        explorerOriginalListPanel = new JPanel();
        explorerOriginalListPanel.setLayout(new CardLayout(0, 0));
        explorerCenterPanel.add(explorerOriginalListPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        explorerNoListPanel = new JPanel();
        explorerNoListPanel.setLayout(new BorderLayout(0, 0));
        explorerOriginalListPanel.add(explorerNoListPanel, "noTable");
        explorerListPanel = new JPanel();
        explorerListPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        explorerOriginalListPanel.add(explorerListPanel, "table");
        explorerList = new JList();
        explorerListPanel.add(explorerList, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        explorerListPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(100, 10), new Dimension(100, 10), new Dimension(100, 10), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        explorerListPanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        newPlaylistWindow = new JPanel();
        newPlaylistWindow.setLayout(new BorderLayout(0, 0));
        centralPanel.add(newPlaylistWindow, "newPlaylist");
        newPlaylistWindow.setBorder(BorderFactory.createTitledBorder(null, "NEW PLAYLIST", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, -1, newPlaylistWindow.getFont()), new Color(-16777216)));
        recentWindow = new JPanel();
        recentWindow.setLayout(new BorderLayout(0, 0));
        centralPanel.add(recentWindow, "recent");
        recentWindow.setBorder(BorderFactory.createTitledBorder(null, "RECENT SONGS", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, -1, recentWindow.getFont()), new Color(-16777216)));
        myPlaylistWindow = new JPanel();
        myPlaylistWindow.setLayout(new BorderLayout(0, 0));
        centralPanel.add(myPlaylistWindow, "myPlaylists");
        myPlaylistWindow.setBorder(BorderFactory.createTitledBorder(null, "MY PLAYLISTS", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, -1, myPlaylistWindow.getFont()), new Color(-16777216)));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
