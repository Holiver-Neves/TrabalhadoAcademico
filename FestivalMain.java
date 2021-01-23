import java.io.*;
import java.util.Scanner;

public class FestivalMain {

  static StringBuffer memoriaCantor = new StringBuffer();
  static StringBuffer memoriaEstilo = new StringBuffer();
  static Scanner scan = new Scanner(System.in); 

  public static void main(String[] args) {
    char opcao,resp='N';
    do{
      opcao = menu();
      switch (opcao){
      case '1':
        inserirEstilo();
        break;
      case '2':
        inserirCantor();
        break;
      case '3':
        alterarDadosCantor();
        break;
      case '4':
        excluirDadosCantor();
        break;
      case '5':
        consultarGeralCantor();
        break;
      case '6':
        consultarGeralEstilo();
        break;
      case '7':
        consultaEspecifica();
        break;
      case '8':
        System.out.println("Deseja realmente sair do programa? S/N");
        resp = Character.toUpperCase(scan.next().charAt(0));
        break;
      default: 
        System.out.println("Opção inválida. Escolha outra opção do menu.");
      }
    } while  (resp!='S');
    System.out.println("Programa finalizado.\n\n"+"Feito por Holiver Neves, Gabriel Holz, Vinícius e Alejandro.");
    System.exit(0);

  }

  // MENU:
  static char menu(){
    System.out.println("\n\nEscolha uma opção:\n"+"1. Inserir Estilo Musical\n"+"2. Inserir Cantor\n"+"3. Alterar dados de um cantor\n"+"4. Excluir dados de um cantor\n"+"5. Consultar lista dos cantores\n"+"6. Consultar lista de estilos musicais\n"+"7. Pesquisar cantores de determinado estilo\n"+"8. Sair");
    return scan.next().charAt(0);
  } 

  // 1 - INICIAR ARQUIVO: (METODO PARA LIMPAR A VARI�VEL MEMORIA E ATUALIZA-LA COM OS NOVOS DADOS)
  
  static void iniciarArquivoCantores(){
    try{
      BufferedReader arqEntrada;
      arqEntrada = new BufferedReader (new FileReader ("Cantores.txt"));
      String linha = "";
      memoriaCantor.delete(0,memoriaCantor.length());
      while ( (linha = arqEntrada.readLine()) != null ) {
        memoriaCantor.append (linha + "\n");
      }
      arqEntrada.close();
    } catch (FileNotFoundException erro){
      System.out.println("\nArquivo não encontrado");
    } catch (Exception e){
      System.out.println("\nErro de Leitura !");
    }
  }
  
  static void iniciarArquivoEstilos(){
    try{
      BufferedReader arqEntrada;
      arqEntrada = new BufferedReader (new FileReader ("EstilosMusicais.txt"));
      String linha = "";
      memoriaEstilo.delete(0,memoriaEstilo.length());
      while ( (linha = arqEntrada.readLine()) != null ) {
        memoriaEstilo.append (linha + "\n");
      }
      arqEntrada.close();
    } catch (FileNotFoundException erro){
      System.out.println("\nArquivo não encontrado");
    } catch (Exception e){
      System.out.println("\nErro de Leitura !");
    }
  }


  // 2 - GRAVAR: apenas para classe cantores
  public static void gravarDados (){
    try{
      BufferedWriter arqSaida;
      arqSaida = new BufferedWriter(new FileWriter ("Cantores.txt"));
      arqSaida.write(memoriaCantor.toString());
      arqSaida.flush();
      arqSaida.close();
    } catch (Exception e){
      System.out.println("\nErro de gravacao!");
    }
  }

  //3 - INSERIR ESTILOS MUSICAIS:
  static void inserirEstilo() {
	int codigo;
    String nome; 
    try{
      BufferedWriter saida;
      saida=new BufferedWriter(new FileWriter ("EstilosMusicais.txt",true));
      
      System.out.println("Digite o codigo do estilo (diferente de 0): ");
      codigo = scan.nextInt();
      System.out.println("Digite o nome do estilo: ");
      nome = scan.next();
      
      EstilosMusicais reg = new EstilosMusicais (codigo, nome);
      saida.write (reg.toString());
      saida.flush ();
      saida.close ();
      System.out.println("Operação realizada com sucesso");
    }catch (Exception e){
      System.out.println("Erro de gravação");
    }

  }
  
  //INSERIR DADOS DO CANTOR
  static void inserirCantor() {
    String nome, musicaPrincipal;
    int estilo;

    
    try{
      
      BufferedWriter saida;
      saida=new BufferedWriter(new FileWriter ("Cantores.txt",true));
      System.out.println("Digite o nome do cantor: ");
      nome = scan.next();
      System.out.println("Digite o código do estilo do cantor: ");
      estilo = scan.nextInt();
      System.out.println("Digite a musica mais famosa do cantor: ");
      musicaPrincipal = scan.next();

      if(verificarCod(estilo)) {

      Cantores reg = new Cantores (nome, estilo, musicaPrincipal);
      saida.write (reg.toString());
      saida.flush ();
      saida.close ();
      System.out.println("Operação realizada com sucesso");
     
      }else {
        System.out.println("Estilo não encontrado!\nTente novamente!");
      }
     
    }catch (Exception e){
      System.out.println("Erro de gravação");
    }

  }


  // 4- ALTERAR DADOS:(apenas para classe cantores)
  static void alterarDadosCantor() {
    String nome, musica;
    int inicio, fim, ultimo, primeiro, estilo;
    iniciarArquivoCantores();

    try{
      if (memoriaCantor.length()!=0) {
        System.out.println("Digite o nome do cantor que deseja alterar: ");
        nome = scan.next();
        estilo = 0;
        musica = "";
        inicio = memoriaCantor.indexOf (nome);
        if (inicio > -1){
          ultimo = memoriaCantor.indexOf("\t",inicio);
          nome = memoriaCantor.substring(inicio, ultimo);
          primeiro = ultimo + 1;
          ultimo = memoriaCantor.indexOf("\t",primeiro);
          estilo = Integer.parseInt(memoriaCantor.substring(primeiro, ultimo));
          primeiro = ultimo + 1;
          fim = memoriaCantor.indexOf("\n", primeiro);
          musica = memoriaCantor.substring(primeiro, fim);
          Cantores reg = new Cantores (nome, estilo, musica);
          System.out.println("Deseja alterar?"+"\n"+"Digite S ou N"+"\n\n"+"Nome do cantor: "+reg.getNome()+"\n"+"Estilo Musical: "+reg.getEstilo()+" - "+getNomeEstilo(reg.getEstilo())+"\n"+"M�sica principal: "+reg.getMusicaPrincipal());
          char resp = Character.toUpperCase(scan.next().charAt(0));    
          if (resp == 'S'){
            System.out.println("Entre com o novo nome: ");
            nome = scan.next();
            reg.setNome(nome);
            System.out.println("Entre com o codigo do novo estilo musical: ");
            estilo = scan.nextInt();
            reg.setEstilo(estilo);
            System.out.println("Entre com a nova musica: ");
            musica = scan.next();
            reg.setMusicaPrincipal(musica);
            memoriaCantor.replace(inicio, fim+1,reg.toString());
            System.out.println("Registro alterado.");
          } else{
            System.out.println("Alteração cancelada.");
          }  
          gravarDados();
        }else{
          System.out.println("Nome não encontrado");
        }
      }else{
        System.out.println("Arquivo vazio.");
      }
    }catch(Exception erro2){
      System.out.println("Erro de leitura.");
    }
  }


  //5 - EXCLUIR DADOS / apenas para classe cantores
  private static void excluirDadosCantor() {
    String nome, musica;
    int inicio, fim, ultimo, primeiro, estilo;
    iniciarArquivoCantores();
    try{
      if (memoriaCantor.length()!=0) {
        System.out.println("Digite o nome do cantor que deseja excluir:");
        nome= scan.next();
        estilo = 0;
        musica = "";
        inicio = memoriaCantor.indexOf (nome);
        if (inicio != -1){
        	ultimo = memoriaCantor.indexOf("\t",inicio);
            nome = memoriaCantor.substring(inicio, ultimo);
            primeiro = ultimo + 1;
            ultimo = memoriaCantor.indexOf("\t",primeiro);
            estilo = Integer.parseInt(memoriaCantor.substring(primeiro, ultimo));
            primeiro = ultimo + 1;
            fim = memoriaCantor.indexOf("\n", primeiro);
            musica = memoriaCantor.substring(primeiro, fim);
            Cantores reg = new Cantores (nome, estilo, musica);
          System.out.println("Deseja excluir?"+"\n"+"Digite S ou N"+"\n\n"+"Nome do cantor: " +reg.getNome()+"\n"+"Estilo Musical: "+reg.getEstilo()+" - "+getNomeEstilo(reg.getEstilo())+"\n"+"M�sica principal: "+reg.getMusicaPrincipal());
          char resp = Character.toUpperCase(scan.next().charAt(0));
          if (resp == 'S'){
            memoriaCantor.delete (inicio, fim + 1);  
            System.out.println("Registro excluido.");
            gravarDados(); 
          } else{
            System.out.println("Exclusão cancelada.");
          }

        }else{
          System.out.println("Nome não encontrado");
        }
      }else{
        System.out.println("Arquivo vazio.");
      }
    }catch(Exception erro2){
      System.out.println("Erro de leitura.");
    }
  }

  // 6 - CONSULTA GERAL CANTORES
  static private void consultarGeralCantor(){
    String nome, musica;
    String dados="\nCantores cadastrados:\n\n";
    int inicio, fim, ultimo, primeiro, estilo;
    iniciarArquivoCantores();
    inicio = 0;
    try{
      if(memoriaCantor.length() != 0){
        while(inicio != memoriaCantor.length()){
          ultimo = memoriaCantor.indexOf("\t",inicio);
          nome = memoriaCantor.substring(inicio, ultimo);
          primeiro = ultimo + 1;
          ultimo = memoriaCantor.indexOf("\t",primeiro);
          estilo = Integer.parseInt(memoriaCantor.substring(primeiro, ultimo));
          primeiro = ultimo + 1;
          fim = memoriaCantor.indexOf("\n", primeiro);
          musica = memoriaCantor.substring(primeiro, fim);
          Cantores reg = new Cantores (nome, estilo, musica);
          dados+="Nome do cantor: "+reg.getNome()+"\nEstilo Musical: "+reg.getEstilo()+" - "+getNomeEstilo(reg.getEstilo())+"\nMúsica Principal: "+reg.getMusicaPrincipal()+"\n\n";
          inicio = fim + 1;
        }
        System.out.println(dados);
      }else{
        System.out.println("Arquivo vazio.");
      }
    }catch(Exception erro2){
      System.out.println("Erro de leitura."+erro2);
    }
  }
  
  //FUNCAO AUXILIAR - BUSCA NOME DO ESTILO A PARTIR DO CODIGO
  static String getNomeEstilo(int codigoBusca) {
	String nome, resposta = "";
    int inicio, fim, ultimo, primeiro, codigo;
    iniciarArquivoEstilos();
    boolean achou = false;
    inicio = 0;
    try{
      if(memoriaEstilo.length() != 0){
        while((inicio != memoriaEstilo.length()) && (!achou)){
          ultimo = memoriaEstilo.indexOf("\t",inicio);
          codigo = Integer.parseInt(memoriaEstilo.substring(inicio, ultimo));
          primeiro = ultimo + 1;
          fim = memoriaEstilo.indexOf("\n", primeiro);
          nome = memoriaEstilo.substring(primeiro, fim);
          if (codigo == codigoBusca) {
        	  resposta = nome;
        	  achou = true;
          }
          inicio = fim + 1;
        }
      }else{
        resposta = "*Erro ao obter nome do estilo.*";
      }
    }catch(Exception erro2){
      resposta = "*Erro ao obter nome do estilo.*";
    }
    return resposta;
  }
  
  // 7 - CONSULTA GERAL ESTILOS MUSICAIS
  static private void consultarGeralEstilo(){
	String nome;
    String dados="\nEstilos cadastrados:\n\n";
    int inicio, fim, ultimo, primeiro, codigo;
    iniciarArquivoEstilos();
    inicio = 0;
    try{
      if(memoriaEstilo.length() != 0){
        while(inicio != memoriaEstilo.length()){
          ultimo = memoriaEstilo.indexOf("\t",inicio);
          codigo = Integer.parseInt(memoriaEstilo.substring(inicio, ultimo));
          primeiro = ultimo + 1;
          fim = memoriaEstilo.indexOf("\n", primeiro);
          nome = memoriaEstilo.substring(primeiro, fim);
          EstilosMusicais reg = new EstilosMusicais (codigo, nome);
          dados+="Codigo do estilo: "+reg.getCodigo()+"\nNome do Estilo: "+reg.getNome()+"\n\n";
          inicio = fim + 1;
        }
        System.out.println(dados);
      }else{
        System.out.println("Arquivo vazio.");
      }
    }catch(Exception erro2){
      System.out.println("Erro de leitura.");
    }
  } 
 

  // 8 - CONSULTA ESPEC�FICA ENVOLVENDO OS DOIS
  private static void consultaEspecifica(){
    String estiloPesquisa, nome, musica;
    int inicio , fim, ultimo, primeiro, estilo, estiloPesquisaCodigo;
    iniciarArquivoCantores();
    inicio = 0;
    boolean achou = false;
    try{
      if (memoriaCantor.length()!=0) {
        System.out.println("Digite o estilo dos cantores que deseja pesquisar: ");
        estiloPesquisa = scan.next();
        estiloPesquisaCodigo = getCodigoEstilo(estiloPesquisa);
        while (inicio != memoriaCantor.length()) {
      	  ultimo = memoriaCantor.indexOf("\t",inicio);
          nome = memoriaCantor.substring(inicio, ultimo);
          primeiro = ultimo + 1;
          ultimo = memoriaCantor.indexOf("\t",primeiro);
          estilo = Integer.parseInt(memoriaCantor.substring(primeiro, ultimo));
          primeiro = ultimo + 1;
          fim = memoriaCantor.indexOf("\n", primeiro);
          musica = memoriaCantor.substring(primeiro, fim);
          Cantores reg = new Cantores (nome, estilo, musica);
          if (reg.getEstilo() == estiloPesquisaCodigo) {
            System.out.println("Nome do cantor: "+reg.getNome()+"\nEstilo Musical: "+reg.getEstilo()+" - "+getNomeEstilo(reg.getEstilo())+"\nM�sica Principal: "+reg.getMusicaPrincipal()+"\n\n");
            achou = true;
          }
          inicio = fim + 1;
        }
        if (!achou){
          System.out.println("ERRO! Estilo inválido, ou não cadastrado!");
        }
      }
    }catch(Exception erro2){
      System.out.println("Erro de leitura.");
    }
  }
  
//FUNCAO AUXILIAR - BUSCA CODIGO DO ESTILO A PARTIR DO NOME
  static int getCodigoEstilo(String nomeBusca) {
	String nome;
	int resposta = 0;
    int inicio, fim, ultimo, primeiro, codigo;
    iniciarArquivoEstilos();
    boolean achou = false;
    inicio = 0;
    try{
      if(memoriaEstilo.length() != 0){
        while((inicio != memoriaEstilo.length()) && (!achou)){
          ultimo = memoriaEstilo.indexOf("\t",inicio);
          codigo = Integer.parseInt(memoriaEstilo.substring(inicio, ultimo));
          primeiro = ultimo + 1;
          fim = memoriaEstilo.indexOf("\n", primeiro);
          nome = memoriaEstilo.substring(primeiro, fim);
          if (nome.equalsIgnoreCase(nomeBusca)) {
        	  resposta = codigo;
        	  achou = true;
          }
          inicio = fim + 1;
        }
      }else{
        resposta = 0;
      }
    }catch(Exception erro2){
      resposta = 0;
    }
    return resposta;
  }

  //FUNCAO AUXILIAR - VERIFICA SE O CÓDIGO ESTÁ OU NÃO CADASTRADO
  static boolean verificarCod(int codigoBusca) {
    String nome;
      int inicio, fim, ultimo, primeiro, codigo;
      iniciarArquivoEstilos();
      boolean achou = false;
      inicio = 0;
      try{
        if(memoriaEstilo.length() != 0){
          while((inicio != memoriaEstilo.length()) && (!achou)){
            ultimo = memoriaEstilo.indexOf("\t",inicio);
            codigo = Integer.parseInt(memoriaEstilo.substring(inicio, ultimo));
            primeiro = ultimo + 1;
            fim = memoriaEstilo.indexOf("\n", primeiro);
            nome = memoriaEstilo.substring(primeiro, fim);
            if (codigo == codigoBusca) {
              
              achou = true;
            }
            inicio = fim + 1;
          }
        }else{
          achou = false;
        }
      }catch(Exception erro2){
        achou = false;
      }
      return achou;
    }

}