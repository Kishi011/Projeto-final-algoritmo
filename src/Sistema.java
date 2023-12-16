import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
// import java.util.Queue;

public class Sistema {
    private Scanner scan = new Scanner(System.in);
    private static final String USER_FILE = "public/usuario.txt";
    private static final String ADMIN_FILE = "public/admin.txt";
    private static Usuario user = null;
    // private Queue<Lanche> lanches;
    
    public Sistema() {
        try {
            File fileUser = new File(USER_FILE);
            if(!fileUser.canRead())
                if(fileUser.createNewFile())
                    this.criaConta();
                else
                    throw new Exception("Nao foi possivel criar o arquivo");
            else
                if(this.validaLogin() != null) {
                    System.out.println("logado como: " + (user instanceof Admin ? "Admin" : "Funcionario"));
                    this.main();
                }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    private void criaConta() {
        try {
            FileWriter fw = new FileWriter(USER_FILE);
            System.out.print("login: ");
            String login = scan.next();
            System.out.print("senha: ");
            String senha = scan.next();
            fw.write(login);
            fw.write(senha);
            fw.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    private Usuario validaLogin() {
        try {
            Scanner scanUser = new Scanner(new File(USER_FILE));
            Scanner scanAdmin = new Scanner(new File(ADMIN_FILE));
            System.out.print("login: ");
            String login = scan.next();
            System.out.print("senha: ");
            String senha = scan.next();
            String loginUser = scanUser.next();
            String senhaUser = scanUser.next();
            String loginAdmin = scanAdmin.next();
            String senhaAdmin = scanAdmin.next();
            scanUser.close();
            scanAdmin.close();
            if(login.equals(loginUser) && senha.equals(senhaUser))
              user = new Funcionario(login);
            else if(login.equals(loginAdmin) && senha.equals(senhaAdmin))
                user = new Admin(login);
                
            else
                System.out.println("Login ou senha invalidos");
            
        } catch(Exception e) {
            System.out.println(e);
        }
        
        return user;
    }
	
	public void main() {
	    user.menu();
	}
}