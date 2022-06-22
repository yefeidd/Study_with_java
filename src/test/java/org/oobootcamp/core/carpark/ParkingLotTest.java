package org.oobootcamp.core.carpark;

import org.junit.jupiter.api.Test;
import org.oobootcamp.carpark.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    @Test
    void should_return_a_ticket_when_park_a_car_given_have_left_space() {
        // given
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car();

        // when
        Ticket ticket = parkingLot.park(car);

        // then
        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_full_pack_when_park_a_car_given_no_left_space() {
        // given
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());
        parkingLot.park(new Car());

        // when
        Car myCar = new Car();

        // then
        assertThrows(FullParkException.class, () -> parkingLot.park(myCar));
    }

    @Test
    void should_return_my_car_when_get_a_car_given_a_right_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot(2);
        Car myCar = new Car();
        Ticket ticket = parkingLot.park(myCar);

        // when
        Car car = parkingLot.pickUp(ticket);

        // then
        assertThat(car).isEqualTo(myCar);
    }

    @Test
    void should_can_not_get_car_when_get_a_car_given_an_invalid_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot(2);
        Car myCar = new Car();
        parkingLot.park(myCar);

        // when
        Ticket invalidTicket = new Ticket();

        // then
        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUp(invalidTicket));
    }
}
