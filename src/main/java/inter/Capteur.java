package inter;

public interface Capteur{
    int getValue();

    void tick() throws InterruptedException;

    int getCompteur();

    void setAlgoDiffusion(AlgoDiffusion s);

}
