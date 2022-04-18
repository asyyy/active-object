package impl;

import inter.AlgoDiffusion;
import inter.Capteur;

import static java.lang.Thread.sleep;

public class CapteurImpl implements Capteur {

    private AlgoDiffusion strategie;
    private int compteur;

    public CapteurImpl(){
        this.compteur = 0;
    }

    public int getCompteur(){
        return this.compteur;
    }

    public void tick() {
        compteur++;
        System.out.println("Valeur envoyée : " + compteur);
        strategie.execute();
    }

    public void setAlgoDiffusion(AlgoDiffusion s){
        this.strategie = s;
    }

    @Override
    public int getValue() {
        return this.compteur;
    }
}
