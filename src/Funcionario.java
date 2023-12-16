public class Funcionario extends Usuario {

  public Funcionario() {

  }

  public Funcionario(String login) {
    super(login);
  }

  @Override
  public void main() {
    System.out.println("Logado como Funcionario, Bem viade " + this.login + " :)");
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

  public void listarProdutos() {
    
  }

  public void cadastrarProduto() {
    
  }
  
  @Override
  public void menu() {
    System.out.println("MENU");
    System.out.println("1 - GERENCIAR PRODUTOS");
    System.out.println("2 - TROCAR PERFIL");
    System.out.println("0 - SAIR");
  }
}