package tds.AppMusic.GUI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;

public class SignupWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField surnameTextField;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel birthdayLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JLabel keyLabel;
    private JTextField usernameTextField;
    private JPasswordField keyPasswordField;
    private JPasswordField keyConfirmPasswordField;
    private JLabel keyConfirmLabel;
    private JPanel inputPanel;
    private JPanel boxesPanel;
    private JPanel labelsPanel;
    private JPanel buttonsPanel;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel errorsPanel;
    private JLabel missingFieldsLabel;
    private JLabel mismatchingPasswordsLabel;
    private JLabel duplicateFoundLabel;
    private JDateChooser dateChooser;
    private JPanel boxesSubPanel;

    private void createUIComponents() {
        dateChooser = new JDateChooser();
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
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayoutManager(1, 4, new Insets(20, 20, 20, 20), -1, -1));
        mainPanel.add(inputPanel, new GridConstraints(0, 1, 6, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        boxesPanel = new JPanel();
        boxesPanel.setLayout(new GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        inputPanel.add(boxesPanel, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nameTextField = new JTextField();
        boxesPanel.add(nameTextField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(300, -1), null, 0, false));
        surnameTextField = new JTextField();
        boxesPanel.add(surnameTextField, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(300, -1), null, 0, false));
        boxesSubPanel = new JPanel();
        boxesSubPanel.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        boxesPanel.add(boxesSubPanel, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        emailTextField = new JTextField();
        boxesSubPanel.add(emailTextField, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        usernameTextField = new JTextField();
        boxesSubPanel.add(usernameTextField, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        keyPasswordField = new JPasswordField();
        boxesSubPanel.add(keyPasswordField, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        keyConfirmLabel = new JLabel();
        keyConfirmLabel.setText("Repetir Clave :");
        boxesSubPanel.add(keyConfirmLabel, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        keyConfirmPasswordField = new JPasswordField();
        boxesSubPanel.add(keyConfirmPasswordField, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        boxesPanel.add(labelsPanel, new GridConstraints(0, 0, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setText("Nombre :");
        labelsPanel.add(nameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        surnameLabel = new JLabel();
        surnameLabel.setText("Apellidos :");
        labelsPanel.add(surnameLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        birthdayLabel = new JLabel();
        birthdayLabel.setText("Fecha Nacimiento :");
        labelsPanel.add(birthdayLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailLabel = new JLabel();
        emailLabel.setText("Email :");
        labelsPanel.add(emailLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameLabel = new JLabel();
        usernameLabel.setText("Usuario :");
        labelsPanel.add(usernameLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        keyLabel = new JLabel();
        keyLabel.setText("Clave :");
        labelsPanel.add(keyLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        boxesPanel.add(buttonsPanel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        registerButton = new JButton();
        registerButton.setText("Registrar");
        buttonsPanel.add(registerButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancelar");
        buttonsPanel.add(cancelButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        buttonsPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        boxesPanel.add(spacer2, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        final Spacer spacer3 = new Spacer();
        boxesPanel.add(spacer3, new GridConstraints(5, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        errorsPanel = new JPanel();
        errorsPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        boxesPanel.add(errorsPanel, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        missingFieldsLabel = new JLabel();
        missingFieldsLabel.setText("Label");
        errorsPanel.add(missingFieldsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mismatchingPasswordsLabel = new JLabel();
        mismatchingPasswordsLabel.setText("Label");
        errorsPanel.add(mismatchingPasswordsLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        duplicateFoundLabel = new JLabel();
        duplicateFoundLabel.setText("Label");
        errorsPanel.add(duplicateFoundLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxesPanel.add(dateChooser, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        mainPanel.add(spacer5, new GridConstraints(0, 2, 7, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        mainPanel.add(spacer6, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
