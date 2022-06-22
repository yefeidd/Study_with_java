package org.oobootcamp.core.carpark;

import org.junit.jupiter.api.Test;
import org.oobootcamp.carpark.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {
    /**
     * Contxt:
     * 作为 银卡VIP用户
     * 我想要 一个初入职场的停车小弟来帮我停车和取车
     * 从而 节省我的时间
     * <p>
     * <p>
     * // 停车
     * ###AC： 三个停车场有空位，小弟在一号停车场停车成功
     * - Example
     * - Given 有人来停车, 三个停车场有空位, When 停车 ,Then 在一号停车场停车成功, 返回停车票
     * <p>
     * <p>
     * ###AC：仅有二三号停车场有空位，小弟在二号停车场停车成功
     * - Example
     * - Given 有人来停车, 仅有二三号停车场有空位, When 停车 ,Then 在二号停车场停车成功, 返回停车票
     * <p>
     * <p>
     * ###AC：仅有三号停车场有空位，小弟在三号停车场停车成功
     * - Example
     * - Given 有人来停车, 仅有三号停车场有空位, When 停车 ,Then 在三号停车场停车成功, 返回停车票
     * <p>
     * <p>
     * ###AC：车位已满，小弟无法停车
     * - Example
     * - Given 有人来停车, 车位已满, When 停车 ,Then 给出车位已满的提示
     * <p>
     * <p>
     * // 取车
     * ###AC：有停车票取车成功
     * - Example
     * - Given 有人来，有有效停车票,
     * - When 取车,
     * - Then 取车成功
     * <p>
     *
     * <p>
     * ###AC：有无效停车票无法取车
     * - Example
     * - Given 有人来，有有效停车票,
     * - When 取车,
     * - Then 取车成功
     **/


    @Test
    void should_get_a_ticket_when_park_a_car_given_three_car_parks_and_some_have_lef_space() {
        // given
        ParkingLot parkingLotA = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLotA, new ParkingLot(1), new ParkingLot(1)));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Car myCar = new Car();

        // when
        Ticket ticket = graduateParkingBoy.park(myCar);

        // then
        assertThat(parkingLotA.pickUp(ticket)).isSameAs(myCar);
    }

    @Test
    void should_get_a_ticket_when_park_a_car_given_two_car_parks_and_the_second_have_lef_space() {
        // given
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot(1), parkingLotTwo));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        graduateParkingBoy.park(new Car());

        Car myCar = new Car();

        // when
        Ticket ticket = graduateParkingBoy.park(myCar);

        // then
        assertThat(parkingLotTwo.pickUp(ticket)).isSameAs(myCar);
    }

    @Test
    void should_get_a_full_pack_tips_when_pack_a_car_given_car_park_full() {
        // given
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot(1), new ParkingLot(1), new ParkingLot(1)));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        graduateParkingBoy.park(new Car());
        graduateParkingBoy.park(new Car());
        graduateParkingBoy.park(new Car());

        // when
        Car myCar = new Car();

        // then
        assertThrows(FullParkException.class, () -> graduateParkingBoy.park(myCar));
    }


    @Test
    void should_get_an_invalid_tick_exception_when_pack_a_car_given_an_invalid_ticket() {
        // given
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot(1), new ParkingLot(1), new ParkingLot(1)));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        graduateParkingBoy.park(new Car());

        // when
        Ticket invalidTicket = new Ticket();

        // then
        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUp(invalidTicket));
    }


    @Test
    void should_get_an_invalid_tick_exception_when_pack_a_car_given_a_used_ticket() {
        // given
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot(1), new ParkingLot(1), new ParkingLot(1)));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket ticket = graduateParkingBoy.park(new Car());
        graduateParkingBoy.pickUp(ticket);

        // when
        // then
        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUp(ticket));
    }

    @Test
    void should_get_a_car_when_get_car_given_a_valid_ticket() {
        // given
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot(1), new ParkingLot(1), new ParkingLot(1)));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket ticket = graduateParkingBoy.park(myCar);

        // when
        Car car = graduateParkingBoy.pickUp(ticket);

        // then
        assertThat(car).isSameAs(myCar);
    }
}
