public class Admin extends Usuario {
    
  public Admin() {
      super();
  }
  
  public Admin(String login) {
      super(login);
  }
  
  public void cadastrarUsuario() {
      
  }
  
  public void visualizarRelatorioVendas() {
      
  }
  
  @Override
  public void menu() {
      System.out.println("MENU");
      System.out.println("1 - GERENCIAR PRODUTOS");
      System.out.println("2 - GERENCIAR FUNCIONARIOS");
      System.out.println("3 - GERENCIAR VENDAS");
  }
}