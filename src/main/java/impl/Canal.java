package impl;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import inter.AlgoDiffusion;
import inter.Capteur;
import inter.CapteurAsync;
import inter.ObsCapteurAsync;
import java.util.concurrent.Future;


public class Canal implements ObsCapteurAsync, CapteurAsync{

    private Afficheur afficheur;
    private ScheduledExecutorService sch;
    private Capteur capteur;

    public Canal(ScheduledExecutorService sch){
        this.sch = sch;
    }
    public void setObsCapteur(Afficheur c) {
        this.afficheur = c;
    }
    public void setCapteur(Capteur capteur) {this.capteur = capteur;}

    @Override
    public void update(){
        /*Update u = new Update(this.afficheur,this);*/
        Random r = new Random();
        System.out.println("update");
        sch.schedule(() -> this.afficheur.update(), r.nextInt(1000), TimeUnit.MILLISECONDS);
    }

    @Override
    public Future<Integer> getValue() {
        /*GetValue g = new GetValue(this.capteur.getValue());*/
        Random r = new Random();
        System.out.println("getValue");
        return sch.schedule(() -> this.capteur.getValue(), r.nextInt(1000)+500 , TimeUnit.MILLISECONDS);
    }

}
