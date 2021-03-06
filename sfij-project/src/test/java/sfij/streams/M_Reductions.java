/*
 * Copyright (C) 2021 Maurice Naftalin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sfij.streams;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This set of exercises covers the reduction step.
 * <p>
 * Some of these exercises use a BufferedReader variable
 * named "reader" that the test has set up for you.
 */
public class M_Reductions {

    /**
     * Compute the sums of the 10 first integers starting with 1.
     */
    @Test
    @Ignore
    public void m_reduction01() {

        int sum = 0; // TODO

        assertThat(sum).isEqualTo(55);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // You can use a factory method from IntStream then the sum() method.
    // </editor-fold>

    /**
     * Compute the factorial of 21. This number is quite large.
     */
    @Test
    @Ignore
    public void m_reduction02() {

        BigInteger result = BigInteger.ONE; // TODO

        assertThat(result).isEqualTo(new BigInteger("51090942171709440000"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // You can use a factory method from Stream then the Stream.reduce() method.
    // Check the different versions of this reduce() method.
    // Check carefully the role of the first parameter you need to pass to the reduce() method.
    // </editor-fold>

    /**
     * Given a a list of Consumer, create a single consumer that will pass the
     * element it consumes to all the elements of the list.
     * Try to use a method reference in your solution.
     * The given list contains 10 consumers of StringBuilder. Each of this consumer
     * just adds 1..9 to the content of the StringBuilder.
     */
    @Test
    @Ignore
    public void m_reduction03() {

        List<Consumer<StringBuilder>> consumers =
                IntStream.range(1, 10)
                        .mapToObj(index -> (Consumer<StringBuilder>) (sb -> sb.append(index)))
                        .collect(Collectors.toList());

        Consumer<StringBuilder> consumer = null; // TODO

        StringBuilder sb = new StringBuilder("Hello");
        consumer.accept(sb);

        assertThat(sb.toString()).isEqualTo("Hello123456789");
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Even a list of Consumer can be streamed and reduced!
    // Check the Consumer.andThen() method.
    // </editor-fold>

    /**
     * Given a a list of Predicates, create a single predicate that will be true if one predicate of the list
     * is true.
     * Try to use a method reference in your solution.
     * The given list contains 10 predicates, each of them tests if the index 1..9 is contained in the
     * tested String.
     */
    @Test
    @Ignore
    public void m_reduction04() {

        List<Predicate<String>> predicates =
                IntStream.range(1, 10)
                        .mapToObj(String::valueOf)
                        .map(index -> (Predicate<String>) (s -> s.contains(index)))
                        .collect(Collectors.toList());

        Predicate<String> predicate = null; // TODO

        assertThat(predicate.test("Hello 01")).isTrue();
        assertThat(predicate.test("Hello")).isFalse();
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Even a list of Predicate can be streamed and reduced!
    // Check the Predicate.or() method.
    // </editor-fold>

    /**
     * Get the last word in the text file.
     * <p/>
     * Remember to use the BufferedReader named "reader" that has already been
     * opened for you.
     */
    @Test
    @Ignore
    public void m_reduction05() {

        String result = null; // TODO

        assertThat(result).isEqualTo("thee");
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // A cunning use of the Stream.reduce() method!
    // </editor-fold>


// ========================================================
// END OF EXERCISES
// TEST INFRASTRUCTURE IS BELOW
// ========================================================

    // Pattern for splitting a string into words
    static final Pattern SPLIT_PATTERN = Pattern.compile("[- .:,]+");

    private BufferedReader reader;

    @Before
    public void z_setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(
                Paths.get("files/Sonnet.txt"), StandardCharsets.UTF_8);
    }

    @After
    public void z_closeBufferedReader() throws IOException {
        reader.close();
    }
}
