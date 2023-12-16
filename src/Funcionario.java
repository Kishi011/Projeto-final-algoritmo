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
        menu();
        opt = Sistema.scan.nextInt();
        switch(opt) {
          case 1:
            gerenciarLanches();
            break;
          case 0: break;
          default:
            System.out.println("OPCAO INVALIDO");
            break;
        }
      } while(opt != 0);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void listarLanches() {
    
  }

  public void cadastrarLanches() {
    
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

  private void menuLanches() {
    System.out.println("GERENCIAR LANCHES");
    System.out.println("1 - CADASTRAR LANCHE");
    System.out.println("2 - LISTAR LANCHES");
    System.out.println("0 - VOLTAR");
  }

  private void menu() {
    System.out.println("MENU");
    System.out.println("1 - GERENCIAR LANCHES");
    System.out.println("2 - TROCAR PERFIL");
    System.out.println("0 - SAIR");
  }
}