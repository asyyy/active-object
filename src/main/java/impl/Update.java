package impl;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {
    private Afficheur obsCapt;

    public Update(Afficheur obs){
        this.obsCapt = obs;
    }

    @Override
    public Void call() throws Exception {
       return obsCapt.update();
    }

}
