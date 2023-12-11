public class Funcionario implements Usuario {
  String login;
  String senha;

  public Funcionario() {

  }
  
  public Funcionario(String login) {
    this.login = login;
  }

  @Override
  public void main() {
    System.out.println("Logado como Funcionario");
    try {
      int opt;
      do {
        this.menu();
        opt = Sistema.scan.nextInt();
        switch(opt) {
          case 1:
            // menuGerenciarProdutos
            break;
          case 0: break;
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
  public void listarProdutos() {
    
  }

  @Override
  public void cadastrarProduto() {
    
  }
  
  @Override
  public void menu() {
    System.out.println("MENU");
    System.out.println("1 - GERENCIAR PRODUTOS");
    System.out.println("2 - TROCAR PERFIL");
    System.out.println("0 - SAIR");
  }

  @Override
  public String getLogin() {
    return this.login;
  }
}