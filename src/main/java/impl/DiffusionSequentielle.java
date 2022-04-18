package impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import inter.AlgoDiffusion;
import inter.Capteur;

public class DiffusionSequentielle implements AlgoDiffusion{

    private List<Canal> listCanal;
    private final Random rand = new Random();

    public DiffusionSequentielle(){
        this.listCanal = new ArrayList<>();
    }

    public void addCanal(Canal c){
        this.listCanal.add(c);
    }

    @Override
    public void execute() {
        if(rand.nextDouble() > 0.5) {
            for(Canal c : listCanal){
                c.update();
            }
        }
    }
}
