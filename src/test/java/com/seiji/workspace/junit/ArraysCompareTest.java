package com.seiji.workspace.junit;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ArraysCompareTest {
    
    @Test 
    @DisplayName("Compare Arrays Objects when values equals produce true return")
    void compareArraysObject_whenValuesEquals_produceTrueReturn() {

        List<Integer> numbers = new ArrayList<>(List.of(25, 8 ,21, 32, 3));
        List<Integer> expectedArray = new ArrayList<>(List.of(3, 8 ,21, 25, 32));

        Collections.sort(numbers);

        assertIterableEquals(numbers, expectedArray);

    }

    @Test 
    @Timeout(1)
    // @Timeout(value = 15, unit = TimeUnit.MILLISECONDS)
    void testSortPerformance() throws InterruptedException {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random(42);

        IntStream.rangeClosed(1, 100)
            .forEach(i -> numbers.add(random.nextInt(100000) + 1));

        Collections.sort(numbers);

    }

}
