package jogoRaiz.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private List<Tiro> tiros;
	
	private boolean visivel;

	public Player() {
		this.x = 0;
		this.y = 50;
		visivel = true;
		tiros = new ArrayList<Tiro>();
	}

	// definir imagem do player
	public void load() {
		ImageIcon referencia = new ImageIcon("img//spaceship.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}

	public void update() {
		// imagem movendo no eixo x e y
		x += dx;
		y += dy;
	}

	public void tiroSimples() {
		this.tiros.add(new Tiro(x + altura, y + (altura / 2)));
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,largura,altura);
	}
	

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_A) {
			tiroSimples();
		}
		// quando clicar pra cima dy vira 3 e nave vai pra cima
		if (codigo == KeyEvent.VK_UP) {
			dy = -3;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 3;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			if(x>=3){
				dx = -3;
			}
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
	}

	public void keyReleased(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		// quando clicar pra cima dy vira 3 e nave vai pra cima
		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
}
