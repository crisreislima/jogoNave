package jogoRaiz.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiro {

	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean visivel;
	
	private static final int LARGURA = 1000;
	private static final int VELOCIDADE = 2;

	public Tiro(int x, int y) {
		this.x = x;
		this.y=y;
		visivel=true;
		
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("img//virus.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	public void update() {
		// imagem movendo no eixo x e y
		x += VELOCIDADE;
		
		if(x > LARGURA) {
			visivel = false;
			
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,largura,altura);
	}
	
	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static int getVelocidade() {
		return VELOCIDADE;
	}

	public Image getImagem() {
		return imagem;
	}
	
	
	
	
}
