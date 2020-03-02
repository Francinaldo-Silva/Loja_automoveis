package Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Sistema.entidades.Cargo;
import Sistema.entidades.Carros;
import Sistema.entidades.Funcionario;
import Sistema.Telas.CargosConsultar;
import Sistema.Telas.CargosEditar;
import Sistema.Telas.CargosInserir;
import Sistema.Telas.FuncionariosConsultar;
import Sistema.Telas.FuncionariosEditar;
import Sistema.Telas.FuncionarioInserir;
import Sistema.Telas.Home;
import Sistema.Telas.Login;
import Sistema.Telas.RelatoriosCargos;
import Sistema.Telas.RelatorioCarros;
import Sistema.Telas.RelatoriosSalarios;
import Sistema.Telas.CarrosConsultar;
import Sistema.Telas.CarrosInserir;


public class Navegador {
	private static boolean menuConstruido;
	private static boolean menuHabilitado;
	private static JMenuBar menuBar;
	private static JMenu menuArquivo, menuFuncionarios, menuCargos, menuRelatorios, menuCarros;
	private static JMenuItem miSair, miFuncionariosConsultar, miFuncionariosCadastrar, miCargosConsultar;
	private static JMenuItem miCargosCadastrar, miRelatoriosCargos, miRelatoriosSalarios, miCarrosCadastrar, miRelatoriosCarros,miCarrosConsultar;


	public static void login() {
		Sistema.tela = new Login();
		Sistema.frame.setTitle("Funcionários " + "Empresa");
		Navegador.atualizarTela();
	}

	public static void inicio() {
		Sistema.tela = new Home();
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}

	public static void funcionariosCadastrar() {
		Sistema.tela = new FuncionarioInserir();
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}

	public static void funcionariosConsultar() {
		Sistema.tela = new FuncionariosConsultar();
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}

	public static void funcionariosEditar(Funcionario funcionario) {
		Sistema.tela = new FuncionariosEditar(funcionario);
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}

	public static void cargosCadastrar() {
		Sistema.tela = new CargosInserir();
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}
	public static void carrosCadastrar() {
		Sistema.tela = new CarrosInserir();
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}

	public static void cargosConsultar() {
		Sistema.tela = new CargosConsultar();
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}

	public static void carrosConsultar() {
		Sistema.tela = new CarrosConsultar();
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}


	public static void cargosEditar(Cargo cargo) {
		Sistema.tela = new CargosEditar(cargo);
		Sistema.frame.setTitle("  ");
		Navegador.atualizarTela();
	}

	public static void relatoriosCargos() {
		Sistema.tela = new RelatoriosCargos();
		Sistema.frame.setTitle("Relatorio dos cargos");
		Navegador.atualizarTela();
	}
	public static void relatorioCarros() {
		Sistema.tela = new RelatorioCarros();
		Sistema.frame.setTitle("Relatorio dos carros");
		Navegador.atualizarTela();
	}

	public static void relatoriosSalarios() {
		Sistema.tela = new RelatoriosSalarios();
		Sistema.frame.setTitle("Relatorios do salario");
		Navegador.atualizarTela();
	}

	private static void atualizarTela() {
		Sistema.frame.getContentPane().removeAll();
		Sistema.tela.setVisible(true);
		Sistema.frame.add(Sistema.tela);

		Sistema.frame.setVisible(true);
	}

	private static void construirMenu() {
		if (!menuConstruido) {
			menuConstruido = true;

			menuBar = new JMenuBar();

			menuArquivo = new JMenu("Arquivo");
			menuBar.add(menuArquivo);
			miSair = new JMenuItem("Sair");
			menuArquivo.add(miSair);

			
			menuFuncionarios = new JMenu("Funcionarios");
			menuBar.add(menuFuncionarios);
			miFuncionariosCadastrar = new JMenuItem("Cadastrar");
			menuFuncionarios.add(miFuncionariosCadastrar);
			miFuncionariosConsultar = new JMenuItem("Consultar");
			menuFuncionarios.add(miFuncionariosConsultar);

		
			menuCargos = new JMenu("Cargos");
			menuBar.add(menuCargos);
			miCargosCadastrar = new JMenuItem("Cadastrar");
			menuCargos.add(miCargosCadastrar);
			miCargosConsultar = new JMenuItem("Consultar");
			menuCargos.add(miCargosConsultar);
			
			menuCarros = new JMenu("Carros");
			menuBar.add(menuCarros);
			miCarrosCadastrar = new JMenuItem("Cadastrar");
			menuCarros.add(miCarrosCadastrar);
			miCarrosConsultar = new JMenuItem("Consultar");
			menuCarros.add(miCarrosConsultar);
			
			
			menuRelatorios = new JMenu("Relatorios");
			menuBar.add(menuRelatorios);
			miRelatoriosCargos = new JMenuItem("Funcionarios por cargos");
			menuRelatorios.add(miRelatoriosCargos);
			miRelatoriosSalarios = new JMenuItem("Salarios dos funcionarios");
			menuRelatorios.add(miRelatoriosSalarios);
			miRelatoriosCarros = new JMenuItem("Relatorio carros");
			menuRelatorios.add(miRelatoriosCarros);

			criarEventosMenu();

		}
	}

	public static void habilitaMenu() {
		if (!menuConstruido)
			construirMenu();
		if (!menuHabilitado) {
			menuHabilitado = true;
			Sistema.frame.setJMenuBar(menuBar);
		}
	}

	public static void desabilitaMenu() {
		if (menuHabilitado) {
			menuHabilitado = false;
			Sistema.frame.setJMenuBar(null);
		}
	}

	private static void criarEventosMenu() {
		miSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		
		miFuncionariosCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funcionariosCadastrar();
			}
		});
		miFuncionariosConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funcionariosConsultar();
			}
		});

		miCargosCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargosCadastrar();
			}
		});
		miCargosConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargosConsultar();
			}
		});

		miRelatoriosCargos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				relatoriosCargos();
			}
		});
		miCarrosCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carrosCadastrar();
			}
		});
		miCarrosConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carrosConsultar();
			}
		});

		miRelatoriosCarros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				relatorioCarros();
			}
		});
		
		miRelatoriosSalarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				relatoriosSalarios();
			}
		});
	}
}
