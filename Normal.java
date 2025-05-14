import java.util.Random;

public class Normal extends Jogador {
    
    public Normal(String cor){
        super(cor);
    }
    public Normal(String cor, int casa){
        super(cor, casa);
    }

    public int rolarDados(){
        Random random = new Random();
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        return dado1 + dado2;
    }

}
