package Sistema.Telas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import Sistema.Navegador;


public class Home extends JPanel {
	JLabel labelTitulo;

	public void Inicio(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

	private void criarComponentes() {
		setLayout(null);

		labelTitulo = new JLabel("Escolha uma opção no menu superior", JLabel.CENTER);

		labelTitulo.setBounds(20, 100, 660, 40);

		add(labelTitulo);

		setVisible(true);
	}

	private void criarEventos() {

	}

}
