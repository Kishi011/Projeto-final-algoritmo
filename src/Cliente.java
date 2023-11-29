public class Cliente implements Usuario {
  String login;
  String senha;

  public Cliente() {
    
  }

  public Cliente(String login) {
    this.login = login;
  }

  @Override
  public void main() {
    System.out.println("Bem vindo, " + this.getLogin());
    try {
      int opt;
      do {
        this.menu();
        opt = scan.nextInt();
        switch(opt) {
          case 0:
            break;
          case 1:
            // menuGerenciarPedidos
            break;
          case 2:
            // menuGerenciarLanches
            break;
          default:
            System.out.println("Opcao invalida");
            break;
        }
        } while(opt != 0);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  @Override
  public void menu() {
    System.out.println("MENU");
    System.out.println("1 - GERENCIAR PEDIDOS");
    System.out.println("2 - VISUALIZAR LANCHES");
    System.out.println("0 - SAIR");
  }

  @Override
  public String getLogin() {
    return this.login;
  }
}
