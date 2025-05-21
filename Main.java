import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    //private static Jogador jogador;
    private static Tabuleiro tabuleiro; 
    private static Scanner T = Entrada.getScanner();

    //cores
    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001B[34m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[38;5;226m";
    public static final String LARANJA = "\u001B[38;5;214m";
    public static final String VERMELHO = "\u001B[38;5;196m";
    public static final String ROSA = "\u001B[38;2;255;105;180m";

    public static void main(String[] args){
        int numJogadores = 0;
        Set<Integer> tipos  = new HashSet<>();

        int certo = -1;
        while (certo ==-1){
            while(numJogadores < 2 || numJogadores > 6){
                System.out.print("Digite o número de jogadores: ");
                numJogadores = T.nextInt();
                if(numJogadores < 2){
                    System.out.println("Número mínimo de jogadores é 2.");
                }else if(numJogadores > 6){
                    System.out.println("Número máximo de jogadores é 6.");
                }
            }

            tabuleiro = new Tabuleiro(numJogadores);
            int[] cor = new int[numJogadores];
            int esc = 0;

            int [] recebeTipo = criandoJogadoresValidos(numJogadores, cor, esc);
            for (int h=0; h < numJogadores; h++){
                tipos.add(recebeTipo[h]);
            }
            if(tipos.size() < 2){
                    System.out.println("É necessário ter pelo menos dois tipos diferentes de jogador!");
                    tabuleiro.getJogadores().clear();
                    System.out.println("Recomençando o jogo!");
            } else {
                certo = 0;
            }
        }

        System.out.println("\nJogadores criados com sucesso!");
        System.out.println("\nIniciando o jogo...");
        System.out.println("\nVocê deseja jogar no modo normal ou Debug? \n1 - Normal\n2 - Debug");
        System.out.print("-> ");
        int modo = T.nextInt();
        T.nextLine();
        
        int rodada = 0;
        int jogadorVitorioso = -1;
        while(jogadorVitorioso == -1){
            rodada++;
            if (modo == 1) {
            	jogadorVitorioso = vitoriaNormal(rodada); 
            }
            else if (modo ==2) {
            	jogadorVitorioso = vitoriaDebug(rodada);
            }
            else {
            	System.out.println("Escolha inválida!");
            }
            
        }

        System.out.println("\nRodada " + rodada + " - Fim de jogo!");
        System.out.println(tabuleiro.getJogadores().get(jogadorVitorioso).getCor() + "Jogador " + (jogadorVitorioso + 1) + RESET + " vitorioso.");
        System.out.println("Número de jogadas dos jogadores:");
        for (int j=0; j<tabuleiro.getJogadores().size(); j++) {
        	System.out.println("Número de jogadas do " + tabuleiro.getJogadores().get(j).getCor() + "Jogador " + (j + 1) + RESET + " é " + tabuleiro.getJogadores().get(j).getNumJogadas());
        }
    }
 
    
   public static  int[] criandoJogadoresValidos(int numJogadores, int[] cor, int esc){
        int [] jogadorTipo = new int [numJogadores];
        for(int i = 0; i < numJogadores; i++){
            
            boolean corValida = false;

            while(!corValida){
                System.out.println("Jogador " + (i + 1) + ", escolha a cor:");
                System.out.println("1 - " + AZUL + "Azul" + RESET);
                System.out.println("2 - " + VERDE + "Verde" + RESET);    
                System.out.println("3 - " + AMARELO + "Amarelo" + RESET);
                System.out.println("4 - " + LARANJA + "Laranja" + RESET);
                System.out.println("5 - " + VERMELHO + "Vermelho" + RESET);
                System.out.println("6 - " + ROSA + "Rosa" + RESET);
                System.out.print("-> ");
                esc = T.nextInt();

                boolean corRepetida = false;
                for(int j = 0; j < i; j++){
                    if(cor[j] == esc){
                        corRepetida = true;
                        break;
                    }
                }

                if(corRepetida){
                    System.out.println("Essa cor já foi escolhida. Ten  te outra.");
                } else {
                    cor[i] = esc;
                    corValida = true;
                }
            }
            switch(esc){
                case 1:
                    jogadorTipo[i] = escolherJogador(AZUL);
                    break;
                case 2:
                    jogadorTipo[i] = escolherJogador(VERDE);
                    break;
                case 3:
                    jogadorTipo[i] = escolherJogador(AMARELO);
                    break;
                case 4:
                    jogadorTipo[i] = escolherJogador(LARANJA);
                    break;
                case 5:
                    jogadorTipo[i] = escolherJogador(VERMELHO);
                    break;
                case 6:
                    jogadorTipo[i] = escolherJogador(ROSA);
                    break;                      
            }
        }

        return jogadorTipo;
    }
    
    public static int escolherJogador(String cor){
        boolean flag = true;            
        int tipo = 0;
        while(flag == true){ // TEM QUE TER PELO MENOS DOIS TIPOS DIFERENTES = TODOS NÃO PODEM SER IGUAIS
            System.out.println("Escolha o tipo do jogador:");
            System.out.println("1 - Normal:");
            System.out.println("2 - Azarado:");
            System.out.println("3 - Sortudo:");
            System.out.print("-> ");
            tipo = T.nextInt();
            switch(tipo){
                case 1:
                    tabuleiro.adicionarJogadores(new Normal(cor));
                    flag = false;
                    tipo = 1;
                    break;
                case 2:
                    tabuleiro.adicionarJogadores(new Azarado(cor));
                    flag = false;
                    tipo = 2;
                    break;
                case 3:
                    tabuleiro.adicionarJogadores(new Sortudo(cor));
                    flag = false;
                    tipo = 3;
                    break;
                default:
                    System.out.println("Tipo inválido, tente novamente");
            }   
        }
        return tipo;
    }

    public static int vitoriaDebug(int rodada) {
    	for (int i=0; i<tabuleiro.getJogadores().size(); i++) {
            if(tabuleiro.getJogadores().get(i).getPodeJogar()){
                System.out.println("\n\nRodada " + rodada);
                System.out.println("- - - VEZ DO " + tabuleiro.getJogadores().get(i).getCor() + "JOGADOR " + (i + 1) + RESET + " - - -");
                System.out.println("Você deseja que o " + tabuleiro.getJogadores().get(i).getCor() + "Jogador " + (i + 1) + RESET + " ande até que casa? ");
                System.out.print("-> ");
                int casa = T.nextInt();
                int flagTabuleiro = tabuleiro.modoDebug(rodada, casa, i);
                if (flagTabuleiro != -1) {
                    return flagTabuleiro;
                }
            }else{
                tabuleiro.getJogadores().get(i).setPodeJogar(true);
                System.out.println("\n\nRodada " + rodada);
                System.out.println("- - - VEZ DO " + tabuleiro.getJogadores().get(i).getCor() + "JOGADOR " + (i + 1) + RESET + " - - -");
                System.out.println(tabuleiro.getJogadores().get(i).getCor() + "Jogador " + (i + 1) + RESET + " não pode jogar nesta rodada.");   
            } 
    	}
    	return -1;
    
    }

    private static int vitoriaNormal(int rodada) {
        for (int i=0; i<tabuleiro.getJogadores().size(); i++) {
            
            if (tabuleiro.getJogadores().get(i).getPodeJogar()) {
                System.out.println("\n\nRodada " + rodada);
                System.out.println("- - - VEZ DO " + tabuleiro.getJogadores().get(i).getCor() + "JOGADOR " + (i + 1) + RESET + " - - -");

                System.out.println("Role os dados!");

                System.out.println("Pressione:");
                System.out.println("1 - para rolar os dados");
                System.out.println("2 - Passar Rodada");
                System.out.print("-> ");
                int esc = T.nextInt();
                if (esc == 2) {
                    System.out.println("Rodada passada.");
                    continue;
                }else if(esc == 1){
                    System.out.println("Rolando os dados...");

                }
                int resultado = tabuleiro.getJogadores().get(i).rolarDados();
                int flagTabuleiro = tabuleiro.fazerRodada(resultado, i);    		
                if (flagTabuleiro != -1) {
                    return flagTabuleiro;
                }
            } else {
                tabuleiro.getJogadores().get(i).setPodeJogar(true);
                System.out.println("\n\nRodada " + rodada);
                System.out.println("- - - VEZ DO " + tabuleiro.getJogadores().get(i).getCor() + "JOGADOR " + (i + 1) + RESET + " - - -");
                System.out.println(tabuleiro.getJogadores().get(i).getCor() + "Jogador " + (i + 1) + RESET + " não pode jogar nesta rodada.");
            } 
    	}
    	return -1;
    }
}