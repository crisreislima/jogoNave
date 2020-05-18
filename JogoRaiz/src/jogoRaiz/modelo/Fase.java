package jogoRaiz.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Inimigo1> inimigos;
	private boolean emJogo;

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("img//background.jpg");
		fundo = referencia.getImage();
		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());
		// velocidade do jogo
		timer = new Timer(5, this);
		timer.start();
		inicializaInimigos();
		emJogo = true;
	}

	public void inicializaInimigos() {
		int coordenadas[] = new int[40];
		inimigos = new ArrayList<Inimigo1>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			inimigos.add(new Inimigo1(x, y));

		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo) {
			graficos.drawImage(fundo, 0, 0, null);
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			List<Tiro> tiros = player.getTiros();

			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo1 in = inimigos.get(j);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}

		} else {
			ImageIcon fimDeJogo = new ImageIcon("img//fimdejogo.png");
			graficos.drawImage(fimDeJogo.getImage(), 0, 0, this);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}

		for (int j = 0; j < inimigos.size(); j++) {
			Inimigo1 in = inimigos.get(j);
			if (in.isVisivel()) {
				in.update();
			} else {
				inimigos.remove(j);
			}
		}
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaInimigo1 = player.getBounds();
		Rectangle formaTiro = player.getBounds();

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo1 tempInimigo = inimigos.get(i);
			formaInimigo1 = tempInimigo.getBounds();
			if (formaNave.intersects(formaInimigo1)) {
				player.setVisivel(false);
				tempInimigo.setVisivel(false);
				emJogo = false;

			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < inimigos.size(); o++) {
				Inimigo1 tempInimigo = inimigos.get(o);
				formaInimigo1 = tempInimigo.getBounds();
				if (formaTiro.intersects(formaInimigo1)) {
					tempInimigo.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
		}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent tecla) {
			player.keyPressed(tecla);
		}

		@Override
		public void keyReleased(KeyEvent tecla) {
			player.keyReleased(tecla);
		}
	}
}
