public class Lanche {
  private String nome;
  private float valor;
  
  public Lanche() {
    
  }
  
  public Lanche(String nome, float valor) {
    this.nome = nome;
    this.valor = valor;
  }
  
  public String getNome() {
    return this.nome;
  }
  
  public float getValor() {
    return this.valor;
  }
}