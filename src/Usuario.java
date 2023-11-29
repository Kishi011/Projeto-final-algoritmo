import java.util.Scanner;

public interface Usuario {
  Scanner scan = new Scanner(System.in);
  
  public String getLogin();
  
  void menu();

  public void main();
}