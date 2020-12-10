package juego;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class Personaje {

	Juego miJuego;

	static boolean saltando = false;
	boolean sube = false;
	boolean baja = false;

	Area cuerpo;

	int anchoPersonaje = 112;
	int altoPersonaje = 78;

	static int inicialX = 50;
	static int inicialY = 270;

	int auxiliarX = 0;
	int auxiliarY = 0;

	public Personaje(Juego miJuego) {
		this.miJuego = miJuego;
	}

	public void mover() {
		if (inicialX + auxiliarX > 0 && inicialX + auxiliarX < miJuego.getWidth() - anchoPersonaje) {
			inicialX += auxiliarX;
		}
		if (saltando) {
			if (inicialY == 270) {
				sube = true;
				auxiliarY = -2;
				baja = false;
			}
			if (inicialY == 150) {
				baja = true;
				auxiliarY = 2;
				sube = false;
			}

			if (sube) {
				inicialY += auxiliarY;
			}
			if (baja) {
				inicialY += auxiliarY;
				if (inicialY == 270) {
					saltando = false;
				}
			}
		}
	}

	public void paint(Graphics2D g) {
		ImageIcon personaje = new ImageIcon(this.getClass().getResource("/imagenes/personaje.png"));
		g.drawImage(personaje.getImage(), inicialX, inicialY, anchoPersonaje, altoPersonaje, null);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			saltando = true;
		}
	}

	public Area getBounds() {
		Ellipse2D cuerpoForma = new Ellipse2D.Double(inicialX + 50, inicialY + 39, 38, 38);
		cuerpo = new Area(cuerpoForma);

		return cuerpo;
	}
}