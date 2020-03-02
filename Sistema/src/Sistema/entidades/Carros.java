package Sistema.entidades;

public class Carros {
	private int id;
	private String nome;
	private float motor;
	private String fabricante;
	private int ano_fabricacacao;
	private int ano_modelo;
	private String cor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getMotor() {
		return motor;
	}
	public void setMotor(float motor) {
		this.motor = motor;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public int getAno_fabricacacao() {
		return ano_fabricacacao;
	}
	public void setAno_fabricacacao(int ano_fabricacacao) {
		this.ano_fabricacacao = ano_fabricacacao;
	}
	public int getAno_modelo() {
		return ano_modelo;
	}
	public void setAno_modelo(int ano_modelo) {
		this.ano_modelo = ano_modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	@Override
	public String toString() {
		return this.fabricante + " " + this.nome;
	}

}
