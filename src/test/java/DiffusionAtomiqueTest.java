import impl.*;

import inter.Capteur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.lang.Thread.sleep;

class DiffusionAtomiqueTest {

    ScheduledExecutorService exec;
    Canal canal;
    Canal canal2;
    Canal canal3;

    Afficheur afficheur;
    Afficheur afficheur2;
    Afficheur afficheur3;

    DiffusionAtomique strat;

    Capteur capteur;

    @BeforeEach
    void setUp(){
        exec = Executors.newScheduledThreadPool(10);

        //Création de 4 canaux
        canal = new Canal(exec);
        canal2 = new Canal(exec);
        canal3 = new Canal(exec);


        strat = new DiffusionAtomique();

        //Création de 4 afficheurs relié à leurs canaux
        afficheur = new Afficheur(1, canal);
        afficheur2 = new Afficheur(2,canal2);
        afficheur3 = new Afficheur(3, canal3);


        // Lié canaux aux afficheurs
        canal.setObsCapteur(afficheur);
        canal2.setObsCapteur(afficheur2);
        canal3.setObsCapteur(afficheur3);


        // Création capteur relié à sa strategie
        capteur = new CapteurImpl();
        capteur.setAlgoDiffusion(strat);

        canal.setCapteur(capteur);
        canal2.setCapteur(capteur);
        canal3.setCapteur(capteur);

        // lié stratégie aux canaux
        strat.addCanal(canal);
        strat.addCanal(canal2);
        strat.addCanal(canal3);
    }

    /**
     * On vérifie que pour chaques afficheurs, on a bien
     * toutes les valeurs de ticks envoyées
     */
    @Test
    void test() throws InterruptedException {

        /*
         * Obliger de mettre un sleep sinon le getValue est trop lent
         * et ira chercher la nouvelle valeur du tick suivant
         */

        capteur.tick();
        sleep(3000);
        Assertions.assertTrue(allAfficheursContains(1));

        capteur.tick();
        sleep(3000);
        Assertions.assertTrue(allAfficheursContains(2));

        capteur.tick();
        sleep(3000);
        Assertions.assertTrue(allAfficheursContains(3));

        capteur.tick();
        sleep(3000);
        Assertions.assertTrue(allAfficheursContains(4));

        afficheur.printMemory();
        afficheur2.printMemory();
        afficheur3.printMemory();

        exec.shutdownNow();
    }
    /**
     *
     * @param value int, valeur du tick à verifier
     * @return boolean true, si tous les afficheurs contiennent value
     */
    private boolean allAfficheursContains(int value){
        return afficheur.getMemory().contains(value) &&
                afficheur2.getMemory().contains(value) &&
                 afficheur3.getMemory().contains(value);

    }

}
