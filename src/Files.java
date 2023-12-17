import java.io.File;

/*
 * classe para controlar os arquivos do sistema
 */
public enum Files { // classe enumeradora do java, serve para criar instâncias constantes que não se alteram
  
  ADMIN_LOGIN_FILE(new File("public/admin_login.txt")), // instância constante de arquivo de registro do admin
  FUNCIONARIO_LOGIN_FILE(new File("public/funcionario_login.txt")), // instância constante de arquivo de registro de funcionarios
  LANCHES_FILE(new File("public/lanches.txt")), // instância constante de arquivo de registro de lanches
  VENDAS_FILE(new File("public/vendas.txt")); // instância constante de arquivo do relatório de vendas

  private File f; // recebe um atributo do tipo File

  private Files(File f) {
    this.f = f;
  }

  /*
   * retorna o arquivo da instância
   */
  public File getFile() {
    return this.f;
  }
}
