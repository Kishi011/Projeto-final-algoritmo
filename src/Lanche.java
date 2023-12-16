public class Lanche {
  private int codigo;
  private String nome;
  private float valor;
  
  public Lanche() {
    
  }
  
  public Lanche(int codigo, String nome, float valor) {
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
  
  public float getValor() {
    return this.valor;
  }
}