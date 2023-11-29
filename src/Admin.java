public class Admin implements Usuario {
  String login;
  String senha;

  public Admin() {
    
  }
  
  public Admin(String login) {
    this.login = login;
  }

  @Override
  public void main() {
    System.out.println("Logado como Administrador");
    try {
      int opt;
      do {
        this.menu();
        opt = Sistema.scan.nextInt();
        switch(opt) {
          case 1:
            // menuGerenciarProdutos
            break;
          case 2:
            // menuGerenciarFuncionarios
            break;
          case 3:
            // menuGerenciarVendas
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

  public void cadastrarUsuario() {
    
  }
  
  public void visualizarRelatorioVendas() {
    
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
    System.out.println("2 - GERENCIAR FUNCIONARIOS");
    System.out.println("3 - GERENCIAR VENDAS");
    System.out.println("0 - TROCAR PERFIL");
  }

  @Override
  public String getLogin() {
    return this.login;
  }
}