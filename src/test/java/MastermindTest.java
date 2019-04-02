import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MastermindTest {
    public boolean isMisplaced(ArrayList<Integer> secret, ArrayList<Integer> guess, int index) {
        return secret.contains(guess.get(index)) &&
                secret.get(index) != guess.get(index);
    }

    public boolean isWellPlaced(ArrayList<Integer> secret, ArrayList<Integer> guess, int index) {
        return secret.get(index) == guess.get(index);
    }

    public interface TwoListIterator {
        boolean test(ArrayList<Integer> a, ArrayList<Integer> b, int index);
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

    private ArrayList<Integer> list(int... i) {
        ArrayList<Integer> returnValue = new ArrayList<>();

        for (int item : i) {
            returnValue.add(item);
        }

        return returnValue;
    }

    public ArrayList<Integer> evaluate(ArrayList<Integer> secret, ArrayList<Integer> guess) {
        ArrayList<Integer> returnValue = new ArrayList<>();
        returnValue.add(count(secret, guess, this::isWellPlaced));
        returnValue.add(count(secret, guess, this::isMisplaced));
        return returnValue;
    }


    @Test
    public void givenOneMisplacedAndOneWrongReturn0_1() {
        assertEquals(list(0, 1), evaluate(list(1, 3), list(2, 1)));
    }

    @Test
    public void givenOneWellPlacedAndOneWrongReturn1_0() {
        assertEquals(list(1, 0), evaluate(list(1, 2), list(1, 3)));
    }

    @Test
    public void givenTotallyWrongGuessReturn0_0() {
        assertEquals(list(0, 0), evaluate(list(1), list(2)));
    }

    @Test
    public void givenCorrectGuessReturnSizeOfBoard_0() {
        assertEquals(list(2, 0), evaluate(list(2, 3), list(2, 3)));
    }
}
