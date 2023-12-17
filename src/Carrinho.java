import java.util.ArrayList;
import java.util.List;

public class Carrinho {
  private List<Lanche> itens = new ArrayList<>();
  public static int ultimoCodigo = 1;
  private int codigo = ultimoCodigo;
  private double valor = 0;

  public void adicionarLanche(Lanche l) {
    itens.add(l);
  }

  public void listarPedido() {
    System.out.println("PEDIDO");
    itens.forEach(lanche -> {
      valor += lanche.getValor();
      System.out.printf("%20s - %8s\n", lanche.getNome(), "R$ " + lanche.getValor());
    });
    System.out.printf("%28s\n","VALOR TOTAL: R$ " + valor);
  }

  public void limparCarrinho() {
    itens.clear();
  }

  public double getValor() {
    return valor;
  }

  public int getCodigo() {
    return codigo;
  }
}
