import java.util.Scanner;

public class Main {
    //private static Jogador jogador;
    private static Tabuleiro tabuleiro; 
    private static Scanner T = Entrada.getScanner();
    public static void main(String[] args){

        int numJogadores = 0;

        while(numJogadores < 2){
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
        

        for(int i = 0; i < numJogadores; i++){
            
            boolean corValida = false;

            while(!corValida){
                System.out.println("Jogador " + (i + 1) + ", escolha a cor:");
                System.out.println("1 - Azul");
                System.out.println("2 - Verde");    
                System.out.println("3 - Amarelo");
                System.out.println("4 - Laranja");
                System.out.println("5 - Vermelho");
                System.out.println("6 - Rosa");
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
                    System.out.println("Essa cor já foi escolhida. Tente outra.");
                } else {
                    cor[i] = esc;
                    corValida = true;
                }
            }
            switch(esc){
                case 1:
                    escolherJogador("Azul");
                    break;
                case 2:
                    escolherJogador("Verde");
                    break;
                case 3:
                    escolherJogador("Amarelo");
                    break;
                case 4:
                    escolherJogador("Laranja");
                    break;
                case 5:
                    escolherJogador("Vermelho");
                    break;
                case 6:
                    escolherJogador("Rosa");
                    break;                      
            }
        }

        System.out.println("Jogadores criados com sucesso!");

        System.out.println("Iniciando o jogo...");
        
        System.out.println("Você deseja jogar no modo normal ou Debug? \n1 - Normal\n2 - Debug");
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
        System.out.println("Jogador " + tabuleiro.getJogadores().get(jogadorVitorioso).getCor() + " vitorioso.");
        System.out.println("Número de jogadas dos jogadores:");
        for (int j=0; j<tabuleiro.getJogadores.size(); j++) {
        	System.out.println("Número de jogadas do jogador " + tabuleiro.getJogadores().get(j).getCor() + " é " + tabuleiro.getJogadores().get(j).getNumJogadas());
        }
    }
    
    
    public static void escolherJogador(String cor){
        boolean flag = true;
            while(flag == true){ // TEM QUE TER PELO MENOS DOIS TIPOS DIFERENTES = TODOS NÃO PODEM SER IGUAIS
                System.out.println("Escolha o tipo do jogador:");
                System.out.println("1 - Normal:");
                System.out.println("2 - Azarado:");
                System.out.println("3 - Sortudo:");
                System.out.print("-> ");
                int tipo = T.nextInt();
                switch(tipo){
                    case 1:
                        tabuleiro.adicionarJogadores(new Normal(cor));
                        flag = false;
                        break;
                    case 2:
                        tabuleiro.adicionarJogadores(new Azarado(cor));
                        flag = false;
                        break;
                    case 3:
                        tabuleiro.adicionarJogadores(new Sortudo(cor));
                        flag = false;
                        break;
                    default:
                        System.out.println("Tipo invalido tente novamente");
                }   
            }
    }
    public static int vitoriaDebug(int rodada) {
    	for (int i=0; i<tabuleiro.getJogadores().size(); i++) {
            System.out.println("Você deseja que o Jogador " + tabuleiro.getJogadores().get(i).getCor() + " ande até que casa? ");
    		int casa = T.nextInt();
    		int flagTabuleiro = tabuleiro.modoDebug(rodada, casa, i);
    		if (flagTabuleiro != -1) {
    			return flagTabuleiro;
    		} 
    	}
    	return -1;
    
    }

    private static int vitoriaNormal(int rodada) {
        for (int i=0; i<tabuleiro.getJogadores().size(); i++) {

            System.out.println("\nRodada " + rodada);
            System.out.println("- - - VEZ DO JOGADOR " + tabuleiro.getJogadores().get(i).getCor().toUpperCase() + " - - -");

            System.out.println("Vez do jogador "+ tabuleiro.getJogadores().get(i).getCor());
            System.out.println("Role os dados!");

    		int resultado = tabuleiro.getJogadores().get(i).rolarDados();
    		int flagTabuleiro = tabuleiro.fazerRodada(resultado, i);
    		
            if (flagTabuleiro != -1) {
    			return flagTabuleiro;
    		} 
    	}
    	return -1;
    }
}
