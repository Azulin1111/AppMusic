package pulsador;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Vector;

public class Luz extends Canvas implements Serializable {

	// propiedades
	private Color color; // color de la luz
	private boolean encendido = false; // propiedad ligada
	private String nombre; // identificador del pulsador

	private static Color colorBoton1 = new Color(160, 160, 160);
	private static Color colorBoton2 = new Color(200, 200, 200);

	// atributos
	private boolean bPulsado = false; // indica si el botón está presionado o no
	private PropertyChangeSupport pcsEncendido;

	public Luz() {
		pcsEncendido = new PropertyChangeSupport(this);
		setSize(30, 30); // tamaño inicial por defecto del pulsador
		setMinimumSize(new Dimension(30, 30));
		repaint();
		// Añadir eventos de ratón
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				luzPressed(e);
			}

			public void mouseReleased(MouseEvent e) {
				luzReleased(e);
			}
		});
	}

	public void paint(Graphics g) {
		// public void paint(Graphics g) {
		// obtener el tamaño del pulsador
		int ancho = getSize().width;
		int alto = getSize().height;
		// bloquear relación de aspecto
		if (ancho != alto) {
			if (ancho < alto)
				alto = ancho;
			else
				ancho = alto;
			setSize(ancho, alto);
			invalidate();
			//repaint();
		}
		int grosor = 3; // grosor del botón
		int anchuraBoton = ancho - grosor;
		int bordeBoton = anchuraBoton / 5;
		int anchuraLuz = anchuraBoton - 2 * bordeBoton;
		int x = 0; // desplazamiento;
		if (!bPulsado) {
			x = 0;
		} else {
			x = grosor;
		}
		g.setColor(colorBoton1);
		g.fillOval(grosor, grosor, anchuraBoton, anchuraBoton); // dibuja grosor
		g.setColor(Color.BLACK); // dibujar circulos negros
		g.drawOval(grosor, grosor, anchuraBoton - 1, anchuraBoton - 1);
		g.setColor(colorBoton2);
		g.fillOval(x, x, anchuraBoton, anchuraBoton); // dibuja tapa
		if (encendido)
			g.setColor(color);
		else
			g.setColor(getBackground());
		g.fillOval(x + bordeBoton, x + bordeBoton, anchuraLuz, anchuraLuz);
		// dibujar luz
		g.setColor(Color.BLACK); // dibujar circulos negros
		g.drawOval(x, x, anchuraBoton - 1, anchuraBoton - 1);
		g.drawOval(x + bordeBoton, x + bordeBoton, anchuraLuz - 1, anchuraLuz - 1);
		//
		g.setColor(getForeground()); // restituir color
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		repaint();
	}

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean newEncendido) {
		boolean oldEncendido = encendido;
		encendido = newEncendido;
		pcsEncendido.firePropertyChange("encendido", oldEncendido, newEncendido);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void luzPressed(MouseEvent e) {
		bPulsado = true; 
		repaint();
	}

	public void luzReleased(MouseEvent e) {
		if (bPulsado) {
			bPulsado = false;
			if (encendido)
				setEncendido(false);
			else
				setEncendido(true);
			repaint();
		}
	}

	public void addEncendidoListener(PropertyChangeListener listener) {
		pcsEncendido.addPropertyChangeListener(listener);
	}

	public void removeEncendidoListener(PropertyChangeListener listener) {
		pcsEncendido.removePropertyChangeListener(listener);
	}


	public Dimension getPreferredSize() {
		return new Dimension(30, 30);
	}

	// deprecated
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
}