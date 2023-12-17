public class Funcionario extends Usuario {

  public Funcionario() {

  }

  public Funcionario(String login) {
    super(login);
  }

  /*
   * sobreescreve a função main da classe Usuario
   */
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
            gerenciarLanches(); // entra em um submenu relacionado a lanches
            break;
          case 2:
            gerenciarPedidos(); // entra em um submenu relacionado a pedidos
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

  /*
   * função que lista os pedidos no sistema
   */
  private void listarPedidos() {
    Sistema.filaPedidos(); // delega a função para a função filaPedidos() na classe Sistema
  }

  /*
   * função que lista os lanches no sistema
   */
  private void listarLanches() {
    Sistema.listarLanches(); // delega a função para a função listarLanches() na classe Sistema
  }

  /*
   * função que cadastra um lanche no sistema
   */
  private void cadastrarLanches() {
    System.out.print("Codigo: ");
    int codigo = Sistema.scan.nextInt();
    System.out.print("Nome: ");
    String nome = Sistema.scan.next();
    System.out.print("Preco: ");
    double preco = Double.valueOf(Sistema.scan.next());
    Lanche l = new Lanche(codigo, nome, preco); // instancia um novo Lanche
    Sistema.cadastrarLanches(l); // redireciona a função de cadastro do lanche para cadastrarLanches() passando o lanche criado
  }

  private void despacharPedido() {
    Sistema.despacharPedido();
  }

  /*
   * função que faz um pedido no sistema
   */
  private int fazerPedido() {
    return Sistema.fazerPedido(); // delega a função para a função fazerPedido() na classe Sistema
  }

  /*
   * controla o fluxo para funções relacionadas ao controle de lanches
   */
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

  /*
   * controla o fluxo para funções relacionadas ao controle de pedidos
   */
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
        case 3:
          despacharPedido();
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
    System.out.println("3 - DESPACHAR PEDIDO");
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