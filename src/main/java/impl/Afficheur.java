package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import inter.ObsCapteur;


public class Afficheur implements ObsCapteur {

    private final int id;
    private final Canal canal;
    private final List<Integer> memory = new ArrayList<>();

    public Afficheur(int id, Canal canal){
        this.id = id;
        this.canal = canal;
    }

    @Override
    public Void update() {
        try {
            int value = this.canal.getValue().get();
            memory.add(value);
            System.out.println("L'afficheur : " + this.id + " a pour valeur : " + value);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return null;

    }

    public void printMemory() {
        StringBuilder res = new StringBuilder("A" + id + " memory -> ");
        for(Integer i : memory) {
            res.append(i).append(" ");
        }
        System.out.println(res.toString());
    }

    public List<Integer> getMemory(){
        return memory;
    }
}
