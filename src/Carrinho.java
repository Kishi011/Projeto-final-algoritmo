import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {
  private List<Lanche> itens = new ArrayList<>(); // lista de lanches adicionados no carrinho do cliente
  public static int ultimoCodigo = 1;
  private int codigo; // variável para controlar o código do pedido
  private double valor = 0; // variável para controlar o valor total do pedido

  public Carrinho() {
    this.codigo = ultimoCodigo; // código recebe o último código de pedido
    ultimoCodigo++; // incrementa o último código;
  }

  /*
   * função que adicona um item no carrinho
   */
  public void adicionarLanche(Lanche l) {
    itens.add(l);
  }

  /*
   * função que lista o pedido do cliente
   */
  public void listarPedido() {
    System.out.println("PEDIDO");
    itens.forEach(lanche -> {
      valor += lanche.getValor();
      System.out.printf("%20s - %8s\n", lanche.getNome(), "R$ " + lanche.getValor());
    });
    System.out.printf("%28s\n","VALOR TOTAL: R$ " + valor);
  }

  /*
   * função que apaga todos os itens do carrinho
   */
  public void limparCarrinho() {
    itens.clear();
  }

  public List getItens() {
    return Collections.unmodifiableList(itens);
  }

  public double getValor() {
    return valor;
  }

  public int getCodigo() {
    return codigo;
  }

  @Override
  public String toString() {
    String texto = "";
    for(Lanche l : itens) {
      texto += l.getNome();
      texto += " ";
    }
    return texto;
  }
}
