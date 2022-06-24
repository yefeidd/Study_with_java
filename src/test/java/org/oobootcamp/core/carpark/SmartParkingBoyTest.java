package org.oobootcamp.core.carpark;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/***
 # Story（Calarification Needed）
 **作为** 金卡VIP用户
 **我想要** 让聪明的小弟来帮我停车和取车
 **从而** 更好地节省我的时间

 将车停再**空车位最多**的那个停车场

 相同空位数量，停在第一个停车场。

 ---
 // 停车
 # Tasking（Business-Oriented Tasking）
 ### AC1：多个停车场都有空位，小弟把车停在空位最多的停车位上
 - Example
 -  Given 有两个停车场A/B， A有两个空位，B有一个空位
 -  When 小弟停车
 -  Then 在停车场A停车成功, 返回停车票

 - Example
 -   Given 有两个停车场A/B， A有一个空位，B有两个空位
 -  When 小弟停车
 -  Then 在停车场B停车成功, 返回停车票

 ### AC2：多个停车场有相同数量的空位，小弟把车停在第一个有空位的停车场
 - Example
 -   Given 有两个停车场A/B， A/B均有两个空位
 -   When 小弟停车
 -   Then 在停车场A停车成功, 返回停车票

 ### AC3：多个停车场，车位已满，停车失败提示“车位已满”
 - Example
 -  Given 有停车场A/B，A/B停车场车位均已满,
 -  When 停车 ,
 -  Then 停车失败并提示“车位已满”

 // 取车
 ### AC4：使用有效停车票取车成功
 - Example
 -  Given 在两个停车场A/B，用户的车停在停车场A，并且有对应的票
 -  When 取车
 -  Then 取车成功

 - Example

 -  Given 在两个停车场A/B，用户的车停在停车场B，并且有对应的票
 -  When 取车
 -  Then 取车成功

 ### AC5 使用无效停车票取车提示无效票

 - Example
 -  Given 用户停车在停车场A，并用票取车成功
 -  When 再次使用该停车票取车
 -  Then 取车成功
 - Example
 -  Given 用户使用其它停车场的停车票
 -  When 取车
 -  Then 提示无效票
 **/

class SmartParkingBoyTest {
    /**
     * ### AC1：多个停车场都有空位，小弟把车停在空位最多的停车位上
     * - Example
     * -  Given 有两个停车场A/B， A有两个空位，B有一个空位
     * -  When 小弟停车
     * -  Then 在停车场A停车成功, 返回停车票
     * <p>
     * - Example
     * -   Given 有两个停车场A/B， A有一个空位，B有两个空位
     * -  When 小弟停车
     * -  Then 在停车场B停车成功, 返回停车票
     **/
    @Test
    void should_parked_car_in_A_and_get_a_ticket_when_park_given_A_has_2_left_space_and_B_has_1_left_space() {
        // given
        ParkingLot parkingLotA = new ParkingLot(2);
        ParkingLot parkingLotB = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLotA, parkingLotB));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car myCar = new Car();

        // when
        Ticket ticket = smartParkingBoy.park(myCar);

        // then
        assertNotNull(ticket);
        assertSame(myCar, parkingLotA.pickUp(ticket));
    }

    /**
     * ### AC2：多个停车场有相同数量的空位，小弟把车停在第一个有空位的停车场
     * - Example
     * -   Given 有两个停车场A/B， A/B均有两个空位
     * -   When 小弟停车
     * -   Then 在停车场A停车成功, 返回停车票*
     */
    @Test
    void should_parked_car_in_A_and_get_a_ticket_when_park_given_A_has_2_left_space_and_B_has_2_left_space() {
        // given
        ParkingLot parkingLotA = new ParkingLot(2);
        ParkingLot parkingLotB = new ParkingLot(2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLotA, parkingLotB));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car myCar = new Car();

        // when
        Ticket ticket = smartParkingBoy.park(myCar);

        // then
        assertNotNull(ticket);
        assertSame(myCar, parkingLotA.pickUp(ticket));
    }

    /**
     * ### AC3：多个停车场，车位已满，停车失败提示“车位已满”
     * - Example
     * -  Given 有停车场A/B，A/B停车场车位均已满,
     * -  When 停车 ,
     * -  Then 停车失败并提示“车位已满”
     **/
    @Test
    void should_get_a_full_park_exception_when_park_given_A_full_park_and_B_full_pack() {
        // given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLotA, parkingLotB));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());

        Car myCar = new Car();

        // when
        // then
        assertThrows(FullParkException.class, () -> smartParkingBoy.park(myCar));
    }

    /**
     * ### AC4：使用有效停车票取车成功
     * - Example
     * -  Given 在两个停车场A/B，用户的车停在停车场A，并且有对应的票
     * -  When 取车
     * -  Then 取车成功
     * <p>
     * - Example
     * <p>
     * -  Given 在两个停车场A/B，用户的车停在停车场B，并且有对应的票
     * -  When 取车
     * -  Then 取车成功
     **/
    @Test
    void should_get_my_car_when_pick_up_given_car_was_park_in_A_and_has_the_valid_ticket() {
        // given
        ParkingLot parkingLotA = new ParkingLot(2);
        ParkingLot parkingLotB = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLotA, parkingLotB));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket ticket = smartParkingBoy.park(myCar);

        // when
        Car car = smartParkingBoy.pickUp(ticket);

        // then
        assertSame(car, myCar);
    }

    @Test
    void should_get_my_car_when_pick_up_given_car_was_park_in_B_and_has_the_valid_ticket() {
        // given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLotA, parkingLotB));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket ticket = smartParkingBoy.park(myCar);

        // when
        Car car = smartParkingBoy.pickUp(ticket);

        // then
        assertSame(car, myCar);
    }

    /**
     * ### AC5 使用无效停车票取车提示无效票
     * <p>
     * - Example
     * -  Given 用户停车在停车场A，并用票取车成功
     * -  When 再次使用该停车票取车
     * -  Then 取车成功
     * - Example
     * -  Given 用户使用其它停车场的停车票
     * -  When 取车
     * -  Then 提示无效票
     */
    @Test
    void should_get_an_invalid_tick_exception_when_pick_up_given_an_invalid_ticket() {
        // given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLotA, parkingLotB));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car myCar = new Car();
        smartParkingBoy.park(myCar);

        // when
        Ticket invalidTicket = new Ticket();

        // then
        assertThrows(InvalidTicketException.class, ()->smartParkingBoy.pickUp(invalidTicket));
    }


    @Test
    void should_get_an_invalid_tick_exception_when_pick_up_given_a_used_ticket() {
        // given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLotA, parkingLotB));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket ticket = smartParkingBoy.park(myCar);

        // when
        smartParkingBoy.pickUp(ticket);

        // then
        assertThrows(InvalidTicketException.class, ()->smartParkingBoy.pickUp(ticket));
    }
}