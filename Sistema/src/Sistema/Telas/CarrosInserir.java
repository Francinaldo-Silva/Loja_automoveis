package Sistema.Telas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Sistema.BancodeDados;
import Sistema.entidades.Carros;
import javax.swing.JPanel;
public class CarrosInserir extends JPanel{
	JLabel labelTitulo, labelNome, labelMotor, labelAnoModelo, labelAnofabricacao, labelCor, labelFabricante;
    JTextField campoCarro,campoFabricante,campoNome,campoAnomodelo,campoanoFabricacao,campoCor,campoMotor;
    JButton botaoGravar;        
            
    public CarrosInserir(){
        criarComponentes();
        criarEventos();
    }

    private void criarComponentes() {
        setLayout(null);
        
        labelTitulo = new JLabel("Cadastro de Carro", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));      
        labelNome = new JLabel("Nome:", JLabel.LEFT);
        campoNome = new JTextField();
        labelFabricante = new JLabel("Fabricante:", JLabel.LEFT);
        campoFabricante = new JTextField();
        labelMotor = new JLabel("Motor:", JLabel.LEFT);
        campoMotor = new JTextField();
        labelAnoModelo = new JLabel("Ano do modelo:", JLabel.LEFT);
        campoAnomodelo = new JTextField();
        labelAnofabricacao = new JLabel("Ano fabricaçâo:", JLabel.LEFT);
        campoanoFabricacao = new JTextField();
        labelCor = new JLabel("Cor:", JLabel.LEFT);
        campoCor = new JTextField();
        botaoGravar = new JButton("Adicionar Carro");
        
        labelTitulo.setBounds(20, 20, 660, 40);
        labelNome.setBounds(150, 120, 400, 20);
        labelFabricante.setBounds(150, 120, 400, 20);
        labelMotor.setBounds(150, 120, 400, 20);
        labelAnoModelo.setBounds(150, 120, 400, 20);
        labelAnofabricacao.setBounds(150, 120, 400, 20);
        labelCor.setBounds(150, 120, 400, 20);
        campoNome.setBounds(150, 140, 400, 40);
        campoFabricante.setBounds(150, 140, 400, 40);
        campoMotor.setBounds(150, 140, 400, 40);
        campoanoFabricacao.setBounds(150, 140, 400, 40);
        campoAnomodelo.setBounds(150, 140, 400, 40);
        campoCor.setBounds(150, 140, 400, 40);
        botaoGravar.setBounds(250, 380, 200, 40); 
        
        add(labelTitulo);
        add(labelNome);
        add(labelFabricante);
        add(labelMotor);
        add(labelAnoModelo);
        add(labelAnofabricacao);
        add(labelCor);
        add(campoNome);
        add(campoFabricante);
        add(campoMotor);
        add( campoanoFabricacao);
        add(campoAnomodelo);
        add(campoCor);
        add(botaoGravar);
        
        setVisible(true);
    }



	private void criarEventos() {
        botaoGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Carros novoCarro = new Carros();
                novoCarro.setNome(campoCarro.getText());
                
                sqlInserirCarro(novoCarro);
                        
            }
        });
    }

    private void sqlInserirCarro(Carros novoCarro) {
        
        
        if(campoNome.getText().length() <= 3){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente.");
            return;
        }
        
       
        Connection conexao;
        
        Statement instrucaoSQL;
       
        ResultSet resultados;
        
        try {
           
        	conexao = DriverManager.getConnection(BancodeDados.url, BancodeDados.user, BancodeDados.senha);
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            instrucaoSQL.executeUpdate("INSERT INTO loja_automoveis.carros ('nome','modelo','fabricante','motor','anoModelo','anoFabricacao','cor') VALUES ('"+novoCarro.getNome()+novoCarro.getFabricante()+novoCarro.getMotor()+novoCarro.getAno_modelo()+novoCarro.getAno_fabricacacao()+novoCarro.getCor()+"')");            
            
            JOptionPane.showMessageDialog(null, "Carro adicionado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar o Carro.");
            Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
