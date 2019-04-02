import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MastermindTest {
    public boolean misplaced(ArrayList<Integer> secret, ArrayList<Integer> guess, int index){
        return secret.contains(guess.get(index)) &&
                secret.get(index) != guess.get(index);
    }

    public boolean wellPlaced(ArrayList<Integer> secret, ArrayList<Integer> guess, int index){
        return secret.get(index) == guess.get(index);
    }

    public interface TwoListIterator{
        boolean test(ArrayList<Integer> a, ArrayList<Integer>b, int index);
    }

    public int count(ArrayList<Integer> secret, ArrayList<Integer> guess, TwoListIterator iterator) {
        int total = 0;

        for (int i = 0; i < secret.size(); i++) {
            if (iterator.test(secret, guess, i)) {
                total++;
            }
        }
        return total;
    }

    public ArrayList<Integer> list(int... i) {
        ArrayList<Integer> ret = new ArrayList<>();

        for(int item: i){
            ret.add(item);
        }

        return ret;
    }

    public ArrayList<Integer> evaluate(ArrayList<Integer> secret, ArrayList<Integer> guess) {
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(count(secret, guess, this::wellPlaced));
        ret.add(count(secret, guess, this::misplaced));
        return ret;
    }


    @Test
    public void testEvaluate() {
        assertEquals(list(0, 0), evaluate(list(1), list(2)));
        assertEquals(list(1, 0), evaluate(list(2), list(2)));
        assertEquals(list(0, 1), evaluate(list(1, 3), list(2, 1)));
    }
}
