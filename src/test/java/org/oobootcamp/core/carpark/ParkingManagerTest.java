package org.oobootcamp.core.carpark;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingManagerTest {

    /**
     * ### AC：车停到经理直接管理的有空余位置的停车场， 并拿到相应的停车票
     * - Example
     * - Given 经理无小弟，自己管理1个停车场，且有空位
     * - When 停车
     * - Then  经理自己停车成功，并拿到停车票
     * <p>
     * - Example
     * - Given 经理有两个停车场，仅有的小弟A管理一个停车场，且已停满. 剩余1个停车场有空位
     * - When 停车
     * - Then 经理自己把车停到有空位的停车场，并拿到停车票
     */
    @Test
    void should_get_a_ticket_when_park_given_manager_has_no_boy_and_one_parking_lot_which_has_1_left_space() {
        // given
        ParkingLot parkingLot = new ParkingLot(1);

        ParkingManager parkingManager =
                new ParkingManager(List.of(parkingLot));
        Car car = new Car();
        // when
        Ticket ticket = parkingManager.park(car);

        // then
        assertSame(car, parkingLot.pickUp(ticket));
    }

    @Test
    void should_get_a_ticket_when_park_given_1_boy_who_has_1_full_parking_lot_and_1_unassigned_parking_lots_which_has_1_left_space() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(List.of(new GraduateParkingBoy(List.of(parkingLotOne)), parkingLotTwo));
        parkingLotOne.park(new Car());
        Car car = new Car();
        // when
        Ticket ticket = parkingManager.park(car);

        // then
        assertNotNull(ticket);
        assertSame(car, parkingLotTwo.pickUp(ticket));
    }

    /**
     * ### AC：车停到小弟直接管理的有空余的停车场，并拿到相应的停车票
     * <p>
     * - Example
     * - Given 经理有A/B两个小弟, A(普通小弟)管理1个停车场，B(聪明小弟)管理1个停车场，所有的停车场均有空位.
     * - When 停车
     * - Then  经理让A在他管理的停车场停车成功，并拿到停车票
     * <p>
     * - Example
     * - Given 经理有A/B两个小弟, A(普通小弟)管理1个停车场，B(聪明小弟)管理1个停车场，仅B管理的停车场有空位.
     * - When 停车
     * - Then  经理让B在他管理的停车场停车成功，并拿到停车票
     */

    @Test
    void should_get_a_ticket_when_park_given_a_graduate_and_smart_boy_and_everyone_has_a_parking_lot_which_have_1_left_space() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotTwo));
        ParkingManager parkingManager = new ParkingManager(List.of(graduateParkingBoy, smartParkingBoy));
        Car car = new Car();

        // when
        Ticket ticket = parkingManager.park(car);

        // then
        assertNotNull(ticket);
        assertSame(car, graduateParkingBoy.pickUp(ticket));
    }

    @Test
    void should_get_a_ticket_when_park_given_a_graduate_and_smart_boy_and_just_smart_boy_has_a_parking_lot_which_have_1_left_space() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        List<ParkAble> unassignedParkingLots = new ArrayList<>();

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotTwo));
         List<ParkAble> parkingBoys = new ArrayList<>(List.of(graduateParkingBoy, smartParkingBoy));

        ParkingManager parkingManager = new ParkingManager(parkingBoys, unassignedParkingLots);

        graduateParkingBoy.park(new Car());
        Car car = new Car();

        // when
        Ticket ticket = parkingManager.park(car);

        // then
        assertNotNull(ticket);
        assertSame(car, smartParkingBoy.pickUp(ticket));
    }

    /**
     * ### AC：所有停车场均已停满，停车，提示车位已满
     * <p>
     * - Example
     * - Given 经理有三个停车场，小弟A/B各自管理一个停车场，经理自己管理一个停车场。 所有停车场均已停满
     * - When 停车
     * - Then  停车失败，提示车位已满
     */
    @Test
    void should_throw_full_park_exception_when_park_given_all_parking_lots_are_full() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ParkingLot parkingLotThree = new ParkingLot(1);
        List<ParkAble> unassignedParkingLots = new ArrayList<>(List.of(parkingLotThree));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotTwo));
         List<ParkAble> parkingBoys = new ArrayList<>(List.of(graduateParkingBoy, smartParkingBoy));

        ParkingManager parkingManager = new ParkingManager(parkingBoys, unassignedParkingLots);

        graduateParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        parkingLotThree.park(new Car());

        Car car = new Car();

        // when
        // then
        assertThrows(FullParkException.class, () -> parkingManager.park(car));
    }

    /**
     * ### AC： 使用有效停车票取车成功
     * <p>
     * - Example
     * - Given 经理有三个停车场，小弟A/B各自管理一个停车场，经理自己管理一个停车场。 车已经停在A小弟的停车场，并有对应的停车票
     * - When 取车
     * - Then  能够取出对应的车
     * <p>
     * - Example
     * - Given 经理有三个停车场，小弟A/B各自管理一个停车场，经理自己管理一个停车场。 车已经停在B小弟的停车场，并有对应的停车票
     * - When 取车
     * - Then  能够取出对应的车
     * <p>
     * - Example
     * - Given 经理有三个停车场，小弟A/B各自管理一个停车场，经理自己管理一个停车场。 车已经停在经理直接管理的停车场，并有对应的停车票
     * - When 取车
     * - Then  能够取出对应的车
     */
    @Test
    void should_get_a_car_when_pick_up_given_the_car_already_in_the_lots_of_graduate_parking_boy_and_the_corresponding_ticket() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ParkingLot parkingLotThree = new ParkingLot(1);
        List<ParkAble> unassignedParkingLots = new ArrayList<>(List.of(parkingLotThree));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotTwo));
         List<ParkAble> parkingBoys = new ArrayList<>(List.of(graduateParkingBoy, smartParkingBoy));

        ParkingManager parkingManager = new ParkingManager(parkingBoys, unassignedParkingLots);

        Car myCar = new Car();
        Ticket ticket = parkingManager.park(myCar);

        // when
        Car car = parkingManager.pickUp(ticket);

        // then
        assertSame(myCar, car);
    }

    @Test
    void should_get_a_car_when_pick_up_given_the_car_already_in_the_lots_of_smart_parking_boy_and_the_corresponding_ticket() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ParkingLot parkingLotThree = new ParkingLot(1);
        List<ParkAble> unassignedParkingLots = new ArrayList<>(List.of(parkingLotThree));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotTwo));
         List<ParkAble> parkingBoys = new ArrayList<>(List.of(graduateParkingBoy, smartParkingBoy));

        ParkingManager parkingManager = new ParkingManager(parkingBoys, unassignedParkingLots);

        parkingManager.park(new Car());
        Car myCar = new Car();
        Ticket ticket = parkingManager.park(myCar);

        // when
        Car car = parkingManager.pickUp(ticket);

        // then
        assertSame(myCar, car);
    }


    @Test
    void should_get_a_car_whe_pick_up_given_then_car_already_in_the_unassigned_lots_and_the_corresponding_ticket() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ParkingLot parkingLotThree = new ParkingLot(1);
        List<ParkAble> unassignedParkingLots = new ArrayList<>(List.of(parkingLotThree));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotTwo));
         List<ParkAble> parkingBoys = new ArrayList<>(List.of(graduateParkingBoy, smartParkingBoy));

        ParkingManager parkingManager = new ParkingManager(parkingBoys, unassignedParkingLots);

        parkingManager.park(new Car());
        parkingManager.park(new Car());
        Car myCar = new Car();
        Ticket ticket = parkingManager.park(myCar);

        // when
        Car car = parkingManager.pickUp(ticket);

        // then
        assertSame(myCar, car);
    }

    /**
     * ### AC5 使用无效停车票取车提示无效票
     * <p>
     * - Example
     * -  Given 用户的车停在小弟A管理的停车场，并用票取车成功
     * -  When 再次使用该停车票取车
     * -  Then 提示无效票
     * <p>
     * - Example
     * -  Given 用户使用其它停车场的停车票
     * -  When 取车
     * -  Then 提示无效票
     */

    @Test
    void should_get_a_invalid_exception_when_pick_up_given_the_already_used_ticket() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ParkingLot parkingLotThree = new ParkingLot(1);
        List<ParkAble> unassignedParkingLots = new ArrayList<>(List.of(parkingLotThree));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotTwo));
         List<ParkAble> parkingBoys = new ArrayList<>(List.of(graduateParkingBoy, smartParkingBoy));

        ParkingManager parkingManager = new ParkingManager(parkingBoys, unassignedParkingLots);

        Car myCar = new Car();
        Ticket ticket = parkingManager.park(myCar);
        parkingManager.pickUp(ticket);

        // when
        // then
        assertThrows(InvalidTicketException.class, () -> parkingManager.pickUp(ticket));
    }

    @Test
    void should_get_a_invalid_exception_when_pick_up_given_the_an_invalid_ticket() {
        // given
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ParkingLot parkingLotThree = new ParkingLot(1);
        List<ParkAble> unassignedParkingLots = new ArrayList<>(List.of(parkingLotThree));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotTwo));
         List<ParkAble> parkingBoys = new ArrayList<>(List.of(graduateParkingBoy, smartParkingBoy));

        ParkingManager parkingManager = new ParkingManager(parkingBoys, unassignedParkingLots);

        Car myCar = new Car();
       parkingManager.park(myCar);

        // when
        // then
        Ticket invalidTicket = new Ticket();
        assertThrows(InvalidTicketException.class, () -> parkingManager.pickUp(invalidTicket));
    }


}