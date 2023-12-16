import java.io.File;
import java.io.FileWriter;

public class Admin extends Usuario {

  public Admin() {
    
  }

  public Admin(String login) {
    super(login);
  }

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
            gerenciarFuncionarios();
            break;
          case 2:
            // menuGerenciarVendas
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
  
  public void visualizarRelatorioVendas() {
    
  }
  
  private void menu() {
    System.out.println("MENU");
    System.out.println("1 - GERENCIAR FUNCIONARIOS");
    System.out.println("2 - GERENCIAR VENDAS");
    System.out.println("0 - TROCAR PERFIL");
  }

  public void gerenciarFuncionarios() {
    int opt;
    do {
        menuFuncionarios();
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

  private int cadastrarUsuario() {
    try {
      File f = Files.FUNCIONARIO_LOGIN_FILE.getFile();
      if(!f.canRead()) { // checa se o arquivo não existe
        if(f.createNewFile())
          System.out.println("Criando arquivo...");
        else
            throw new Exception("Nao foi possivel criar o arquivo :(");
      }
    
      FileWriter fw = new FileWriter(f);
      System.out.print("login: ");
      String login = Sistema.scan.next();
      System.out.print("senha: ");
      String senha = Sistema.scan.next();
      fw.write(login + " ");
      fw.write(senha + "\n");
      fw.close();

      return 1;
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    return 0;
  }
}