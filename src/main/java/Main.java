import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.lang.Thread.sleep;

import impl.*;
import inter.Capteur;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);

        //Création de 4 canaux
        Canal canal = new Canal(exec);
        Canal canal2 = new Canal(exec);
        Canal canal3 = new Canal(exec);


        // DiffusionAtomique strat = new DiffusionAtomique();
        // DiffusionSequentielle strat = new DiffusionSequentielle();
        DiffusionEpoque strat = new DiffusionEpoque();

        //Création de 4 afficheurs relié à leurs canaux
        Afficheur afficheur = new Afficheur(1, canal);
        Afficheur afficheur2 = new Afficheur(2,canal2);
        Afficheur afficheur3 = new Afficheur(3, canal3);


        // Lié canaux aux afficheurs
        canal.setObsCapteur(afficheur);
        canal2.setObsCapteur(afficheur2);
        canal3.setObsCapteur(afficheur3);


        // Création capteur relié à sa strategie
        Capteur capteur = new CapteurImpl();
        capteur.setAlgoDiffusion(strat);

        canal.setCapteur(capteur);
        canal2.setCapteur(capteur);
        canal3.setCapteur(capteur);

        // lié stratégie aux canaux
        strat.addCanal(canal);
        strat.addCanal(canal2);
        strat.addCanal(canal3);


        /*
         * Obliger de mettre un sleep sinon le getValue est trop lent
         * et ira chercher la nouvelle valeur du tick suivant
         */
        capteur.tick();
        sleep(3000);
        capteur.tick();
        sleep(3000);
        capteur.tick();
        sleep(3000);
        capteur.tick();
        sleep(3000);
        exec.shutdownNow();
        afficheur.printMemory();
        afficheur2.printMemory();
        afficheur3.printMemory();

    }
}
