public class Lanche {
  private int codigo;
  private String nome;
  private double valor;

  public Lanche() {
    
  }
  
  public Lanche(int codigo, String nome, double valor) {
    this.codigo = codigo;
    this.nome = nome;
    this.valor = valor;
  }

  public int getCodigo() {
    return this.codigo;
  }
  
  public String getNome() {
    return this.nome;
  }
  
  public double getValor() {
    return this.valor;
  }
}