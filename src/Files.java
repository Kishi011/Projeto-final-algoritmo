import java.io.File;

public enum Files {
  
  ADMIN_LOGIN_FILE(new File("public/admin_login.txt")),
  FUNCIONARIO_LOGIN_FILE(new File("public/funcionario_login.txt")),
  LANCHES_FILE(new File("public/lanches.txt")),
  VENDAS_FILE(new File("public/vendas.txt"));

  private File f;

  private Files(File f) {
    this.f = f;
  }

  public File getFile() {
    return this.f;
  }
}
