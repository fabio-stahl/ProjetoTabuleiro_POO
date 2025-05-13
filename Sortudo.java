import java.util.Random;

public class Sortudo extends Jogador {

    public Sortudo(){

    } 

    public Sortudo(String cor){
        super(cor);
    }
    @Override
    public int rolarDados(){
        int dado1, dado2;
        while(true){
            Random random = new Random();
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
            if(dado1 + dado2 >= 7)
                return dado1 + dado2;
        }
    }
}
