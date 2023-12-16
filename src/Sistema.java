import java.util.Scanner;
import java.io.File;
import java.util.List;

public class Sistema {
  public static Scanner scan = new Scanner(System.in);
  private static Usuario user = null;
  // TODO: fazer lista de lanches
  private List<Lanche> lanches;

  public static void main(String[] args) {
    int opt;
    do {
      menu();
      opt = scan.nextInt();
      switch(opt) {
        case 0: break;
        case 1:
          if(login(Files.ADMIN_LOGIN_FILE) != 0) user.main();
          break;
        case 2:
          if(login(Files.FUNCIONARIO_LOGIN_FILE) != 0) user.main();
          break;
        default:
          System.out.println("Opcao invalida");
          break;
      }
    } while(opt != 0);
  }

  private static int login(Files files) {
    try {
        File f = files.getFile();
      if(!f.canRead()) // checa se o arquivo não existe
        System.out.println("Nenhum usuario cadastrado");
      else {
        Scanner scanFile = new Scanner(f);
        String login = scanFile.next();
        String senha = scanFile.next();
        scanFile.close();

        int tentativas = 3;
        while(tentativas > 0) {
          if(validaLogin(login, senha, files) != 0)
            return 1;
          else {
            tentativas--;
            System.out.println("Tentativas restantes: " + tentativas);
          }
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return 0;
  }
  
  private static int validaLogin(String login, String senha, Files file) {
    System.out.print("login: ");
    String loginUser = scan.next();
    System.out.print("senha: ");
    String senhaUser = scan.next();

    if(login.equals(loginUser) && senha.equals(senhaUser))
      return atribuiUsuario(file, login);
    else
      System.out.println("Login ou senha invalidos");
    
    return 0;
  }

  private static int atribuiUsuario(Files file, String login) {
    switch(file) {
      case ADMIN_LOGIN_FILE: user = new Admin(login); break;
      case FUNCIONARIO_LOGIN_FILE: user = new Funcionario(login); break;
      default: break;
    }
    return 1;
  }

  private static void menu() {
    System.out.println("MENU");
    System.out.println("1 - ENTRAR COMO ADMINISTRADOR");
    System.out.println("2 - ENTRAR COMO FUNCIONARIO");
    System.out.println("0 - SAIR");
  }
}