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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Sistema.BancodeDados;
import Sistema.Navegador;
import Sistema.entidades.Carros;
public class CarrosConsultar extends JPanel{
	Carros carroAtual;
    JLabel labelTitulo, labelCarro;
    JTextField campoCarro;
    JButton botaoPesquisar, botaoEditar, botaoExcluir;
    DefaultListModel<Carros> listasCarrosModelo = new DefaultListModel();
    JList<Carros> listaCarros;
            
    public CarrosConsultar(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);
        
        labelTitulo = new JLabel("Consulta de Carros", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));      
        labelCarro = new JLabel("Nome do carro", JLabel.LEFT);
        campoCarro = new JTextField();
        botaoPesquisar = new JButton("Pesquisar Carro");
        botaoEditar = new JButton("Editar Carro");
        botaoEditar.setEnabled(false);
        botaoExcluir = new JButton("Excluir Carro");
        botaoExcluir.setEnabled(false);
        listasCarrosModelo = new DefaultListModel();
        listaCarros = new JList();
        listaCarros.setModel(listasCarrosModelo);
        listaCarros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        
        labelTitulo.setBounds(20, 20, 660, 40);
        labelCarro.setBounds(150, 120, 400, 20);
        campoCarro.setBounds(150, 140, 400, 40);
        botaoPesquisar.setBounds(560, 140, 130, 40); 
        listaCarros.setBounds(150, 200, 400, 240);
        botaoEditar.setBounds(560, 360, 130, 40); 
        botaoExcluir.setBounds(560, 400, 130, 40);
        
        add(labelTitulo);
        add(labelCarro);
        add(campoCarro);
        add(listaCarros);
        add(botaoPesquisar);
        add(botaoEditar);
        add(botaoExcluir);
        
        setVisible(true);
    }

    private void criarEventos() {
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlPesquisarCarros(campoCarro.getText());
            }
        });
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.carrosEditar(carroAtual);
            }
        });
        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlDeletarCarro();
            }

			
        });
        listaCarros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                carroAtual = listaCarros.getSelectedValue();
                if(carroAtual == null){
                    botaoEditar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                }else{
                    botaoEditar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                }
            }
        });
    }

    private void sqlPesquisarCarros(String nome) {
        Connection conexao;

        Statement instrucaoSQL;
       
        ResultSet resultados;
        
        try {
         
        	conexao = DriverManager.getConnection(BancodeDados.url, BancodeDados.user, BancodeDados.senha);          
            
             
           instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM loja_automoveis.carros WHERE nome like '%"+nome+"%';");
            
            listasCarrosModelo.clear();

            while (resultados.next()) {                
                Carros carro = new Carros();
                carro.setId(resultados.getInt("id"));
                carro.setNome(resultados.getString("nome"));
                
                listasCarrosModelo.addElement(carro);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os Cargos.");
            Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlDeletarCarro() {
        
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Cargo "+carroAtual.getNome()+"?", "Excluir", JOptionPane.YES_NO_OPTION);
        if(confirmacao == JOptionPane.YES_OPTION){

          
            Connection conexao;
        
            Statement instrucaoSQL;
         
            ResultSet resultados;

            try {
                
            	conexao = DriverManager.getConnection(BancodeDados.url, BancodeDados.user, BancodeDados.senha);
             
                instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                instrucaoSQL.executeUpdate("DELETE loja_automoveis.carros WHERE id ;"+carroAtual.getId()+"");            

                JOptionPane.showMessageDialog(null, "Cargo deletado com sucesso!");
                Navegador.inicio();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Cargo.");
                Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

}
