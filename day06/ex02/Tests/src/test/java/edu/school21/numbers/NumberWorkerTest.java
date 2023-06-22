package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
public class NumberWorkerTest {
    NumberWorker worker;

    @BeforeEach
    void beforeEachMethod() {
        worker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 17, 19, 23, 29, 31, 37})
    public void isPrimeTrue(int number) {
        Assertions.assertTrue(worker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 15, 25, 9, 6, 112})
    public void isPrimeFalse(int number) {
        Assertions.assertFalse(worker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, Integer.MIN_VALUE})
    public void isPrimeExceptionTest(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () ->{worker.isPrime(number); });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void checkDigitsSum(int input, int expected) {
        Assertions.assertEquals(expected, worker.digitsSum(input));
    }
}
