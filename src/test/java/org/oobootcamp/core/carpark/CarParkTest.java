package org.oobootcamp.core.carpark;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarParkTest {
    @Test
    void should_return_a_key_when_park_a_car_given_have_left_space() {
        // given
        CarPark carPark = new CarPark(3);
        String carNumber = "123456";

        // when
        Integer expected = carNumber.hashCode();
        Integer actual = carPark.requestParkCar(carNumber);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_failed_when_park_a_car_given_no_left_space() {
        // given
        CarPark carPark = new CarPark(2);
        carPark.requestParkCar("1");
        carPark.requestParkCar("2");
        String carNumber = "1234562";

        // when
        Integer actual = carPark.requestParkCar(carNumber);

        // then
        assertThat(actual).isNull();
    }

    @Test
    void should_return_failed_when_get_a_car_given_not_key() {
        // given
        CarPark carPark = new CarPark(2);
        String carNumber = "1234562";
        carPark.requestParkCar(carNumber);

        // when
        String actual = carPark.getCar(null);

        // then
        assertThat(actual).isNull();
    }

    @Test
    void should_return_a_car_when_get_a_car_given_a_key() {
        // given
        CarPark carPark = new CarPark(2);
        String carNumber = "1234562";
        carPark.requestParkCar(carNumber);

        // when
        String actual = carPark.getCar(carNumber.hashCode());

        // then
        assertThat(actual).isEqualTo(carNumber);
    }

    @Test
    void should_return_failed_when_get_a_car_given_an_error_key() {
        // given
        CarPark carPark = new CarPark(2);
        String carNumber = "1234562";
        carPark.requestParkCar(carNumber);

        // when
        String actual = carPark.getCar("111".hashCode());

        // then
        assertThat(actual).isNull();
    }
}
