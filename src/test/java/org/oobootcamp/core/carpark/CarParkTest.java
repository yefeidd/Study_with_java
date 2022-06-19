package org.oobootcamp.core.carpark;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.oobootcamp.core.carpark.CarPackName.ONE;

public class CarParkTest {
    @Test
    void should_return_a_ticket_when_park_a_car_given_have_left_space() {
        // given
        CarPark carPark = new CarPark(ONE, 3);
        Car car = new Car();

        // when
        CarPackResult result = carPark.requestParkCar(car);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Ticket.class);
    }

    @Test
    void should_return_full_pack_when_park_a_car_given_no_left_space() {
        // given
        CarPark carPark = new CarPark(ONE, 2);
        carPark.requestParkCar(new Car());
        carPark.requestParkCar(new Car());
        Car myCar = new Car();

        // when
        CarPackResult result = carPark.requestParkCar(myCar);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(FullPackTips.class);
    }

    @Test
    void should_return_my_car_when_get_a_car_given_a_right_ticket() {
        // given
        CarPark carPark = new CarPark(ONE, 2);
        Car myCar = new Car();
        Ticket ticket = (Ticket) carPark.requestParkCar(myCar);

        // when
        Car car = carPark.getCar(ticket);

        // then
        assertThat(car).isEqualTo(myCar);
    }

    @Test
    void should_can_not_get_car_when_get_a_car_given_an_invalid_ticket() {
        // given
        CarPark carPark = new CarPark(ONE, 2);
        Car myCar = new Car();
        carPark.requestParkCar(myCar);

        // when
        Ticket invalidTicket = new Ticket();
        Car car = carPark.getCar(invalidTicket);

        // then
        assertThat(car).isNull();
    }
}
