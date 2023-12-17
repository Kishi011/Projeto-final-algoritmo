public class Funcionario extends Usuario {

  public Funcionario() {

  }

  public Funcionario(String login) {
    super(login);
  }

  @Override
  public void main() {
    System.out.println("Logado como Funcionario, Bem viade " + login + " :)");
    try {
      int opt;
      do {
        menu();
        opt = Sistema.scan.nextInt();
        switch(opt) {
          case 0: break;
          case 1:
            gerenciarLanches();
            break;
          case 2:
            gerenciarPedidos();
            break;
          case 3: break;
          default:
            System.out.println("OPCAO INVALIDA");
            break;
        }
      } while(opt != 0 && opt != 3);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void listarPedidos() {
    Sistema.filaPedidos();
  }

  private void listarLanches() {
    Sistema.listarLanches();
  }

  private void cadastrarLanches() {
    System.out.print("Nome: ");
    String nome = Sistema.scan.next();
    System.out.print("Preco: ");
    double preco = Double.valueOf(Sistema.scan.next());
    Lanche l = new Lanche(Carrinho.ultimoCodigo, nome, preco);
    Carrinho.ultimoCodigo++;
    Sistema.cadastrarLanches(l);
  }

  private int fazerPedido() {
    return Sistema.fazerPedido();
  }

  private void gerenciarLanches() {
    int opt;
    do {
      menuLanches();
      opt = Sistema.scan.nextInt();
      switch(opt) {
        case 0: break;
        case 1:
          cadastrarLanches();
          break;
        case 2:
          listarLanches();
          break;
      }
    } while(opt != 0);
  }

  private void gerenciarPedidos() {
    int opt;
    do {
      menuPedidos();
      opt = Sistema.scan.nextInt();
      switch(opt) {
        case 0: break;
        case 1:
          fazerPedido();
          break;
        case 2:
          listarPedidos();
          break;
      }
    } while(opt != 0);
  }

  private void menuLanches() {
    System.out.println("GERENCIAR LANCHES");
    System.out.println("1 - CADASTRAR LANCHE");
    System.out.println("2 - LISTAR LANCHES");
    System.out.println("0 - VOLTAR");
  }

  private void menuPedidos() {
    System.out.println("GERENCIAR PEDIDOS");
    System.out.println("1 - FAZER PEDIDO");
    System.out.println("2 - FILA DE PEDIDOS");
    System.out.println("0 - VOLTAR");
  }

  private void menu() {
    System.out.println("MENU");
    System.out.println("1 - GERENCIAR LANCHES");
    System.out.println("2 - GERENCIAR PEDIDOS");
    System.out.println("3 - TROCAR PERFIL");
    System.out.println("0 - SAIR");
  }
}