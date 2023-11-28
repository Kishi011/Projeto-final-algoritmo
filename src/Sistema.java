import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
// import java.util.Queue;

public class Sistema {
    private static Scanner scan = new Scanner(System.in);
    private static Usuario user = null;
    // TODO: fazer fila de lanches
    // private Queue<Lanche> lanches;

    public static void main(String[] args) {
        int opt;
        do {
            menu();
            opt = scan.nextInt();
            switch(opt) {
                case 0: break;
                case 1:
                    if(login(Files.ADMIN_LOGIN_FILE) != 0)
                        user.main();
                    break;
                case 2:
                    if(login(Files.USER_LOGIN_FILE) != 0)
                        user.main();
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while(opt != 0);
	}
    
    private static int criaConta(Files file) {
        try {
            FileWriter fw = new FileWriter(file.getFilePath());
            System.out.print("login: ");
            String login = scan.next();
            System.out.print("senha: ");
            String senha = scan.next();
            fw.write(login + " ");
            fw.write(senha + "\n");
            fw.close();

            user = file.getFilePath() == "admin_login.txt" ? new Admin(login) : new Funcionario(login);

            return 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private static int login(Files file) {
        try {
            File f = new File(file.getFilePath());
            if(!f.canRead()) // checa se o arquivo não existe, caso verdadeiro ele cria
                if(f.createNewFile()) // se ele consegue criar o arquivo
                    return criaConta(file); // e se ele consegue criar uma conta, então...
                else
                    throw new Exception("Nao foi possivel criar o arquivo: " + file.getFilePath());
            else {
                Scanner scanFile = new Scanner(new File(file.getFilePath()));
                String login = scanFile.next();
                String senha = scanFile.next();
                scanFile.close();
    
                int tentativas = 3;
                while(tentativas > 0) {
                    if(validaLogin(login, senha, file) != null) return 1;
                    tentativas--;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    private static Usuario validaLogin(String login, String senha, Files file) {
        System.out.print("login: ");
        String loginUser = scan.next();
        System.out.print("senha: ");
        String senhaUser = scan.next();
        
        if(login.equals(loginUser) && senha.equals(senhaUser))
            user = file.getFilePath() == "public/admin_login.txt" ? new Admin(login) : new Funcionario(login);
        else
            System.out.println("Login ou senha invalidos");
        
        return user;
    }

    private static void menu() {
        System.out.println("MENU");
        System.out.println("1 - LOGAR COMO ADMININASTROR");
        System.out.println("2 - LOGAR COMO FUNCIONARIO");
        System.out.println("0 - SAIR");
    }

    // TODO: fazer função para trocar o perfil do usuário
}