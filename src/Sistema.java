import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Sistema {
  public static Scanner scan = new Scanner(System.in);
  private static Usuario user = null;
  private static List<Lanche> lanches = new ArrayList<>();
  private static Queue<Carrinho> pedidos = new LinkedList<>();

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
          System.out.println("OPCAO INVALIDA");
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

  private static Lanche pegarLanche(int codigo) {
    for(Lanche lanche : lanches) {
      if(lanche.getCodigo() == codigo)
        return lanche;
    }
    return null;
  }

  public static void cadastrarLanches(Lanche lanche) {
    lanches.add(lanche);
  }

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

  private static void adicionarPedido(Carrinho c) {
    pedidos.add(c);
  }

  public static int fazerPedido() {
    if(lanches.size() > 0) {
      Carrinho c = new Carrinho();
      Carrinho.ultimoCodigo++;
      do {
        Sistema.listarLanches();
        System.out.print("SELECIONE O LANCHE PELO CODIGO: ");
        int codigo = Sistema.scan.nextInt();
        Lanche l = pegarLanche(codigo);
        if(l == null) {
          System.out.println("NENHUM LANCHE ENCONTRADO");
        } else {
          c.adicionarLanche(l);
        }
        System.out.print("ADICIONAR OUTRO LANCHE? 1 - SIM\t0 - NAO: ");
      } while(Sistema.scan.nextInt() != 0);
      c.listarPedido();
      System.out.print("CONFIRMAR PEDIDO? 1 - SIM\t0 - NAO: ");
      if(Sistema.scan.nextInt() != 0) {
        double troco = 0;
        System.out.print("INFORME O VALOR PAGO PELO CLIENTE: R$ ");
        double valorPago = Double.valueOf(Sistema.scan.next());
        if(valorPago < c.getValor()) {
          System.out.println("VALOR INSUFICIENTE");
          System.out.println("CANCELANDO PEDIDO");
          return 0;
        } else {
          troco = valorPago - c.getValor();
        }
        System.out.println("PEDIDO CONCLUIDO COM SUCESSO");
        if(troco != 0)
          System.out.println("VALOR DE TROCO: R$ " + troco);
        adicionarPedido(c);
        return 1;
      }
    }
    return 0;
  }

  public static void filaPedidos() {
    if(pedidos.size() == 0) {
      System.out.println("NENHUM PEDIDO NA FILA");
      return;
    }
    int i = 1;
    pedidos.forEach(p -> {
      System.out.println("PEDIDO #" + i);
      System.out.println("VALOR: R$ " + p.getValor());
      System.out.println("-------------------------------");
    });
  }

  public static void despacharPedido() {
    Carrinho c = pedidos.poll();
    System.out.println("CODIGO: #" + c.getCodigo());
    System.out.println("VALOR DO PEDIDO: R$ " + c.getValor());
    System.out.println("PEDIDO DESPACHADO");
  }

  private static void menu() {
    System.out.println("MENU");
    System.out.println("1 - ENTRAR COMO ADMINISTRADOR");
    System.out.println("2 - ENTRAR COMO FUNCIONARIO");
    System.out.println("0 - SAIR");
  }
}