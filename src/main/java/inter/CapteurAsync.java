package inter;

import java.util.concurrent.Future;

public interface CapteurAsync {

   Future<Integer> getValue();
}
