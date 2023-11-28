public class Funcionario extends Usuario {
    
  public Funcionario() {
      super();
  }
  
  public Funcionario(String login) {
      super(login);
  }

  @Override
  public void main() {
    try {
        int opt;
        do {
            this.menu();
            opt = scan.nextInt();
            switch(opt) {
                case 0:
                    break;
                case 1:
                    // menuGerenciarProdutos
                    break;
                case 2:
                    // this.trocaPerfil();
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
      System.out.println("1 - GERENCIAR PRODUTOS");
      System.out.println("2 - TROCAR PERFIL");
      System.out.println("0 - SAIR");
  }
}