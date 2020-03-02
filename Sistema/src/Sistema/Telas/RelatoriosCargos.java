package Sistema.Telas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Sistema.Navegador;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import Sistema.BancodeDados;

public class RelatoriosCargos extends JPanel{
	JLabel labelTitulo, labelDescricao;
    
    public RelatoriosCargos(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);
        
        labelTitulo = new JLabel("Relat�rios", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelDescricao = new JLabel("Esse gr�fico representa a quantidade de funcion�rios por cargo", JLabel.CENTER);
        
        DefaultPieDataset dadosGrafico = this.criarDadosGrafico();
        
        JFreeChart someChart = ChartFactory.createPieChart3D("", dadosGrafico, false, true, false);
        PiePlot plot = (PiePlot) someChart.getPlot();
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        someChart.setBackgroundPaint(null);

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})",
                new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        
        ChartPanel chartPanel = new ChartPanel(someChart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(660, 340);
            }
        };
        
        labelTitulo.setBounds(20, 20, 660, 40);
        labelDescricao.setBounds(20, 50, 660, 40);
        chartPanel.setBounds(20, 100, 660, 340);
        
        add(labelTitulo);
        add(labelDescricao);
        add(chartPanel);
        
        setVisible(true);
    }

    private void criarEventos() {
        
    }

    private DefaultPieDataset criarDadosGrafico() {
        
        DefaultPieDataset dados = new DefaultPieDataset();
           
       
        Connection conexao;
       
        Statement instrucaoSQL;
        
        ResultSet resultados;
        
        try {
           
        	conexao = DriverManager.getConnection(BancodeDados.url, BancodeDados.user, BancodeDados.senha);

            
        
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT loja_automoveis.cargos.nome, count(*) as quantidade FROM loja_automoveis.cargos,loja_automoveis.funcionarios;";
            query = query + " WHERE cargos.id = funcionarios.cargo group by cargos.nome order by nome ASC;";
            resultados = instrucaoSQL.executeQuery(query);

            while (resultados.next()) {
                dados.setValue(resultados.getString("nome"), resultados.getInt("quantidade"));
            }
            
            return dados;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relat�rio.\n\n"+ex.getMessage());
            Navegador.inicio();
        }
        
        return null;
    }


}
