package impl;

import java.util.concurrent.Callable;

import inter.Capteur;

public class GetValue implements Callable<Integer> {
    private Capteur capteur;

    public GetValue(Capteur capteur){
        this.capteur = capteur;
    }

    @Override
    public Integer call() {
        return capteur.getCompteur();
    }

}
