package cleancoders.practice.primefactors;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PrimeFactorsTest {

    @Test
    public void canFactorIntoPrimes() {
        assertPrimeFactors(list(), of(1));
        assertPrimeFactors(list(2), of(2));
        assertPrimeFactors(list(3), of(3));
        assertPrimeFactors(list(2, 2), of(4));
        assertPrimeFactors(list(5), of(5));
        assertPrimeFactors(list(2, 3), of(6));
        assertPrimeFactors(list(7), of(7));
        assertPrimeFactors(list(2, 2, 2), of(8));
        assertPrimeFactors(list(3, 3), of(9));
        assertPrimeFactors(list(2, 2, 2, 3, 3, 5, 7, 11, 13),
            of(2 * 2 * 2 * 3 * 3 * 5 * 7 * 11 * 13));
    }

    private void assertPrimeFactors(List<Integer> list, List<Integer> of) {
        assertThat(list).isEqualTo(of);
    }

    private List<Integer> list(Integer... ints) {
        return Arrays.asList(ints);
    }

    private List<Integer> of(int n) {
        ArrayList<Integer> factors = new ArrayList<>();
        int divisor = 2;
        for (; n > 1; divisor++) {
            for (; n % divisor == 0; n /= divisor) {
                factors.add(divisor);
            }
        }
        return factors;
    }

}
