package tds.AppMusic.GUI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class LoginView {
    private JPanel panel1;
    private JPanel northPanel;
    private JLabel title;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel southPanel;
    private JPanel centerPanel;
    private JLabel userLabel;
    private JLabel passwdLabel;
    private JButton aceptarButton;
    private JButton cancelarButton;

    public LoginView() {
        aceptarButton.addActionListener(e -> {

        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(10, 10));
        panel1.setEnabled(true);
        panel1.setFocusCycleRoot(false);
        panel1.setFocusable(true);
        Font panel1Font = this.$$$getFont$$$(null, -1, 36, panel1.getFont());
        if (panel1Font != null) panel1.setFont(panel1Font);
        panel1.setPreferredSize(new Dimension(100, 100));
        northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout(0, 0));
        panel1.add(northPanel, BorderLayout.NORTH);
        title = new JLabel();
        title.setDoubleBuffered(false);
        title.setEnabled(true);
        Font titleFont = this.$$$getFont$$$(null, -1, 36, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setHorizontalAlignment(0);
        title.setHorizontalTextPosition(0);
        title.setIconTextGap(10);
        title.setOpaque(false);
        title.setText("AppMusic");
        northPanel.add(title, BorderLayout.SOUTH);
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(centerPanel, BorderLayout.CENTER);
        userLabel = new JLabel();
        userLabel.setText("Usuario:");
        centerPanel.add(userLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        centerPanel.add(spacer1, new GridConstraints(0, 3, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        centerPanel.add(spacer2, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        textField1 = new JTextField();
        centerPanel.add(textField1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField2 = new JTextField();
        centerPanel.add(textField2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwdLabel = new JLabel();
        passwdLabel.setText("Clave:");
        centerPanel.add(passwdLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout(0, 0));
        panel1.add(southPanel, BorderLayout.SOUTH);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        southPanel.add(panel2, BorderLayout.NORTH);
        aceptarButton = new JButton();
        aceptarButton.setText("Aceptar");
        panel2.add(aceptarButton);
        cancelarButton = new JButton();
        cancelarButton.setText("Cancelar");
        panel2.add(cancelarButton);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        southPanel.add(panel3, BorderLayout.SOUTH);
        final JLabel label1 = new JLabel();
        label1.setEnabled(true);
        label1.setFocusable(true);
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Si no estás registrado, regístrate");
        panel3.add(label1, BorderLayout.CENTER);
        final Spacer spacer3 = new Spacer();
        panel3.add(spacer3, BorderLayout.SOUTH);
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
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}