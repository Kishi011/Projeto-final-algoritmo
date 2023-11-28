public class Funcionario extends Usuario {
    
  public Funcionario() {
      super();
  }
  
  public Funcionario(String login) {
      super(login);
  }
  
  @Override
  public void menu() {
      System.out.println("MENU");
      System.out.println("1 - GERENCIAR PRODUTOS");
  }
}