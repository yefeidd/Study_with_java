package org.oobootcamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oobootcamp.core.count_out.FizzBuzz;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSpeakGameTest {

    /* 1. given 9 when output then return Fizz
     * 2. given 10 when output then return Buzz
     * 3. given 15 when output then return FizzBuzz
     * 4. given 1 when output  return self
     */

    @Test
    void given_input_number_when_the_number_is_multiple_3_then_return_Fizz() {
        // given
        FizzBuzz fizzBuzz = new FizzBuzz();
        int number = 3;

        // when
        String actualData = fizzBuzz.generateOutputData(number);

        // then

        String expectedData = "Fizz";
        assertThat(expectedData).isEqualTo(actualData);
    }

    @Test
    void given_input_number_when_the_number_is_multiple_5_then_return_Buzz() {

    }

    @Test
    void given_input_number_when_the_number_is_multiple_5_and_3_then_return_FizzBuzz() {

    }

    @Test
    void given_input_number_when_the_number_is_not_the_multiple_3_or_5_return_self() {
    }
}
