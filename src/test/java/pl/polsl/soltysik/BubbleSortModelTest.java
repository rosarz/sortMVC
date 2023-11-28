package pl.polsl.soltysik;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.soltysik.exception.SortException;
import pl.polsl.soltysik.model.BubbleSortModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class BubbleSortModelTest {

    /**
     * Test sprawdzający poprawność metody getData() w modelu sortowania bąbelkowego.
     * Sprawdza, czy dane zwrócone przez metodę getData() zgadzają się z oczekiwanymi danymi.
     *
     * @param input String zawierający liczby oddzielone przecinkiem, reprezentujące dane wejściowe do modelu.
     */
    @ParameterizedTest
    @CsvSource({"1,2", "5,10,15,20", "0,0,0,0,0"})
    public void testGetData(String input) throws SortException {
        List<Integer> expected = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        BubbleSortModel model = new BubbleSortModel(new ArrayList<>(expected));
        assertEquals(expected, model.getData());
    }

    /**
     * Test sprawdzający, czy metoda swap rzuci wyjątek IndexOutOfBoundsException
     * dla nieprawidłowych indeksów w modelu sortowania bąbelkowego.
     *
     * @param index1 Pierwszy indeks do zamiany.
     * @param index2 Drugi indeks do zamiany.
     */
    @ParameterizedTest
    @CsvSource({"0,2", "3,-1", "-1,0", "-1,-1"})
    public void testSwapOutOfBounds(int index1, int index2) throws SortException {
        BubbleSortModel model = new BubbleSortModel(List.of(1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            model.swap(index1, index2);
        });
    }

    /**
     * Test sprawdzający, czy metoda swap rzuci wyjątek IndexOutOfBoundsException
     * dla nieprawidłowych indeksów w modelu sortowania bąbelkowego.
     *
     * @param index1 Pierwszy indeks do zamiany.
     * @param index2 Drugi indeks do zamiany.
     */
    @ParameterizedTest
    @CsvSource({"0,-1", "-1,0", "-1,-1"})
    public void testSwapNegative(int index1, int index2) throws SortException {
        BubbleSortModel model = new BubbleSortModel(List.of(1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            model.swap(index1, index2);
        });
    }

    /**
     * Test sprawdzający, czy metoda swap rzuci wyjątek IndexOutOfBoundsException
     * dla nieprawidłowych indeksów w modelu sortowania bąbelkowego (obie wartości indeksów są ujemne).
     */
    @Test
    public void testSwapNegativeBoth() throws SortException {
        BubbleSortModel model = new BubbleSortModel(List.of(1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            model.swap(-1, -1);
        });
    }

    /**
     * Test sprawdzający, czy metoda swap rzuci wyjątek IndexOutOfBoundsException
     * dla nieprawidłowych indeksów w pustym modelu sortowania bąbelkowego.
     *
     * @param index1 Pierwszy indeks do zamiany.
     * @param index2 Drugi indeks do zamiany.
     */
    @ParameterizedTest
    @CsvSource({"0,0", "1,-1"})
    public void testSwapEmpty(int index1, int index2) throws SortException {
        BubbleSortModel model = new BubbleSortModel(List.of());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            model.swap(index1, index2);
        });
    }


}