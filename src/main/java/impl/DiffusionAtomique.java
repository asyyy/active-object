package impl;
import java.util.ArrayList;
import java.util.List;

import inter.AlgoDiffusion;
import inter.Capteur;

public class DiffusionAtomique implements AlgoDiffusion{

    private List<Canal> listCanal;

    public DiffusionAtomique(){
        this.listCanal = new ArrayList<>();
    }

    public void addCanal(Canal c){
        this.listCanal.add(c);
    }

    @Override
    public void execute() {
        for(Canal c : listCanal){
            c.update();
        }
    }
}
