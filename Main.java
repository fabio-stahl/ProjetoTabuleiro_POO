import java.util.Scanner;

public class Main {
    private static Jogador jogador;
    private static Tabuleiro tabuleiro; 
    private static Scanner T = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Digite o número de jogadores:");
        int numJogadores = T.nextInt();
        tabuleiro = new Tabuleiro(numJogadores);
        int[] cor = new int[numJogadores];
        int esc = 0;;
        

        for(int i = 0; i < numJogadores; i++){
            
            boolean corValida = false;

            while(!corValida){
                System.out.println("Jogador " + (i + 1) + ", escolha a cor:");
                System.out.println("1 - Azul");
                System.out.println("2 - Verde");    
                System.out.println("3 - Vermelho");
                System.out.println("4 - Amarelo");
                System.out.println("5 - Rosa");
                System.out.println("6 - Laranja");
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
                    escolherJogador("Vermelho");
                    break;
                case 4:
                    escolherJogador("Amarelo");
                    break;
                case 5:
                    escolherJogador("Rosa");
                    break;
                case 6:
                    escolherJogador("Laranja");
                    break;                      
            }
        }

        System.out.println("Jogadores criados com sucesso!");

        System.out.println("Iniciando o jogo...");

        while(true){
            System.out.println("Iniciando rodada...");
            
        }
    }
    
    
    public static void escolherJogador(String cor){
        boolean flag = true;
            while(flag == true){
                System.out.println("Escolha o tipo do jogador:");
                System.out.println("1 - Normal:");
                System.out.println("2 - Azarado:");
                System.out.println("3 - Sortudo:");
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
}
