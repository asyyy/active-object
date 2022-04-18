package impl;

import inter.AlgoDiffusion;
import inter.Capteur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiffusionEpoque implements AlgoDiffusion{

    private List<Canal> listCanal;
    private final Random r = new Random();

    public DiffusionEpoque(){
        this.listCanal = new ArrayList<>();
    }

    public void addCanal(Canal c){
        this.listCanal.add(c);
    }

    @Override
    public void execute() {

        boolean loop = true;
        boolean ticked = false;
        int chanceUpgrade = 0;

        // While s'arrete uniquement si au moins 1 tick est envoyé à un canal
        while(loop) {
            for (Canal c : listCanal) {
                double random1 = r.nextDouble()+chanceUpgrade;
                double random2 = r.nextDouble();
                // 2 randoms pour savoir vers si on l'envoie vers c
                if(random1 > random2){
                    c.update();
                    ticked = true;
                }
            }
            if(ticked) loop = false;
            /*
             * incremente chance upgrade pour qu'on puisse forcement sortir de la boucle à
             * moment tout en gardant le même ratio de chance pour chaque canal
             */
            if(!ticked) chanceUpgrade += 0.1;
        }
    }
}
