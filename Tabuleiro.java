import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Tabuleiro{
    private ArrayList<Jogador> jogadores;
    private int[][] jogadas;
    private int numJogadores;

    public Tabuleiro(int numJogadores){
        this.jogadores = new ArrayList<>();
        this.numJogadores = numJogadores;
    }

    public void adicionarJogadores(Jogador j){
        jogadores.add(j);
    }

    private void mostrarFimdeRodada(){
        for(Jogador jogador : jogadores){
            System.out.println("Jogador: " + jogador.getCor());
            System.out.println("Casa Atual:" + jogador.getCasaAtual());
        }
    }
    public void fazerRodada(){
        for(int i = 0; i < numJogadores; i++){
            if(jogadores.get(i).getPodeJogar()){
                
                int resultado = jogadores.get(i).rolarDados();
                System.out.println("Resultado dos dados:" + resultado);
                jogadores.get(i).setCasaAtual(resultado);
                int casaAtual = jogadores.get(i).getCasaAtual();
                if (casaAtual >= 40){
                    System.out.println("Jogador "+ jogadores.get(i).getCor() + "venceu!");
                }else if (casaAtual == 10 || casaAtual == 25 || casaAtual == 38) {
                    jogadores.get(i).setPodeJogar(false);
                }else if (casaAtual == 20 || casaAtual == 35) {
                    trocarDeCasa(i);
                }
                else if (casaAtual == 5 || casaAtual == 15 || casaAtual == 30){
                    if (jogadores.get(i) instanceof Normal || jogadores.get(i) instanceof Sortudo){
                        System.out.println("Jogador anda mais 3 casas.");
                        jogadores.get(i).setCasaAtual(3);   
                    }
                }else if(casaAtual == 17 || casaAtual == 27){
                    voltarParaInicio(i);
                }else if (casaAtual == 13) {
                    mudarTipoJogador(i);
                }
            }else{
                jogadores.get(i).setPodeJogar(true);
                System.out.println("Jogador " + jogadores.get(i).getCor() + " não pode jogar nesta rodada.");   
            }
        }
        mostrarFimdeRodada();
    }
    private void trocarDeCasa(int i){
        int casaAtual = jogadores.get(i).getCasaAtual();
        int menorCasa = 40;
        int indiceMenorCasa = i;
        for(int j = 0; j < numJogadores; j++){
            if(j != i){
                if(menorCasa > jogadores.get(j).getCasaAtual()){
                    menorCasa = jogadores.get(j).getCasaAtual();
                    indiceMenorCasa = j;
                }
            }
        }
        jogadores.get(i).setCasaAtual(menorCasa);
        jogadores.get(indiceMenorCasa).setCasaAtual(casaAtual);
    }
    private void voltarParaInicio(int i){
        
        for(int j = 0; j< numJogadores; j++){
            if(j != i){
                System.out.println(j + "-" + jogadores.get(j).getCor());
            }
        }
        System.out.println("Escolha a cor do jogador que você deseja que volte para o início");
        Scanner T = new Scanner(System.in);
        int esc = T.nextInt();
        while(esc < 0 || esc >= numJogadores || esc == i){
            System.out.println("Escolha uma cor válida:");
            esc = T.nextInt();
        }
        jogadores.get(esc).setCasaAtual(0);
        System.out.println("Jogador " + jogadores.get(esc).getCor() + " voltou para o início.");
    }
    private void mudarTipoJogador(int i){
        Random random = new Random();
        int tipo = random.nextInt(3) + 1;
        System.out.println("Carta sorteada:" + tipo);
        switch (tipo) {
            case 1:
                jogadores.set(i, new Normal(jogadores.get(i).getCor()));
                System.out.println("Jogador " + jogadores.get(i).getCor() + " agora é Normal.");
                break;
            case 2:
                jogadores.set(i, new Sortudo(jogadores.get(i).getCor()));
                System.out.println("Jogador " + jogadores.get(i).getCor() + " agora é sortudo");
                break;
            case 3:
                jogadores.set(i, new Azarado(jogadores.get(i).getCor()));
                System.out.println("Jogador " + jogadores.get(i).getCor() + " agora é Azarado");
                break;
        }

    }


}