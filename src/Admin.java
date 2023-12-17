import java.io.File;
import java.io.FileWriter;

public class Admin extends Usuario { // classe Admin herda da classe Usuario

  public Admin() {
    
  }

  public Admin(String login) {
    super(login);
  }

  /*
   * sobreescreve a função main da classe Usuario
   */
  @Override
  public void main() {
    System.out.println("Logado como Administrador");
    try {
      int opt;
      do {
        menu();
        opt = Sistema.scan.nextInt();
        switch(opt) {
          case 0: break;
          case 1:
            gerenciarFuncionarios(); // entra em um submenu relacionado a funcionarios
            break;
          case 2:
            gerenciarVendas(); // entra em um submenu relacionado a vendas
            break;
          default:
            System.out.println("OPCAO INVALIDA");
            break;
        }
      } while(opt != 0);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  /*
   * TODO: visualização do relatório de vendas pelo admin
   */
  private void visualizarRelatorioVendas() {
    System.out.println("RELATORIO DE VENDAS");
    Sistema.printarVendas();
  }

  private void gerenciarVendas() {
    int opt;
    do {
        menuVendas();
        opt = Sistema.scan.nextInt();
        switch(opt) {
            case 0: break;
            case 1: visualizarRelatorioVendas(); break;
            default: System.out.println("OPCAO INVALIDA"); break;
        }
    } while(opt != 0);
  }

  private void menuVendas() {
    System.out.println("GERENCIAR VENDAS");
    System.out.println("1 - VISUALIZAR RELATORIO DE VENDAS");
    System.out.println("0 - VOLTAR");
  }
  
  private void menu() {
    System.out.println("MENU");
    System.out.println("1 - GERENCIAR FUNCIONARIOS");
    System.out.println("2 - GERENCIAR VENDAS");
    System.out.println("0 - TROCAR PERFIL");
  }

  /*
   * controla o fluxo para funções relacionadas ao controle de funcionários
   */
  private void gerenciarFuncionarios() {
    int opt;
    do {
        menuFuncionarios(); // printa o submenu de funcionários
        opt = Sistema.scan.nextInt();
        switch(opt) {
            case 0: break;
            case 1: cadastrarUsuario(); break;
            default: System.out.println("OPCAO INVALIDA"); break;
        }
    } while(opt != 0);
  }

  private void menuFuncionarios() {
    System.out.println("GERENCIAR FUNCIONARIOS");
    System.out.println("1 - CADASTRAR NOVO USUARIO");
    System.out.println("0 - VOLTAR");
  }

  /*
   * cria um novo registro no arquivo de funcionários
   */
  private int cadastrarUsuario() {
    try {
      File f = Files.FUNCIONARIO_LOGIN_FILE.getFile();
      if(Sistema.verificaArquivo(f) != 0) {
        FileWriter fw = new FileWriter(f); // abre o arquivo para escrita
        System.out.print("login: ");
        String login = Sistema.scan.next(); // pega o input do usuário e guarda em login
        System.out.print("senha: ");
        String senha = Sistema.scan.next(); // pega o input do usuário e guarda em senha
        fw.write(login + " "); // escreve no arquivo o login
        fw.write(senha + "\n"); // escreve no arquivo a senha e quebra a linha
        fw.close(); // fecha o arquivo
  
        return 1;
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    return 0;
  }
}