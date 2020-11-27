package tds.AppMusic.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Box;

public class PrincipalView {

    private JFrame frame;
    private JButton btnExplorar;
    private JButton btnNuevaLista;
    private JButton btnReciente;
    private JButton btnMisListas;
    private JPanel panelMisListas;
    private JPanel panelExplorar;
    private JPanel panelNuevaLista;
    private JPanel panelReciente;
    private JPanel panelRelleno;
    private JPanel panelCentro;
    private JPanel panelInfo;
    private JTextField Inteprete;
    private JTextField Titulo;
    private JComboBox comboBoxGenero;
    private JPanel panelBuscarCancelar;
    private JPanel panelTabla;
    private JButton btnBuscar;
    private JButton btnCancelar;
    private JScrollBar scrollBar;
    private JTable table;
    private JPanel panel;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private Component horizontalStrutDer;
    private Component horizontalStrutIzq;
    private Component horizontalStrut;
    private Component horizontalStrut_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    pruebaSwing window = new pruebaSwing();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public PrincipalView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        String nombre = new String("Sergio");
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelNorte = new JPanel();
        frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
        panelNorte.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JLabel lblHola = new JLabel("Hola, "+nombre);
        lblHola.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelNorte.add(lblHola);


        JButton bPremium = new JButton("Mejora la cuenta");
        panelNorte.add(bPremium);

        JButton bLogOut = new JButton("logout");
        panelNorte.add(bLogOut);

        JPanel panelIzq = new JPanel();
        frame.getContentPane().add(panelIzq, BorderLayout.WEST);
        panelIzq.setLayout(new BoxLayout(panelIzq, BoxLayout.Y_AXIS));

        panelExplorar = new JPanel();
        panelIzq.add(panelExplorar);
        panelExplorar.setLayout(new GridLayout(0, 1, 0, 0));

        btnExplorar = new JButton("Explorar");
        panelExplorar.add(btnExplorar);
        btnExplorar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnExplorar.setIcon(new ImageIcon(pruebaSwing.class.getResource("/com/sun/java/swing/plaf/windows/icons/Warn.gif")));

        panelNuevaLista = new JPanel();
        panelIzq.add(panelNuevaLista);
        panelNuevaLista.setLayout(new GridLayout(0, 1, 0, 0));

        btnNuevaLista = new JButton("Nueva lista");
        panelNuevaLista.add(btnNuevaLista);
        btnNuevaLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNuevaLista.setIcon(new ImageIcon(pruebaSwing.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));

        panelReciente = new JPanel();
        panelIzq.add(panelReciente);
        panelReciente.setLayout(new GridLayout(0, 1, 0, 0));

        btnReciente = new JButton("Reciente");
        panelReciente.add(btnReciente);
        btnReciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnReciente.setIcon(new ImageIcon(pruebaSwing.class.getResource("/javax/swing/plaf/metal/icons/Question.gif")));

        panelMisListas = new JPanel();
        panelIzq.add(panelMisListas);
        panelMisListas.setLayout(new GridLayout(0, 1, 0, 0));

        btnMisListas = new JButton("Mis listas");
        panelMisListas.add(btnMisListas);
        btnMisListas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMisListas.setIcon(new ImageIcon(pruebaSwing.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));

        panelRelleno = new JPanel();
        panelIzq.add(panelRelleno);

        panelCentro = new JPanel();
        frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

        panelInfo = new JPanel();
        panelCentro.add(panelInfo);
        panelInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        Inteprete = new JTextField();
        Inteprete.setText("Interprete");
        Inteprete.setToolTipText("Interprete");
        panelInfo.add(Inteprete);
        Inteprete.setColumns(10);

        Titulo = new JTextField();
        Titulo.setText("Titulo");
        panelInfo.add(Titulo);
        Titulo.setColumns(10);

        comboBoxGenero = new JComboBox();
        comboBoxGenero.setModel(new DefaultComboBoxModel(new String[] {"Jazz", "Pop", "Rock"}));
        panelInfo.add(comboBoxGenero);

        panelBuscarCancelar = new JPanel();
        panelCentro.add(panelBuscarCancelar);

        btnBuscar = new JButton("Buscar");
        panelBuscarCancelar.add(btnBuscar);

        btnCancelar = new JButton("Cancelar");
        panelBuscarCancelar.add(btnCancelar);

        panelTabla = new JPanel();
        panelCentro.add(panelTabla);
        panelTabla.setLayout(new BorderLayout(0, 0));

        horizontalStrutIzq = Box.createHorizontalStrut(40);
        panelTabla.add(horizontalStrutIzq, BorderLayout.EAST);



        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        {"Ekam es un puto", "Azulin1111"},
                        {"Pika y Raska", "Roza Melano"},
                        {"Hazte con todos", "Has Mayonesa"},
                        {"Imagina ser una ardilla", "16:00"},
                        {"Pablo Verza", "Baloncesto"},
                        {"Ardillas Volando", "Baloncesto"},
                        {"Perdón", "Ha-Ash"},
                        {"La ardilla", "Tortu"},
                        {"Marría", "Gorun"},
                        {"Marría", "Gorun"},
                        {"Marría", "Gorun"},
                        {"Marría", "Gorun"},
                        {"Marría", "Gorun"},
                        {"Cecilia Jimwnez", "20:00"},
                },
                new String[] {
                        "Titulo", "Interprete"
                }
        ));

        JScrollPane scrollPane = new JScrollPane(table);
        panelTabla.add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        horizontalStrutDer = Box.createHorizontalStrut(40);
        panelTabla.add(horizontalStrutDer, BorderLayout.WEST);

        lblNewLabel_1 = new JLabel("");
        panelCentro.add(lblNewLabel_1);
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\Sergio\\3\u00BA\\Tecnolog\u00EDas de desarrollo de Software\\Workspace\\SERGIO\\Imagenes\\BotonPlay.png"));

        panel = new JPanel();
        panelCentro.add(panel);

        lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\Sergio\\3\u00BA\\Tecnolog\u00EDas de desarrollo de Software\\Workspace\\SERGIO\\Imagenes\\BotonAnterior.png"));
        panel.add(lblNewLabel_2);

        horizontalStrut = Box.createHorizontalStrut(20);
        panel.add(horizontalStrut);

        lblNewLabel = new JLabel("");
        panel.add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\Sergio\\3\u00BA\\Tecnolog\u00EDas de desarrollo de Software\\Workspace\\SERGIO\\Imagenes\\BotonPausa.png"));

        horizontalStrut_1 = Box.createHorizontalStrut(20);
        panel.add(horizontalStrut_1);

        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\Sergio\\3\u00BA\\Tecnolog\u00EDas de desarrollo de Software\\Workspace\\SERGIO\\Imagenes\\BotonSiguiente.png"));
        panel.add(lblNewLabel_3);
    }


}
