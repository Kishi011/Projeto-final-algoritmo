public enum Files {
  
  ADMIN_LOGIN_FILE("public/admin_login.txt"),
  USER_LOGIN_FILE("public/usuario_login.txt");

  private String filePath;

  Files(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return this.filePath;
  }
}
