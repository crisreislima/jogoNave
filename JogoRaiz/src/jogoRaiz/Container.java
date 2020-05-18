package jogoRaiz;

import javax.swing.JFrame;

import jogoRaiz.modelo.Fase;

public class Container extends JFrame{

	public Container () {
		add(new Fase());
		//caracteristicas do container - Container = aba do jogo
		setTitle("Saúde de Atleta");
		// largura e altura da aba
		setSize(1024,728);
		//Quando clicar no x da aba fechar o jogo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ponto da tela que aparece assim que iniciar, coloca null paara ser no meio
		setLocationRelativeTo(null);
		// quando vai poder maximizar o jogo
		this.setResizable(false);
		setVisible(true);
	}
	public static void main (String[]args) {
		new Container();
	}
}
