import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Sistema {
  public static Scanner scan = new Scanner(System.in); // atributo para controlar o input do usuário
  private static Usuario user = null; // atributo para controlar a sessão do usuário, se ele loga como admin ou funcionário
  private static List<Lanche> lanches = new ArrayList<>(); // atributo que guarda a lista de laches cadastrados no sistema
  private static Queue<Carrinho> pedidos = new LinkedList<>(); // atributo que guarda a fila de pedidos dos clientes no sistema

  // função principal do programa, onde o programa inicia
  public static void main(String[] args) {
    // looping do menu principal
    int opt;
    do {
      menu(); // printa o menu
      opt = scan.nextInt(); // lê o input que o usuário digitar e armazena na variável "opt"
      switch(opt) { // testa o valor de opt para os casos
        case 0: break;
        case 1: // se o valor de opt for 1
          if(login(Files.ADMIN_LOGIN_FILE) != 0) user.main(); // tenta fazer login como admin, caso tenha sucesso acessa a função main() da usuário instanciado
          break;
        case 2: // se o valor de opt for 2
          if(login(Files.FUNCIONARIO_LOGIN_FILE) != 0) user.main(); // tenta fazer login como funcionário, caso tenha sucesso acessa a função main() da usuário instanciado
          break;
        default: // se o valor de opt não bater com nenhum dos outros casos
          System.out.println("OPCAO INVALIDA");
          break;
      }
    } while(opt != 0); // enquanto o usuário não escolher a opção SAIR ele continua no sistema
  }

  /*
   * função para fazer login no sistema
   *
   * files -> recebe o arquivo para procurar o registro do usuário
   */
  private static int login(Files files) {
    /*
     * try - catch: bloco de tratamento do código, caso alguma coisa dê de errado no bloco try o sistema para a execução do código no bloco try e manda uma Excessão para ser tratada no catch
     */
    try {
        File f = files.getFile(); // armazena o arquivo em uma referência de File
      if(!f.canRead()) // checa se o arquivo não existe
        System.out.println("NENHUM USUARIO CADASTRADO");
      else {
        Scanner scanFile = new Scanner(f); // cria uma instância de Scanner a partir do arquivo
        String login = scanFile.next(); // lê a primeiro coisa escrita no arquivo até encontrar um espaço
        String senha = scanFile.next(); // lê a segunda coisa escrita no arquivo até encontrar um espaço
        scanFile.close(); // fecha o Scanner do arquivo

        /*
         * bloco para controlar as tentativas que o usuário tem para acessar o sistema
         */
        int tentativas = 3;
        while(tentativas > 0) {
          if(validaLogin(login, senha, files) != 0) // tenta validar o login passando o login e a senha pegos do arquivo
            return 1;
          else {
            tentativas--;
            System.out.println("Tentativas restantes: " + tentativas);
          }
        }
      }
    } catch (Exception e) { // recebe uma Excessão para ser tratada
      System.out.println(e.getMessage());
    }
    return 0;
  }
  
  /*
   * função que valida o login que o usuário informar
   * 
   * login -> login lido do arquivo
   * senha -> senha lida do arquivo
   * file -> arquivo de login lido
   */
  private static int validaLogin(String login, String senha, Files file) {
    System.out.print("login: ");
    String loginUser = scan.next(); // lê a próxima coisa que o usuário digitar
    System.out.print("senha: ");
    String senhaUser = scan.next(); // lê a próxima coisa que o usuário digitar

    if(login.equals(loginUser) && senha.equals(senhaUser)) // checa se o login e a senha informados pelo usuário coincidem com o login e a senha lidos do arquivo
      return atribuiUsuario(file, login); // atribui uma instância para o usuário: Admin ou Funcionário
    else
      System.out.println("Login ou senha invalidos");
    
    return 0;
  }

  /*
   * função que instancia um usuário
   */
  private static int atribuiUsuario(Files file, String login) {
    switch(file) { // checa qual arquivo foi lido no login
      case ADMIN_LOGIN_FILE: user = new Admin(login); break; // se foi o arquivo de login de admin instancia um novo Admin
      case FUNCIONARIO_LOGIN_FILE: user = new Funcionario(login); break; // se foi o arquivo de login de funcionário instancia um novo Funcionario
      default: return 0; // alguma coisa deu errado
    }
    return 1;
  }

  /*
   * função que busca um lanche na lista de lanches do sistema através do código dele
   */
  private static Lanche pegarLanche(int codigo) {
    for(Lanche lanche : lanches) {
      if(lanche.getCodigo() == codigo) // se o código do lache coincidir com o código informado pelo usuário
        return lanche; // retorna o lanche
    }
    return null; // não encontrou o lanche e retorna null
  }

  /*
   * função que adiciciona um lanche na lista de lanches
   */
  public static void cadastrarLanches(Lanche lanche) {
    lanches.add(lanche);
  }

  /*
   * função que lista os lanches cadastrados no sistema
   */
  public static void listarLanches() {
    if(lanches.size() == 0) {
      System.out.println("NENHUM LANCHE CADASTRADO");
      return;
    }
    else {
      System.out.printf("| %10s\t%20s\t%8s |\n", "CODIGO", "LANCHE", "PRECO");
      lanches.forEach(lanche -> {
        System.out.printf("| #%10s\t%20s\t%8s |\n", lanche.getCodigo(), lanche.getNome(), "R$ " + lanche.getValor());
      });
    }
  }

  /*
   * função que adiciona um pedido na fila de pedidos
   */
  private static void adicionarPedido(Carrinho c) {
    pedidos.add(c);
  }

  /*
   * função para cadastrar um pedido no sistema
   */
  public static int fazerPedido() {
    if(lanches.size() > 0) { // primeiro checa se a lista de lanches não está vazia
      Carrinho c = new Carrinho(); // cria um novo carrinho para o cliente
      do { // looping para adicionar lanches no pedido
        Sistema.listarLanches(); // lista os lanches do sistema
        System.out.print("SELECIONE O LANCHE PELO CODIGO: ");
        int codigo = Sistema.scan.nextInt(); // lê o código que o usuário digita
        Lanche l = pegarLanche(codigo); // pega o lanche a partir do código
        if(l == null) { // se não encontra o lanche
          System.out.println("NENHUM LANCHE ENCONTRADO");
        } else { // se encontra adiciona no pedido
          c.adicionarLanche(l);
        }
        System.out.print("ADICIONAR OUTRO LANCHE? 1 - SIM\t0 - NAO: ");
      } while(Sistema.scan.nextInt() != 0); // enquanto o usuário não digitar 0 continua adicionando no pedido
      c.listarPedido(); // printa o pedido
      System.out.print("CONFIRMAR PEDIDO? 1 - SIM\t0 - NAO: ");
      if(Sistema.scan.nextInt() != 0) { // se o usuário confirma o pedido
        double troco = 0; // variável para controlar o valor de troco
        System.out.print("INFORME O VALOR PAGO PELO CLIENTE: R$ ");
        double valorPago = Double.valueOf(Sistema.scan.next()); // guarda o valor pago pelo cliente
        if(valorPago < c.getValor()) { // se o valor pago pelo cliente é menor que o valor do pedido cancela o pedido
          System.out.println("VALOR INSUFICIENTE");
          System.out.println("CANCELANDO PEDIDO");
          return 0;
        } else { // se não calcula o troco
          troco = valorPago - c.getValor();
        }
        System.out.println("PEDIDO CONCLUIDO COM SUCESSO");
        if(troco != 0)
          System.out.println("VALOR DE TROCO: R$ " + troco);
        adicionarPedido(c); // adiciona o pedido na fila de pedidos
        registraVenda(c);
        return 1;
      }
    }
    return 0;
  }

  public static int verificaArquivo(File f) {
    try {
      if(!f.canRead()) { // checa se o arquivo não existe
        if(f.createNewFile()) // se não existe tenta criar
          System.out.println("Criando arquivo... " + f.getName());
        else
            throw new Exception("Nao foi possivel criar o arquivo :(");
      }
      return 1;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return 0;
  }

  private static void registraVenda(Carrinho c) {
    try {
      File f = Files.VENDAS_FILE.getFile();
      if(verificaArquivo(f) != 0) {
        FileWriter fw = new FileWriter(f);
        Date date = Date.valueOf(LocalDate.now());
        fw.append("Lanche: " + c + "\t" + "Valor total: " + c.getValor() + "\t" + "- " + date.toString() + "\n");
        fw.close();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void printarVendas() {
    try {
      File f = Files.VENDAS_FILE.getFile();
      if(!f.canRead()) {
        System.out.println("NENHUM REGISTRO DE VENDA");
        return;
      }
      Scanner scanner = new Scanner(f);
      while(scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
      }
      scanner.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /*
   * função que exibe a fila de pedidos no sistema
   */
  public static void filaPedidos() {
    if(pedidos.size() == 0) {
      System.out.println("NENHUM PEDIDO NA FILA");
      return;
    }
    pedidos.forEach(p -> { // para cada pedido executa o trecho de código
      System.out.println("PEDIDO #" + p.getCodigo());
      System.out.println("VALOR: R$ " + p.getValor());
      System.out.println("-------------------------------");
    });
  }

  /*
   * função que despacha um pedido
   */
  public static void despacharPedido() {
    Carrinho c = pedidos.poll(); // retira da fila o primeiro pedido e armazena na variável
    System.out.println("CODIGO: #" + c.getCodigo());
    System.out.println("VALOR DO PEDIDO: R$ " + c.getValor());
    System.out.println("PEDIDO DESPACHADO");
  }

  /*
   * função que printa o menu principal
   */
  private static void menu() {
    System.out.println("MENU");
    System.out.println("1 - ENTRAR COMO ADMINISTRADOR");
    System.out.println("2 - ENTRAR COMO FUNCIONARIO");
    System.out.println("0 - SAIR");
  }
}